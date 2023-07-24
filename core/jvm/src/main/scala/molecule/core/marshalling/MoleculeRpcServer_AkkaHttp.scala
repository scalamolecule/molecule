package molecule.core.marshalling

import java.nio.ByteBuffer
import akka.actor.ActorSystem
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.model.ws._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.OverflowStrategy
import akka.stream.scaladsl.{Flow, Sink, Source}
import akka.util.ByteString
import boopickle.Default._
import ch.megard.akka.http.cors.scaladsl.CorsDirectives._
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling.serialize.PickleTpls
import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.language.implicitConversions

abstract class MoleculeRpcServer_AkkaHttp(rpc: MoleculeRpc) extends RpcHandlers(rpc) {
  implicit val system          : ActorSystem              = ActorSystem()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  private def toRoute(handler: ByteBuffer => Future[Array[Byte]]): Route = {
    post {
      extractRequest { req =>
        req.entity match {
          case HttpEntity.Strict(_, argsSerialized) => complete(
            handler(argsSerialized.asByteBuffer)
          )
          case HttpEntity.Default(_, _, chunks)     => complete(
            chunks.reduce(_ ++ _).runFoldAsync(Array.empty[Byte]) {
              case (_, argsSerialized) => handler(argsSerialized.asByteBuffer)
            }
          )

          case other => complete("Unexpected HttpEntity in MoleculeRpcServer: " + other)
        }
      }
    }
  }

  private def wsFlow: Flow[Message, Message, _] = {
    val (queue, source) = Source.queue[Message](
      1, OverflowStrategy.backpressure
    ).preMaterialize()

    val sink = Sink.foreach[Message] {
      case BinaryMessage.Strict(argsSerialized) =>
        // Deserialize query coordinates
        val (proxy, elements, limit) = Unpickle.apply[(ConnProxy, List[Element], Option[Int])]
          .fromBytes(argsSerialized.asByteBuffer)

        // What to do when new query results emerge
        val callback: List[Any] => Unit = { (result: List[Any]) =>
          val resultBytes   = PickleTpls(elements, false).pickle(Right(result))
          val binaryMessage = BinaryMessage(ByteString(resultBytes))

          // Offer result to queue
          queue.offer(binaryMessage)
        }

        // Subscribe to updated query results from db queue in separate thread
        // Data result is typed when deserialized on the client side
        rpc.subscribe[Any](proxy, elements, limit, callback)

      case _ =>
        val resultBytes = Pickle.intoBytes[Either[ModelError, Int]](
          Left(ModelError("Expected a single strict binary message"))
        ).toArray
        Source.single(BinaryMessage(ByteString(resultBytes)))
    }
    Flow.fromSinkAndSource(sink, source)
    //          .keepAlive(10.seconds, () => TextMessage.Strict("keep alive..."))
  }

  private val prefix = "molecule"
  val moleculeRpcRoute = cors() {
    path(prefix / "query")(toRoute(handleQuery)) ~
      path(prefix / "queryOffset")(toRoute(handleQueryOffset)) ~
      path(prefix / "queryCursor")(toRoute(handleQueryCursor)) ~
      path(prefix / "save")(toRoute(handleSave)) ~
      path(prefix / "insert")(toRoute(handleInsert)) ~
      path(prefix / "update")(toRoute(handleUpdate)) ~
      path(prefix / "delete")(toRoute(handleDelete)) ~
      path(prefix / "ws" / Remaining)(_ => handleWebSocketMessages(wsFlow))
  }
}
