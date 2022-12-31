package molecule.db.datomic.api.ops

import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.Model._
import molecule.core.api.ops.{DeleteOps, SaveOps}
import molecule.core.api.{Connection, TxReport}
import zio.ZIO
import scala.concurrent.{ExecutionContext, Future}

class DatomicDeleteOpsImpl(elements: Seq[Element]) extends DeleteOps {

  override def multiple: DeleteOps = ???

  override def run: ZIO[Connection, MoleculeException, TxReport] = ???

  override def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = {
    Future {
      try {
        ???
      } catch {
        case e: Throwable => Future.failed(e)
      }
    }.flatten
  }
}
