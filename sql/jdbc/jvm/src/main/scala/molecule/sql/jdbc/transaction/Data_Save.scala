package molecule.sql.jdbc.transaction

import java.net.URI
import java.sql.{Statement, PreparedStatement => PS}
import java.util.{Date, UUID}
import molecule.base.ast.SchemaAST._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.SaveExtraction
import molecule.core.transaction.ops.SaveOps

trait Data_Save
  extends JdbcTxBase_JVM
    with SaveOps
    with MoleculeLogging { self: SaveExtraction =>

  // Resolve after all back refs have been resolved and namespaces grouped
  var resolvers = List.empty[() => Unit]

  def getData(elements: List[Element]): Data = {
    //    elements.foreach(println)

    curRefPath = List(getInitialNs(elements))
    val (mainElements, _) = separateTxElements(elements)
    resolve(mainElements)
    resolvers.foreach(_())
    addRowSetters()
    getInsertResolvers
  }

  private def getInsertResolvers: List[Resolver] = {
    // Add insert resolver to each table insert
    tableCols.map { case (refPath, ns, _) =>
      val rowSetters    = rowSettersMap(refPath, ns)
      val resolveInsert = (ps: PS, insertIds: Map[(Int, List[String], String), Array[Long]], rowIndex: Int) => {
        // Set all column values for this row in this insert/batch
        rowSetters.foreach(rowSetter =>
          rowSetter(ps, insertIds, rowIndex)
        )
      }
      insertResolvers((refPath, ns)).copy(populatePS = resolveInsert)
    }
  }

  private def addRowSetters(): Unit = {
    tableCols.foreach {
      case (refPath, ns, cols) =>
        val stmt =
          s"""INSERT INTO $ns (
             |  ${cols.mkString(",\n  ")}
             |) VALUES (${cols.map(_ => "?").mkString(", ")})""".stripMargin
        val ps   = sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)

        insertResolvers((refPath, ns)) = Resolver(level, refPath, ns, stmt, ps)

        val colSetters = colSettersMap(refPath, ns)
        //        println(s"----------------------  ${colSetters.length}  $refPath  $ns")
        //          println(stmt)
        colSettersMap((refPath, ns)) = Nil
        val resolveRow = (ps: PS, insertIds: Map[(Int, List[String], String), Array[Long]], _: Int) => {
          // Set all column values for this row in this insert/batch
          colSetters.foreach(colSetter =>
            colSetter(ps, insertIds, 0)
          )
          // Complete row
          ps.addBatch()
        }
        rowSettersMap((refPath, ns)) = List(resolveRow)
    }
  }

  override protected def addV(ns: String, attr: String, optValue: Option[Any]): Unit = {
    val refPath = curRefPath
    if (tableCols.exists(_._1 == refPath)) {
      tableCols = tableCols.map {
        case (refPath1, ns1, cols) if refPath1 == refPath && ns1 == ns =>
          paramIndexes = paramIndexes + ((refPath, ns, attr) -> (cols.length + 1))
          (refPath1, ns, cols :+ attr)
        case other                                                     =>
          other
      }
    } else {
      paramIndexes = paramIndexes + ((refPath, ns, attr) -> 1)
      tableCols = tableCols :+ (refPath, ns, List(attr))
    }

    val paramIndex        = paramIndexes((refPath, ns, attr))
    val colSetter: Setter = optValue.fold {
      (ps: PS, _: Map[(Int, List[String], String), Array[Long]], _: Int) => {
        ps.setNull(paramIndex, 0)
      }
    } { value =>
      (ps: PS, _: Map[(Int, List[String], String), Array[Long]], _: Int) => {
        value.asInstanceOf[(PS, Int) => Unit](ps, paramIndex)
      }
    }
    addColSetter(refPath, ns, colSetter)
  }

  override protected def addSet[T](ns: String, attr: String, optSet: Option[Set[T]]): Unit = {
    //    optSet.foreach { set =>
    //      val a = kw(ns, attr)
    //      set.foreach { v =>
    //        appendStmt(add, e, a, v.asInstanceOf[AnyRef])
    //      }
    //    }
  }

  override protected def addRef(ns: String, refAttr: String, refNs: String, card: Card): Unit = {
    val joinTable = s"${ns}_${refAttr}_$refNs"
    val refPath   = curRefPath
    curRefPath = curRefPath ++ List(refAttr, refNs)
    val refPath1 = curRefPath

    if (tableCols.exists(_._1 == refPath)) {
      // Add ref attribute to current namespace
      tableCols = tableCols.map {
        case (refPath1, ns1, cols) if card == CardOne && refPath1 == refPath && ns1 == ns =>
          paramIndexes = paramIndexes + ((refPath, ns, refAttr) -> (cols.length + 1))
          (refPath, ns, cols :+ refAttr)

        case other => other
      }

    } else if (card == CardOne) {
      // Make card-one ref from current empty namespace
      paramIndexes = paramIndexes + ((refPath, ns, refAttr) -> 1)
      tableCols = tableCols :+ (refPath, ns, List(refAttr))

    } else if (card == CardSet) {
      // ref to join table
      // Make card-many ref from current empty namespace
      tableCols = tableCols :+ (refPath, ns, Nil)
    }

    if (card == CardSet) {
      // join table
      val (id1, id2) = if (ns == refNs) (s"${ns}_1_id", s"${refNs}_2_id") else (s"${ns}_id", s"${refNs}_id")
      tableCols = (refPath, joinTable, List(id1, id2)) +: tableCols
    }

    // Start new ref table
    tableCols = tableCols :+ (refPath1, refNs, Nil)

    if (card == CardOne) {
      // Card-one ref setter
      val paramIndex = paramIndexes((refPath, ns, refAttr))
      resolvers = resolvers :+ (() => {
        val colSetter: Setter = (ps: PS, insertIds: Map[(Int, List[String], String), Array[Long]], rowIndex: Int) => {
          //          println("insertIds.length 0: " + insertIds.size)
          //          insertIds.foreach {
          //            case ((a, b, c, d), ids) => println(s"$a  $b  $c  $d  " + ids.toList)
          //          }
          val refId = insertIds((level, refPath1, refNs))(rowIndex)
          //          printValue(rowIndex, ns, refAttr, -1, paramIndex, refId)
          ps.setLong(paramIndex, refId)
        }
        addColSetter(refPath, ns, colSetter)
      })

    } else {

      resolvers = resolvers :+ (() => {
        // Empty row if no attributes in namespace in order to have an id for join table
        if (!paramIndexes.exists { case ((refPath2, ns1, _), _) => refPath2 == refPath && ns1 == ns }) {
          // If current namespace has no attributes, then add an empty row with
          // default null values (only to be referenced as the left side of the join table)
          val emptyRowSetter: Setter = (ps: PS, insertIds: Map[(Int, List[String], String), Array[Long]], _: Int) => {
            //          println("insertIds.length 1: " + insertIds.size)
            ps.addBatch()
          }
          addColSetter(refPath, ns, emptyRowSetter)
        }

        // Join table setter
        val joinSetter: Setter = (ps: PS, insertIds: Map[(Int, List[String], String), Array[Long]], rowIndex: Int) => {
          //          println("insertIds.length 2: " + insertIds.size)
          //          insertIds.foreach {
          //            case ((a, b, c, d), ids) => println(s"$a  $b  $c  $d  " + ids.toList)
          //          }
          //          println(s"------------ $ns,  $refNs")
          //          println("  " + refPath)
          //          println("  " + refPath1)
          val refId1 = insertIds((level, refPath, ns))(rowIndex)
          val refId2 = insertIds((level, refPath1, refNs))(rowIndex)
          //          println("id1: " + refId1)
          //          println("id2: " + refId2)
          ps.setLong(1, refId1)
          ps.setLong(2, refId2)
          ps.addBatch()
        }
        addColSetter(refPath, joinTable, joinSetter)
      })
    }
  }

  override protected def addBackRef(backRefNs: String): Unit = {
    curRefPath = curRefPath.dropRight(2)
  }

  override protected def handleNs(ns: String): Unit = {
    //    backRefs = backRefs + (ns -> e)
  }
  override protected def handleComposite(isInsertTxData: Boolean): Unit = {
    //    e = if (isInsertTxData) datomicTx else e0
  }
  override protected def handleTxData(ns: String): Unit = {
    //    e = datomicTx
    //    e0 = datomicTx
  }

  override protected lazy val valueString     = (v: String) => (ps: PS, n: Int) => ps.setString(n, v)
  override protected lazy val valueInt        = (v: Int) => (ps: PS, n: Int) => ps.setInt(n, v)
  override protected lazy val valueLong       = (v: Long) => (ps: PS, n: Int) => ps.setLong(n, v)
  override protected lazy val valueFloat      = (v: Float) => (ps: PS, n: Int) => ps.setFloat(n, v)
  override protected lazy val valueDouble     = (v: Double) => (ps: PS, n: Int) => ps.setDouble(n, v)
  override protected lazy val valueBoolean    = (v: Boolean) => (ps: PS, n: Int) => ps.setBoolean(n, v)
  override protected lazy val valueBigInt     = (v: BigInt) => (ps: PS, n: Int) => ps.setBigDecimal(n, BigDecimal(v).bigDecimal)
  override protected lazy val valueBigDecimal = (v: BigDecimal) => (ps: PS, n: Int) => ps.setBigDecimal(n, v.bigDecimal)
  override protected lazy val valueDate       = (v: Date) => (ps: PS, n: Int) => ps.setDate(n, new java.sql.Date(v.getTime))
  override protected lazy val valueUUID       = (v: UUID) => (ps: PS, n: Int) => ps.setString(n, v.toString)
  override protected lazy val valueURI        = (v: URI) => (ps: PS, n: Int) => ps.setString(n, v.toString)
  override protected lazy val valueByte       = (v: Byte) => (ps: PS, n: Int) => ps.setByte(n, v)
  override protected lazy val valueShort      = (v: Short) => (ps: PS, n: Int) => ps.setShort(n, v)
  override protected lazy val valueChar       = (v: Char) => (ps: PS, n: Int) => ps.setString(n, v.toString)
}