package molecule.core.spi

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.marshalling.{ConnProxy, MoleculeRpc}
import molecule.core.util.ModelUtils
import scala.concurrent.{ExecutionContext, Future}

abstract class Conn(val proxy: ConnProxy)
  extends ModelUtils { self: DataType =>

  def transact_async(data: Data)(implicit ec: ExecutionContext): Future[TxReport] = throw jvmOnly("transact_async")
  def transact_sync(data: Data): TxReport = throw jvmOnly("transact_sync")

  def db: Any = ???

  private[molecule] lazy val rpc: MoleculeRpc = throw jsOnly("rpc")

  protected def jsOnly(method: String): ExecutionError =
    ExecutionError(s"`$method` only implemented on JS platform.")

  protected def jvmOnly(method: String): ExecutionError =
    ExecutionError(s"`$method` only implemented on JVM platform.")

  var startTime = 0L

  // Subscriptions --------------------------------------------------------------

  private var callbacks = List.empty[(List[Element], (Set[String], Boolean) => Unit)]

  def callback(mutation: List[Element], isDelete: Boolean = false): Unit = {
    val mutationAttrs = getAttrNames(mutation)
    callbacks.foreach {
      case (_, callback) => callback(mutationAttrs, isDelete)
    }
  }

  def addCallback(callback: (List[Element], (Set[String], Boolean) => Unit)): Unit = {
    callbacks = callbacks :+ callback
  }

  def removeCallback(elements: List[Element]): Unit = {
    callbacks = callbacks.filterNot(_._1 == elements)
  }
}
