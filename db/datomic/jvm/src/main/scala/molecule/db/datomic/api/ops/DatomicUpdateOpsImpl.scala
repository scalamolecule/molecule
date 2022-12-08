package molecule.db.datomic.api.ops

import java.util.Collections
import datomic.Peer
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.MoleculeModel._
import molecule.core.api.ops.UpdateOps
import molecule.core.api.{Connection, TxReport}
import molecule.db.datomic.facade.Conn_Peer
import molecule.db.datomic.transaction.{DatomicTransactionBase, UpdateStmts}
import zio.ZIO
import scala.concurrent.{ExecutionContext, Future}
import java.util.{Collections, List => jList}

class DatomicUpdateOpsImpl(
  elements: Seq[Element],
  isMultiple: Boolean = false
) extends DatomicTransactionBase(elements) with UpdateOps {

  override def run: ZIO[Connection, MoleculeException, TxReport] = ???

  override def transactAsync(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = ???

  override def transact(implicit conn0: Connection): TxReport = {
    val conn = conn0.asInstanceOf[Conn_Peer]

    val (eids, idQuery, inputs, dataStmts) =
      new UpdateStmts(conn.schema.uniqueAttrs, elements, isMultiple).getStmts

    stmts.clear()
    idQuery.fold {
      eids.foreach { eid =>
        dataStmts.forEach { dataStmt =>
          val updateStmt = stmtList
          updateStmt.addAll(dataStmt)
          updateStmt.set(1, eid.asInstanceOf[AnyRef])
          stmts.add(updateStmt)
        }
      }
    } { query =>
      var eid: AnyRef = 0L.asInstanceOf[AnyRef]
      Peer.q(query, conn.peerConn.db() +: inputs: _*).forEach { idRow =>
        eid = idRow.get(0)
        dataStmts.forEach { dataStmt =>
          dataStmt.set(1, eid)
          stmts.add(dataStmt)
        }
      }
    }
    println("---")
    stmts.forEach(stmt => println(stmt))
    conn.transact(stmts)
  }
  override def multiple: UpdateOps = new DatomicUpdateOpsImpl(elements, true)
}
