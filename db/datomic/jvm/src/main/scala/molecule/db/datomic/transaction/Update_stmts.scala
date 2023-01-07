package molecule.db.datomic.transaction

import java.util.{Set => jSet}
import clojure.lang.Keyword
import datomic.Util.list
import datomic.query.EntityMap
import datomic.{Database, Peer}
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.Model._
import molecule.core.transaction.{Update, Update2Data}
import molecule.core.validation.CheckConflictingAttrs
import molecule.db.datomic.facade.DatomicConn_JVM
import molecule.db.datomic.query.DatomicModel2Query
import scribe.Logging

trait Update_stmts extends DatomicTxBase_JVM with Update2Data with Logging { self: Update =>

  def getStmts(
    conn: DatomicConn_JVM,
    elements: Seq[Element]
  ): Data = {
    CheckConflictingAttrs(elements, distinguishMode = true)

    val (eids, filterElements, data) = resolve(elements, Nil, Nil, Nil)

    if (!isMultiple && eids.length > 1) {
      val update = if (self.isUpsert) "upsert" else "update"
      throw MoleculeException(
        s"Please provide explicit `$update.multiple` to $update multiple entities " +
          s"(found ${eids.length} matching entities)."
      )
    }

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
      val eidRows = Peer.q(query, db +: inputs: _*)
      val count   = eidRows.size()
      if (!isMultiple && count > 1) {
        throw MoleculeException(
          s"Please provide explicit `$update.multiple` to $update multiple entities " +
            s"(found $count matching entities)."
        )
      }
      val addStmts = eid2stmts(data, db)
      eidRows.forEach(eidRow => addStmts(eidRow.get(0)))
    }

    logger.debug(("UPDATE:" +: elements).mkString("\n"), "\n\n", stmts.toArray().mkString("\n"))
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

        case other => throw MoleculeException("Unexpected data in update: " + other)
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
      case AttrOneTacString(_, _, _, vs, _, _, _)     => vs.map(v => list(at, v))
      case AttrOneTacInt(_, _, _, vs, _, _, _)        => vs.map(v => list(at, v))
      case AttrOneTacLong(_, _, _, vs, _, _, _, _)    => vs.map(v => list(at, v))
      case AttrOneTacFloat(_, _, _, vs, _, _, _)      => vs.map(v => list(at, v))
      case AttrOneTacDouble(_, _, _, vs, _, _, _)     => vs.map(v => list(at, v))
      case AttrOneTacBoolean(_, _, _, vs, _, _, _)    => vs.map(v => list(at, v))
      case AttrOneTacBigInt(_, _, _, vs, _, _, _)     => vs.map(v => list(at, v.bigInteger))
      case AttrOneTacBigDecimal(_, _, _, vs, _, _, _) => vs.map(v => list(at, v.bigDecimal))
      case AttrOneTacDate(_, _, _, vs, _, _, _)       => vs.map(v => list(at, v))
      case AttrOneTacUUID(_, _, _, vs, _, _, _)       => vs.map(v => list(at, v))
      case AttrOneTacURI(_, _, _, vs, _, _, _)        => vs.map(v => list(at, v))
      case AttrOneTacByte(_, _, _, vs, _, _, _)       => vs.map(v => list(at, v.toInt))
      case AttrOneTacShort(_, _, _, vs, _, _, _)      => vs.map(v => list(at, v.toInt))
      case AttrOneTacChar(_, _, _, vs, _, _, _)       => vs.map(v => list(at, v.toString))
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