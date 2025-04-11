package molecule.adhoc

import java.nio.ByteBuffer
import boopickle.Default._
import molecule.adhoc.domains.MoleculeEndpoints
import molecule.base.error._
import molecule.core.util.Executor._
import molecule.core.util.{MoleculeLogging, SerializationUtils}
import sttp.tapir._
import sttp.tapir.server.ServerEndpoint
import scala.concurrent.Future


trait MoleculeServerEndpoints extends MoleculeEndpoints with MoleculeLogging with SerializationUtils {

  private def mkServerEndpoint(
    endpoint: PublicEndpoint[ByteBuffer, MoleculeError, ByteBuffer, Any],
    execute: ByteBuffer => ByteBuffer
  ): ServerEndpoint[Any, Future] = {
    endpoint.serverLogic { args =>
      Future {
        try {
          Right(execute(args))
        } catch {
          case e: MoleculeError =>
            // User errors
            Left(e)
          case e: Throwable     =>
            // Db/pickling errors
            Left(ExecutionError(e.getMessage))
        }
      }
    }
  }


  val moleculeServerEndpoint_Query: ServerEndpoint[Any, Future] =
    mkServerEndpoint(
      moleculeEndpoint_Query,
      (args: ByteBuffer) => {
        val name     = Unpickle[String].fromBytes(args)
        val response = Domain.query(name) // RpcHandlers.handleQuery...
        Pickle.intoBytes(response)
      }
    )

  //  val moleculeServerEndpoint_QueryOffset: ServerEndpoint[Any, Future] = ???
  //  val moleculeServerEndpoint_QueryCursor: ServerEndpoint[Any, Future] = ???
  //  val moleculeServerEndpoint_QueryStream: ServerEndpoint[Any, Future] = ???
  //  val moleculeServerEndpoint_Save       : ServerEndpoint[Any, Future] = ???
  //  val moleculeServerEndpoint_Insert     : ServerEndpoint[Any, Future] = ???
  //  val moleculeServerEndpoint_Update     : ServerEndpoint[Any, Future] = ???
  //  val moleculeServerEndpoint_Delete     : ServerEndpoint[Any, Future] = ???

  val moleculeServerEndpoints_async: List[ServerEndpoint[Any, Future]] = List(
    moleculeServerEndpoint_Query,
    //    moleculeServerEndpoint_QueryOffset,
    //    moleculeServerEndpoint_QueryCursor,
    //    moleculeServerEndpoint_QueryStream,
    //    moleculeServerEndpoint_Save,
    //    moleculeServerEndpoint_Insert,
    //    moleculeServerEndpoint_Update,
    //    moleculeServerEndpoint_Delete,
  )
}