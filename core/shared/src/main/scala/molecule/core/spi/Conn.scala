package molecule.core.spi

import cats.effect.IO
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.api.Savepoint
import molecule.core.marshalling.{ConnProxy, MoleculeRpc}
import molecule.core.util.ModelUtils
import zio.ZIO
import scala.concurrent.{ExecutionContext, Future}

abstract class Conn(val proxy: ConnProxy)
  extends ModelUtils { self: DataType =>


  def transact_async(data: Data)
                    (implicit ec: ExecutionContext): Future[TxReport] =
    throw jvmOnly("transact_async")

  def transact_sync(data: Data): TxReport =
    throw jvmOnly("transact_sync")

  def db: Any = ???

  private[molecule] lazy val rpc: MoleculeRpc = throw jsOnly("rpc")

  protected def jsOnly(method: String): ExecutionError =
    ExecutionError(s"`$method` only implemented on JS platform.")

  protected def jvmOnly(method: String): ExecutionError =
    ExecutionError(s"`$method` only implemented on JVM platform.")


  // Subscriptions -------------------------------------------------------------

  private var callbacks = List.empty[(List[Element], (Set[String], Boolean) => Future[Unit])]

  def callback(mutation: List[Element], isDelete: Boolean = false)
              (implicit ec: ExecutionContext): Future[List[Unit]] = {
    val mutationAttrs = getAttrNames(mutation)
    // Ensure all callbacks are called
    Future.sequence(callbacks.map {
      case (_, callback) => callback(mutationAttrs, isDelete)
    })
  }

  def addCallback(callback: (List[Element], (Set[String], Boolean) => Future[Unit])): Unit = {
    callbacks = callbacks :+ callback
  }

  def removeCallback(elements: List[Element]): Unit = {
    callbacks = callbacks.filterNot(_._1 == elements)
  }


  // Transaction handling ------------------------------------------------------

  private   var uow_    = false
  protected var commit_ = true

  def waitCommitting(): Unit = ???
  def commit(): Unit = ???
  def rollback(): Unit = ???

  def savepoint_sync[T](body: Savepoint => T): T = ???
  def savepoint_async[T](body: Savepoint => Future[T])
                        (implicit ec: ExecutionContext): Future[T] = ???

  def savepoint_zio[T](
    body: Savepoint => ZIO[Conn, MoleculeError, T]
  ): ZIO[Conn, MoleculeError, T] = ???

  def savepoint_io[T](body: Savepoint => IO[T]): IO[T] = ???


  def setInsideUOW(inside: Boolean): Unit = uow_ = inside
  def isInsideUOW: Boolean = uow_

  def isInsideSavepoint: Boolean = ???

  def setAutoCommit(bool: Boolean): Unit = ???
}
