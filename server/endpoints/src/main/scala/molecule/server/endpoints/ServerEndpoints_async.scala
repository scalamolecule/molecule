package molecule.server.endpoints

import java.nio.ByteBuffer
import scala.concurrent.Future
import molecule.core.error.*
import molecule.db.common.marshalling.MoleculeRpc
import molecule.db.common.util.Executor.*
import sttp.tapir.*
import sttp.tapir.server.ServerEndpoint

abstract class ServerEndpoints_async(rpc: MoleculeRpc) extends Execution(rpc) {

  private val msg = "RPC failed on server: "

  private def mkServerEndpoint_Future(
    endpoint: PublicEndpoint[ByteBuffer, MoleculeError, ByteBuffer, Any],
    executeAction: ByteBuffer => Future[Either[MoleculeError, ByteBuffer]]
  ): ServerEndpoint[Any, Future] = {
    endpoint.serverLogic { args =>
      executeAction(args)
        .recover {
          case e: ModelError =>
            logger.warn(e.toString)
            Left(e.copy(message = msg + e.message))

          case e: ValidationErrors =>
            logger.warn(e.toString)
            Left(e)

          case e: InsertErrors =>
            logger.warn(e)
            Left(e.copy(message = Some(msg + e.msg)))

          case e: ExecutionError =>
            logger.error(e)
            Left(e.copy(message = msg + e.message))

          case e: Throwable =>
            logger.error(e.toString)
            logger.error(e.getStackTrace.mkString("\n"))
            Left(ExecutionError(msg + e.toString))
        }
    }
  }

  /** Check if test endpoints should be enabled (evaluated once at server startup)
   *
   * ⚠️  SECURITY WARNING: Test endpoints bypass authentication!
   *
   * Enable only in test/development environments by setting:
   *   -Dmolecule.enable.test.endpoints=true
   *
   * Or via environment variable:
   *   MOLECULE_ENABLE_TEST_ENDPOINTS=true
   *
   * NEVER enable in production!
   *
   * Note: This is a val (not def) so it's evaluated once at server startup.
   * This is intentional for security - the setting cannot change at runtime.
   * To enable/disable, you must restart the server.
   */
  private val testEndpointsEnabled: Boolean = {
    val enabled = sys.props.get("molecule.enable.test.endpoints")
      .orElse(sys.env.get("MOLECULE_ENABLE_TEST_ENDPOINTS"))
      .exists(_.toLowerCase == "true")

    if (enabled) {
      logger.warn("⚠️  TEST ENDPOINTS ENABLED - DO NOT USE IN PRODUCTION!")
    } else {
      logger.info("Test auth endpoint disabled (production mode)")
    }
    enabled
  }

  /** All available endpoints (always includes auth endpoint, but it returns error if not enabled) */
  val moleculeServerEndpoints_Future: List[ServerEndpoint[Any, Future]] = List(
    mkServerEndpoint_Future(moleculeEndpoint_query, executeQuery),
    mkServerEndpoint_Future(moleculeEndpoint_queryOffset, executeQueryOffset),
    mkServerEndpoint_Future(moleculeEndpoint_queryCursor, executeQueryCursor),
    mkServerEndpoint_Future(moleculeEndpoint_unsubscribe, executeUnsubscribe),
    mkServerEndpoint_Future(moleculeEndpoint_save, executeSave),
    mkServerEndpoint_Future(moleculeEndpoint_insert, executeInsert),
    mkServerEndpoint_Future(moleculeEndpoint_update, executeUpdate),
    mkServerEndpoint_Future(moleculeEndpoint_delete, executeDelete),
    mkServerEndpoint_Future(moleculeEndpoint_auth, executeAuth(_, testEndpointsEnabled)),
  )
}