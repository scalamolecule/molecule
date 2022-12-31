package molecule.db.datomic.api.ops

import java.util.{Set => jSet}
import clojure.lang.Keyword
import datomic.query.EntityMap
import datomic.{Database, Peer}
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.Model._
import molecule.core.api.ops.UpdateOps
import molecule.core.api.{Connection, TxReport}
import molecule.core.transaction.Update
import molecule.db.datomic.facade.DatomicConn_JVM
import molecule.db.datomic.transaction.{DatomicTxBase_JVM, Update_stmts}
import zio.ZIO
import scala.concurrent.{ExecutionContext, Future}

class DatomicUpdateOpsImpl(
  elements: Seq[Element],
  isUpsert: Boolean = false,
  isMultiple: Boolean = false,
) extends DatomicTxBase_JVM with UpdateOps {

  // Modifiers
  override def multiple: UpdateOps = new DatomicUpdateOpsImpl(elements, isUpsert, true)

  // Actions
  override def run: ZIO[Connection, MoleculeException, TxReport] = ???

  override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = try {
    initTxBase(elements, isUpsert)
    val conn = conn0.asInstanceOf[DatomicConn_JVM]



    val (eids, filterQuery, inputs, data) = (new Update(
      conn.proxy.uniqueAttrs, isUpsert
    ) with Update_stmts).getStmtsData(elements, isMultiple)

    val db = conn.peerConn.db()
    filterQuery.fold {
      val addStmts = eid2stmts(data, db, isUpsert)
      eids.foreach(addStmts)
    } { query =>
      val eidRows = Peer.q(query, db +: inputs: _*)
      val count   = eidRows.size()
      if (!isMultiple && count > 1)
        multipleModifierMissing(count)
      val addStmts = eid2stmts(data, db)
      eidRows.forEach(eidRow => addStmts(eidRow.get(0)))
    }

    println("\n\n--- UPDATE -----------------------------------------------------------------------")
    elements.foreach(println)
    println("---")
    stmts.forEach(stmt => println(stmt))
    conn.transact(stmts)
  } catch {
    case e: Throwable => Future.failed(e)
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
}
