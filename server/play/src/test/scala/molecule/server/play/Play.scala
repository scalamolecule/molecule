package molecule.server.play

import boopickle.Default.*
import molecule.core.marshalling.MoleculeRpc
import org.apache.pekko.actor.{ActorSystem, Terminated}
import play.api.Mode
import play.core.server.*
import sttp.tapir.server.play.PlayServerInterpreter
import scala.concurrent.Future
import scala.io.StdIn

case class Play(rpc: MoleculeRpc) extends PlayServerEndpoints(rpc) {

  implicit val actorSystem: ActorSystem = ActorSystem("tapir-play-server")

  def run(db: String): Future[Terminated] = {
    val config = ServerConfig(port = Some(8080), mode = Mode.Dev)
    val routes = PlayServerInterpreter().toRoutes(moleculeServerEndpoints)
    val server = PekkoHttpServer.fromRouterWithComponents(config)(_ => routes)

    println(s"\n✅ Play server running on http://localhost:8080 for $db")
    println("   Press ENTER to stop the server.")
    StdIn.readLine()
    println("🛑 Shutting down server...")
    server.stop()
    actorSystem.terminate()
  }
}