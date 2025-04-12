package molecule.server

import boopickle.Default._
import molecule.core.marshalling.MoleculeServerEndpoints
import molecule.sql.h2.marshalling.Rpc_h2
import org.apache.pekko.actor.ActorSystem
import org.apache.pekko.http.scaladsl.Http
import org.apache.pekko.http.scaladsl.server.Route
import org.apache.pekko.stream.Materializer
import sttp.tapir.server.pekkohttp.PekkoHttpServerInterpreter
import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

object Pekko extends MoleculeServerEndpoints(Rpc_h2) {

  def main(args: Array[String]): Unit = {
    // Set up Pekko environment
    implicit val system: ActorSystem              = ActorSystem("molecule-server")
    implicit val ec    : ExecutionContextExecutor = system.dispatcher
    implicit val mat   : Materializer             = Materializer.matFromSystem(system) // Optional, depending on context

    // Convert Tapir endpoints to Pekko HTTP route
    val route: Route = PekkoHttpServerInterpreter().toRoute(moleculeServerEndpoints)

    // Start Pekko HTTP server
    val bindingFuture = Http().newServerAt("localhost", 8080).bind(route)

    println("✅ Pekko Http server running on http://localhost:8080")
    println("Press ENTER to stop the server.")
    StdIn.readLine()

    println("🛑 Shutting down server...")
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }
}