package molecule.server.pekko

import boopickle.Default.*
import molecule.base.error.*
import molecule.core.ast.DataModel.Element
import molecule.core.marshalling.Boopicklers.*
import molecule.core.marshalling.serialize.PickleTpls
import molecule.core.marshalling.{ConnProxy, MoleculeRpc}
import molecule.server.core.ServerEndpoints_async
import org.apache.pekko.NotUsed
import org.apache.pekko.http.scaladsl.model.ws.{BinaryMessage, Message}
import org.apache.pekko.stream.scaladsl.{Flow, Sink, Source, SourceQueueWithComplete}
import org.apache.pekko.stream.{Materializer, OverflowStrategy}
import org.apache.pekko.util.ByteString


abstract class PekkoServerEndpoints(rpc: MoleculeRpc) extends ServerEndpoints_async(rpc) {

  def moleculeWebsocketHandler_MessageFlow(implicit mat: Materializer): Flow[Message, Message, NotUsed] = {
    val (queue, source): (SourceQueueWithComplete[Message], Source[Message, NotUsed]) =
      Source.queue[Message](bufferSize = 2, OverflowStrategy.backpressure).preMaterialize()

    val sink: Sink[Message, NotUsed] =
      Sink.foreach[Message] {
        case BinaryMessage.Strict(argsSerialized) =>
          // Deserialize callback query coordinates
          val (proxy, elements, limit) =
            Unpickle[(ConnProxy, List[Element], Option[Int])]
              .fromBytes(argsSerialized.asByteBuffer)

          // Set up callback to serialize and emit results
          val callback: List[Any] => Unit = { (result: List[Any]) =>
            val outBytes = PickleTpls(elements, false).pickleEither(Right(result))
            queue.offer(BinaryMessage(ByteString(outBytes)))
          }
          rpc.subscribe[Any](proxy, elements, limit, callback)

        case _ =>
          val errorMsg = BinaryMessage(ByteString(
            Pickle.intoBytes[Either[ModelError, Int]](
              Left(ModelError("Expected BinaryMessage.Strict from websocket queue"))
            )
          ))
          queue.offer(errorMsg)
      }.mapMaterializedValue(_ => NotUsed)

    Flow.fromSinkAndSource(sink, source)
  }
}