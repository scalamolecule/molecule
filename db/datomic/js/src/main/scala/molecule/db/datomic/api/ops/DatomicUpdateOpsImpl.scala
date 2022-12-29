package molecule.db.datomic.api.ops

import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.MoleculeModel._
import molecule.core.api.ops.{SaveOps, UpdateOps}
import molecule.core.api.{Connection, TxReport}
import zio.ZIO
import scala.concurrent.{ExecutionContext, Future}

class DatomicUpdateOpsImpl(elements: Seq[Element], isUpsert: Boolean) extends UpdateOps {

  override def run: ZIO[Connection, MoleculeException, TxReport] = ???

  override def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = {
//    conn.asInstanceOf[Conn_Peer].transact(stmts)
    Future {
      try {
        ???
      } catch {
        case e: Throwable => Future.failed(e)
      }
    }.flatten
  }

  override def multiple: UpdateOps = ???
}
