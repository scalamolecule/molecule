package molecule.server.ziohttp

import java.nio.ByteBuffer
import boopickle.Default.*
import molecule.core.dataModel.DataModel
import molecule.db.common.marshalling.Boopicklers.*
import molecule.db.common.marshalling.serialize.PickleTpls
import molecule.db.common.marshalling.{ConnProxy, MoleculeRpc}
import molecule.server.endpoints.ServerEndpoints_zio
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
            val (proxy, dataModel, limit) =
              Unpickle[(ConnProxy, DataModel, Option[Int])].fromBytes(ByteBuffer.wrap(msg))

            val callback: List[Any] => Unit = { result =>
              val outBytes = PickleTpls(dataModel, false).pickleEither2ByteArray(Right(result))
              Unsafe.unsafe { implicit u =>
                Runtime.default.unsafe.run(outgoingQueue.offer(outBytes)).getOrThrowFiberFailure()
              }
            }

            rpc.subscribe[Any](proxy, dataModel, limit, callback)
          }
          .catchAll(e => ZIO.logWarning(s"Deserialization failed: ${e.getMessage}"))
      }.drain

      val outgoing: ZStream[Any, Throwable, Array[Byte]] = ZStream.fromQueue(outgoingQueue)
      outgoing.merge(incoming)
    }
  }

  val moleculeServerEndpoint_subscribe: ServerEndpoint[ZioStreams & WebSockets, Task] =
    endpoint
      .in("molecule" / "subscribe")
      .out(webSocketBody[Array[Byte], CodecFormat.OctetStream, Array[Byte], CodecFormat.OctetStream](ZioStreams))
      .serverLogicSuccess(_ => ZIO.succeed(moleculeWebsocketHandler_zioPipe))

  val moleculeServerEndpoints: List[ServerEndpoint[ZioStreams & WebSockets, Task]] =
    moleculeServerEndpoints_ZIO :+ moleculeServerEndpoint_subscribe
}