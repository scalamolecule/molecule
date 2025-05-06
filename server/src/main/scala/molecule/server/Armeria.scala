package molecule.server

import java.util.concurrent.CompletableFuture
import com.linecorp.armeria.server.Server
import molecule.core.marshalling.{MoleculeRpc, MoleculeServerEndpoints}
import molecule.db.sql.h2.marshalling.Rpc_h2
import sttp.tapir.server.armeria.ArmeriaFutureServerInterpreter
import scala.concurrent.Future

case class Armeria(rpc: MoleculeRpc) extends MoleculeServerEndpoints(rpc) {

//  def run(db: String): CompletableFuture[Void] = {
  def run(db: String) = {

    // Create Armeria HTTP service from Tapir endpoints
    val tapirService = ArmeriaFutureServerInterpreter()
      .toService(moleculeServerEndpoints_Future)

    // Build and start the Armeria server
    val server = Server.builder()
      .http(8080)
      .serviceUnder("/", tapirService) // Serve all endpoints under "/"
      .build()

    server.start().join()
    println(s"\n✅ Armeria server running on http://localhost:8080 for $db")
    println("   Press ENTER to stop the server.")
    scala.io.StdIn.readLine()

    println("🛑 Shutting down server...")
    server.stop().join()
  }
}