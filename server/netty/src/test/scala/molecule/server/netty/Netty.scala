package molecule.server.netty

import scala.io.StdIn
import cats.effect.*
import cats.effect.kernel.Resource
import molecule.db.common.marshalling.MoleculeRpc
import sttp.tapir.server.netty.cats.NettyCatsServer

case class Netty(rpc: MoleculeRpc) extends NettyServerEndpoints(rpc) {

  def run(db: String): Resource[IO, Unit] = {
    for {
      server <- NettyCatsServer.io()
      _ <- Resource.make(
        server
          .host("localhost")
          .port(8080)
          .addEndpoints(moleculeServerEndpoints)
          .start()
      )(_.stop())

      _ <- Resource.eval {
        for {
          _ <- IO.println(s"✅ Netty server running on http://localhost:8080 for $db")
          _ <- IO.println(s"   Press ENTER to stop the server.")
          _ <- IO.blocking(StdIn.readLine())
          _ <- IO.println("🛑 Shutting down server...")
          _ <- IO(System.exit(0))
        } yield ()
      }
    } yield ()
  }
}