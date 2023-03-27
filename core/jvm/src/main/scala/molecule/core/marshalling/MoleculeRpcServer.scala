package molecule.core.marshalling

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
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
import scala.io.StdIn
import scala.language.implicitConversions


/** Akka Http RPC server responding to molecule ajax requests
 *
 * Start this server in terminal with
 *
 * sbt datomicJVM/run
 * */
abstract class MoleculeRpcServer(rpc: MoleculeRpc) extends RpcHandlers(rpc) {
  implicit val system          : ActorSystem              = ActorSystem()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher


  def toRoute(handler: ByteString => Future[Array[Byte]]): Route = {
    post {
      extractRequest { req =>
        req.entity match {
          case HttpEntity.Strict(_, argsSerialized) => complete(
            handler(argsSerialized)
          )
          case HttpEntity.Default(_, _, chunks)     => complete(
            chunks.reduce(_ ++ _).runFoldAsync(Array.empty[Byte]) {
              case (_, argsSerialized) => handler(argsSerialized)
            }
          )

          case other => complete("Unexpected HttpEntity in MoleculeRpcServer: " + other)
        }
      }
    }
  }

  def wsFlow: Flow[Message, Message, _] = {
    val (queue, source) = Source.queue[Message](
      1, OverflowStrategy.backpressure
    ).preMaterialize()

    val sink = Sink.foreach[Message] {
      case BinaryMessage.Strict(argsSerialized) =>
        // Deserialize query coordinates
        val (proxy, elements, limit) = Unpickle.apply[(ConnProxy, List[Element], Option[Int])]
          .fromBytes(argsSerialized.asByteBuffer)
        //        println("Elements: " + elements)

        // What to do when new query results emerge
        val callback: List[Any] => Unit = { (result: List[Any]) =>
          val resultBytes   = PickleTpls(elements, false).pickle(Right(result))
          val binaryMessage = BinaryMessage(ByteString(resultBytes))
          //          println("CALLBACK: " + result)

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

  val prefix = "molecule"
  val route  = cors() {
    path(prefix / "query")(toRoute(handleQuery)) ~
      path(prefix / "queryOffset")(toRoute(handleQueryOffset)) ~
      path(prefix / "queryCursor")(toRoute(handleQueryCursor)) ~
      path(prefix / "save")(toRoute(handleSave)) ~
      path(prefix / "insert")(toRoute(handleInsert)) ~
      path(prefix / "update")(toRoute(handleUpdate)) ~
      path(prefix / "delete")(toRoute(handleDelete)) ~
      path(prefix / "ws" / Remaining)(_ => handleWebSocketMessages(wsFlow))
  }

  Http()
    .newServerAt("localhost", 8080)
    .bind(route)
  //    .map(_.addToCoordinatedShutdown(hardTerminationDeadline = 120.seconds))

  println("Started server at 127.0.0.1:8080, press enter to kill server")
  StdIn.readLine()
  system.terminate()
}
