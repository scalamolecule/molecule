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
  var postResolvers = List.empty[() => Unit]

  def getData(elements: List[Element]): Data = {
    //    elements.foreach(println)

    curRefPath = List(getInitialNs(elements))
    val (mainElements, _) = separateTxElements(elements)
    resolve(mainElements)
    postResolvers.foreach(_())
    addRowSetterToTableInserts()
    (getTableInserts, Nil)
  }

  private def addRowSetterToTableInserts(): Unit = {
    inserts.foreach {
      case (refPath, cols) =>
        val table = refPath.last
        val stmt  =
          s"""INSERT INTO $table (
             |  ${cols.mkString(",\n  ")}
             |) VALUES (${cols.map(_ => "?").mkString(", ")})""".stripMargin
        val ps    = sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)
        tableInserts(refPath) = TableInsert(refPath, stmt, ps)

        val colSetters = colSettersMap(refPath)
        //        println(s"----------------------  ${colSetters.length}  $refPath  $ns")
        //          println(stmt)
        colSettersMap(refPath) = Nil
        val rowSetter = (ps: PS, idsMap: IdsMap, _: RowIndex) => {
          // Set all column values for this row in this insert/batch
          colSetters.foreach(colSetter =>
            colSetter(ps, idsMap, 0)
          )
          // Complete row
          ps.addBatch()
        }
        rowSettersMap(refPath) = List(rowSetter)
    }
  }

  private def getTableInserts: List[TableInsert] = {
    // Add insert resolver to each table insert
    inserts.map { case (refPath, _) =>
      val rowSetters = rowSettersMap(refPath)
      val populatePS = (ps: PS, idsMap: IdsMap, rowIndex: RowIndex) => {
        // Set all column values for this row in this insert/batch
        rowSetters.foreach(rowSetter =>
          rowSetter(ps, idsMap, rowIndex)
        )
      }
      tableInserts(refPath).copy(populatePS = populatePS)
    }
  }


  override protected def addV(ns: String, attr: String, optValue: Option[Any]): Unit = {
    val curPath = curRefPath
    if (inserts.exists(_._1 == curPath)) {
      inserts = inserts.map {
        case (path, cols) if path == curPath =>
          paramIndexes += ((curPath, attr) -> (cols.length + 1))
          (path, cols :+ attr)

        case other => other
      }
    } else {
      paramIndexes += (curPath, attr) -> 1
      inserts = inserts :+ (curPath, List(attr))
    }

    val paramIndex        = paramIndexes(curPath, attr)
    val colSetter: Setter = optValue.fold {
      (ps: PS, _: IdsMap, _: RowIndex) => {
        ps.setNull(paramIndex, 0)
        printValue(0, ns, attr, -1, paramIndex, "None")
      }
    } { value =>
      (ps: PS, _: IdsMap, _: RowIndex) => {
        value.asInstanceOf[(PS, Int) => Unit](ps, paramIndex)
        printValue(0, ns, attr, -1, paramIndex, value)
      }
    }
    addColSetter(curPath, colSetter)
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
    val curPath   = curRefPath

    if (inserts.exists(_._1 == curPath)) {
      // Add ref attribute to current namespace
      inserts = inserts.map {
        case (path, cols) if card == CardOne && path == curPath =>
          paramIndexes += (curPath, refAttr) -> (cols.length + 1)
          (curPath, cols :+ refAttr)

        case other => other
      }

    } else if (card == CardOne) {
      // Make card-one ref from current empty namespace
      paramIndexes += (curPath, refAttr) -> 1
      inserts = inserts :+ (curPath, List(refAttr))

    } else if (card == CardSet) {
      // ref to join table
      // Make card-many ref from current empty namespace
      inserts = inserts :+ (curPath, Nil)
    }

    lazy val joinPath = curPath :+ joinTable
    if (card == CardSet) {
      // join table with single row (treated as normal insert since there's only 1 join per row)
      val (id1, id2) = if (ns == refNs) (s"${ns}_1_id", s"${refNs}_2_id") else (s"${ns}_id", s"${refNs}_id")
      // When insertion order is reversed, this join table will be set after left and right has been inserted
      inserts = (joinPath, List(id1, id2)) +: inserts
    }

    // Start new ref table
    val refPath = curRefPath ++ List(refAttr, refNs)
    curRefPath = refPath
    inserts = inserts :+ (refPath, Nil)

    if (card == CardOne) {
      // Card-one ref setter
      val paramIndex = paramIndexes(curPath, refAttr)
      postResolvers = postResolvers :+ (() => {
        val colSetter: Setter = (ps: PS, idsMap: IdsMap, rowIndex: RowIndex) => {
          val refId = idsMap(refPath)(rowIndex)
          printValue(0, ns, refAttr, -1, paramIndex, refId)
          ps.setLong(paramIndex, refId)
        }
        addColSetter(curPath, colSetter)
      })

    } else {

      postResolvers = postResolvers :+ (() => {
        // Empty row if no attributes in namespace in order to have an id for join table
        if (!paramIndexes.exists { case ((path, _), _) => path == curPath }) {
          // If current namespace has no attributes, then add an empty row with
          // default null values (only to be referenced as the left side of the join table)
          val emptyRowSetter: Setter = (ps: PS, _: IdsMap, _: RowIndex) => {
            ps.addBatch()
          }
          addColSetter(curPath, emptyRowSetter)
        }

        // Join table setter
        val joinSetter: Setter = (ps: PS, idsMap: IdsMap, rowIndex: RowIndex) => {
          val refId1 = idsMap(curPath)(rowIndex)
          val refId2 = idsMap(refPath)(rowIndex)
          //          println("-----------")
          //          println("id1: " + refId1)
          //          println("id2: " + refId2)
          ps.setLong(1, refId1)
          ps.setLong(2, refId2)
          ps.addBatch()
        }
        addColSetter(joinPath, joinSetter)
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