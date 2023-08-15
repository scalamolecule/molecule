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
  extends JdbcBase_JVM
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
        val table             = refPath.last
        val inputPlaceholders = cols.map(_ => "?").mkString(", ")
        val stmt              =
          s"""INSERT INTO $table (
             |  ${cols.mkString(",\n  ")}
             |) VALUES ($inputPlaceholders)""".stripMargin
        val ps                = sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)
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


  private def updateInserts(attr: String): (List[String], Int) = {
    if (inserts.exists(_._1 == curRefPath)) {
      inserts = inserts.map {
        case (path, cols) if path == curRefPath =>
          paramIndexes += (curRefPath, attr) -> (cols.length + 1)
          (path, cols :+ attr)

        case other => other
      }
    } else {
      paramIndexes += (curRefPath, attr) -> 1
      inserts = inserts :+ (curRefPath, List(attr))
    }
    (curRefPath, paramIndexes(curRefPath, attr))
  }

  override protected def addOne[T](
    ns: String,
    attr: String,
    optValue: Option[T]
  ): Unit = {
    val (curPath, paramIndex) = updateInserts(attr)
    val colSetter: Setter     = optValue.fold {
      (ps: PS, _: IdsMap, _: RowIndex) => {
        ps.setNull(paramIndex, 0)
        //        printValue(0, ns, attr, -1, paramIndex, "None")
      }
    } { value =>
      (ps: PS, _: IdsMap, _: RowIndex) => {
        value.asInstanceOf[(PS, Int) => Unit](ps, paramIndex)
        //        printValue(0, ns, attr, -1, paramIndex, value)
      }
    }
    addColSetter(curPath, colSetter)
  }

  override protected def addSet[T](
    ns: String,
    attr: String,
    optSet: Option[Set[T]],
    set2array: Set[T] => Array[AnyRef]
  ): Unit = {
    val (curPath, paramIndex) = updateInserts(attr)
    val colSetter: Setter     = optSet.fold {
      (ps: PS, _: IdsMap, _: RowIndex) => {
        ps.setNull(paramIndex, 0)
        //        printValue(0, ns, attr, -1, paramIndex, "None")
      }
    } { set =>
      (ps: PS, _: IdsMap, _: RowIndex) => {
        if (set.isEmpty) {
          ps.setNull(paramIndex, 0)
        } else {
          val conn = ps.getConnection
          val arr  = conn.createArrayOf("AnyRef", set2array(set))
          ps.setArray(paramIndex, arr)
          //        printValue(level, ns, attr, tplIndex, paramIndex, array)
        }
      }
    }
    addColSetter(curPath, colSetter)
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
          //          printValue(0, ns, refAttr, -1, paramIndex, refId)
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
  override protected def handleComposite(isInsertTxMetaData: Boolean): Unit = {
    //    e = if (isInsertTxMetaData) datomicTx else e0
  }
  override protected def handleTxMetaData(ns: String): Unit = {
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

  override protected lazy val set2arrayString    : Set[String] => Array[AnyRef]     = (set: Set[String]) => set.toArray
  override protected lazy val set2arrayInt       : Set[Int] => Array[AnyRef]        = (set: Set[Int]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayLong      : Set[Long] => Array[AnyRef]       = (set: Set[Long]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayFloat     : Set[Float] => Array[AnyRef]      = (set: Set[Float]) => set.map(_.toString.toDouble.asInstanceOf[AnyRef]).toArray
  override protected lazy val set2arrayDouble    : Set[Double] => Array[AnyRef]     = (set: Set[Double]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayBoolean   : Set[Boolean] => Array[AnyRef]    = (set: Set[Boolean]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayBigInt    : Set[BigInt] => Array[AnyRef]     = (set: Set[BigInt]) => set.map(v => BigDecimal(v).bigDecimal.asInstanceOf[AnyRef]).toArray
  override protected lazy val set2arrayBigDecimal: Set[BigDecimal] => Array[AnyRef] = (set: Set[BigDecimal]) => set.map(v => v.bigDecimal.asInstanceOf[AnyRef]).toArray
  override protected lazy val set2arrayDate      : Set[Date] => Array[AnyRef]       = (set: Set[Date]) => set.toArray
  override protected lazy val set2arrayUUID      : Set[UUID] => Array[AnyRef]       = (set: Set[UUID]) => set.toArray
  override protected lazy val set2arrayURI       : Set[URI] => Array[AnyRef]        = (set: Set[URI]) => set.map(_.toString.asInstanceOf[AnyRef]).toArray
  override protected lazy val set2arrayByte      : Set[Byte] => Array[AnyRef]       = (set: Set[Byte]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayShort     : Set[Short] => Array[AnyRef]      = (set: Set[Short]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayChar      : Set[Char] => Array[AnyRef]       = (set: Set[Char]) => set.asInstanceOf[Set[AnyRef]].toArray
}