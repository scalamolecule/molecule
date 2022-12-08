package molecule.core.api.ops

import molecule.base.util.exceptions.MoleculeException
import molecule.core.api.{Connection, TxReport}
import zio.ZIO
import scala.concurrent.{ExecutionContext, Future}

trait InsertOps {

  def run: ZIO[Connection, MoleculeException, TxReport]

  def transactAsync(implicit conn: Connection, ec: ExecutionContext): Future[TxReport]

  def transact(implicit conn: Connection): TxReport
}
