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
    ).getStmts


    data.foreach(println)

    val db = conn.peerConn.db()
    if (isUpsert && filterQuery.isEmpty) {
      // Add all values regardless of pre-existence
      val addStmts = doAddStmts(data, db)
      eids.foreach(addStmts)

    } else if (filterQuery.isEmpty) {
      // Update only values with current value
      val addStmts = doAddStmts(data, db, false)
      eids.foreach(addStmts)

    } else {
      // Update only values with current value (query guarantees current value exists)
      val query = filterQuery.get
      val res   = Peer.q(query, db +: inputs: _*)
      val count = res.size()
      if (!isMultiple && count > 1)
        throw MoleculeException(
          s"Please provide explicit `$update.multiple` to $update multiple entities " +
            s"(found $count matching entities)."
        )
      val addStmts = doAddStmts(data, db)
      res.forEach { idRow =>
        addStmts(idRow.get(0))
      }
    }
    println("\n\n--- UPDATE -----------------------------------------------------------------------")
    elements.foreach(println)
    println("---")
    stmts.forEach(stmt => println(stmt))
    conn.transact(stmts)
  }


  private def doAddStmts(
    data: Seq[(String, Keyword, Seq[AnyRef], Boolean)],
    db: Database,
    disregardCurrentValue: Boolean = true
  ): AnyRef => Unit = {
    (eid0: AnyRef) => {
      var eid : AnyRef = eid0
      var txId: AnyRef = null
      var entity       = db.entity(eid)
      data.foreach {
        case ("add", a, vs, retractCur) =>
          if (retractCur) {
            val eid1      = if (txId != null) txId else eid
            val curValues = Peer.q("[:find ?v :in $ ?e ?a :where [?e ?a ?v]]", db, eid1, a)
            curValues.forEach { row =>
              val v = row.get(0)
              if (!vs.contains(v))
                addStmt(retract, eid1, a, v)
            }
          }
          if (disregardCurrentValue || entity.get(a) != null) {
            vs.foreach(v => addStmt(add, eid, a, v))
          }

        case ("retract", a, vs, _) =>
          if (vs.isEmpty) {
            val cur = entity.get(a)
            if (cur != null) {
              cur match {
                case set: jSet[AnyRef] =>
                  set.forEach {
                    case kw: Keyword          => addStmt(retract, eid, a, db.entity(kw).get(":db/id"))
                    case entityMap: EntityMap => addStmt(retract, eid, a, entityMap.get(":db/id"))
                    case v                    => addStmt(retract, eid, a, v)
                  }
                case v                 =>
                  addStmt(retract, eid, a, v)
              }
            }
          } else {
            vs.foreach(v =>
              addStmt(retract, eid, a, v)
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
      }
    }
  }
}
