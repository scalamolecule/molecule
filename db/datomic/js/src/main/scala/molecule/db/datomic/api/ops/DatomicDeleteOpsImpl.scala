package molecule.db.datomic.api.ops

import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.MoleculeModel._
import molecule.core.api.ops.{DeleteOps, SaveOps}
import molecule.core.api.{Connection, TxReport}
import zio.ZIO
import scala.concurrent.{ExecutionContext, Future}

class DatomicDeleteOpsImpl(elements: Seq[Element]) extends DeleteOps {

  override def run: ZIO[Connection, MoleculeException, TxReport] = ???

  override def transact(implicit conn: Connection): TxReport = {
//    conn.asInstanceOf[Conn_Peer].transact(stmts)
    ???
  }
  override def transactAsync(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = ???
}
