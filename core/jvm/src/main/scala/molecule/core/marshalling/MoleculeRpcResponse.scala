package molecule.core.marshalling

import java.nio.ByteBuffer
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import boopickle.Default._
import sloth.ServerFailure.{DeserializerError, HandlerError, PathNotFound}
import sloth._
import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.util.control.NonFatal

case class MoleculeRpcResponse(interface: String, port: Int) {
  implicit val system          : ActorSystem[Nothing]     = ActorSystem(Behaviors.empty, "MoleculeAjaxSystem")
  implicit val executionContext: ExecutionContextExecutor = system.executionContext
  val MoleculeRpc = "MoleculeRpc"

  def moleculeRpcResponse(
    router: Router[ByteBuffer, Future],
    pathStr: String,
    argsData: ByteBuffer
  ): Future[Array[Byte]] = Future {
    try {
      val path       = pathStr.split("/").toList
      val args       = Unpickle.apply[ByteBuffer].fromBytes(argsData)
      val callResult = router(Request[ByteBuffer](path, args))
      callResult match {
        case Right(byteBufferResultFuture) =>
          byteBufferResultFuture
            .map(_.array())
            .recoverWith { exc =>
              println("---- MoleculeRpcResponse, unexpected ajax response:\n" + msg(path, args, exc))
              println(exc.getStackTrace.mkString("\n"))
              Future.failed(exc)
            }

        case Left(error) =>
          println(s"##### MoleculeRpcResponse, server failure:\n" + error)
          error match {
            case PathNotFound(path: List[String]) =>
              Future.failed(new RuntimeException(s"PathNotFound($path)"))

            case HandlerError(exc: Throwable) =>
              Future.failed(new RuntimeException(s"HandlerError(${msg(path, args, exc)})"))

            case DeserializerError(exc: Throwable) =>
              Future.failed(new RuntimeException(s"DeserializerError(${msg(path, args, exc)})"))
          }
      }
    } catch {
      case NonFatal(exc) => Future.failed(exc)
    }
  }.flatten

  private def msg(path: List[String], args: ByteBuffer, exc: Throwable): String = {
    s"""path: $path
       |args: ${args.array().toList}
       |err : ${exc.getMessage}
       |$exc""".stripMargin
  }
}
