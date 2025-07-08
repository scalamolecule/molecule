package molecule.server.http4s

import cats.effect.{ExitCode, IO}
import com.comcast.ip4s.{ip, port}
import molecule.db.common.marshalling.MoleculeRpc
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.implicits.*
import org.http4s.server.Router
import sttp.tapir.server.http4s.Http4sServerInterpreter

case class Http4s(rpc: MoleculeRpc) extends Http4sServerEndpoints(rpc) {

  def run(db: String): IO[ExitCode] = {
    EmberServerBuilder
      .default[IO]
      .withHost(ip"0.0.0.0")
      .withPort(port"8080")
      .withHttpWebSocketApp(wsb =>
        Router(
          "molecule/subscribe" ->
            Http4sServerInterpreter[IO]().toWebSocketRoutes(moleculeServerEndpoint_subscribe)(wsb),
          "/" ->
            Http4sServerInterpreter[IO]().toRoutes(moleculeServerEndpoints_IO)
        ).orNotFound
      )
      .build
      .use { _ =>
        for {
          _ <- IO.println(s"\n✅ Ember Http4s server running on http://localhost:8080 for $db")
          _ <- IO.println("   Press ENTER to stop the server.")

          _ <- IO.readLine
          _ <- IO.println("🛑 Shutting down server...")
        } yield ExitCode.Success
      }
  }
}