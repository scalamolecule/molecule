package molecule.server.netty

import boopickle.Default.*
import cats.effect.IO
import molecule.core.marshalling.Boopicklers.*
import molecule.core.marshalling.MoleculeRpc
import molecule.server.http4s.Http4sServerEndpoints
import sttp.capabilities.WebSockets
import sttp.capabilities.fs2.Fs2Streams
import sttp.tapir.server.ServerEndpoint

abstract class NettyServerEndpoints(rpc: MoleculeRpc) extends Http4sServerEndpoints(rpc) {

  val moleculeServerEndpoints: List[ServerEndpoint[Fs2Streams[IO] with WebSockets, IO]] = {
    // Need to upcast for NettyCastServer to understand correct binary websocket handling
    moleculeServerEndpoints_IO
      .map(_.asInstanceOf[ServerEndpoint[Fs2Streams[IO] with WebSockets, IO]]) :+
      moleculeServerEndpoint_subscribe
  }
}