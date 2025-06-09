package molecule.db.core.marshalling

import java.nio.ByteBuffer
import boopickle.Default.*
import molecule.base.error.{ExecutionError, MoleculeError}
import molecule.core.ast.*
import molecule.db.core.marshalling.Boopicklers.*
import molecule.db.core.marshalling.deserialize.UnpickleTpls
import molecule.db.core.spi.TxReport
import molecule.db.core.util.Executor.global as x
import molecule.db.core.util.FutureUtils
import org.scalajs.dom
import org.scalajs.dom.{MessageEvent, WebSocket}
import sttp.client4.UriContext
import sttp.client4.fetch.FetchBackend
import sttp.tapir.PublicEndpoint
import sttp.tapir.client.sttp4.SttpClientInterpreter
import scala.concurrent.Future
import scala.scalajs.js
import scala.scalajs.js.typedarray.TypedArrayBufferOps.*
import scala.scalajs.js.typedarray.{ArrayBuffer, TypedArrayBuffer}

case class MoleculeFrontend(host: String, port: Int, protocol: String)
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
      .toRequestThrowDecodeFailures(endpoint, Some(uri"$protocol://$host:$port"))
      .apply(args)
      .send(FetchBackend())
      .map { response =>
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
    dataModel: DataModel,
    limit: Option[Int],
    callback: List[Tpl] => Unit
  ): Future[Unit] = Future {
    val socket = new WebSocket(s"ws://$host:$port/molecule/subscribe")
    socket.binaryType = "arraybuffer"
    socket.onerror = {
      case e: dom.Event =>
        logger.error(s"WebSocket error (non-ErrorEvent): " + e.asInstanceOf[js.Any])

      // not uncommented since it can try to close an already closed connection
      // socket.close(1000, e.toString)
    }
    socket.onclose = {
      case _: dom.CloseEvent => logger.warn("WebSocket onclose")
    }
    socket.onopen = {
      case _: dom.Event =>
        logger.trace(s"WebSocket onopen")
        socket.send(
          Pickle.intoBytes((proxy, dataModel, limit)).typedArray().buffer
        )
    }
    socket.onmessage = {
      case e: MessageEvent =>
        logger.trace(s"WebSocket onmessage")
        val resultSerialized = TypedArrayBuffer.wrap(e.data.asInstanceOf[ArrayBuffer])
        UnpickleTpls[Tpl](dataModel, resultSerialized).unpickleEither match {
          case Right(tpls)                      => callback(tpls)
          case Left(ExecutionError("no match")) => // do nothing
          case Left(moleculeError)              => logger.warn(moleculeError.toString)
        }
    }
  }

  override def unsubscribe(
    proxy: ConnProxy,
    dataModel: DataModel
  ): Future[Either[MoleculeError, Unit]] = {
    fetch[Unit](
      moleculeEndpoint_unsubscribe,
      Pickle.intoBytes((proxy, dataModel)),
      (_: ByteBuffer) => ()
    )
  }

  override def query[Tpl](
    proxy: ConnProxy,
    dataModel: DataModel,
    limit: Option[Int]
  ): Future[Either[MoleculeError, List[Tpl]]] = {
    fetch[List[Tpl]](
      moleculeEndpoint_query,
      Pickle.intoBytes((proxy, dataModel, limit)),
      (result: ByteBuffer) => UnpickleTpls[Tpl](dataModel, result).unpickleTpls
    )
  }

  override def queryOffset[Tpl](
    proxy: ConnProxy,
    dataModel: DataModel,
    limit: Option[Int],
    offset: Int
  ): Future[Either[MoleculeError, (List[Tpl], Int, Boolean)]] = {
    fetch[(List[Tpl], Int, Boolean)](
      moleculeEndpoint_queryOffset,
      Pickle.intoBytes((proxy, dataModel, limit, offset)),
      (result: ByteBuffer) => UnpickleTpls[Tpl](dataModel, result).unpickleOffset
    )
  }

  override def queryCursor[Tpl](
    proxy: ConnProxy,
    dataModel: DataModel,
    limit: Option[Int],
    cursor: String
  ): Future[Either[MoleculeError, (List[Tpl], String, Boolean)]] = {
    fetch[(List[Tpl], String, Boolean)](
      moleculeEndpoint_queryCursor,
      Pickle.intoBytes((proxy, dataModel, limit, cursor)),
      (result: ByteBuffer) => UnpickleTpls[Tpl](dataModel, result).unpickleCursor
    )
  }

  override def save(
    proxy: ConnProxy,
    dataModel: DataModel
  ): Future[Either[MoleculeError, TxReport]] = {
    fetch(
      moleculeEndpoint_save,
      Pickle.intoBytes((proxy, dataModel)),
      (result: ByteBuffer) => Unpickle[TxReport].fromBytes(result)
    )
  }

  override def insert(
    proxy: ConnProxy,
    dataModel: DataModel,
    tplsSerialized: ByteBuffer,
  ): Future[Either[MoleculeError, TxReport]] = {
    fetch(
      moleculeEndpoint_insert,
      Pickle.intoBytes((proxy, dataModel, tplsSerialized)),
      (result: ByteBuffer) => Unpickle[TxReport].fromBytes(result)
    )
  }

  override def update(
    proxy: ConnProxy,
    dataModel: DataModel,
    isUpsert: Boolean = false
  ): Future[Either[MoleculeError, TxReport]] = {
    fetch(
      moleculeEndpoint_update,
      Pickle.intoBytes((proxy, dataModel, isUpsert)),
      (result: ByteBuffer) => Unpickle[TxReport].fromBytes(result)
    )
  }

  override def delete(
    proxy: ConnProxy,
    dataModel: DataModel
  ): Future[Either[MoleculeError, TxReport]] = {
    fetch(
      moleculeEndpoint_delete,
      Pickle.intoBytes((proxy, dataModel)),
      (result: ByteBuffer) => Unpickle[TxReport].fromBytes(result)
    )
  }
}
