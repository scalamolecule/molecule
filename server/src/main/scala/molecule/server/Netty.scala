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
      .addEndpoints(moleculeServerEndpoints_Future)
      .start()

    server.foreach { _ =>
      println(s"✅ Netty server running on http://localhost:8080 for $db")
    }

    println("\nPress ENTER to stop the server.")
    StdIn.readLine() // Blocks until user presses ENTER

    println("🛑 Shutting down server...")
    server.flatMap(_.stop())
  }
}