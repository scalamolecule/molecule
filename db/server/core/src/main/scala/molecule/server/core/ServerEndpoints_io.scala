package molecule.server.core

import java.nio.ByteBuffer
import boopickle.Default.*
import cats.effect.IO
import molecule.db.core.marshalling.Boopicklers.*
import molecule.db.base.error.*
import molecule.db.core.marshalling.MoleculeRpc
import sttp.tapir.*
import sttp.tapir.server.ServerEndpoint
import scala.concurrent.Future

abstract class ServerEndpoints_io(rpc: MoleculeRpc) extends Execution(rpc) {

  private val msg = "RPC failed on server: "

  private def mkServerEndpoint_IO(
    endpoint: PublicEndpoint[ByteBuffer, MoleculeError, ByteBuffer, Any],
    executeAction: ByteBuffer => Future[Either[MoleculeError, ByteBuffer]]
  ): ServerEndpoint[Any, IO] = {
    endpoint.serverLogic { args =>
      IO.fromFuture(IO(executeAction(args))).handleError {
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

  val moleculeServerEndpoints_IO: List[ServerEndpoint[Any, IO]] = List(
    mkServerEndpoint_IO(moleculeEndpoint_query, executeQuery),
    mkServerEndpoint_IO(moleculeEndpoint_queryOffset, executeQueryOffset),
    mkServerEndpoint_IO(moleculeEndpoint_queryCursor, executeQueryCursor),
    mkServerEndpoint_IO(moleculeEndpoint_unsubscribe, executeUnsubscribe),
    mkServerEndpoint_IO(moleculeEndpoint_save, executeSave),
    mkServerEndpoint_IO(moleculeEndpoint_insert, executeInsert),
    mkServerEndpoint_IO(moleculeEndpoint_update, executeUpdate),
    mkServerEndpoint_IO(moleculeEndpoint_delete, executeDelete),
  )
}