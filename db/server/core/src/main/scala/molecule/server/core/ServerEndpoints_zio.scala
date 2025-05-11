package molecule.server.core

import java.nio.ByteBuffer
import boopickle.Default.*
import molecule.db.core.marshalling.Boopicklers.*
import molecule.db.base.error.*
import molecule.db.core.marshalling.MoleculeRpc
import sttp.tapir.*
import sttp.tapir.server.ServerEndpoint
import zio.{Task, ZIO}
import scala.concurrent.Future

abstract class ServerEndpoints_zio(rpc: MoleculeRpc) extends Execution(rpc) {

  private val msg = "RPC failed on server: "

  def mkServerEndpoint_ZIO(
    endpoint: PublicEndpoint[ByteBuffer, MoleculeError, ByteBuffer, Any],
    executeAction: ByteBuffer => Future[Either[MoleculeError, ByteBuffer]]
  ): ServerEndpoint[Any, Task] = {
    endpoint.serverLogic { args =>
      ZIO
        .fromFuture(_ => executeAction(args))
        .catchAll {
          case e: ModelError =>
            ZIO.succeed(Left(e.copy(message = msg + e.message)))

          case e: ValidationErrors =>
            ZIO.succeed(Left(e))

          case e: InsertErrors =>
            ZIO.succeed(Left(e.copy(message = Some(msg + e.msg))))

          case e: ExecutionError =>
            ZIO.succeed(Left(e.copy(message = msg + e.message)))

          case e: Throwable =>
            ZIO.succeed {
              logger.error(e.toString)
              logger.error(e.getStackTrace.mkString("\n"))
              Left(ExecutionError(msg + e.toString))
            }
        }
    }
  }

  val moleculeServerEndpoints_ZIO: List[ServerEndpoint[Any, Task]] = List(
    mkServerEndpoint_ZIO(moleculeEndpoint_query, executeQuery),
    mkServerEndpoint_ZIO(moleculeEndpoint_queryOffset, executeQueryOffset),
    mkServerEndpoint_ZIO(moleculeEndpoint_queryCursor, executeQueryCursor),
    mkServerEndpoint_ZIO(moleculeEndpoint_unsubscribe, executeUnsubscribe),
    mkServerEndpoint_ZIO(moleculeEndpoint_save, executeSave),
    mkServerEndpoint_ZIO(moleculeEndpoint_insert, executeInsert),
    mkServerEndpoint_ZIO(moleculeEndpoint_update, executeUpdate),
    mkServerEndpoint_ZIO(moleculeEndpoint_delete, executeDelete),
  )
}