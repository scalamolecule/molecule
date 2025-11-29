package molecule.db.common.spi

import scala.concurrent.{ExecutionContext, Future}
import cats.effect.IO
import molecule.core.dataModel.DataModel
import molecule.core.error.{ExecutionError, MoleculeError}
import molecule.db.common.api.{AuthContext, Savepoint}
import molecule.db.common.marshalling.{ConnProxy, MoleculeRpc}
import molecule.db.common.util.ModelUtils
import zio.ZIO

abstract class Conn(
  val proxy: ConnProxy,
  val authContext: Option[AuthContext] = None
) extends ModelUtils {

  // Underlying real database connection
  def db: Any = ???

  /** Authentication context created by backend after validating credentials
    *
    * This is NEVER sent from frontend. It is created on the backend by:
    * 1. Receiving authentication token (JWT, OAuth, etc.)
    * 2. Validating token with appropriate AuthProvider
    * 3. Extracting userId and role from validated token
    * 4. Creating AuthContext with validated information
    *
    * None means unauthenticated (public access only)
    *
    * IMMUTABLE: To change auth context, create a new connection with:
    * - conn.withAuth(userId, role)
    * - conn.withAuthContext(authContext)
    * - conn.clearAuth
    */

  /** Create a new connection with the specified authentication context */
  def withAuthContext(authCtx: AuthContext): Conn

  /** Create a new connection with the specified userId and role */
  def withAuth(userId: String, role: String): Conn =
    withAuthContext(AuthContext(userId, role))

  /** Create a new connection without authentication (public access only) */
  def clearAuth: Conn

  private[molecule] lazy val rpc: MoleculeRpc = throw jsOnly("rpc")

  protected def jsOnly(method: String): ExecutionError =
    ExecutionError(s"`$method` only implemented on JS platform.")

  protected def jvmOnly(method: String): ExecutionError =
    ExecutionError(s"`$method` only implemented on JVM platform.")


  // Subscription callbacks ----------------------------------------------------

  private var callbacks = List.empty[(DataModel, Int, java.util.BitSet, () => Unit)]

  def callback(mutation: DataModel, isDelete: Boolean = false)
              (using ec: ExecutionContext): Future[Unit] = Future(
    callbacks.foreach {
      case (_, deletionEntityIndex, _, callback) if isDelete =>
        if (deletionEntityIndex == mutation.firstEntityIndex) {
          // Trigger callback if deleted entity matches a subscription entity
          callback()
        }

      case (_, _, involvedAttrIndexes, callback) =>
        val mutationAttrs = new java.util.BitSet()
        mutation.attrIndexes.foreach(mutationAttrs.set)
        if (involvedAttrIndexes.intersects(mutationAttrs)) {
          // Trigger callback if any mutation attribute matches an involved subscription attribute
          callback()
        }
    }
  )

  def addCallback(dataModel: DataModel, callback: () => Unit): Unit = {
    val triggerAttrs = new java.util.BitSet()
    dataModel.attrIndexes.foreach(triggerAttrs.set)
    val callbackCoords = (dataModel, dataModel.firstEntityIndex, triggerAttrs, callback)
    callbacks = callbacks :+ callbackCoords
  }

  def removeCallback(dataModel: DataModel): Unit = {
    callbacks = callbacks.filterNot(_._1 == dataModel)
  }


  // Transaction handling ------------------------------------------------------

  protected var commit_ = true

  def waitCommitting(): Unit = ???
  def commit(): Unit = ???
  def rollback(): Unit = ???

  def savepoint_sync[T](body: Savepoint => T): T = ???
  def savepoint_async[T](body: Savepoint => Future[T])(using ec: ExecutionContext): Future[T] = ???
  def savepoint_zio[T](body: Savepoint => ZIO[Conn, MoleculeError, T]): ZIO[Conn, MoleculeError, T] = ???
  def savepoint_io[T](body: Savepoint => IO[T]): IO[T] = ???

  def setAutoCommit(bool: Boolean): Unit = ???
}
