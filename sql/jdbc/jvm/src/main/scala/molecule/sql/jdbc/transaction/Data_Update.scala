package molecule.sql.jdbc.transaction

import java.sql.{Statement, PreparedStatement => PS}
import java.util.{Date, Set => jSet}
import clojure.lang.Keyword
import datomic.Util.list
import datomic.query.EntityMap
import datomic.{Database, Peer}
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ResolveUpdate
import molecule.core.transaction.ops.UpdateOps
import molecule.sql.jdbc.facade.JdbcConn_jvm

trait Data_Update extends JdbcBase_JVM with UpdateOps with MoleculeLogging { self: ResolveUpdate =>

  def getData(elements: List[Element]): Data = {
    curRefPath = List(getInitialNs(elements))
    val (updateModel, _) = separateTxElements(elements)

    resolve(updateModel)

    addRowSetterToTables()
    (getTables, Nil)
  }

  private def addRowSetterToTables(): Unit = {
    inserts.foreach {
      case (refPath, cols) =>
        val table         = refPath.last
        val columnSetters = cols.map(col => s"$col = ?").mkString(",\n  ")
        val ids_          = ids.mkString(", ")
        val updateCols_   = if (updateCols.isEmpty) "" else
          updateCols.map(c => s"$c IS NOT NULL").mkString(" AND\n  ", " AND\n  ", "")

        val stmt =
          s"""UPDATE $table SET
             |  $columnSetters
             |WHERE $table.id IN($ids_)$updateCols_""".stripMargin

        val ps = sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)
        tableDatas(refPath) = Table(refPath, stmt, ps)

        val colSetters = colSettersMap(refPath)

        //        println(s"--- update -------------------  ${colSetters.length}  $refPath")
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

  def getStmts(
    conn: JdbcConn_jvm,
    elements: List[Element],
    isRpcCall: Boolean = false,
    debug: Boolean = true
  ): Int = { // todo
    //    val db = conn.sqlConn.db()
    //
    //    if (isRpcCall) {
    //      // Check against db on jvm if rpc from client
    //
    //      val getCurSetValues: Attr => Set[Any] = (attr: Attr) => {
    //        val a = s":${attr.ns}/${attr.attr}"
    //        try {
    //          val curValues = Peer.q(s"[:find ?vs :where [_ $a ?vs]]", db)
    //          if (curValues.isEmpty) {
    //            throw ExecutionError(s"While checking to avoid removing the last values of mandatory " +
    //              s"attribute ${attr.ns}.${attr.attr} the current Set of values couldn't be found.")
    //          }
    //          val vs = ListBuffer.empty[Any]
    //          curValues.forEach(row => vs.addOne(row.get(0)))
    //          vs.toSet
    //        } catch {
    //          case e: MoleculeError => throw e
    //          case t: Throwable     => throw ExecutionError(
    //            s"Unexpected error trying to find current values of mandatory attribute ${attr.name}:\n" + t)
    //        }
    //      }
    //
    //      val validationErrors = ModelValidation(
    //        conn.proxy.nsMap,
    //        conn.proxy.attrMap,
    //        "update",
    //        Some(getCurSetValues)
    //      ).validate(elements)
    //      if (validationErrors.nonEmpty) {
    //        throw ValidationErrors(validationErrors)
    //      }
    //    }
    //
    //    val (ids, filterElements, data) = resolve(elements, Nil, Nil, Nil)
    //    val (filterQuery, inputs)        = if (ids.isEmpty && filterElements.nonEmpty) {
    //      val filterElements1 = AttrOneManLong("_Generic", "id", V) +: filterElements
    //      val (query, inputs) = new SqlModel2Query[Any](filterElements1).getEidQueryWithInputs
    //      (Some(query), inputs)
    //    } else {
    //      (None, Nil)
    //    }
    //    filterQuery.fold {
    //      val addStmts = id2stmts(data, db, isUpsert)
    //      ids.foreach(addStmts)
    //    } { query =>
    //      val idRows  = Peer.q(query, db +: inputs: _*)
    //      val addStmts = id2stmts(data, db)
    //      idRows.forEach(idRow => addStmts(idRow.get(0)))
    //    }
    //    if (debug) {
    //      val updateStrs = "UPDATE:" +: elements :+ "" :+ stmts.toArray().mkString("\n")
    //      logger.debug(updateStrs.mkString("\n").trim)
    //    }
    //    stmts
    ???
  }


  override def handleIds(ids1: Seq[Long]): Unit = {
    if (ids.nonEmpty) {
      throw ModelError(s"Can't apply entity ids twice in $update.")
    }
    ids = ids1
  }

  override def handleUniqueFilterAttr(uniqueFilterAttr: AttrOneTac): Unit = {
    if (ids.nonEmpty) {
      throw ModelError(
        s"Can only apply one unique attribute value for $update. Found:\n" + uniqueFilterAttr
      )
    }
    ???
  }

  override def handleFilterAttr(filterAttr: AttrOneTac): Unit = {
    ???
  }

  override def updateOne[T](
    a: AttrOne,
    vs: Seq[T],
    transformValue: T => Any,
    handleValue: T => Any
  ): Unit = {
    val (curPath, paramIndex) = updateInserts(a.name)
    val colSetter: Setter     = {
      vs match {
        case Seq(v) =>

          if (!isUpsert) {
            updateCols += a.name
          }
          (ps: PS, _: IdsMap, _: RowIndex) => {
            handleValue(v).asInstanceOf[(PS, Int) => Unit](ps, paramIndex)
          }
        case Nil    =>
          (ps: PS, _: IdsMap, _: RowIndex) => {
            ps.setNull(paramIndex, 0)
          }
        case vs     => throw ExecutionError(
          s"Can only $update one value for attribute `${a.name}`. Found: " + vs.mkString(", ")
        )
      }
    }
    addColSetter(curPath, colSetter)
  }

  override def updateSetEq[T](
    a: AttrSet,
  ): Unit = {
    ???
  }

  override def updateSetAdd[T](
    a: AttrSet,
    sets: Seq[Set[T]],
    transform: T => Any,
    retractCur: Boolean
  ): Unit = {
    ???
  }

  override def updateSetSwab[T](
    a: AttrSet,
    sets: Seq[Set[T]],
    transform: T => Any
  ): Unit = {
    ???
  }

  override def updateSetRemove[T](
    a: AttrSet,
    set: Set[T],
    transform: T => Any
  ): Unit = {
    ???
  }


  override def handleRefNs(ref: Ref): Unit = {
    // Some sql databases support updating joined tables.
    // When not (like h2), we have to update each related table.
    val updateRelatedSupported = false // todo: retrieve sql db dialect from configuration
    if (updateRelatedSupported) {
      // todo
    } else {
      //      ???
    }
  }

  override def handleBackRef(backRef: BackRef): Unit = {
    ???
  }

  override def handleTxMetaData(): Unit = {
    ???
  }

  private def id2stmts(
    data: Seq[(String, String, String, Seq[AnyRef], Boolean)],
    db: Database,
    addNewValues: Boolean = true
  ): AnyRef => Unit = {
    (id0: AnyRef) => {
      var id  : AnyRef  = id0
      var txId: AnyRef  = null
      var isTx: Boolean = false
      var entity        = db.entity(id)
      data.foreach {
        case ("add", ns, attr, newValues, retractCur) =>
          val a = kw(ns, attr)
          if (retractCur) {
            val id1       = if (txId != null) txId else id
            // todo: optimize with one query for all ids
            val curValues = Peer.q("[:find ?v :in $ ?e ?a :where [?e ?a ?v]]", db, id1, a)
            curValues.forEach { row =>
              val curValue = row.get(0)
              if (!newValues.contains(curValue))
                appendStmt(retract, id1, a, curValue)
            }
          }
          if (addNewValues || entity.get(a) != null || isTx) {
            newValues.foreach(newValue =>
              appendStmt(add, id, a, newValue)
            )
          }

        case ("retract", ns, attr, retractValues, _) =>
          val a = kw(ns, attr)
          if (retractValues.isEmpty) {
            val cur = entity.get(a)
            if (cur != null) {
              cur match {
                case set: jSet[_] =>
                  set.forEach {
                    case kw: Keyword          => appendStmt(retract, id, a, db.entity(kw).get(":db/id"))
                    case entityMap: EntityMap => appendStmt(retract, id, a, entityMap.get(":db/id"))
                    case v                    => appendStmt(retract, id, a, v.asInstanceOf[AnyRef])
                  }
                case v            =>
                  appendStmt(retract, id, a, v)
              }
            }
          } else {
            retractValues.foreach(retractValue =>
              appendStmt(retract, id, a, retractValue)
            )
          }

        case ("ref", ns, refAttr, _, _) =>
          val a = kw(ns, refAttr)
          entity = entity.get(a).asInstanceOf[EntityMap]
          id = entity.get(dbId)

        case ("tx", _, _, _, _) =>
          // Get transaction entity id
          txId = Peer.q("[:find ?tx :in $ ?e :where [?e _ _ ?tx]]", db, id).iterator.next.get(0)
          entity = db.entity(txId)
          isTx = true
          id = datomicTx

        case other => throw ModelError("Unexpected data in update: " + other)
      }
    }
  }


  override protected def uniqueIds(
    filterAttr: AttrOneTac,
    ns: String,
    attr: String
  ): Seq[AnyRef] = {
    val at = s":$ns/$attr"
    filterAttr match {
      case a: AttrOneTacString     => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case a: AttrOneTacInt        => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case a: AttrOneTacLong       => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case a: AttrOneTacFloat      => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case a: AttrOneTacDouble     => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case a: AttrOneTacBoolean    => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case a: AttrOneTacBigInt     => a.vs.map(v => list(at, v.bigInteger.asInstanceOf[AnyRef]))
      case a: AttrOneTacBigDecimal => a.vs.map(v => list(at, v.bigDecimal.asInstanceOf[AnyRef]))
      case a: AttrOneTacDate       => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case a: AttrOneTacUUID       => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case a: AttrOneTacURI        => a.vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case a: AttrOneTacByte       => a.vs.map(v => list(at, v.toInt.asInstanceOf[AnyRef]))
      case a: AttrOneTacShort      => a.vs.map(v => list(at, v.toInt.asInstanceOf[AnyRef]))
      case a: AttrOneTacChar       => a.vs.map(v => list(at, v.toString.asInstanceOf[AnyRef]))
    }
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

  //  override protected lazy val set2arrayString    : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  //  override protected lazy val set2arrayInt       : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  //  override protected lazy val set2arrayLong      : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  //  override protected lazy val set2arrayFloat     : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.map(_.toString.toDouble.asInstanceOf[AnyRef]).toArray
  //  override protected lazy val set2arrayDouble    : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  //  override protected lazy val set2arrayBoolean   : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  //  override protected lazy val set2arrayBigInt    : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[BigInt]].map(v => BigDecimal(v).bigDecimal.asInstanceOf[AnyRef]).toArray
  //  override protected lazy val set2arrayBigDecimal: Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[BigDecimal]].map(v => v.bigDecimal.asInstanceOf[AnyRef]).toArray
  //  override protected lazy val set2arrayDate      : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  //  override protected lazy val set2arrayUUID      : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  //  override protected lazy val set2arrayURI       : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.map(_.toString.asInstanceOf[AnyRef]).toArray
  //  override protected lazy val set2arrayByte      : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  //  override protected lazy val set2arrayShort     : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
  //  override protected lazy val set2arrayChar      : Set[Any] => Array[AnyRef] = (set: Set[Any]) => set.asInstanceOf[Set[AnyRef]].toArray
}