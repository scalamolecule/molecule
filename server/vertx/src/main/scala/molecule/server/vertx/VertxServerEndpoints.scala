package molecule.server.vertx

import boopickle.Default.*
import molecule.core.marshalling.Boopicklers.*
import molecule.core.marshalling.MoleculeRpc
import molecule.server.core.ServerEndpoints_async


abstract class VertxServerEndpoints(rpc: MoleculeRpc) extends ServerEndpoints_async(rpc) {
//
//  def moleculeWebsocketHandler_PekkoFlow(implicit mat: Materializer): Flow[Message, Message, NotUsed] = {
//    val (queue, source): (SourceQueueWithComplete[Message], Source[Message, NotUsed]) =
//      Source.queue[Message](bufferSize = 2, OverflowStrategy.backpressure).preMaterialize()
//
//    val sink: Sink[Message, NotUsed] =
//      Sink.foreach[Message] {
//        case BinaryMessage.Strict(argsSerialized) =>
//          // Deserialize callback query coordinates
//          val (proxy, elements, limit) =
//            Unpickle[(ConnProxy, List[Element], Option[Int])]
//              .fromBytes(argsSerialized.asByteBuffer)
//
//          // Send query results if match
//          val callback: List[Any] => Unit = { (result: List[Any]) =>
//            val bytes = PickleTpls(elements, false).pickleEither(Right(result))
//            queue.offer(BinaryMessage(ByteString(bytes)))
//          }
//          rpc.subscribe[Any](proxy, elements, limit, callback)
//
//        case _ =>
//          val errorMsg = BinaryMessage(ByteString(
//            Pickle.intoBytes[Either[ModelError, Int]](
//              Left(ModelError("Expected BinaryMessage.Strict from websocket queue"))
//            )
//          ))
//          queue.offer(errorMsg)
//      }.mapMaterializedValue(_ => NotUsed)
//
//    Flow.fromSinkAndSource(sink, source)
//  }

}