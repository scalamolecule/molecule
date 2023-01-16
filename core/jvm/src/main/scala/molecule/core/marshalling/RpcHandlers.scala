package molecule.core.marshalling

import akka.util.ByteString
import boopickle.Default._
import molecule.base.util.exceptions.MoleculeError
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.api.TxReport
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling.serialize.PickleTpls
import molecule.core.util.Executor._
import molecule.core.util.SerializationUtils
import scala.concurrent.Future
import scala.language.implicitConversions


abstract class RpcHandlers(rpc: MoleculeRpc) extends MoleculeLogging with SerializationUtils {

  def handleQuery(argsSerialized: ByteString): Future[Array[Byte]] = tryUnpickle {
    val (proxy, elements) = Unpickle.apply[(ConnProxy, List[Element])]
      .fromBytes(argsSerialized.asByteBuffer)
    // Data is typed when un-serialized on the client side
    rpc.query[Any](proxy, elements).map(either =>
      PickleTpls(elements, either, false).pickle
    )
  }

  def handleSave(argsSerialized: ByteString): Future[Array[Byte]] = tryUnpickle {
    val (proxy, elements) = Unpickle.apply[(ConnProxy, List[Element])]
      .fromBytes(argsSerialized.asByteBuffer)
    rpc.save(proxy, elements).map(either =>
      Pickle.intoBytes[Either[MoleculeError, TxReport]](either).toArray
    )
  }

  def handleInsert(argsSerialized: ByteString): Future[Array[Byte]] = tryUnpickle {
    val (proxy, tplElements, tplsSerialized, txElements) =
      Unpickle.apply[(ConnProxy, List[Element], Array[Byte], List[Element])]
        .fromBytes(argsSerialized.asByteBuffer)
    rpc.insert(proxy, tplElements, tplsSerialized, txElements)
      .map(either =>
        Pickle.intoBytes[Either[MoleculeError, TxReport]](either).toArray
      )
  }

  def handleUpdate(argsSerialized: ByteString): Future[Array[Byte]] = tryUnpickle {
    val (proxy, elements, isUpsert, isMultiple) =
      Unpickle.apply[(ConnProxy, List[Element], Boolean, Boolean)]
        .fromBytes(argsSerialized.asByteBuffer)
    rpc.update(proxy, elements, isUpsert, isMultiple)
      .map(either => Pickle.intoBytes[Either[MoleculeError, TxReport]](either).toArray)
  }

  def handleDelete(argsSerialized: ByteString): Future[Array[Byte]] = tryUnpickle {
    val (proxy, elements, isMultiple) =
      Unpickle.apply[(ConnProxy, List[Element], Boolean)]
        .fromBytes(argsSerialized.asByteBuffer)
    rpc.delete(proxy, elements, isMultiple)
      .map(either => Pickle.intoBytes[Either[MoleculeError, TxReport]](either).toArray)
  }


  private type Dummy = Int
  private val msg = "Pickling on server unexpectedly failed: "

  private def left(err: MoleculeError): Future[Array[Byte]] = {
    Future(
      Pickle.intoBytes[Either[MoleculeError, Dummy]](Left(err)).toArray
    )
  }

  private def tryUnpickle(body: => Future[Array[Byte]]): Future[Array[Byte]] = try {
    body
  } catch {
    case e: MoleculeError =>
      logger.trace(e)
      left(e.copy(message = msg + e.message))
    case e: Throwable     =>
      logger.error(e.toString)
      logger.error(e.getStackTrace.mkString("\n"))
      left(MoleculeError(msg + e.toString, e))
  }
}
