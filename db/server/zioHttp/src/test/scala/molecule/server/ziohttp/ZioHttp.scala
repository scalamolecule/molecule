package molecule.server.ziohttp

import java.io.IOException
import boopickle.Default.*
import molecule.db.core.marshalling.Boopicklers.*
import molecule.db.core.marshalling.MoleculeRpc
import sttp.tapir.server.ziohttp.ZioHttpInterpreter
import zio.http.Server
import zio.{ExitCode, ZIO, *}

case class ZioHttp(rpc: MoleculeRpc) extends ZiohttpServerEndpoints(rpc) {

  private val app = ZioHttpInterpreter().toHttp(moleculeServerEndpoints)

  def run(db: String): ZIO[Any, IOException, ExitCode] = {
    for {
      fiber <- Server.serve(app)
        .provide(Server.defaultWithPort(8080))
        .fork // Run server in background
      _ <- Console.printLine(s"\n✅ ZioHttp server running on http://localhost:8080 for $db")
      _ <- Console.printLine("   Press ENTER to stop the server...")
      _ <- Console.readLine
      _ <- Console.printLine("🛑 Shutting down server...")
      _ <- fiber.interrupt // Gracefully shutdown the server
    } yield ExitCode.success
  }
}