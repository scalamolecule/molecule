//package molecule.core.marshalling
//
//import boopickle.Default.*
//import molecule.base.error.*
//import molecule.core.ast.DataModel.Element
//import molecule.core.marshalling.Boopicklers.*
//import molecule.core.marshalling.serialize.PickleTpls
//import molecule.core.util.MoleculeLogging
//import org.apache.pekko.NotUsed
//import org.apache.pekko.actor.ActorSystem
//import org.apache.pekko.http.scaladsl.model.ws.{BinaryMessage, Message}
//import org.apache.pekko.stream.scaladsl.{Flow, Sink, Source, SourceQueueWithComplete}
//import org.apache.pekko.stream.{Materializer, OverflowStrategy}
//import org.apache.pekko.util.ByteString
//import scala.concurrent.ExecutionContext
//import scala.language.implicitConversions
//
//abstract class Pekko(rpc: MoleculeRpc)
//  extends MoleculeEndpoints with MoleculeLogging {
//
//
//  def wsFlow(implicit
//             system: ActorSystem,
//             mat: Materializer,
//             ec: ExecutionContext
//  ): Flow[Message, Message, NotUsed] = {
//
//    val (queue, source): (SourceQueueWithComplete[Message], Source[Message, NotUsed]) =
//      Source
//        .queue[Message](bufferSize = 16, OverflowStrategy.backpressure)
//        .preMaterialize()
//
//    val sink: Sink[Message, NotUsed] =
//      Sink.foreach[Message] {
//        case BinaryMessage.Strict(bytes) =>
//          val byteBuffer               = bytes.asByteBuffer
//          val (proxy, elements, limit) =
//            Unpickle[(ConnProxy, List[Element], Option[Int])].fromBytes(byteBuffer)
//
//          val callback: List[Any] => Unit = { result =>
//            val resultBytes = PickleTpls(elements, false).pickleEither(Right(result))
//            val msg         = BinaryMessage(ByteString(resultBytes))
//            queue.offer(msg).recover {
//              case ex => system.log.warning(s"WebSocket queue offer failed: ${ex.getMessage}")
//            }
//            ()
//          }
//
//          rpc.subscribe[Any](proxy, elements, limit, callback)
//
//        case _ =>
//          val errorMsg = BinaryMessage(ByteString(Pickle.intoBytes(Left(ModelError("Expected binary message")))))
//          queue.offer(errorMsg)
//      }.mapMaterializedValue(_ => NotUsed)
//
//    Flow.fromSinkAndSource(sink, source)
//  }
//}