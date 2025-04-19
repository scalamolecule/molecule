package molecule.core.marshalling

import java.nio.ByteBuffer
import boopickle.Default.*
import molecule.base.error.*
import molecule.core.ast.DataModel.*
import molecule.core.marshalling.Boopicklers.*
import molecule.core.marshalling.deserialize.UnpickleTpls
import molecule.core.spi.{Conn, TxReport}
import molecule.core.util.Executor.*
import molecule.core.util.FutureUtils
import sttp.client4.fetch.FetchBackend
import sttp.model.Uri
import sttp.tapir.PublicEndpoint
import sttp.tapir.client.sttp4.SttpClientInterpreter
import scala.concurrent.Future

case class MoleculeClient(backendBaseUri: Uri)
  extends MoleculeRpc
    with MoleculeEndpoints
    with FutureUtils {

  // Use Tapir endpoints to make request to backend
  private def fetch[T](
    endpoint: PublicEndpoint[ByteBuffer, MoleculeError, ByteBuffer, Any],
    args: ByteBuffer,
    unpickle: ByteBuffer => T
  ): Future[Either[MoleculeError, T]] = {
    SttpClientInterpreter()
      .toRequestThrowDecodeFailures(endpoint, Some(backendBaseUri))
      .apply(args)
      .send(FetchBackend())
      .map { response =>
        response.body match {
          case Right(result) => Right(unpickle(result))
          case Left(error)   => Left(error)
        }
      }
      .recover {
        case unexpected => Left(ExecutionError(unexpected.getMessage))
      }
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
