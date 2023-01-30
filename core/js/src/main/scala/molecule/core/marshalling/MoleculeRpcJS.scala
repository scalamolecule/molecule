package molecule.core.marshalling

import boopickle.Default._
import molecule.base.util.exceptions.MoleculeError
import molecule.boilerplate.ast.Model._
import molecule.core.api.TxReport
import molecule.core.api.action.ApiUtils
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling.deserialize.UnpickleTpls
import molecule.core.util.Executor._
import scala.concurrent.Future
import scala.scalajs.js.typedarray.TypedArrayBufferOps._


case class MoleculeRpcJS(interface: String, port: Int)
  extends MoleculeRpcRequest(interface, port)
    with MoleculeRpc
    with ApiUtils {


  override def query[Tpl](
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, List[Tpl]]] = Future {
    val argsSerialized = Pickle.intoBytes((proxy, elements)).typedArray()
    xmlRequest("query", argsSerialized).map(resultSerialized =>
      UnpickleTpls[Tpl](elements, resultSerialized).unpickle
    )
  }.flatten


  override def save(
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, TxReport]] = Future {
    val argsSerialized = Pickle.intoBytes((proxy, elements)).typedArray()
    xmlRequest("save", argsSerialized).map(resultSerialized =>
      Unpickle.apply[Either[MoleculeError, TxReport]].fromBytes(resultSerialized)
    )
  }.flatten


  override def insert(
    proxy: ConnProxy,
    tplElements: List[Element],
    tplsSerialized: Array[Byte],
    txElements: List[Element],
  ): Future[Either[MoleculeError, TxReport]] = Future {
    val argsSerialized = Pickle.intoBytes((proxy, tplElements, tplsSerialized, txElements)).typedArray()
    xmlRequest("insert", argsSerialized).map(resultSerialized =>
      Unpickle.apply[Either[MoleculeError, TxReport]].fromBytes(resultSerialized)
    )
  }.flatten


  override def update(
    proxy: ConnProxy,
    elements: List[Element],
    isUpsert: Boolean = false,
    isMultiple: Boolean = false,
  ): Future[Either[MoleculeError, TxReport]] = Future {
    val argsSerialized = Pickle.intoBytes((proxy, elements, isUpsert, isMultiple)).typedArray()
    xmlRequest("update", argsSerialized).map(resultSerialized =>
      Unpickle.apply[Either[MoleculeError, TxReport]].fromBytes(resultSerialized)
    )
  }.flatten


  override def delete(
    proxy: ConnProxy,
    elements: List[Element],
    isMultiple: Boolean = false
  ): Future[Either[MoleculeError, TxReport]] = Future {
    val argsSerialized = Pickle.intoBytes((proxy, elements, isMultiple)).typedArray()
    xmlRequest("delete", argsSerialized).map(resultSerialized =>
      Unpickle.apply[Either[MoleculeError, TxReport]].fromBytes(resultSerialized)
    )
  }.flatten
}
