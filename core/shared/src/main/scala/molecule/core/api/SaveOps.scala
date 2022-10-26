package molecule.core.api

import molecule.base.util.exceptions.MoleculeException
import zio.ZIO
import scala.concurrent.{ExecutionContext, Future}

trait SaveOps {

  def run: ZIO[Connection, MoleculeException, TxReport]

  def transactAsync(implicit conn: Connection, ec: ExecutionContext): Future[TxReport]

  def transact(implicit conn: Connection): TxReport
}
