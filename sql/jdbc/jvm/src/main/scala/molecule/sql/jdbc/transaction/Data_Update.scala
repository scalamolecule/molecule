package molecule.sql.jdbc.transaction

import java.sql.{Statement, PreparedStatement => PS}
import java.util.Date
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ResolveUpdate
import molecule.core.transaction.ops.UpdateOps
import molecule.sql.core.query.SqlModel2Query

trait Data_Update
  extends JdbcBase_JVM
    with UpdateOps
    with MoleculeLogging { self: ResolveUpdate =>

  def getData(elements: List[Element]): Data = {
    curRefPath = List(getInitialNs(elements))
    resolve(elements)
    addRowSetterToTables()
    (getTables, Nil)
  }

  private def addRowSetterToTables(): Unit = {
    //    println("-------- ids")
    //    println(ids)
    //    println("-------- updateCols")
    //    updateCols.foreach(println)
    //    println("-------- filterElements")
    //    filterElements.foreach(println)
    //    println("-------- uniqueFilterElements")
    //    uniqueFilterElements.foreach(println)

    inserts.foreach {
      case (refPath, cols) =>
        val table         = refPath.last
        val columnSetters = cols.map(col => s"$col = ?").mkString(",\n  ")


        val clauses_ = if (ids.nonEmpty) {
          s"$table.id IN(${ids.mkString(", ")})"
        } else {
          if (uniqueFilterElements.nonEmpty) {

            // todo: if unique filter attributes exists, query to get ids...

            new SqlModel2Query(uniqueFilterElements).getWhereClauses.map {
              case (attr, expr) => s"$attr $expr"
            }.mkString(" AND\n  ")
          } else {
            new SqlModel2Query(filterElements).getWhereClauses.map {
              case (attr, expr) => s"$attr $expr"
            }.mkString(" AND\n  ")
          }
        }
        //        val updateCols_                      = if (updateCols.isEmpty) "" else {
        //          updateCols.map(c => s"$c IS NOT NULL").mkString(" AND\n  ", " AND\n  ", "")
        //        }

        val updateCols2_ = if (updateCols.isEmpty) "" else {
          updateCols(refPath).map(c => s"$c IS NOT NULL").mkString(" AND\n  ", " AND\n  ", "")
        }
        val stmt         =
          s"""UPDATE $table
             |SET
             |  $columnSetters
             |WHERE $clauses_$updateCols2_""".stripMargin

        val ps = sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)
        tableDatas(refPath) = Table(refPath, stmt, ps)

        val colSetters = colSettersMap(refPath)

        println(s"--- update -------------------  ${colSetters.length}  $refPath")
        println(stmt)

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

  private def updateUpdateCols(a: Attr) = {
    if (!updateCols.contains(curRefPath)) {
      updateCols += curRefPath -> Nil
    }
    updateCols(curRefPath) = updateCols(curRefPath) :+ a.name
  }


  override def updateOne[T](
    a: AttrOne,
    vs: Seq[T],
    transformValue: T => Any,
    handleValue: T => Any
  ): Unit = {
    val (curPath, paramIndex) = updateInserts(a.name)
    val colSetter: Setter     = vs match {
      case Seq(v) =>
        if (!isUpsert) {
          updateUpdateCols(a)
        }
        (ps: PS, _: IdsMap, _: RowIndex) => handleValue(v).asInstanceOf[(PS, Int) => Unit](ps, paramIndex)

      case Nil =>
        (ps: PS, _: IdsMap, _: RowIndex) => ps.setNull(paramIndex, 0)

      case vs => throw ExecutionError(
        s"Can only $update one value for attribute `${a.name}`. Found: " + vs.mkString(", ")
      )
    }
    addColSetter(curPath, colSetter)
  }


  override def updateSetEq[T](
    a: AttrSet,
    sets: Seq[Set[T]],
    transform: T => Any,
    set2array: Set[Any] => Array[AnyRef]
  ): Unit = {
    val (curPath, paramIndex) = updateInserts(a.name)
    val colSetter             = sets match {
      case Seq(set) =>
        if (!isUpsert) {
          updateUpdateCols(a)
        }
        val array = set2array(set.asInstanceOf[Set[Any]])
        (ps: PS, _: IdsMap, _: RowIndex) => {
          val conn = ps.getConnection

          // todo: add to existing values instead of replacing

          val arr = conn.createArrayOf("AnyRef", array)
          ps.setArray(paramIndex, arr)
        }

      case Nil =>
        (ps: PS, _: IdsMap, _: RowIndex) => {
          ps.setNull(paramIndex, 0)
        }

      case vs => throw ExecutionError(
        s"Can only $update one Set of values for Set attribute `${a.name}`. Found: " + vs.mkString(", ")
      )
    }
    addColSetter(curPath, colSetter)
  }


  override def updateSetAdd[T](
    a: AttrSet,
    sets: Seq[Set[T]],
    transform: T => Any,
    set2array: Set[Any] => Array[AnyRef]
  ): Unit = {
    val (curPath, paramIndex) = updateInserts(a.name)
    val colSetter             = sets match {
      case Seq(set) =>
        if (!isUpsert) {
          updateUpdateCols(a)
        }
        val array = set2array(set.asInstanceOf[Set[Any]])
        (ps: PS, _: IdsMap, _: RowIndex) => {
          val conn = ps.getConnection

          // todo: add to existing values instead of replacing

          val arr = conn.createArrayOf("AnyRef", array)
          ps.setArray(paramIndex, arr)
        }

      case Nil =>
        (ps: PS, _: IdsMap, _: RowIndex) => {
          ps.setNull(paramIndex, 0)
        }

      case vs => throw ExecutionError(
        s"Can only $update one Set of values for Set attribute `${a.name}`. Found: " + vs.mkString(", ")
      )
    }
    addColSetter(curPath, colSetter)
  }


  override def updateSetSwab[T](
    a: AttrSet,
    sets: Seq[Set[T]],
    transform: T => Any,
    set2array: Set[Any] => Array[AnyRef],
  ): Unit = {
    val (retracts0, adds0) = sets.splitAt(sets.length / 2)
    val (retracts, adds)   = (retracts0.flatten, adds0.flatten)
    if (retracts.length != retracts.distinct.length)
      throw ExecutionError(s"Can't swap from duplicate retract values.")

    if (adds.length != adds.distinct.length)
      throw ExecutionError(s"Can't swap to duplicate replacement values.")

    if (retracts.nonEmpty) {
      if (retracts.size != adds.size)
        throw ExecutionError(
          s"""Can't swap duplicate keys/values:
             |  RETRACTS: $retracts
             |  ADDS    : $adds
             |""".stripMargin
        )

      val (curPath, paramIndex) = updateInserts(a.name)
      val array                 = set2array(adds.asInstanceOf[Set[Any]])
      val colSetter             = (ps: PS, _: IdsMap, _: RowIndex) => {
        val conn = ps.getConnection
        val arr  = conn.createArrayOf("AnyRef", array)
        ps.setArray(paramIndex, arr)
      }
      addColSetter(curPath, colSetter)
    }
  }

  override def updateSetRemove[T](
    a: AttrSet,
    set: Set[T],
    transform: T => Any
  ): Unit = {
    ???
  }


  override def handleIds(ids1: Seq[Long]): Unit = {
    if (ids.nonEmpty) {
      throw ModelError(s"Can't apply entity ids twice in $update.")
    }
    ids = ids1
  }

  override def handleUniqueFilterAttr(uniqueFilterAttr: AttrOneTac): Unit = {
    if (uniqueFilterElements.nonEmpty) {
      throw ModelError(
        s"Can only apply one unique attribute value for $update. Found:\n"
          + (uniqueFilterElements :+ uniqueFilterAttr).mkString(", ")
      )
    }
    uniqueFilterElements = uniqueFilterElements :+ uniqueFilterAttr
  }

  override def handleFilterAttr(filterAttr: AttrOneTac): Unit = {
    filterElements = filterElements :+ filterAttr
  }

  override def handleRefNs(ref: Ref): Unit = ()

  override def handleBackRef(backRef: BackRef): Unit = ()

  override protected def uniqueIds(
    filterAttr: AttrOneTac,
    ns: String,
    attr: String
  ): Seq[AnyRef] = {
    //    val at = s":$ns/$attr"
    //    filterAttr match {
    //      case a: AttrOneTacString     => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
    //      case a: AttrOneTacInt        => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
    //      case a: AttrOneTacLong       => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
    //      case a: AttrOneTacFloat      => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
    //      case a: AttrOneTacDouble     => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
    //      case a: AttrOneTacBoolean    => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
    //      case a: AttrOneTacBigInt     => a.vs.map(v => list(at, v.bigInteger.asInstanceOf[AnyRef]))
    //      case a: AttrOneTacBigDecimal => a.vs.map(v => list(at, v.bigDecimal.asInstanceOf[AnyRef]))
    //      case a: AttrOneTacDate       => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
    //      case a: AttrOneTacUUID       => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
    //      case a: AttrOneTacURI        => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
    //      case a: AttrOneTacByte       => a.vs.map(v => list(at, v.toInt.asInstanceOf[AnyRef]))
    //      case a: AttrOneTacShort      => a.vs.map(v => list(at, v.toInt.asInstanceOf[AnyRef]))
    //      case a: AttrOneTacChar       => a.vs.map(v => list(at, v.toString.asInstanceOf[AnyRef]))
    //    }
    Nil
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