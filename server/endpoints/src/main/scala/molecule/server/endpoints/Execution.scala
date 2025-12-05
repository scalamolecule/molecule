package molecule.server.endpoints

import java.nio.ByteBuffer
import java.util.UUID
import scala.collection.concurrent.TrieMap
import scala.concurrent.Future
import boopickle.Default.*
import molecule.core.dataModel.{DataModel, Value}
import molecule.core.error.MoleculeError
import molecule.core.util.MoleculeLogging
import molecule.db.common.authentication.AuthContext
import molecule.db.common.marshalling.Boopicklers.*
import molecule.db.common.marshalling.serialize.PickleTpls
import molecule.db.common.marshalling.{ConnProxy, MoleculeEndpoints, MoleculeRpc}
import molecule.db.common.spi.TxReport
import molecule.db.common.util.Executor.*

abstract class Execution(rpc: MoleculeRpc)
  extends MoleculeEndpoints with MoleculeLogging {

  // Server-side session cache: maps connection UUID to auth context
  private[server] val authCache = TrieMap.empty[UUID, AuthContext]

  // Inject auth cache into RPC if it supports it (MoleculeBackend_SQL via CachedConnection)
  rpc match {
    case backend: molecule.db.common.transaction.CachedConnection =>
      backend.setAuthCache(authCache)
    case _ => // RPC doesn't support auth cache
  }

  /** Server logic
   *
   * Pattern of each action:
   * 1. Unpickle arguments from client
   * 2. Execute action on server (shared MoleculeRpc actions come in handy here)
   * 3. Pickle result for transfer back to client
   * */

  def executeQuery(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
    val (proxy, dataModel, limit, bindValues) =
      Unpickle[(ConnProxy, DataModel, Option[Int], List[Value])].fromBytes(args)
    rpc
      .query[Any](proxy, dataModel, limit, bindValues)
      .map {
        case Right(result) => Right(PickleTpls(dataModel, false).getPickledTpls(result))
        case Left(err)     => Left(err)
      }
  }

  def executeQueryOffset(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
    val (proxy, dataModel, limit, offset, bindValues) =
      Unpickle[(ConnProxy, DataModel, Option[Int], Int, List[Value])].fromBytes(args: ByteBuffer)
    rpc
      .queryOffset[Any](proxy, dataModel, limit, offset, bindValues)
      .map {
        case Right((tpls, limit, more)) =>
          Right(PickleTpls(dataModel, false).pickleOffset(tpls, limit, more))
        case Left(err)                  => Left(err)
      }
  }

  def executeQueryCursor(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
    val (proxy, dataModel, limit, cursor, bindValues) =
      Unpickle[(ConnProxy, DataModel, Option[Int], String, List[Value])].fromBytes(args: ByteBuffer)
    rpc
      .queryCursor[Any](proxy, dataModel, limit, cursor, bindValues)
      .map {
        case Right((tpls, cursor, more)) =>
          Right(PickleTpls(dataModel, false).pickleCursor(tpls, cursor, more))
        case Left(err)                   => Left(err)
      }
  }

  def executeUnsubscribe(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
    val (proxy, dataModel) =
      Unpickle[(ConnProxy, DataModel)].fromBytes(args: ByteBuffer)
    rpc
      .unsubscribe(proxy, dataModel)
      .map {
        case Right(()) => Right(Pickle.intoBytes[Unit](()))
        case Left(err) => Left(err)
      }
  }

  def executeSave(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
    val (proxy, dataModel) = Unpickle[(ConnProxy, DataModel)].fromBytes(args)
    rpc
      .save(proxy, dataModel)
      .map {
        case Right(txReport) => Right(Pickle.intoBytes[TxReport](txReport))
        case Left(err)       => Left(err)
      }
  }

  def executeInsert(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
    val (proxy, tpldataModel, tplsSerialized) =
      Unpickle[(ConnProxy, DataModel, ByteBuffer)].fromBytes(args: ByteBuffer)
    rpc
      .insert(proxy, tpldataModel, tplsSerialized)
      .map {
        case Right(txReport) => Right(Pickle.intoBytes[TxReport](txReport))
        case Left(err)       => Left(err)
      }
  }

  def executeUpdate(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
    val (proxy, dataModel, isUpsert) =
      Unpickle[(ConnProxy, DataModel, Boolean)].fromBytes(args: ByteBuffer)
    rpc
      .update(proxy, dataModel, isUpsert)
      .map {
        case Right(txReport) => Right(Pickle.intoBytes[TxReport](txReport))
        case Left(err)       => Left(err)
      }
  }

  def executeDelete(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
    val (proxy, dataModel) =
      Unpickle[(ConnProxy, DataModel)].fromBytes(args: ByteBuffer)
    rpc
      .delete(proxy, dataModel)
      .map {
        case Right(txReport) => Right(Pickle.intoBytes[TxReport](txReport))
        case Left(err)       => Left(err)
      }
  }

  /** TESTING ONLY: Set authentication for a connection UUID
   *
   * ⚠️  CRITICAL SECURITY WARNING ⚠️
   *
   * This endpoint has NO authentication or authorization checks!
   * Any client can call it to impersonate any user with any role.
   *
   * PRODUCTION DEPLOYMENT:
   * - DO NOT expose this endpoint in production
   * - Use proper token-based authentication instead (JWT, OAuth, session cookies)
   *
   * TESTING USE:
   * - Only for testing authorization from JS frontend against test server
   * - Server must be on localhost or isolated test environment
   * - Never deploy this to publicly accessible servers
   *
   * PROPER PRODUCTION AUTH FLOW:
   * 1. Client authenticates with credentials (username/password, OAuth, etc.)
   * 2. Server validates credentials and issues signed token (JWT)
   * 3. Client sends token with each request
   * 4. Server validates token signature and extracts userId/role
   * 5. Server creates AuthContext from validated token claims
   *
   * TODO for production:
   * - Implement token validation endpoint
   * - Add middleware to validate tokens and populate authCache
   */
  def executeAuth(args: ByteBuffer, enabled: Boolean): Future[Either[MoleculeError, ByteBuffer]] = {
    if (!enabled) {
      Future.successful(Left(molecule.core.error.ExecutionError(
        "Test auth endpoint is disabled. Enable it with:\n" +
        "  • Environment variable: MOLECULE_ENABLE_TEST_ENDPOINTS=true\n" +
        "  • Or JVM property: -Dmolecule.enable.test.endpoints=true\n" +
        "Then restart the backend server.\n\n" +
        "⚠️  WARNING: This endpoint is INSECURE and bypasses authentication.\n" +
        "Only enable in test/development environments. NEVER in production!"
      )))
    } else {
      val (uuid, userId, role) = Unpickle[(UUID, String, String)].fromBytes(args)
      authCache.put(uuid, AuthContext(userId, role, Map.empty[String, Any]))
      Future.successful(Right(Pickle.intoBytes[Unit](())))
    }
  }
}