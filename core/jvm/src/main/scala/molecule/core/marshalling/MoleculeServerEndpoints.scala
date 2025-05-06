package molecule.core.marshalling

import java.nio.ByteBuffer
import boopickle.Default.*
import cats.effect.std.Queue
import cats.effect.unsafe.IORuntime
import cats.effect.{IO, Ref}
import fs2.Pipe
import molecule.base.error.*
import molecule.core.ast.DataModel.Element
import molecule.core.marshalling.Boopicklers.*
import molecule.core.marshalling.serialize.PickleTpls
import molecule.core.spi.TxReport
import molecule.core.util.Executor.*
import molecule.core.util.MoleculeLogging
import org.apache.pekko.NotUsed
import org.apache.pekko.actor.ActorSystem
import org.apache.pekko.http.scaladsl.model.ws.{BinaryMessage, Message}
import org.apache.pekko.stream.scaladsl.{Flow, Sink, Source, SourceQueueWithComplete}
import org.apache.pekko.stream.{Materializer, OverflowStrategy}
import org.apache.pekko.util.ByteString
import sttp.capabilities.WebSockets
import sttp.capabilities.fs2.Fs2Streams
import sttp.tapir.*
import sttp.tapir.server.ServerEndpoint
import scala.concurrent.{ExecutionContext, Future}
import scala.language.implicitConversions

abstract class MoleculeServerEndpoints(rpc: MoleculeRpc)
  extends MoleculeEndpoints with MoleculeLogging {

  private val msg = "RPC failed on server: "

  implicit class byteBuffer2byteArray(byteBuffer: ByteBuffer) {
    def toArray: Array[Byte] = {
      val length    = byteBuffer.remaining()
      val byteArray = Array.ofDim[Byte](length)
      System.arraycopy(byteBuffer.array(), 0, byteArray, 0, length)
      byteArray
    }
  }

  // Execute Molecule action corresponding to each Tapir endpoint
  private def mkServerEndpoint(
    endpoint: PublicEndpoint[ByteBuffer, MoleculeError, ByteBuffer, Any],
    executeAction: ByteBuffer => Future[Either[MoleculeError, ByteBuffer]]
  ): ServerEndpoint[Any, Future] = {
    endpoint.serverLogic { args =>
      executeAction(args)
        .recover {
          case e: ModelError =>
            logger.warn(e.toString)
            Left(e.copy(message = msg + e.message))

          case e: ValidationErrors =>
            logger.warn(e.toString)
            Left(e)

          case e: InsertErrors =>
            logger.warn(e)
            Left(e.copy(message = Some(msg + e.msg)))

          case e: ExecutionError =>
            logger.error(e)
            Left(e.copy(message = msg + e.message))

          case e: Throwable =>
            logger.error(e.toString)
            logger.error(e.getStackTrace.mkString("\n"))
            Left(ExecutionError(msg + e.toString))
        }
    }
  }

  /** Tapir server endpoints to Molecule actions
   *
   * Add to Tapir backend server of your choice like this:
   *
   * val server = NettyFutureServer()
   * .port(8080)
   * .addEndpoints(moleculeServerEndpoints)
   * ...
   */
  val moleculeServerEndpoints_Future: List[ServerEndpoint[Any, Future]] = List(
    mkServerEndpoint(moleculeEndpoint_query, executeQuery),
    mkServerEndpoint(moleculeEndpoint_queryOffset, executeQueryOffset),
    mkServerEndpoint(moleculeEndpoint_queryCursor, executeQueryCursor),
    mkServerEndpoint(moleculeEndpoint_unsubscribe, executeUnsubscribe),
    mkServerEndpoint(moleculeEndpoint_save, executeSave),
    mkServerEndpoint(moleculeEndpoint_insert, executeInsert),
    mkServerEndpoint(moleculeEndpoint_update, executeUpdate),
    mkServerEndpoint(moleculeEndpoint_delete, executeDelete),
  )

  val moleculeEndpoint_subscribe = endpoint.get.in("molecule" / "subscribe" / path[String])


  val websocket = webSocketBody[Array[Byte], CodecFormat.OctetStream, Array[Byte], CodecFormat.OctetStream]

  val moleculeEndpoint_subscribe_IO = moleculeEndpoint_subscribe.out(websocket(Fs2Streams[IO]))


  def moleculeSubscribePipe(implicit runtime: IORuntime): Pipe[IO, Array[Byte], Array[Byte]] = { in =>
    fs2.Stream.eval {
      for {
        queue <- Queue.unbounded[IO, Array[Byte]]
        ref <- Ref.of[IO, Boolean](false)

        inProcessed = in.evalMap { msg =>
          ref.get.flatMap {
            case false =>
              val (proxy, elements, limit) =
                Unpickle[(ConnProxy, List[Element], Option[Int])].fromBytes(ByteBuffer.wrap(msg))

              val callback: List[Any] => Unit = { result =>
                val bytes = PickleTpls(elements, false).pickleEither2ByteArray(Right(result))
                // Run offer in background since callback is synchronous
                queue.offer(bytes).start.void.unsafeRunSync()
              }

              ref.set(true) *> IO(rpc.subscribe[Any](proxy, elements, limit, callback))

            case true =>
              IO.unit
          }
        }

        out = fs2.Stream.fromQueueUnterminated(queue)

      } yield inProcessed.drain.merge(out)
    }.flatten
  }


  val moleculeServerEndpoint_subscribe: ServerEndpoint[Fs2Streams[IO] & WebSockets, IO] = {
    moleculeEndpoint_subscribe_IO
      .serverLogicSuccess(_ => IO.pure(moleculeSubscribePipe(cats.effect.unsafe.implicits.global)))
  }


  def wsFlow(implicit
             system: ActorSystem,
             mat: Materializer,
             ec: ExecutionContext
  ): Flow[Message, Message, NotUsed] = {
    val (queue, source): (SourceQueueWithComplete[Message], Source[Message, NotUsed]) =
      Source
        .queue[Message](bufferSize = 16, OverflowStrategy.backpressure)
        .preMaterialize()

    val sink: Sink[Message, NotUsed] =
      Sink.foreach[Message] {
        case BinaryMessage.Strict(argsSerialized) =>
          // Deserialize callback query coordinates
          val (proxy, elements, limit) =
            Unpickle[(ConnProxy, List[Element], Option[Int])]
              .fromBytes(argsSerialized.asByteBuffer)

          // Send query results if match
          val callback: List[Any] => Unit = { (result: List[Any]) =>
            queue.offer(
              BinaryMessage(ByteString(
                PickleTpls(elements, false).pickleEither(Right(result))
              ))
            ).recover {
              case ex => system.log.warning(s"WebSocket queue offer failed: ${ex.getMessage}")
            }
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


  /** Server logic
   *
   * Pattern of each action:
   * 1. Unpickle arguments from client
   * 2. Execute action on server (shared MoleculeRpc actions come in handy here)
   * 3. Pickle result for transfer to client
   * */

  def executeQuery(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
    val (proxy, elements, limit) =
      Unpickle[(ConnProxy, List[Element], Option[Int])].fromBytes(args)
    rpc
      .query[Any](proxy, elements, limit)
      .map {
        case Right(result) => Right(PickleTpls(elements, false).getPickledTpls(result))
        case Left(err)     => Left(err)
      }
  }

  def executeQueryOffset(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
    val (proxy, elements, limit, offset) =
      Unpickle[(ConnProxy, List[Element], Option[Int], Int)].fromBytes(args: ByteBuffer)
    rpc
      .queryOffset[Any](proxy, elements, limit, offset)
      .map {
        case Right((tpls, limit, more)) =>
          Right(PickleTpls(elements, false).pickleOffset(tpls, limit, more))
        case Left(err)                  => Left(err)
      }
  }

  def executeQueryCursor(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
    val (proxy, elements, limit, cursor) =
      Unpickle[(ConnProxy, List[Element], Option[Int], String)].fromBytes(args: ByteBuffer)
    rpc
      .queryCursor[Any](proxy, elements, limit, cursor)
      .map {
        case Right((tpls, cursor, more)) =>
          Right(PickleTpls(elements, false).pickleCursor(tpls, cursor, more))
        case Left(err)                   => Left(err)
      }
  }

  def executeUnsubscribe(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
    val (proxy, elements) =
      Unpickle[(ConnProxy, List[Element])].fromBytes(args: ByteBuffer)
    rpc
      .unsubscribe(proxy, elements)
      .map {
        case Right(()) => Right(Pickle.intoBytes[Unit](()))
        case Left(err) => Left(err)
      }
  }

  def executeSave(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
    val (proxy, elements) = Unpickle[(ConnProxy, List[Element])].fromBytes(args)
    rpc
      .save(proxy, elements)
      .map {
        case Right(txReport) => Right(Pickle.intoBytes[TxReport](txReport))
        case Left(err)       => Left(err)
      }
  }

  def executeInsert(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
    val (proxy, tplElements, tplsSerialized) =
      Unpickle[(ConnProxy, List[Element], ByteBuffer)].fromBytes(args: ByteBuffer)
    rpc
      .insert(proxy, tplElements, tplsSerialized)
      .map {
        case Right(txReport) => Right(Pickle.intoBytes[TxReport](txReport))
        case Left(err)       => Left(err)
      }
  }

  def executeUpdate(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
    val (proxy, elements, isUpsert) =
      Unpickle[(ConnProxy, List[Element], Boolean)].fromBytes(args: ByteBuffer)
    rpc
      .update(proxy, elements, isUpsert)
      .map {
        case Right(txReport) => Right(Pickle.intoBytes[TxReport](txReport))
        case Left(err)       => Left(err)
      }
  }

  def executeDelete(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
    val (proxy, elements) =
      Unpickle[(ConnProxy, List[Element])].fromBytes(args: ByteBuffer)
    rpc
      .delete(proxy, elements)
      .map {
        case Right(txReport) => Right(Pickle.intoBytes[TxReport](txReport))
        case Left(err)       => Left(err)
      }
  }
}