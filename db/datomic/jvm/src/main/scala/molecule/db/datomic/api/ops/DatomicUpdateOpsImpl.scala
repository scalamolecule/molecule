package molecule.db.datomic.api.ops

import datomic.Peer
import datomic.query.EntityMap
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

  override def run: ZIO[Connection, MoleculeException, TxReport] = ???

  override def transactAsync(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = ???

  override def transact(implicit conn0: Connection): TxReport = {
    val conn = conn0.asInstanceOf[Conn_Peer]

    val (eids, idQuery, inputs, data) = new UpdateStmts(
      conn.schema.uniqueAttrs, elements, isUpsert, isMultiple
    ).getStmts

    lazy val db = conn.peerConn.db()
    idQuery.fold(eids.foreach(addStmts)) { query =>
      val res   = Peer.q(query, db +: inputs: _*)
      val count = res.size()
      if (!isMultiple && count > 1)
        throw MoleculeException(
          s"Please provide explicit `$update.multiple` to $update multiple entities " +
            s"(found $count matching entities)."
        )
      res.forEach { idRow =>
        addStmts(idRow.get(0))
      }
    }

    def addStmts(eid0: AnyRef): Unit = {
      var eid: AnyRef = eid0
      lazy val entity = db.entity(eid)
      data.foreach {
        case ("add", a, v) =>
          val addStmt = stmtList
          addStmt.add(add)
          addStmt.add(eid)
          addStmt.add(a)
          addStmt.add(v)
          stmts.add(addStmt)

        case ("retract", a, _) =>
          val retractStmt = stmtList
          retractStmt.add(retract)
          retractStmt.add(eid)
          retractStmt.add(a)
          retractStmt.add(entity.get(a)) // lookup current value to retract
          stmts.add(retractStmt)

        case ("ref", refAttr, _) => eid = entity.get(refAttr).asInstanceOf[EntityMap].get(dbId)
        case ("tx", _, _)        => eid = datomicTx
      }
    }
    println("--- insert stmts: ---")
    stmts.forEach(stmt => println(stmt))
    conn.transact(stmts)
  }


  // Modifiers

  override def multiple: UpdateOps = new DatomicUpdateOpsImpl(elements, isUpsert, true)
}
