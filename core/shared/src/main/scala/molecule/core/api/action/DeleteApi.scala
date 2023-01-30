package molecule.core.api.action

import molecule.base.util.exceptions.MoleculeError
import molecule.core.api.{Connection, TxReport}
import zio.ZIO
import scala.concurrent.{ExecutionContext, Future}

trait DeleteApi extends ApiUtils {
  // Modifiers
  def multiple: DeleteApi

  // Actions
  def run: ZIO[Connection, MoleculeError, TxReport] = ???
  def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport]
}
