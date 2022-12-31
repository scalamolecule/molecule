package molecule.core.api.ops

import molecule.base.util.exceptions.MoleculeException
import molecule.core.api.{Connection, TxReport}
import zio.ZIO
import scala.concurrent.{ExecutionContext, Future}

trait DeleteOps extends BaseOps {
  // Modifiers
  def multiple: DeleteOps

  // Actions
  def run: ZIO[Connection, MoleculeException, TxReport]
  def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport]
}
