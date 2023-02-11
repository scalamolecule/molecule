package molecule.core.api

import molecule.base.util.exceptions.MoleculeError
import molecule.core.marshalling.{ConnProxy, MoleculeRpc}
import scala.concurrent.{ExecutionContext, Future}

abstract class Connection(val proxy: ConnProxy) { self: DataType =>

  def transact_async(data: Data)(implicit ec: ExecutionContext): Future[TxReport] = throw jvmOnly("transact_async")
  def transact_sync(data: Data): TxReport = throw jvmOnly("transact_sync")

  def db: Any = ???

  private[molecule] lazy val rpc: MoleculeRpc = throw jsOnly("rpc")

  protected def jsOnly(method: String): MoleculeError =
    MoleculeError(s"`$method` only implemented on JS platform.")

  protected def jvmOnly(method: String): MoleculeError =
    MoleculeError(s"`$method` only implemented on JVM platform.")
}
