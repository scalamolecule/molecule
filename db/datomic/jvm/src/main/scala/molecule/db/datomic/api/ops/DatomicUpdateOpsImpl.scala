package molecule.db.datomic.api.ops

import datomic.Peer
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
  isMultiple: Boolean = false
) extends DatomicTransactionBase(elements) with UpdateOps {

  override def run: ZIO[Connection, MoleculeException, TxReport] = ???

  override def transactAsync(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = ???

  override def transact(implicit conn0: Connection): TxReport = {
    val conn              = conn0.asInstanceOf[Conn_Peer]
    val (stmts0, idQuery) = new UpdateStmts(conn.schema.uniqueAttrs, elements, isMultiple).getStmts
    var eid               = 0L
    val stmts1            = idQuery.fold(stmts0) { query =>
      stmts.clear()
      Peer.q(query, conn.peerConn.db()).forEach { idRow =>
        eid = idRow.get(0).asInstanceOf[Long]
        stmts0.forEach { stmt =>
          stmt.set(1, eid)
          stmts.add(stmt)
        }
      }
      stmts
    }
    conn.transact(stmts1)
  }
  override def multiple: UpdateOps = new DatomicUpdateOpsImpl(elements, true)
}
