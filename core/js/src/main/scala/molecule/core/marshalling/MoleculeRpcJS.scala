package molecule.core.marshalling

import java.nio.ByteBuffer
import boopickle.Default._
import molecule.base.util.exceptions.MoleculeError
import molecule.boilerplate.ast.Model._
import molecule.core.api.TxReport
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling.deserialize.UnpickleTpls
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
      UnpickleTpls[Tpl](elements, resultSerialized).unpickle
    )
  }.flatten


  override def subscribe[Tpl](
    proxy: ConnProxy,
    elements: List[Element],
    limit: Option[Int],
    callback: List[Tpl] => Unit
  ): Unit = {
    val argsSerialized      = Pickle.intoBytes((proxy, elements, limit)).typedArray()
    val callbackDeserialize = (resultSerialized: ByteBuffer) => {
      UnpickleTpls[Tpl](elements, resultSerialized).unpickle match {
        case Right(tpls)                        => callback(tpls)
        case Left(MoleculeError("no match", _)) => // do nothing
        case Left(moleculeError)                => logger.warn(moleculeError.toString)
      }
    }
    //    println("ELEMENTS: " + elements)
    websocketSubscription(argsSerialized, callbackDeserialize)
  }

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
    txElements: List[Element],
  ): Future[Either[MoleculeError, TxReport]] = Future {
    val argsSerialized = Pickle.intoBytes((proxy, tplElements, tplsSerialized, txElements)).typedArray()
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
