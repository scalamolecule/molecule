package molecule.sql.h2.marshalling

import boopickle.Default._
import molecule.core.marshalling.MoleculeServerEndpoints
import molecule.core.util.Executor._
import sttp.tapir.server.netty._
import scala.io.StdIn

/**
 * Server app serving http requests from client
 *
 * Run with
 *
 * sbt sqlH2JVM/run
 */
object RpcServer_h2_new extends MoleculeServerEndpoints(Rpc_h2) {

  // Start the server
  def main(args: Array[String]): Unit = {
    val server = NettyFutureServer()
      .port(8080)
      .addEndpoints(moleculeServerEndpoints)
      .start()

    server.foreach { _ =>
      println("Server running on http://localhost:8080")
    }

    println("Press ENTER to stop the server.")
    StdIn.readLine() // Blocks until user presses ENTER

    println("Shutting down server...")
    server.flatMap(_.stop())
  }
}