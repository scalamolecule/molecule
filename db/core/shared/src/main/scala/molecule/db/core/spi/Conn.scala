package molecule.db.core.spi

import cats.effect.IO
import molecule.db.base.error.{ExecutionError, MoleculeError}
import molecule.db.core.api.Savepoint
import molecule.db.core.ast.DataModel
import molecule.db.core.marshalling.{ConnProxy, MoleculeRpc}
import molecule.db.core.util.ModelUtils
import zio.ZIO
import scala.concurrent.{ExecutionContext, Future}

abstract class Conn(val proxy: ConnProxy)
  extends ModelUtils { self: DataType =>


  def transact_async(data: Data)
                    (implicit ec: ExecutionContext): Future[TxReport] =
    throw jvmOnly("transact_async")

  def transact_sync(data: Data): TxReport =
    throw jvmOnly("transact_sync")

  // Underlying real database connection
  def db: Any = ???

  private[molecule] lazy val rpc: MoleculeRpc = throw jsOnly("rpc")

  protected def jsOnly(method: String): ExecutionError =
    ExecutionError(s"`$method` only implemented on JS platform.")

  protected def jvmOnly(method: String): ExecutionError =
    ExecutionError(s"`$method` only implemented on JVM platform.")


  // Subscription callbacks ----------------------------------------------------

  private var callbacks = List.empty[(DataModel, (Set[String], Boolean) => Future[Unit])]

  def callback(mutation: DataModel, isDelete: Boolean = false)
              (implicit ec: ExecutionContext): Future[List[Unit]] = {
    val mutationAttrs = getAttrNames(mutation.elements)
    // Ensure all callbacks are called
    Future.sequence(callbacks.map {
      case (_, callback) => callback(mutationAttrs, isDelete)
    })
  }

  def addCallback(callback: (DataModel, (Set[String], Boolean) => Future[Unit])): Unit = {
    callbacks = callbacks :+ callback
  }

  def removeCallback(elements: DataModel): Unit = {
    callbacks = callbacks.filterNot(_._1 == elements)
  }

  def callbackCount = callbacks.length


  // Transaction handling ------------------------------------------------------

  protected var commit_ = true

  def waitCommitting(): Unit = ???
  def commit(): Unit = ???
  def rollback(): Unit = ???

  def savepoint_sync[T](body: Savepoint => T): T = ???
  def savepoint_async[T](body: Savepoint => Future[T])(implicit ec: ExecutionContext): Future[T] = ???
  def savepoint_zio[T](body: Savepoint => ZIO[Conn, MoleculeError, T]): ZIO[Conn, MoleculeError, T] = ???
  def savepoint_io[T](body: Savepoint => IO[T]): IO[T] = ???

  def setAutoCommit(bool: Boolean): Unit = ???
}
