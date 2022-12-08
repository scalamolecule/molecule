package molecule.db.datomic.api.ops

import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.MoleculeModel._
import molecule.core.api.ops.SaveOps
import molecule.core.api.{Connection, TxReport}
import molecule.db.datomic.facade.Conn_Peer
import molecule.db.datomic.transaction.SaveStmts
import zio.ZIO
import scala.concurrent.{ExecutionContext, Future, blocking}

class DatomicSaveOpsImpl(elements: Seq[Element]) extends SaveOps {

  override def run: ZIO[Connection, MoleculeException, TxReport] = ???

  override def transactAsync(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = {
    Future {
      blocking {
        transact
      }
    }
  }

  override def transact(implicit conn: Connection): TxReport = {
    conn.asInstanceOf[Conn_Peer].transact(
      new SaveStmts(elements).getStmts
    )
  }
}
