package molecule.core.marshalling

import boopickle.Default._
import molecule.base.error._
import molecule.core.ast.DataModel._
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling.deserialize.UnpickleTpls
import molecule.core.spi.TxReport
import molecule.core.util.Executor._
import molecule.core.util.FutureUtils
import scala.concurrent.Future
import scala.scalajs.js.typedarray.TypedArrayBufferOps._

case class MoleculeRpcJS(interface: String, port: Int)
  extends MoleculeRpcRequest(interface, port)
    with MoleculeRpc
    with FutureUtils {


  override def query[Tpl](
    proxy: ConnProxy,
    elements: List[Element],
    limit: Option[Int]
  ): Future[Either[MoleculeError, List[Tpl]]] = Future {
    val argsSerialized = Pickle.intoBytes((proxy, elements, limit)).typedArray()
    xmlHttpRequest("query", argsSerialized).map(resultSerialized =>
      UnpickleTpls[Tpl](elements, resultSerialized).unpickleEither
    )
  }.flatten

  override def queryOffset[Tpl](
    proxy: ConnProxy,
    elements: List[Element],
    limit: Option[Int],
    offset: Int
  ): Future[Either[MoleculeError, (List[Tpl], Int, Boolean)]] = Future {
    val argsSerialized = Pickle.intoBytes((proxy, elements, limit, offset)).typedArray()
    xmlHttpRequest("queryOffset", argsSerialized).map(resultSerialized =>
      UnpickleTpls[Tpl](elements, resultSerialized).unpickleOffset
    )
  }.flatten

  override def queryCursor[Tpl](
    proxy: ConnProxy,
    elements: List[Element],
    limit: Option[Int],
    cursor: String
  ): Future[Either[MoleculeError, (List[Tpl], String, Boolean)]] = Future {
    val argsSerialized = Pickle.intoBytes((proxy, elements, limit, cursor)).typedArray()
    xmlHttpRequest("queryCursor", argsSerialized).map(resultSerialized =>
      UnpickleTpls[Tpl](elements, resultSerialized).unpickleCursor
    )
  }.flatten

  override def save(
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, TxReport]] = Future {
    val argsSerialized = Pickle.intoBytes((proxy, elements)).typedArray()
    xmlHttpRequest("save", argsSerialized).map(resultSerialized =>
      Unpickle.apply[Either[MoleculeError, TxReport]].fromBytes(resultSerialized)
    )
  }.flatten

  override def insert(
    proxy: ConnProxy,
    tplElements: List[Element],
    tplsSerialized: Array[Byte],
  ): Future[Either[MoleculeError, TxReport]] = Future {
    val argsSerialized = Pickle.intoBytes((proxy, tplElements, tplsSerialized)).typedArray()
    xmlHttpRequest("insert", argsSerialized).map(resultSerialized =>
      Unpickle.apply[Either[MoleculeError, TxReport]].fromBytes(resultSerialized)
    )
  }.flatten

  override def update(
    proxy: ConnProxy,
    elements: List[Element],
    isUpsert: Boolean = false
  ): Future[Either[MoleculeError, TxReport]] = Future {
    val argsSerialized = Pickle.intoBytes((proxy, elements, isUpsert)).typedArray()
    xmlHttpRequest("update", argsSerialized).map(resultSerialized =>
      Unpickle.apply[Either[MoleculeError, TxReport]].fromBytes(resultSerialized)
    )
  }.flatten

  override def delete(
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, TxReport]] = Future {
    val argsSerialized = Pickle.intoBytes((proxy, elements)).typedArray()
    xmlHttpRequest("delete", argsSerialized).map(resultSerialized =>
      Unpickle.apply[Either[MoleculeError, TxReport]].fromBytes(resultSerialized)
    )
  }.flatten
}
