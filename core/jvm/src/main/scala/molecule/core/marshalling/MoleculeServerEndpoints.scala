package molecule.core.marshalling

import java.nio.ByteBuffer
import boopickle.Default._
import molecule.base.error._
import molecule.core.util.Executor._
import molecule.core.util.{MoleculeLogging, SerializationUtils}
import sttp.tapir._
import sttp.tapir.server.ServerEndpoint
import scala.concurrent.Future


abstract class MoleculeServerEndpoints(rpc: MoleculeRpc)
  extends RpcHandlers(rpc)
    with MoleculeEndpoints
    with MoleculeLogging {

  private val msg = "RPC call failed on server: "

  private def mkServerEndpoint(
    endpoint: PublicEndpoint[ByteBuffer, MoleculeError, ByteBuffer, Any],
    execute: ByteBuffer => Future[Either[MoleculeError, ByteBuffer]]
  ): ServerEndpoint[Any, Future] = {
    endpoint.serverLogic { args =>
      execute(args)
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


  private val moleculeServerEndpoint_Query       = mkServerEndpoint(moleculeEndpoint_Query, handleQuery)
  private val moleculeServerEndpoint_QueryOffset = mkServerEndpoint(moleculeEndpoint_QueryOffset, handleQueryOffset)
  private val moleculeServerEndpoint_QueryCursor = mkServerEndpoint(moleculeEndpoint_QueryCursor, handleQueryCursor)
  private val moleculeServerEndpoint_QueryStream = mkServerEndpoint(moleculeEndpoint_QueryStream, handleQueryStream)
  private val moleculeServerEndpoint_Save        = mkServerEndpoint(moleculeEndpoint_Save, handleSave)
  private val moleculeServerEndpoint_Insert      = mkServerEndpoint(moleculeEndpoint_Insert, handleInsert)
  private val moleculeServerEndpoint_Update      = mkServerEndpoint(moleculeEndpoint_Update, handleUpdate)
  private val moleculeServerEndpoint_Delete      = mkServerEndpoint(moleculeEndpoint_Delete, handleDelete)

  val moleculeServerEndpoints: List[ServerEndpoint[Any, Future]] = List(
    moleculeServerEndpoint_Query,
    moleculeServerEndpoint_Save,
    moleculeServerEndpoint_QueryOffset,
    moleculeServerEndpoint_QueryCursor,
    moleculeServerEndpoint_QueryStream,
    moleculeServerEndpoint_Insert,
    moleculeServerEndpoint_Update,
    moleculeServerEndpoint_Delete,
  )
}