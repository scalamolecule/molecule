package molecule.server.http4s

import java.nio.ByteBuffer
import boopickle.Default.*
import cats.effect.IO
import cats.effect.std.Queue
import cats.effect.unsafe.IORuntime
import fs2.Pipe
import molecule.core.dataModel.DataModel
import molecule.db.core.marshalling.Boopicklers.*
import molecule.db.core.marshalling.serialize.PickleTpls
import molecule.db.core.marshalling.{ConnProxy, MoleculeRpc}
import molecule.server.core.ServerEndpoints_io
import sttp.capabilities.WebSockets
import sttp.capabilities.fs2.Fs2Streams
import sttp.tapir.*
import sttp.tapir.server.ServerEndpoint

abstract class Http4sServerEndpoints(rpc: MoleculeRpc) extends ServerEndpoints_io(rpc) {

  def moleculeWebsocketHandler_fs2Pipe(implicit runtime: IORuntime): Pipe[IO, Array[Byte], Array[Byte]] = { in =>
    fs2.Stream.eval {
      for {
        outgoingQueue <- Queue.unbounded[IO, Array[Byte]]
        incoming = in.evalMap { msg =>
          // Deserialize callback query coordinates
          val (proxy, dataModel, limit) =
            Unpickle[(ConnProxy, DataModel, Option[Int])].fromBytes(ByteBuffer.wrap(msg))

          // Set up callback to serialize and emit results
          val callback: List[Any] => Unit = { result =>
            val outBytes = PickleTpls(dataModel, false).pickleEither2ByteArray(Right(result))
            outgoingQueue.offer(outBytes).void.unsafeRunAndForget()
          }

          IO(rpc.subscribe[Any](proxy, dataModel, limit, callback))
        }.handleErrorWith(_ => fs2.Stream.empty) // ignore "Reached End Of Stream"

        outgoing = fs2.Stream.fromQueueUnterminated(outgoingQueue)
      } yield outgoing.concurrently(incoming)
    }.flatten
  }

  val moleculeServerEndpoint_subscribe: ServerEndpoint[Fs2Streams[IO] & WebSockets, IO] = {
    endpoint
      .out(
        webSocketBody[Array[Byte], CodecFormat.OctetStream, Array[Byte], CodecFormat.OctetStream](Fs2Streams[IO])
      )
      .serverLogicSuccess(_ => IO.pure(
        moleculeWebsocketHandler_fs2Pipe(using cats.effect.unsafe.implicits.global)
      ))
  }
}