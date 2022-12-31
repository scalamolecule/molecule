package molecule.core.api

import molecule.base.util.exceptions.MoleculeException
import molecule.core.marshalling.{BooPicklers, ConnProxy, MoleculeRpc}
import scala.concurrent.{ExecutionContext, Future}

abstract class Connection(val proxy: ConnProxy)
  extends BooPicklers { self: DataType =>

  def transact(data: Data)(implicit ec: ExecutionContext): Future[TxReport]

  def db: Any = ???

  private[molecule] lazy val rpc: MoleculeRpc = throw jsOnly("rpc")

  private[molecule] var curProxy: ConnProxy = proxy

  protected def jsOnly(method: String): MoleculeException =
    MoleculeException(s"`$method` only implemented on JS platform.")

  protected def jvmOnly(method: String): MoleculeException =
    MoleculeException(s"`$method` only implemented on JVM platform.")
}
