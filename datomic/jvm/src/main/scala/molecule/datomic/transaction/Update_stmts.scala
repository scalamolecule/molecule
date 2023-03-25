package molecule.datomic.transaction

import java.util.{Set => jSet}
import clojure.lang.Keyword
import datomic.Util.list
import datomic.query.EntityMap
import datomic.{Database, Peer}
import molecule.base.error.ExecutionError
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.{UpdateExtraction, UpdateOps}
import molecule.core.validation.ConflictingAttrs
import molecule.datomic.facade.DatomicConn_JVM
import molecule.datomic.query.DatomicModel2Query

trait Update_stmts extends DatomicTxBase_JVM with UpdateOps with MoleculeLogging { self: UpdateExtraction =>

  def getStmts(
    conn: DatomicConn_JVM,
    elements: List[Element]
  ): Data = {
    ConflictingAttrs.check(elements, distinguishMode = true)

    val (eids, filterElements, data) = resolve(elements, Nil, Nil, Nil)

    val (filterQuery, inputs) = if (eids.isEmpty && filterElements.nonEmpty) {
      val filterElements1 = AttrOneManLong("_Generic", "e", V) +: filterElements
      val (query, inputs) = new DatomicModel2Query[Any](filterElements1).getEidQueryWithInputs
      (Some(query), inputs)
    } else {
      (None, Nil)
    }

    val db = conn.peerConn.db()
    filterQuery.fold {
      val addStmts = eid2stmts(data, db, isUpsert)
      eids.foreach(addStmts)
    } { query =>
      val eidRows  = Peer.q(query, db +: inputs: _*)
      val addStmts = eid2stmts(data, db)
      eidRows.forEach(eidRow => addStmts(eidRow.get(0)))
    }

    val updateStrs = "UPDATE:" +: elements :+ "" :+ stmts.toArray().mkString("\n")
    logger.debug(updateStrs.mkString("\n").trim)

    stmts
  }

  private def eid2stmts(
    data: Seq[(String, String, String, Seq[AnyRef], Boolean)],
    db: Database,
    addNewValues: Boolean = true
  ): AnyRef => Unit = {
    (eid0: AnyRef) => {
      var eid : AnyRef = eid0
      var txId: AnyRef = null
      var entity       = db.entity(eid)
      data.foreach {
        case ("add", ns, attr, newValues, retractCur) =>
          val a = kw(ns, attr)
          if (retractCur) {
            val eid1      = if (txId != null) txId else eid
            // todo: optimize with one query for all eids
            val curValues = Peer.q("[:find ?v :in $ ?e ?a :where [?e ?a ?v]]", db, eid1, a)
            curValues.forEach { row =>
              val curValue = row.get(0)
              if (!newValues.contains(curValue))
                appendStmt(retract, eid1, a, curValue)
            }
          }
          if (addNewValues || entity.get(a) != null) {
            newValues.foreach(newValue =>
              appendStmt(add, eid, a, newValue)
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
                    case kw: Keyword          => appendStmt(retract, eid, a, db.entity(kw).get(":db/id"))
                    case entityMap: EntityMap => appendStmt(retract, eid, a, entityMap.get(":db/id"))
                    case v                    => appendStmt(retract, eid, a, v.asInstanceOf[AnyRef])
                  }
                case v            =>
                  appendStmt(retract, eid, a, v)
              }
            }
          } else {
            retractValues.foreach(retractValue =>
              appendStmt(retract, eid, a, retractValue)
            )
          }

        case ("ref", ns, refAttr, _, _) =>
          val a = kw(ns, refAttr)
          entity = entity.get(a).asInstanceOf[EntityMap]
          eid = entity.get(dbId)

        case ("tx", _, _, _, _) =>
          // Get transaction entity id
          txId = Peer.q("[:find ?tx :in $ ?e :where [?e _ _ ?tx]]", db, eid).iterator.next.get(0)
          entity = db.entity(txId)
          eid = datomicTx

        case other => throw ExecutionError("Unexpected data in update: " + other)
      }
    }
  }


  override protected def uniqueEids(
    filterAttr: AttrOneTac,
    ns: String,
    attr: String
  ): Seq[AnyRef] = {
    val at = s":$ns/$attr"
    filterAttr match {
      case AttrOneTacString(_, _, _, vs, _, _, _, _)     => vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case AttrOneTacInt(_, _, _, vs, _, _, _, _)        => vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case AttrOneTacLong(_, _, _, vs, _, _, _, _)    => vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case AttrOneTacFloat(_, _, _, vs, _, _, _, _)      => vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case AttrOneTacDouble(_, _, _, vs, _, _, _, _)     => vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case AttrOneTacBoolean(_, _, _, vs, _, _, _, _)    => vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case AttrOneTacBigInt(_, _, _, vs, _, _, _, _)     => vs.map(v => list(at, v.bigInteger.asInstanceOf[AnyRef]))
      case AttrOneTacBigDecimal(_, _, _, vs, _, _, _, _) => vs.map(v => list(at, v.bigDecimal.asInstanceOf[AnyRef]))
      case AttrOneTacDate(_, _, _, vs, _, _, _, _)       => vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case AttrOneTacUUID(_, _, _, vs, _, _, _, _)       => vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case AttrOneTacURI(_, _, _, vs, _, _, _, _)        => vs.map(v => list(at, v.asInstanceOf[AnyRef]))
      case AttrOneTacByte(_, _, _, vs, _, _, _, _)       => vs.map(v => list(at, v.toInt.asInstanceOf[AnyRef]))
      case AttrOneTacShort(_, _, _, vs, _, _, _, _)      => vs.map(v => list(at, v.toInt.asInstanceOf[AnyRef]))
      case AttrOneTacChar(_, _, _, vs, _, _, _, _)       => vs.map(v => list(at, v.toString.asInstanceOf[AnyRef]))
    }
  }

  override protected lazy val valueString     = identity
  override protected lazy val valueInt        = identity
  override protected lazy val valueLong       = identity
  override protected lazy val valueFloat      = identity
  override protected lazy val valueDouble     = identity
  override protected lazy val valueBoolean    = identity
  override protected lazy val valueBigInt     = (v: BigInt) => v.bigInteger
  override protected lazy val valueBigDecimal = (v: BigDecimal) => v.bigDecimal
  override protected lazy val valueDate       = identity
  override protected lazy val valueUUID       = identity
  override protected lazy val valueURI        = identity
  override protected lazy val valueByte       = (v: Byte) => v.toInt
  override protected lazy val valueShort      = (v: Short) => v.toInt
  override protected lazy val valueChar       = (v: Char) => v.toString
}