package molecule.db.datomic.marshalling

import java.nio.ByteBuffer
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import boopickle.Default._
import ch.megard.akka.http.cors.scaladsl.CorsDirectives._
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling.{MoleculeRpc, MoleculeRpcResponse}
import sloth._
import scala.concurrent.Future
import scala.io.StdIn


/** Akka Http RPC server responding to molecule ajax requests */
object DatomicRpcServer extends MoleculeRpcResponse("localhost", 8080) with App {

  lazy val router = Router[ByteBuffer, Future].route[MoleculeRpc](DatomicRpcJVM)

  lazy val route: Route = cors() {
    // Remaining is the rpc method name
    path("ajax" / MoleculeRpc / Remaining)(respond)
  }

  val respond: String => Route = (method: String) => {
    val pathStr = s"$MoleculeRpc/$method"
    post {
      extractRequest { req =>
        req.entity match {
          case HttpEntity.Strict(_, argsData) =>
            complete(moleculeRpcResponse(router, pathStr, argsData.asByteBuffer))

          case HttpEntity.Default(_, _, chunks) =>
            complete(
              chunks.reduce(_ ++ _)
                .runFoldAsync(Array.empty[Byte]) {
                  case (_, argsData) =>
                    moleculeRpcResponse(router, pathStr, argsData.asByteBuffer)
                }
            )

          case other => complete("Unexpected HttpEntity in DatomicRpcServer: " + other)
        }
      }
    }
  }

  val bindingFuture = Http().newServerAt(interface, port).bind(route)
  println(s"Server online at http://localhost:8088/\nPress RETURN to stop...")
  StdIn.readLine()
  bindingFuture
    .flatMap(_.unbind())
    .onComplete(_ => system.terminate())
}
