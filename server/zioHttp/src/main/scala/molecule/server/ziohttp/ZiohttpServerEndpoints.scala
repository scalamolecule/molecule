package molecule.server.ziohttp

import java.nio.ByteBuffer
import boopickle.Default.*
import molecule.core.ast.DataModel.Element
import molecule.core.marshalling.Boopicklers.*
import molecule.core.marshalling.serialize.PickleTpls
import molecule.core.marshalling.{ConnProxy, MoleculeRpc}
import molecule.server.core.ServerEndpoints_zio
import sttp.capabilities.WebSockets
import sttp.capabilities.zio.ZioStreams
import sttp.tapir.CodecFormat
import sttp.tapir.server.ServerEndpoint
import sttp.tapir.ztapir.*
import zio.stream.ZStream
import zio.{Queue, Runtime, Task, Unsafe, ZIO}


abstract class ZiohttpServerEndpoints(rpc: MoleculeRpc) extends ServerEndpoints_zio(rpc) {

  def moleculeWebsocketHandler_zioPipe: ZStream[Any, Throwable, Array[Byte]] => ZStream[Any, Throwable, Array[Byte]] = { in =>
    ZStream.fromZIO(Queue.unbounded[Array[Byte]]).flatMap { outgoingQueue =>
      val incoming: ZStream[Any, Throwable, Nothing] = in.mapZIO { msg =>
        ZIO
          .attempt {
            val (proxy, elements, limit) =
              Unpickle[(ConnProxy, List[Element], Option[Int])].fromBytes(ByteBuffer.wrap(msg))

            val callback: List[Any] => Unit = { result =>
              val outBytes = PickleTpls(elements, false).pickleEither2ByteArray(Right(result))
              Unsafe.unsafe { implicit u =>
                Runtime.default.unsafe.run(outgoingQueue.offer(outBytes)).getOrThrowFiberFailure()
              }
            }

            rpc.subscribe[Any](proxy, elements, limit, callback)
          }
          .catchAll(e => ZIO.logWarning(s"Deserialization failed: ${e.getMessage}"))
      }.drain

      val outgoing: ZStream[Any, Throwable, Array[Byte]] = ZStream.fromQueue(outgoingQueue)
      outgoing.merge(incoming)
    }
  }

  val moleculeServerEndpoint_subscribe: ServerEndpoint[ZioStreams with WebSockets, Task] =
    endpoint
      .in("molecule" / "subscribe")
      .out(webSocketBody[Array[Byte], CodecFormat.OctetStream, Array[Byte], CodecFormat.OctetStream](ZioStreams))
      .serverLogicSuccess(_ => ZIO.succeed(moleculeWebsocketHandler_zioPipe))

  val moleculeServerEndpoints: List[ServerEndpoint[ZioStreams with WebSockets, Task]] =
    moleculeServerEndpoints_ZIO :+ moleculeServerEndpoint_subscribe
}