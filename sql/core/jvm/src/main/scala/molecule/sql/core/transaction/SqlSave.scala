package molecule.sql.core.transaction

import java.sql.{Statement, PreparedStatement => PS}
import java.util.Date
import molecule.base.ast._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.marshalling.ConnProxy
import molecule.core.transaction.ResolveSave
import molecule.core.transaction.ops.SaveOps

trait SqlSave
  extends SqlBase_JVM
    with SaveOps
    with MoleculeLogging { self: ResolveSave =>

  // Resolve after all back refs have been resolved and namespaces grouped
  protected var postResolvers = List.empty[Unit => Unit]

  doPrint = false

  def getData(elements: List[Element]): Data = {
    initialNs = getInitialNs(elements)
    curRefPath = List(initialNs)
    resolve(elements)
    postResolvers.foreach(_(()))
    addRowSetterToTables()
    (getTables, Nil)
  }

  protected def addRowSetterToTables(): Unit = {
    inserts.foreach {
      case (refPath, cols) =>
        val table             = refPath.last
        val columns           = cols.map(_._1).mkString(",\n  ")
        val inputPlaceholders = cols.map(_ => "?").mkString(", ")
        val stmt              = {
          s"""INSERT INTO $table (
             |  $columns
             |) VALUES ($inputPlaceholders)""".stripMargin
        }
        val colSetters        = colSettersMap(refPath)
        debug(s"--- save -------------------  ${colSetters.length}  $refPath")
        debug(stmt)

        val ps = sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)
        tableDatas(refPath) = Table(refPath, stmt, ps)
        colSettersMap(refPath) = Nil
        val rowSetter = (ps: PS, idsMap: IdsMap, _: RowIndex) => {
          // Set all column values for this row in this insert/batch
          colSetters.foreach { colSetter =>
            colSetter(ps, idsMap, 0)
          }
          // Complete row
          ps.addBatch()
        }
        rowSettersMap(refPath) = List(rowSetter)
    }
  }

  protected def getTables: List[Table] = {
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

  protected def getParamIndex(attr: String, add: Boolean = true, castExt: String = ""): (List[String], Int) = {
    if (inserts.exists(_._1 == curRefPath)) {
      inserts = inserts.map {
        case (path, cols) if path == curRefPath =>
          paramIndexes += (curRefPath, attr) -> (cols.length + 1)
          (path, if (add) cols :+ (attr, castExt) else cols)

        case other => other
      }
    } else {
      paramIndexes += (curRefPath, attr) -> 1
      inserts = inserts :+ (curRefPath, List((attr, castExt)))
    }
    (curRefPath, paramIndexes(curRefPath, attr))
  }

  override protected def addOne[T](
    ns: String,
    attr: String,
    optValue: Option[T],
    handleValue: T => Any,
    exts: List[String] = Nil
  ): Unit = {
    val (curPath, paramIndex) = getParamIndex(attr, castExt = exts.head)
    val colSetter: Setter     = optValue.fold {
      (ps: PS, _: IdsMap, _: RowIndex) => {
        ps.setNull(paramIndex, 0)
      }
    } { value =>
      (ps: PS, _: IdsMap, _: RowIndex) => {
        handleValue(value).asInstanceOf[(PS, Int) => Unit](ps, paramIndex)
      }
    }
    addColSetter(curPath, colSetter)
  }

  override protected def addSet[T](
    ns: String,
    attr: String,
    optSet: Option[Set[Any]],
    transformValue: T => Any,
    set2array: Set[Any] => Array[AnyRef],
    refNs: Option[String],
    exts: List[String] = Nil,
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    refNs.fold {
      val (curPath, paramIndex) = getParamIndex(attr)
      val colSetter: Setter     = optSet.fold {
        (ps: PS, _: IdsMap, _: RowIndex) => {
          ps.setNull(paramIndex, 0)
        }
      } { set =>
        (ps: PS, _: IdsMap, _: RowIndex) => {
          if (set.isEmpty) {
            ps.setNull(paramIndex, 0)
          } else {
            val conn  = ps.getConnection
            val array = conn.createArrayOf(exts(1), set2array(set))
            ps.setArray(paramIndex, array)
          }
        }
      }
      addColSetter(curPath, colSetter)
    } { refNs =>
      val curPath = if (paramIndexes.nonEmpty) curRefPath else List(ns)
      if (optSet.isEmpty || optSet.get.isEmpty) {
        addColSetter(curPath, (_: PS, _: IdsMap, _: RowIndex) => ())
      } else {
        val set       = optSet.get
        val refAttr   = attr
        val joinTable = s"${ns}_${refAttr}_$refNs"
        val joinPath  = curPath :+ joinTable

        // join table with single row (treated as normal insert since there's only 1 join per row)
        val (id1, id2) = if (ns == refNs) (s"${ns}_1_id", s"${refNs}_2_id") else (s"${ns}_id", s"${refNs}_id")
        // When insertion order is reversed, this join table will be set after left and right has been inserted
        inserts = (joinPath, List((id1, ""), (id2, ""))) +: inserts

        if (paramIndexes.isEmpty) {
          // If current namespace has no attributes, then add an empty row with
          // default null values (only to be referenced as the left side of the join table)
          val emptyRowSetter: Setter = (ps: PS, _: IdsMap, _: RowIndex) => ps.addBatch()
          addColSetter(curPath, emptyRowSetter)
          inserts = inserts :+ (curRefPath, List())
        }

        // Join table setter
        val refIds2            = set.iterator.asInstanceOf[Iterator[Long]]
        val joinSetter: Setter = (ps: PS, idsMap: IdsMap, rowIndex: RowIndex) => {
          val refId1 = idsMap(curPath)(rowIndex)
          while (refIds2.hasNext) {
            val refId2 = refIds2.next()
            ps.setLong(1, refId1)
            ps.setLong(2, refId2)
            if (refIds2.hasNext)
              ps.addBatch()
          }
        }
        addColSetter(joinPath, joinSetter)
      }
    }
  }

  override protected def addRef(ns: String, refAttr: String, refNs: String, card: Card): Unit = {
    postResolvers = postResolvers :+ getRefResolver[Unit](ns, refAttr, refNs, card)
  }

  override protected def addBackRef(backRefNs: String): Unit = {
    curRefPath = curRefPath.dropRight(2)
  }

  override protected def handleRefNs(refNs: String): Unit = ()

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

  override protected lazy val extsString     = List("", "AnyRef")
  override protected lazy val extsInt        = List("", "AnyRef")
  override protected lazy val extsLong       = List("", "AnyRef")
  override protected lazy val extsFloat      = List("", "AnyRef")
  override protected lazy val extsDouble     = List("", "AnyRef")
  override protected lazy val extsBoolean    = List("", "AnyRef")
  override protected lazy val extsBigInt     = List("", "AnyRef")
  override protected lazy val extsBigDecimal = List("", "AnyRef")
  override protected lazy val extsDate       = List("", "AnyRef")
  override protected lazy val extsUUID       = List("", "AnyRef")
  override protected lazy val extsURI        = List("", "AnyRef")
  override protected lazy val extsByte       = List("", "AnyRef")
  override protected lazy val extsShort      = List("", "AnyRef")
  override protected lazy val extsChar       = List("", "AnyRef")
}