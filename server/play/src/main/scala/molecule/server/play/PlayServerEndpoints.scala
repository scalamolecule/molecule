package molecule.server.play

import java.nio.ByteBuffer
import boopickle.Default.*
import molecule.base.error.*
import molecule.core.ast.DataModel.Element
import molecule.core.marshalling.Boopicklers.*
import molecule.core.marshalling.serialize.PickleTpls
import molecule.core.marshalling.{ConnProxy, MoleculeRpc}
import molecule.server.pekko.PekkoServerEndpoints
import org.apache.pekko.NotUsed
import org.apache.pekko.stream.scaladsl.{Flow, Sink, Source, SourceQueueWithComplete}
import org.apache.pekko.stream.{Materializer, OverflowStrategy}


abstract class PlayServerEndpoints(rpc: MoleculeRpc) extends PekkoServerEndpoints(rpc) {

  def moleculeWebsocketHandler_ByteFlow(implicit mat: Materializer): Flow[Array[Byte], Array[Byte], NotUsed] = {
    val (queue, source): (SourceQueueWithComplete[Array[Byte]], Source[Array[Byte], NotUsed]) =
      Source.queue[Array[Byte]](bufferSize = 2, OverflowStrategy.backpressure).preMaterialize()

    val sink: Sink[Array[Byte], NotUsed] = Sink.foreach[Array[Byte]] { inBytes =>
      try {
        // Deserialize callback query coordinates
        val (proxy, elements, limit) =
          Unpickle[(ConnProxy, List[Element], Option[Int])].fromBytes(ByteBuffer.wrap(inBytes))

        // Set up callback to serialize and emit results
        val callback: List[Any] => Unit = { result =>
          val outBytes = PickleTpls(elements, false).pickleEither2ByteArray(Right(result))
          queue.offer(outBytes)
        }

        rpc.subscribe[Any](proxy, elements, limit, callback)
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
}