package molecule.core.marshalling

import java.nio.ByteBuffer
import boopickle.Default._
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling.serialize.PickleTpls
import molecule.core.spi.TxReport
import molecule.core.util.Executor._
import molecule.core.util.SerializationUtils
import scala.concurrent.Future
import scala.language.implicitConversions


// Data is only typed when un-serialized on the client side
abstract class RpcHandlers(rpc: MoleculeRpc) extends MoleculeLogging with SerializationUtils {

  def handleQuery(argsSerialized: ByteBuffer): Future[Array[Byte]] = handleErrors {
    val (proxy, elements, limit) = Unpickle.apply[(ConnProxy, List[Element], Option[Int])]
      .fromBytes(argsSerialized)
    rpc.query[Any](proxy, elements, limit).map(result =>
      PickleTpls(elements, false).pickleEither(result)
    )
  }

  def handleQueryOffset(argsSerialized: ByteBuffer): Future[Array[Byte]] = handleErrors {
    val (proxy, elements, limit, offset) = Unpickle.apply[(ConnProxy, List[Element], Option[Int], Int)]
      .fromBytes(argsSerialized: ByteBuffer)
    rpc.queryOffset[Any](proxy, elements, limit, offset).map(result =>
      PickleTpls(elements, false).pickleOffset(result)
    )
  }
  def handleQueryCursor(argsSerialized: ByteBuffer): Future[Array[Byte]] = handleErrors {
    val (proxy, elements, limit, cursor) = Unpickle.apply[(ConnProxy, List[Element], Option[Int], String)]
      .fromBytes(argsSerialized: ByteBuffer)
    rpc.queryCursor[Any](proxy, elements, limit, cursor).map(result =>
      PickleTpls(elements, false).pickleCursor(result)
    )
  }

  def handleSave(argsSerialized: ByteBuffer): Future[Array[Byte]] = handleErrors {
    val (proxy, elements) = Unpickle.apply[(ConnProxy, List[Element])]
      .fromBytes(argsSerialized: ByteBuffer)
    rpc.save(proxy, elements).map(either =>
      Pickle.intoBytes[Either[MoleculeError, TxReport]](either).toArray
    )
  }

  def handleInsert(argsSerialized: ByteBuffer): Future[Array[Byte]] = handleErrors {
    val (proxy, tplElements, tplsSerialized) =
      Unpickle.apply[(ConnProxy, List[Element], Array[Byte])]
        .fromBytes(argsSerialized: ByteBuffer)
    rpc.insert(proxy, tplElements, tplsSerialized).map(either =>
      Pickle.intoBytes[Either[MoleculeError, TxReport]](either).toArray
    )
  }

  def handleUpdate(argsSerialized: ByteBuffer): Future[Array[Byte]] = handleErrors {
    val (proxy, elements, isUpsert) =
      Unpickle.apply[(ConnProxy, List[Element], Boolean)]
        .fromBytes(argsSerialized: ByteBuffer)
    rpc.update(proxy, elements, isUpsert).map(either =>
      Pickle.intoBytes[Either[MoleculeError, TxReport]](either).toArray
    )
  }

  def handleDelete(argsSerialized: ByteBuffer): Future[Array[Byte]] = handleErrors {
    val (proxy, elements) =
      Unpickle.apply[(ConnProxy, List[Element])]
        .fromBytes(argsSerialized: ByteBuffer)
    rpc.delete(proxy, elements).map(either =>
      Pickle.intoBytes[Either[MoleculeError, TxReport]](either).toArray
    )
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
        case e: ModelError       =>
          logger.error(e.toString)
          left(e.copy(message = msg + e.message))
        case e: ValidationErrors =>
          logger.error(e.toString)
          left(e)
        case e: InsertErrors     =>
          logger.trace(e)
          left(e.copy(message = Some(msg + e.msg)))
        case e: ExecutionError   =>
          logger.trace(e)
          left(e.copy(message = msg + e.message))
        case e: Throwable        =>
          logger.error(e.toString)
          logger.error(e.getStackTrace.mkString("\n"))
          left(ExecutionError(msg + e.toString))
      }
  } catch {
    case e: ModelError       =>
      logger.trace(e)
      left(e.copy(message = msg + e.message))
    case e: ValidationErrors =>
      logger.trace(e)
      left(e)
    case e: InsertErrors     =>
      logger.trace(e)
      left(e.copy(message = Some(msg + e.msg)))
    case e: ExecutionError   =>
      logger.trace(e)
      left(e.copy(message = msg + e.message))
    case e: Throwable        =>
      logger.error(e.toString)
      logger.error(e.getStackTrace.mkString("\n"))
      left(ExecutionError(msg + e.toString))
  }
}
