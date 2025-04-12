package molecule.server

import boopickle.Default._
import molecule.core.marshalling.MoleculeServerEndpoints
import molecule.core.util.Executor._
import molecule.sql.h2.marshalling.Rpc_h2
import sttp.tapir.server.netty._
import scala.io.StdIn

object Netty extends MoleculeServerEndpoints(Rpc_h2) {

  def main(args: Array[String]): Unit = {
    val server = NettyFutureServer()
      .port(8080)
      .addEndpoints(moleculeServerEndpoints)
      .start()

    server.foreach { _ =>
      println("✅ Netty server running on http://localhost:8080")
    }

    println("Press ENTER to stop the server.")
    StdIn.readLine() // Blocks until user presses ENTER

    println("🛑 Shutting down server...")
    server.flatMap(_.stop())
  }
}