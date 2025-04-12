package molecule.server

import boopickle.Default._
import molecule.core.marshalling.MoleculeServerEndpoints
import molecule.sql.h2.marshalling.Rpc_h2
import org.apache.pekko.actor.ActorSystem
import play.api.Mode
import play.core.server._
import sttp.tapir.server.play.PlayServerInterpreter
import scala.io.StdIn

object Play extends MoleculeServerEndpoints(Rpc_h2) {

  // Start the server
  def main(args: Array[String]): Unit = {
    implicit val actorSystem: ActorSystem = ActorSystem("tapir-play-server")

    //    val config = ServerConfig()
    val config = ServerConfig(
      port = Some(8080),
      mode = Mode.Dev
    )
    val routes = PlayServerInterpreter().toRoutes(moleculeServerEndpoints)

    // 3. Start the server (Netty backend)
    val server = PekkoHttpServer.fromRouterWithComponents(config)(_ => routes)

    println("✅ PlayServerInterpreter/PekkoHttpServer running on http://localhost:8080")

    // 4. Block for shutdown
    StdIn.readLine()

    println("🛑 Shutting down server...")
    server.stop()
    actorSystem.terminate()
  }
}