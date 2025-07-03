package molecule.server.play

import java.nio.ByteBuffer
import boopickle.Default.*
import molecule.base.error.ModelError
import molecule.core.dataModel.DataModel
import molecule.db.core.marshalling.Boopicklers.*
import molecule.db.core.marshalling.serialize.PickleTpls
import molecule.db.core.marshalling.{ConnProxy, MoleculeRpc}
import molecule.server.pekko.PekkoServerEndpoints
import org.apache.pekko.NotUsed
import org.apache.pekko.stream.scaladsl.{Flow, Sink, Source, SourceQueueWithComplete}
import org.apache.pekko.stream.{Materializer, OverflowStrategy}
import sttp.capabilities.WebSockets
import sttp.capabilities.pekko.PekkoStreams
import sttp.tapir.*
import sttp.tapir.server.ServerEndpoint
import scala.concurrent.Future

abstract class PlayServerEndpoints(rpc: MoleculeRpc) extends PekkoServerEndpoints(rpc) {

  def moleculeWebsocketHandler_ByteFlow(implicit mat: Materializer): Flow[Array[Byte], Array[Byte], NotUsed] = {
    val (queue, source): (SourceQueueWithComplete[Array[Byte]], Source[Array[Byte], NotUsed]) =
      Source.queue[Array[Byte]](bufferSize = 2, OverflowStrategy.backpressure).preMaterialize()

    val sink: Sink[Array[Byte], NotUsed] = Sink.foreach[Array[Byte]] { inBytes =>
      try {
        // Deserialize callback query coordinates
        val (proxy, dataModel, limit) =
          Unpickle[(ConnProxy, DataModel, Option[Int])].fromBytes(ByteBuffer.wrap(inBytes))

        // Set up callback to serialize and emit results
        val callback: List[Any] => Unit = { result =>
          val outBytes = PickleTpls(dataModel, false).pickleEither2ByteArray(Right(result))
          queue.offer(outBytes)
        }

        rpc.subscribe[Any](proxy, dataModel, limit, callback)
      } catch {
        case ex: Throwable =>
          val errorBytes = Pickle.intoBytes[Either[ModelError, Int]](
            Left(ModelError(s"WebSocket deserialization error: ${ex.getMessage}"))
          ).array()
          queue.offer(errorBytes)
      }
    }.mapMaterializedValue(_ => NotUsed)

    Flow.fromSinkAndSource(sink, source)
  }

  def moleculeServerEndpoint_subscribe(implicit mat: Materializer): ServerEndpoint[PekkoStreams & WebSockets, Future] =
    endpoint
      .in("molecule" / "subscribe")
      .out(
        webSocketBody[Array[Byte], CodecFormat.OctetStream, Array[Byte], CodecFormat.OctetStream](PekkoStreams)
      )
      .serverLogicSuccess(_ => Future.successful(moleculeWebsocketHandler_ByteFlow))

  def moleculeServerEndpoints(implicit mat: Materializer): List[ServerEndpoint[PekkoStreams & WebSockets, Future]] =
    moleculeServerEndpoints_Future :+ moleculeServerEndpoint_subscribe

}