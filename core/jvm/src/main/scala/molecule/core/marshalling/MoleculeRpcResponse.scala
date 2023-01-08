package molecule.core.marshalling

import java.nio.ByteBuffer
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import boopickle.Default._
import molecule.boilerplate.util.MoleculeLogging
import sloth.ServerFailure.{DeserializerError, HandlerError, PathNotFound}
import sloth._
import scala.concurrent.{ExecutionContextExecutor, Future}

case class MoleculeRpcResponse(interface: String, port: Int) extends MoleculeLogging {
  implicit val system          : ActorSystem[Nothing]     = ActorSystem(Behaviors.empty, "MoleculeAjaxSystem")
  implicit val executionContext: ExecutionContextExecutor = system.executionContext
  val MoleculeRpc = "MoleculeRpc"

  def moleculeRpcResponse(
    router: Router[ByteBuffer, Future],
    pathStr: String,
    argsData: ByteBuffer
  ): Future[Array[Byte]] = try {
    val path       = pathStr.split("/").toList
    val args       = Unpickle.apply[ByteBuffer].fromBytes(argsData)
    val callResult = router(Request[ByteBuffer](path, args))
    callResult match {
      case Right(byteBufferResultFuture) =>
        byteBufferResultFuture
          .map(_.array())
          .recover {
            case e: Throwable =>
              logger.warn("---- MoleculeRpcResponse, unexpected ajax response:\n" + msg(path, e))
              throw e
          }

      case Left(error) =>
        logger.error(s"##### MoleculeRpcResponse, server failure:\n" + error)
        error match {
          case PathNotFound(path: List[String]) =>
            Future.failed(new RuntimeException(s"PathNotFound($path)"))

          case HandlerError(exc: Throwable) =>
            logger.error(exc.getStackTrace.mkString("\n"))
            Future.failed(new RuntimeException(s"HandlerError(${msg(path, exc)})"))

          case DeserializerError(exc: Throwable) =>
            logger.error(exc.getStackTrace.mkString("\n"))
            Future.failed(new RuntimeException(s"DeserializerError(${msg(path, exc)})"))
        }
    }
  } catch {
    case e: Throwable =>
      logger.error(
        s"""##### Unexpected pickle/router failure:
           |$e
           |--
           |${e.getStackTrace.mkString("\n")}""".stripMargin
      )
      Future.failed(e)
  }

  private def msg(path: List[String], exc: Throwable): String = {
    s"""path: ${path.mkString("/")}
       |$exc
       |--
       |${exc.getStackTrace.mkString("\n")}""".stripMargin
  }
}
