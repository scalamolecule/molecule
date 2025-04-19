package molecule.server

import boopickle.Default.*
import molecule.core.marshalling.{MoleculeRpc, MoleculeServerEndpoints}
import org.apache.pekko.actor.ActorSystem
import org.apache.pekko.http.scaladsl.Http
import org.apache.pekko.http.scaladsl.server.Route
import sttp.tapir.server.pekkohttp.PekkoHttpServerInterpreter
import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.io.StdIn

case class Pekko(rpc: MoleculeRpc) extends MoleculeServerEndpoints(rpc) {

  def run(db: String): Unit = {

    // Set up Pekko environment
    implicit val system: ActorSystem              = ActorSystem("molecule-server")
    implicit val ec    : ExecutionContextExecutor = system.dispatcher

    // Convert Tapir endpoints to Pekko HTTP route
    val route: Route = PekkoHttpServerInterpreter().toRoute(moleculeServerEndpoints)

    // Start Pekko HTTP server
    val bindingFuture = Http().newServerAt("localhost", 8080).bind(route)

    println(s"\n✅ Pekko Http server running on http://localhost:8080 for $db")
    println("   Press ENTER to stop the server.")
    StdIn.readLine()

    println("🛑 Shutting down server...")
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }
}