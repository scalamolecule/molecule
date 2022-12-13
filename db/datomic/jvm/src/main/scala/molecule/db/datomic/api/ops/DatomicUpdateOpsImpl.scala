package molecule.db.datomic.api.ops

import java.util.{Set => jSet}
import clojure.lang.Keyword
import datomic.query.EntityMap
import datomic.{Database, Peer}
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.MoleculeModel._
import molecule.core.api.ops.UpdateOps
import molecule.core.api.{Connection, TxReport}
import molecule.db.datomic.facade.Conn_Peer
import molecule.db.datomic.transaction.{DatomicTransactionBase, UpdateStmts}
import zio.ZIO
import scala.concurrent.{ExecutionContext, Future}

class DatomicUpdateOpsImpl(
  elements: Seq[Element],
  isUpsert: Boolean = false,
  isMultiple: Boolean = false,
) extends DatomicTransactionBase(elements, isUpsert) with UpdateOps {

  // Modifiers
  override def multiple: UpdateOps = new DatomicUpdateOpsImpl(elements, isUpsert, true)

  // Actions
  override def run: ZIO[Connection, MoleculeException, TxReport] = ???

  override def transactAsync(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = ???

  override def transact(implicit conn0: Connection): TxReport = {
    val conn = conn0.asInstanceOf[Conn_Peer]

    val (eids, filterQuery, inputs, data) = new UpdateStmts(
      conn.schema.uniqueAttrs, elements, isUpsert, isMultiple
    ).resolve

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
  }


  private def eid2stmts(
    data: Seq[(String, Keyword, Seq[AnyRef], Boolean)],
    db: Database,
    addNewValues: Boolean = true
  ): AnyRef => Unit = {
    (eid0: AnyRef) => {
      var eid : AnyRef = eid0
      var txId: AnyRef = null
      var entity       = db.entity(eid)
      data.foreach {
        case ("add", a, newValues, retractCur) =>
          if (retractCur) {
            val eid1      = if (txId != null) txId else eid
            // todo: optimize with one query for all eids
            val curValues = Peer.q("[:find ?v :in $ ?e ?a :where [?e ?a ?v]]", db, eid1, a)
            curValues.forEach { row =>
              val curValue = row.get(0)
              if (!newValues.contains(curValue))
                addStmt(retract, eid1, a, curValue)
            }
          }
          if (addNewValues || entity.get(a) != null) {
            newValues.foreach(newValue =>
              addStmt(add, eid, a, newValue)
            )
          }

        case ("retract", a, retractValues, _) =>
          if (retractValues.isEmpty) {
            val cur = entity.get(a)
            if (cur != null) {
              cur match {
                case set: jSet[_] =>
                  set.forEach {
                    case kw: Keyword          => addStmt(retract, eid, a, db.entity(kw).get(":db/id"))
                    case entityMap: EntityMap => addStmt(retract, eid, a, entityMap.get(":db/id"))
                    case v                    => addStmt(retract, eid, a, v.asInstanceOf[AnyRef])
                  }
                case v            =>
                  addStmt(retract, eid, a, v)
              }
            }
          } else {
            retractValues.foreach(retractValue =>
              addStmt(retract, eid, a, retractValue)
            )
          }

        case ("ref", refAttr, _, _) =>
          entity = entity.get(refAttr).asInstanceOf[EntityMap]
          eid = entity.get(dbId)

        case ("tx", _, _, _) =>
          // Get transaction entity id
          txId = Peer.q("[:find ?tx :in $ ?e :where [?e _ _ ?tx]]", db, eid).iterator.next.get(0)
          entity = db.entity(txId)
          eid = datomicTx

        case other => throw MoleculeException("Unexpected data in update: " + other)
      }
    }
  }
}
