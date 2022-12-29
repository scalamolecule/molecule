package molecule.db.datomic.marshalling

import java.nio.ByteBuffer
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import boopickle.Default._
import cats.implicits._
import ch.megard.akka.http.cors.scaladsl.CorsDirectives._
import molecule.core.marshalling.{BooPicklers, MoleculeRpc, MoleculeRpcResponse}
import sloth.ServerFailure._
import sloth._
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt
import scala.util.{Failure, Left, Success}

/** Akka Http RPC server responding to molecule ajax requests */
object MoleculeRpcServer
  extends MoleculeRpcResponse("localhost", 8080)
    with App with BooPicklers {

  lazy val router = Router[ByteBuffer, Future].route[MoleculeRpc](DatomicRpcImpl)

  Http()
    .newServerAt(interface, port)
    .bind(route)
    .map(_.addToCoordinatedShutdown(hardTerminationDeadline = 120.seconds))
    .onComplete {
      case Success(b) => println(s"Ajax server is running ${b.localAddress} ")
      case Failure(e) => println(s"there was an error starting the server $e")
    }

  lazy val route: Route = cors() {
    // Remaining is the method name
    path("ajax" / MoleculeRpc / Remaining)(respond)
  }


  val respond: String => Route = (method: String) => {
    val pathStr = s"$MoleculeRpc/$method"
    post {
      extractRequest { req =>
        req.entity match {
          case HttpEntity.Strict(_, argsData) =>
            moleculeRpcResponse(router, pathStr, argsData.asByteBuffer) match {
              case Left(HandlerError(ex))      => failWith(ex)
              case Left(DeserializerError(ex)) => failWith(ex)
              case Left(x: PathNotFound)       => failWith(x.toException)
              case Right(result)               => complete(result.map(_.array()))
            }

          case HttpEntity.Default(_, _, chunks) =>
            complete(
              chunks.reduce(_ ++ _).runFoldAsync(Array.empty[Byte]) {
                case (_, argsData) =>
                  moleculeRpcResponse(router, pathStr, argsData.asByteBuffer) match {
                    case Left(HandlerError(ex))      => Future.failed(ex)
                    case Left(DeserializerError(ex)) => Future.failed(ex)
                    case Left(x: PathNotFound)       => Future.failed(x.toException)
                    case Right(result)               => result.map(_.array())
                  }
              }
            )

          case other => complete("Unexpected HttpEntity in MoleculeRpcServer: " + other)
        }
      }
    }
  }
}
