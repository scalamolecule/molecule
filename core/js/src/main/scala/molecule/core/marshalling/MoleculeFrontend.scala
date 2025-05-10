package molecule.core.marshalling

import java.nio.ByteBuffer
import boopickle.Default.*
import molecule.base.error.*
import molecule.core.ast.DataModel.*
import molecule.core.marshalling.Boopicklers.*
import molecule.core.marshalling.deserialize.UnpickleTpls
import molecule.core.spi.TxReport
import molecule.core.util.Executor.global as x
import molecule.core.util.FutureUtils
import org.scalajs.dom
import org.scalajs.dom.{MessageEvent, WebSocket}
import sttp.client4.UriContext
import sttp.client4.fetch.FetchBackend
import sttp.tapir.PublicEndpoint
import sttp.tapir.client.sttp4.SttpClientInterpreter
import scala.concurrent.Future
import scala.scalajs.js
import scala.scalajs.js.Dynamic.global
import scala.scalajs.js.typedarray.TypedArrayBufferOps.*
import scala.scalajs.js.typedarray.{ArrayBuffer, TypedArrayBuffer}

case class MoleculeFrontend(host: String, port: Int)
  extends MoleculeRpc
    with MoleculeEndpoints
    with FutureUtils {

  // Use Tapir endpoints to make request to backend
  private def fetch[T](
    endpoint: PublicEndpoint[ByteBuffer, MoleculeError, ByteBuffer, Any],
    args: ByteBuffer,
    unpickle: ByteBuffer => T,
  ): Future[Either[MoleculeError, T]] = {
    SttpClientInterpreter()
      .toRequestThrowDecodeFailures(endpoint, Some(uri"http://$host:$port"))
      .apply(args)
      .send(FetchBackend())
      .map { response =>
        //        println(s"Response: $response")
        response.body match {
          case Right(result) => Right(unpickle(result))
          case Left(error)   => Left(error)
        }
      }
      .recover {
        case unexpected: Throwable =>
          unexpected.printStackTrace()
          Left(ExecutionError(unexpected.getMessage))
      }
  }


  override def subscribe[Tpl](
    proxy: ConnProxy,
    elements: List[Element],
    limit: Option[Int],
    callback: List[Tpl] => Unit
  ): Future[Unit] = Future {
    val socket = new WebSocket(s"ws://$host:$port/molecule/subscribe")
    socket.binaryType = "arraybuffer"
    socket.onerror = {
      case e: dom.Event =>
        println("WebSocket onerror")
        global.console.log("WebSocket error event:", e.asInstanceOf[js.Any])
        logger.error(s"WebSocket error (non-ErrorEvent): $e")
      // socket.close(1000, e.toString) // not active since it can still try to close an already closed connection
    }
    socket.onclose = {
      case _: dom.CloseEvent =>
        println("WebSocket onclose")
        logger.warn("WebSocket onclose")
    }
    socket.onopen = {
      case _: dom.Event =>
        println("WebSocket onopen")
        logger.trace(s"WebSocket onopen")

        //        val x = Pickle.intoBytes((proxy, elements, limit)).typedArray().buffer
        //        println(s"Sending: $x")
        //        socket.send(x)

        socket.send(
          Pickle.intoBytes((proxy, elements, limit)).typedArray().buffer
        )
    }
    socket.onmessage = {
      case e: MessageEvent =>
        println("WebSocket onmessage")
        logger.trace(s"WebSocket onmessage")
        val resultSerialized = TypedArrayBuffer.wrap(e.data.asInstanceOf[ArrayBuffer])
        UnpickleTpls[Tpl](elements, resultSerialized).unpickleEither match {
          case Right(tpls)                      => callback(tpls)
          case Left(ExecutionError("no match")) => // do nothing
          case Left(moleculeError)              => logger.warn(moleculeError.toString)
        }
    }
  }

  override def unsubscribe(
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, Unit]] = {
    fetch[Unit](
      moleculeEndpoint_unsubscribe,
      Pickle.intoBytes((proxy, elements)),
      (_: ByteBuffer) => ()
    )
  }

  override def query[Tpl](
    proxy: ConnProxy,
    elements: List[Element],
    limit: Option[Int]
  ): Future[Either[MoleculeError, List[Tpl]]] = {
    fetch[List[Tpl]](
      moleculeEndpoint_query,
      Pickle.intoBytes((proxy, elements, limit)),
      (result: ByteBuffer) => UnpickleTpls[Tpl](elements, result).unpickleTpls
    )
  }

  override def queryOffset[Tpl](
    proxy: ConnProxy,
    elements: List[Element],
    limit: Option[Int],
    offset: Int
  ): Future[Either[MoleculeError, (List[Tpl], Int, Boolean)]] = {
    fetch[(List[Tpl], Int, Boolean)](
      moleculeEndpoint_queryOffset,
      Pickle.intoBytes((proxy, elements, limit, offset)),
      (result: ByteBuffer) => UnpickleTpls[Tpl](elements, result).unpickleOffset
    )
  }

  override def queryCursor[Tpl](
    proxy: ConnProxy,
    elements: List[Element],
    limit: Option[Int],
    cursor: String
  ): Future[Either[MoleculeError, (List[Tpl], String, Boolean)]] = {
    fetch[(List[Tpl], String, Boolean)](
      moleculeEndpoint_queryCursor,
      Pickle.intoBytes((proxy, elements, limit, cursor)),
      (result: ByteBuffer) => UnpickleTpls[Tpl](elements, result).unpickleCursor
    )
  }

  override def save(
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, TxReport]] = {
    fetch(
      moleculeEndpoint_save,
      Pickle.intoBytes((proxy, elements)),
      (result: ByteBuffer) => Unpickle[TxReport].fromBytes(result)
    )
  }

  override def insert(
    proxy: ConnProxy,
    elements: List[Element],
    tplsSerialized: ByteBuffer,
  ): Future[Either[MoleculeError, TxReport]] = {
    fetch(
      moleculeEndpoint_insert,
      Pickle.intoBytes((proxy, elements, tplsSerialized)),
      (result: ByteBuffer) => Unpickle[TxReport].fromBytes(result)
    )
  }

  override def update(
    proxy: ConnProxy,
    elements: List[Element],
    isUpsert: Boolean = false
  ): Future[Either[MoleculeError, TxReport]] = {
    fetch(
      moleculeEndpoint_update,
      Pickle.intoBytes((proxy, elements, isUpsert)),
      (result: ByteBuffer) => Unpickle[TxReport].fromBytes(result)
    )
  }

  override def delete(
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, TxReport]] = {
    fetch(
      moleculeEndpoint_delete,
      Pickle.intoBytes((proxy, elements)),
      (result: ByteBuffer) => Unpickle[TxReport].fromBytes(result)
    )
  }
}
