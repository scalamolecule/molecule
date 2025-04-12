package molecule.server

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import boopickle.Default._
import molecule.core.marshalling.MoleculeServerEndpoints
import molecule.sql.h2.marshalling.Rpc_h2
import sttp.tapir.server.akkahttp.AkkaHttpServerInterpreter
import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

object Akka extends MoleculeServerEndpoints(Rpc_h2) {

  def main(args: Array[String]): Unit = {

    // Set up Akka environment
    implicit val system: ActorSystem              = ActorSystem("molecule-server")
    implicit val ec    : ExecutionContextExecutor = system.dispatcher

    // Convert Tapir endpoints to Akka HTTP route
    val route: Route = AkkaHttpServerInterpreter().toRoute(moleculeServerEndpoints)

    // Start Akka HTTP server
    val bindingFuture = Http().newServerAt("localhost", 8080).bind(route)

    bindingFuture.foreach { _ =>
      println("✅ Akka Http server running on http://localhost:8080")
    }

    println("Press ENTER to stop the server.")
    StdIn.readLine()

    println("🛑 Shutting down server...")
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }
}