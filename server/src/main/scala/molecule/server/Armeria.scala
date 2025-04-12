package molecule.server

import com.linecorp.armeria.server.Server
import molecule.core.marshalling.MoleculeServerEndpoints
import molecule.sql.h2.marshalling.Rpc_h2
import sttp.tapir.server.armeria.ArmeriaFutureServerInterpreter

object Armeria extends MoleculeServerEndpoints(Rpc_h2) {

  def main(args: Array[String]): Unit = {

    // Create Armeria HTTP service from Tapir endpoints
    val tapirService = ArmeriaFutureServerInterpreter()
      .toService(moleculeServerEndpoints)

    // Build and start the Armeria server
    val server = Server.builder()
      .http(8080)
      .serviceUnder("/", tapirService) // Serve all endpoints under "/"
      .build()

    server.start().join()
    println("✅ Armeria server running on http://localhost:8080")
    println("Press ENTER to stop the server.")
    scala.io.StdIn.readLine()

    println("🛑 Shutting down server...")
    server.stop().join()
  }
}