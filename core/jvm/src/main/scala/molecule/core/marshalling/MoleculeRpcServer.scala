package molecule.core.marshalling

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.util.ByteString
import boopickle.Default._
import ch.megard.akka.http.cors.scaladsl.CorsDirectives._
import molecule.core.marshalling.Boopicklers._
import scala.concurrent.duration.DurationInt
import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.language.implicitConversions
import scala.util.{Failure, Success}


/** Akka Http RPC server responding to molecule ajax requests
 *
 * Start this server in terminal with
 *
 * sbt datomicJVM/run
 * */
abstract class MoleculeRpcServer(rpc: MoleculeRpc) extends RpcHandlers(rpc) {
  implicit val system          : ActorSystem              = ActorSystem()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher


  val MoleculeRpc = "MoleculeRpc"

  Http()
    .newServerAt("localhost", 8080)
    .bind(route)
    .map(_.addToCoordinatedShutdown(hardTerminationDeadline = 120.seconds))
    .onComplete {
      case Success(b) => println(s"Akka http server is running ${b.localAddress} ")
      case Failure(e) => println(s"there was an error starting the server $e")
    }

  lazy val route: Route = cors() {
    path(MoleculeRpc / "query")(toRoute(handleQuery)) ~
      path(MoleculeRpc / "queryOffset")(toRoute(handleQueryOffset)) ~
      path(MoleculeRpc / "queryCursor")(toRoute(handleQueryCursor)) ~
      path(MoleculeRpc / "save")(toRoute(handleSave)) ~
      path(MoleculeRpc / "insert")(toRoute(handleInsert)) ~
      path(MoleculeRpc / "update")(toRoute(handleUpdate)) ~
      path(MoleculeRpc / "delete")(toRoute(handleDelete))
  }


  def toRoute(handler: ByteString => Future[Array[Byte]]): Route = {
    post {
      extractRequest { req =>
        req.entity match {
          case HttpEntity.Strict(_, argsSerialized) => complete(handler(argsSerialized))
          case HttpEntity.Default(_, _, chunks)     => complete(
            chunks.reduce(_ ++ _).runFoldAsync(Array.empty[Byte]) {
              case (_, argsSerialized) => handler(argsSerialized)
            }
          )

          case other => complete("Unexpected HttpEntity in MoleculeRpcServer: " + other)
        }
      }
    }
  }

  // This creates problems with `sbt -client` --> project datomicJS not loading..
  // (don't know if this variation is better, need to consult some Akka Http expert)
  //  val bindingFuture = Http().newServerAt(interface, port).bind(route)
  //  println(s"Server online at http://localhost:8088/\nPress RETURN to stop...")
  //  scala.io.StdIn.readLine()
  //  bindingFuture
  //    .flatMap(_.unbind())
  //    .onComplete(_ => system.terminate())
}
