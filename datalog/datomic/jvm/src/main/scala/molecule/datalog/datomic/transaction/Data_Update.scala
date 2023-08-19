package molecule.datalog.datomic.transaction

import java.util.{Set => jSet}
import clojure.lang.Keyword
import datomic.Util.list
import datomic.query.EntityMap
import datomic.{Database, Peer}
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ResolveUpdate
import molecule.core.transaction.ops.UpdateOps
import molecule.core.validation.ModelValidation
import molecule.datalog.core.query.DatomicModel2Query
import molecule.datalog.datomic.facade.DatomicConn_JVM
import scala.collection.mutable.ListBuffer

trait Data_Update extends DatomicBase_JVM with UpdateOps with MoleculeLogging { self: ResolveUpdate =>

  def getStmts(
    conn: DatomicConn_JVM,
    elements: List[Element],
    isRpcCall: Boolean = false,
    debug: Boolean = true
  ): Data = {
    //    println("-------")
    //    elements.foreach(println)

    val db = conn.peerConn.db()

    if (isRpcCall) {
      // Check against db on jvm if rpc from client

      val getCurSetValues: Attr => Set[Any] = (attr: Attr) => {
        val a = s":${attr.ns}/${attr.attr}"
        try {
          val curValues = Peer.q(s"[:find ?vs :where [_ $a ?vs]]", db)
          if (curValues.isEmpty) {
            throw ExecutionError(s"While checking to avoid removing the last values of mandatory " +
              s"attribute ${attr.ns}.${attr.attr} the current Set of values couldn't be found.")
          }
          val vs = ListBuffer.empty[Any]
          curValues.forEach(row => vs += row.get(0))
          vs.toSet
        } catch {
          case e: MoleculeError => throw e
          case t: Throwable     => throw ExecutionError(
            s"Unexpected error trying to find current values of mandatory attribute ${attr.name}:\n" + t)
        }
      }

      val validationErrors = ModelValidation(
        conn.proxy.nsMap,
        conn.proxy.attrMap,
        "update",
        Some(getCurSetValues)
      ).validate(elements)
      if (validationErrors.nonEmpty) {
        throw ValidationErrors(validationErrors)
      }
    }

    // Resolve the update model
    resolve(elements)

    val (filterQuery, inputs) = if (ids.isEmpty && filterElements.nonEmpty) {
      val filterNs        = filterElements.head.asInstanceOf[Attr].ns
      val filterElements1 = AttrOneManLong(filterNs, "id", V) +: filterElements
      val (query, inputs) = new DatomicModel2Query[Any](filterElements1).getIdQueryWithInputs
      (Some(query), inputs)
    } else {
      (None, Nil)
    }
    filterQuery.fold {
      val addStmts = id2stmts(data, db, isUpsert)
      ids.foreach(addStmts)
    } { query =>
      val idRows   = Peer.q(query, db +: inputs: _*)
      val addStmts = id2stmts(data, db)
      idRows.forEach(idRow => addStmts(idRow.get(0)))
    }
    if (debug) {
      val updateStrs = "UPDATE:" +: elements :+ "" :+ stmts.toArray().mkString("\n")
      logger.debug(updateStrs.mkString("\n").trim)
    }
    stmts
  }


  override def handleIds(ids1: Seq[Long]): Unit = {
    if (ids.nonEmpty) {
      throw ModelError(s"Can't apply entity ids twice in $update.")
    }
    ids = ids ++ ids1.asInstanceOf[Seq[AnyRef]]
  }

  override def handleUniqueFilterAttr(uniqueFilterAttr: AttrOneTac): Unit = {
    if (ids.nonEmpty) {
      throw ModelError(
        s"Can only apply one unique attribute value for $update. Found:\n" + uniqueFilterAttr
      )
    }
    ids = ids ++ uniqueIds(uniqueFilterAttr, uniqueFilterAttr.ns, uniqueFilterAttr.attr)
  }

  override def handleFilterAttr(filterAttr: AttrOneTac): Unit = {
    filterElements = filterElements :+ filterAttr
  }

  override def updateOne[T](
    a: AttrOne,
    vs: Seq[T],
    transformValue: T => Any,
    handleValue: T => Any
  ): Unit = {
    if (!isUpsert) {
      val dummyFilterAttr = AttrOneTacInt(a.ns, a.attr, V, Nil, None, None, Nil, Nil, None, None)
      filterElements = filterElements :+ dummyFilterAttr
    }
    vs match {
      case Seq(v) => data = data :+ (("add", a.ns, a.attr, Seq(transformValue(v).asInstanceOf[AnyRef]), false))
      case Nil    => data = data :+ (("retract", a.ns, a.attr, Nil, false))
      case vs     => throw ExecutionError(
        s"Can only $update one value for attribute `${a.name}`. Found: " + vs.mkString(", ")
      )
    }
  }

  override def updateSetEq[T](a: AttrSet): Unit = {
    if (!isUpsert) {
      val dummyFilterAttr = AttrOneTacInt(a.ns, a.attr, V, Nil, None, None, Nil, Nil, None, None)
      filterElements = filterElements :+ dummyFilterAttr
    }
  }

  override def updateSetAdd[T](
    a: AttrSet,
    sets: Seq[Set[T]],
    transform: T => Any,
    retractCur: Boolean
  ): Unit = {
    sets match {
      case Seq(set) =>
        val add = ("add", a.ns, a.attr, set.map(v => transform(v).asInstanceOf[AnyRef]).toSeq, retractCur)
        data = data :+ add

      case Nil =>
        data = data :+ (("retract", a.ns, a.attr, Nil, retractCur))

      case vs => throw ExecutionError(
        s"Can only $update one Set of values for Set attribute `${a.name}`. Found: " + vs.mkString(", ")
      )
    }
  }

  override def updateSetSwab[T](
    a: AttrSet,
    sets: Seq[Set[T]],
    transform: T => Any
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

      data = data ++ Seq(
        ("retract", a.ns, a.attr, retracts.map(v => transform(v).asInstanceOf[AnyRef]), false),
        ("add", a.ns, a.attr, adds.map(v => transform(v).asInstanceOf[AnyRef]), false),
      )
    }
  }

  override def updateSetRemove[T](
    a: AttrSet,
    set: Set[T],
    transform: T => Any
  ): Unit = {
    if (set.nonEmpty) {
      data = data :+ (("retract", a.ns, a.attr, set.map(v => transform(v).asInstanceOf[AnyRef]).toSeq, false))
      Seq(("retract", a.ns, a.attr, set.map(v => transform(v).asInstanceOf[AnyRef]).toSeq, false))
    }
  }


  override def handleRefNs(ref: Ref): Unit = {
    filterElements = filterElements :+ ref
    data = data :+ (("ref", ref.ns, ref.refAttr, Nil, false))
  }

  override def handleBackRef(backRef: BackRef): Unit = {
    filterElements = filterElements :+ backRef
  }

  override def handleTxMetaData(): Unit = {
    if (data.isEmpty) {
      throw ModelError(s"Please apply the tx id to the namespace of tx meta data to be updated.")
    }
    data = data :+ (("tx", null, null, Nil, false))
  }


  private def id2stmts(
    data: List[(String, String, String, Seq[AnyRef], Boolean)],
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
                case set: jSet[_]         =>
                  set.forEach {
                    case kw: Keyword          => appendStmt(retract, id, a, db.entity(kw).get(":db/id"))
                    case entityMap: EntityMap => appendStmt(retract, id, a, entityMap.get(":db/id"))
                    case v                    => appendStmt(retract, id, a, v.asInstanceOf[AnyRef])
                  }
                case kw: Keyword          => appendStmt(retract, id, a, db.entity(kw).get(":db/id"))
                case entityMap: EntityMap => appendStmt(retract, id, a, entityMap.get(":db/id"))
                case v                    => appendStmt(retract, id, a, v)
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

  override protected lazy val transformString     = identity
  override protected lazy val transformInt        = identity
  override protected lazy val transformLong       = identity
  override protected lazy val transformFloat      = identity
  override protected lazy val transformDouble     = identity
  override protected lazy val transformBoolean    = identity
  override protected lazy val transformBigInt     = (v: BigInt) => v.bigInteger
  override protected lazy val transformBigDecimal = (v: BigDecimal) => v.bigDecimal
  override protected lazy val transformDate       = identity
  override protected lazy val transformUUID       = identity
  override protected lazy val transformURI        = identity
  override protected lazy val transformByte       = (v: Byte) => v.toInt
  override protected lazy val transformShort      = (v: Short) => v.toInt
  override protected lazy val transformChar       = (v: Char) => v.toString
}