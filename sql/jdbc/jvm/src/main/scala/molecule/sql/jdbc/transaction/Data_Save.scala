package molecule.sql.jdbc.transaction

import java.sql.{Statement, PreparedStatement => PS}
import java.util.Date
import molecule.base.ast.SchemaAST._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ResolveSave
import molecule.core.transaction.ops.SaveOps

trait Data_Save
  extends JdbcBase_JVM
    with SaveOps
    with MoleculeLogging { self: ResolveSave =>

  // Resolve after all back refs have been resolved and namespaces grouped
  var postResolvers = List.empty[Unit => Unit]

  def getData(elements: List[Element]): Data = {
    initialNs = getInitialNs(elements)
    curRefPath = List(initialNs)
    val (saveModel, _) = separateTxElements(elements)

    resolve(saveModel)

    postResolvers.foreach(_())
    addRowSetterToTables()
    (getTables, Nil)
  }

  private def addRowSetterToTables(): Unit = {
    inserts.foreach {
      case (refPath, cols) =>
        val table             = refPath.last
        val columns           = cols.mkString(",\n  ")
        val inputPlaceholders = cols.map(_ => "?").mkString(", ")

        val stmt =
          s"""INSERT INTO $table (
             |  $columns
             |) VALUES ($inputPlaceholders)""".stripMargin

        val ps = sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)
        tableDatas(refPath) = Table(refPath, stmt, ps)

        val colSetters = colSettersMap(refPath)
        //        println(s"--- save -------------------  ${colSetters.length}  $refPath")
        //        println(stmt)
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

  private def getTables: List[Table] = {
    // Add insert resolver to each table insert
    inserts.map { case (refPath, _) =>
      val rowSetters = rowSettersMap(refPath)
      val populatePS = (ps: PS, idsMap: IdsMap, rowIndex: RowIndex) => {
        // Set all column values for this row in this insert/batch
        rowSetters.foreach(rowSetter =>
          rowSetter(ps, idsMap, rowIndex)
        )
      }
      tableDatas(refPath).copy(populatePS = populatePS)
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
    optValue: Option[T],
    handleValue: T => Any
  ): Unit = {
    val (curPath, paramIndex) = updateInserts(attr)
    val colSetter: Setter     = optValue.fold {
      (ps: PS, _: IdsMap, _: RowIndex) => {
        ps.setNull(paramIndex, 0)
        //        printValue(0, ns, attr, -1, paramIndex, "None")
      }
    } { value =>
      (ps: PS, _: IdsMap, _: RowIndex) => {
        handleValue(value).asInstanceOf[(PS, Int) => Unit](ps, paramIndex)
        //        printValue(0, ns, attr, -1, paramIndex, value)
      }
    }
    addColSetter(curPath, colSetter)
  }

  override protected def addSet[T](
    ns: String,
    attr: String,
    optSet: Option[Set[T]],
    handleValue: T => Any,
    set2array: Set[Any] => Array[AnyRef]
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
          val arr  = conn.createArrayOf("AnyRef", set2array(set.asInstanceOf[Set[Any]]))
          ps.setArray(paramIndex, arr)
          //        printValue(level, ns, attr, tplIndex, paramIndex, array)
        }
      }
    }
    addColSetter(curPath, colSetter)
  }

  override protected def addRef(ns: String, refAttr: String, refNs: String, card: Card): Unit = {
    postResolvers = postResolvers :+ getRefResolver[Unit](ns, refAttr, refNs, card)
  }

  override protected def addBackRef(backRefNs: String): Unit = {
    curRefPath = curRefPath.dropRight(2)
  }

  override protected def handleRefNs(refNs: String): Unit = {
    //    backRefs = backRefs + (ns -> e)
  }

  override protected def handleComposite(isInsertTxMetaData: Boolean, compositeNs: String): Unit = {
    //    e = if (isInsertTxMetaData) datomicTx else e0
    compositeGroup += 1
    if (compositeGroup > 1) {
      postResolvers = postResolvers :+ getCompositeJoinResolver[Unit](compositeNs, List(initialNs))
    }
  }

  override protected def handleTxMetaData(ns: String): Unit = {
    //    e = datomicTx
    //    e0 = datomicTx
  }

  override protected lazy val handleString     = (v: Any) => (ps: PS, n: Int) => ps.setString(n, v.asInstanceOf[String])
  override protected lazy val handleInt        = (v: Any) => (ps: PS, n: Int) => ps.setInt(n, v.asInstanceOf[Int])
  override protected lazy val handleLong       = (v: Any) => (ps: PS, n: Int) => ps.setLong(n, v.asInstanceOf[Long])
  override protected lazy val handleFloat      = (v: Any) => (ps: PS, n: Int) => ps.setFloat(n, v.asInstanceOf[Float])
  override protected lazy val handleDouble     = (v: Any) => (ps: PS, n: Int) => ps.setDouble(n, v.asInstanceOf[Double])
  override protected lazy val handleBoolean    = (v: Any) => (ps: PS, n: Int) => ps.setBoolean(n, v.asInstanceOf[Boolean])
  override protected lazy val handleBigInt     = (v: Any) => (ps: PS, n: Int) => ps.setBigDecimal(n, BigDecimal(v.asInstanceOf[BigInt]).bigDecimal)
  override protected lazy val handleBigDecimal = (v: Any) => (ps: PS, n: Int) => ps.setBigDecimal(n, v.asInstanceOf[BigDecimal].bigDecimal)
  override protected lazy val handleDate       = (v: Any) => (ps: PS, n: Int) => ps.setDate(n, new java.sql.Date(v.asInstanceOf[Date].getTime))
  override protected lazy val handleUUID       = (v: Any) => (ps: PS, n: Int) => ps.setString(n, v.toString)
  override protected lazy val handleURI        = (v: Any) => (ps: PS, n: Int) => ps.setString(n, v.toString)
  override protected lazy val handleByte       = (v: Any) => (ps: PS, n: Int) => ps.setByte(n, v.asInstanceOf[Byte])
  override protected lazy val handleShort      = (v: Any) => (ps: PS, n: Int) => ps.setShort(n, v.asInstanceOf[Short])
  override protected lazy val handleChar       = (v: Any) => (ps: PS, n: Int) => ps.setString(n, v.toString)

  override protected lazy val set2arrayString    : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayInt       : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayLong      : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayFloat     : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayDouble    : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayBoolean   : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayBigInt    : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[BigInt]].map(v => BigDecimal(v).bigDecimal.asInstanceOf[AnyRef]).toArray
  override protected lazy val set2arrayBigDecimal: Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[BigDecimal]].map(v => v.bigDecimal.asInstanceOf[AnyRef]).toArray
  override protected lazy val set2arrayDate      : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayUUID      : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayURI       : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.map(_.toString.asInstanceOf[AnyRef]).toArray
  override protected lazy val set2arrayByte      : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayShort     : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  override protected lazy val set2arrayChar      : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
}