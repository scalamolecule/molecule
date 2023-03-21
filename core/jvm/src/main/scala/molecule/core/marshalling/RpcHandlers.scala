package molecule.core.marshalling

import akka.util.ByteString
import boopickle.Default._
import molecule.base.util.exceptions.{ExecutionError, MoleculeError, ValidationErrors}
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.api.TxReport
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling.serialize.PickleTpls
import molecule.core.util.Executor._
import molecule.core.util.SerializationUtils
import scala.concurrent.Future
import scala.language.implicitConversions


// Data is only typed when un-serialized on the client side
abstract class RpcHandlers(rpc: MoleculeRpc) extends MoleculeLogging with SerializationUtils {

  def handleQuery(argsSerialized: ByteString): Future[Array[Byte]] = handleErrors {
    val (proxy, elements, limit) = Unpickle.apply[(ConnProxy, List[Element], Option[Int])]
      .fromBytes(argsSerialized.asByteBuffer)
    rpc.query[Any](proxy, elements, limit).map(result =>
      PickleTpls(elements, false).pickle(result)
    )
  }

  def handleQueryOffset(argsSerialized: ByteString): Future[Array[Byte]] = handleErrors {
    val (proxy, elements, limit, offset) = Unpickle.apply[(ConnProxy, List[Element], Option[Int], Int)]
      .fromBytes(argsSerialized.asByteBuffer)
    rpc.queryOffset[Any](proxy, elements, limit, offset).map(result =>
      PickleTpls(elements, false).pickleOffset(result)
    )
  }
  def handleQueryCursor(argsSerialized: ByteString): Future[Array[Byte]] = handleErrors {
    val (proxy, elements, limit, cursor) = Unpickle.apply[(ConnProxy, List[Element], Option[Int], String)]
      .fromBytes(argsSerialized.asByteBuffer)
    rpc.queryCursor[Any](proxy, elements, limit, cursor).map(result =>
      PickleTpls(elements, false).pickleCursor(result)
    )
  }

  def handleSave(argsSerialized: ByteString): Future[Array[Byte]] = handleErrors {
    val (proxy, elements) = Unpickle.apply[(ConnProxy, List[Element])]
      .fromBytes(argsSerialized.asByteBuffer)
    rpc.save(proxy, elements).map(either =>
      Pickle.intoBytes[Either[MoleculeError, TxReport]](either).toArray
    )
  }

  def handleInsert(argsSerialized: ByteString): Future[Array[Byte]] = handleErrors {
    val (proxy, tplElements, tplsSerialized, txElements) =
      Unpickle.apply[(ConnProxy, List[Element], Array[Byte], List[Element])]
        .fromBytes(argsSerialized.asByteBuffer)
    rpc.insert(proxy, tplElements, tplsSerialized, txElements)
      .map(either =>
        Pickle.intoBytes[Either[MoleculeError, TxReport]](either).toArray
      )
  }

  def handleUpdate(argsSerialized: ByteString): Future[Array[Byte]] = handleErrors {
    val (proxy, elements, isUpsert) =
      Unpickle.apply[(ConnProxy, List[Element], Boolean)]
        .fromBytes(argsSerialized.asByteBuffer)
    rpc.update(proxy, elements, isUpsert)
      .map(either => Pickle.intoBytes[Either[MoleculeError, TxReport]](either).toArray)
  }

  def handleDelete(argsSerialized: ByteString): Future[Array[Byte]] = handleErrors {
    val (proxy, elements) =
      Unpickle.apply[(ConnProxy, List[Element])]
        .fromBytes(argsSerialized.asByteBuffer)
    rpc.delete(proxy, elements)
      .map(either => Pickle.intoBytes[Either[MoleculeError, TxReport]](either).toArray)
  }


  private type Dummy = Int
  private val msg = "Pickling on server unexpectedly failed: "

  private def left(err: MoleculeError): Future[Array[Byte]] = {
    Future(
      Pickle.intoBytes[Either[MoleculeError, Dummy]](Left(err)).toArray
    )
  }

  private def handleErrors(body: => Future[Array[Byte]]): Future[Array[Byte]] = try {
    body
      .recoverWith {
        case e: ValidationErrors =>
          logger.error(e.toString)
          left(e.copy(message = Some(msg + e.msg)))
        case e: ExecutionError   =>
          logger.error(e.toString)
          left(e.copy(message = msg + e.message))
        case e: Throwable        =>
          logger.error(e.toString)
          logger.error(e.getStackTrace.mkString("\n"))
          left(ExecutionError(msg + e.toString, e))
      }
  } catch {
    case e: ValidationErrors =>
      logger.trace(e)
      left(e.copy(message = Some(msg + e.msg)))
    case e: ExecutionError   =>
      logger.trace(e)
      left(e.copy(message = msg + e.message))
    case e: Throwable        =>
      logger.error(e.toString)
      logger.error(e.getStackTrace.mkString("\n"))
      left(ExecutionError(msg + e.toString, e))
  }
}
