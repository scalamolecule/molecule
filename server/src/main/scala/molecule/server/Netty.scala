package molecule.server

import boopickle.Default.*
import molecule.core.marshalling.{MoleculeRpc, MoleculeServerEndpoints}
import molecule.core.util.Executor.*
import sttp.tapir.server.netty.*
import scala.concurrent.Future
import scala.io.StdIn

case class Netty(rpc: MoleculeRpc) extends MoleculeServerEndpoints(rpc) {

  def run(db: String): Future[Unit] = {
    val server = NettyFutureServer()
      .port(8080)
      .addEndpoints(moleculeServerEndpoints)
      .start()

    server.map { binding =>
      println(s"\n✅ Netty server running on http://localhost:8080 for $db")
      println("   Press ENTER to stop the server.")

      StdIn.readLine() // block until user input
      println("🛑 Shutting down server...")
      binding.stop()
    }
  }
}