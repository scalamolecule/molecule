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
  ): Future[Array[Byte]] = try {
    val path       = pathStr.split("/").toList
    val args       = Unpickle.apply[ByteBuffer].fromBytes(argsData)
    val callResult = router(Request[ByteBuffer](path, args))
    callResult match {
      case Right(byteBufferResultFuture) =>
        byteBufferResultFuture
          .map(_.array())
          .recover { e =>
            println("---- MoleculeRpcResponse, unexpected ajax response:\n" + msg(path, e))
            throw e
          }

      case Left(error) =>
        println(s"##### MoleculeRpcResponse, server failure:\n" + error)
        error match {
          case PathNotFound(path: List[String]) =>
            Future.failed(new RuntimeException(s"PathNotFound($path)"))

          case HandlerError(exc: Throwable) =>
            printStackTrace(exc)
            Future.failed(new RuntimeException(s"HandlerError(${msg(path, exc)})"))

          case DeserializerError(exc: Throwable) =>
            printStackTrace(exc)
            Future.failed(new RuntimeException(s"DeserializerError(${msg(path, exc)})"))
        }
    }
  } catch {
    case e: Throwable =>
      println(
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

  private def printStackTrace(exc: Throwable) = {
    println(exc.getStackTrace.mkString("\n"))
  }
}
