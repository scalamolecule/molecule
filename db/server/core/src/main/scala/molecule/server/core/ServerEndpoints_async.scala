package molecule.server.core

import java.nio.ByteBuffer
import molecule.base.error.*
import molecule.db.core.marshalling.MoleculeRpc
import molecule.db.core.util.Executor.*
import sttp.tapir.*
import sttp.tapir.server.ServerEndpoint
import scala.concurrent.Future

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

  val moleculeServerEndpoints_Future: List[ServerEndpoint[Any, Future]] = List(
    mkServerEndpoint_Future(moleculeEndpoint_query, executeQuery),
    mkServerEndpoint_Future(moleculeEndpoint_queryOffset, executeQueryOffset),
    mkServerEndpoint_Future(moleculeEndpoint_queryCursor, executeQueryCursor),
    mkServerEndpoint_Future(moleculeEndpoint_unsubscribe, executeUnsubscribe),
    mkServerEndpoint_Future(moleculeEndpoint_save, executeSave),
    mkServerEndpoint_Future(moleculeEndpoint_insert, executeInsert),
    mkServerEndpoint_Future(moleculeEndpoint_update, executeUpdate),
    mkServerEndpoint_Future(moleculeEndpoint_delete, executeDelete),
  )
}