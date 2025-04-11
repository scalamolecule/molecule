//package molecule.core.marshalling
//
//import java.nio.ByteBuffer
//import boopickle.Default._
//import molecule.base.error._
//import molecule.core.ast.DataModel._
//import molecule.core.marshalling.Boopicklers._
//import molecule.core.marshalling.deserialize.UnpickleTpls
//import molecule.core.marshalling.serialize.PickleTpls
//import molecule.core.spi.TxReport
//import molecule.core.util.Executor._
//import molecule.core.util.{MoleculeLogging, SerializationUtils}
//import scala.concurrent.Future
////import scala.language.implicitConversions
//
//// Data is only typed when un-serialized on the client side
//abstract class RpcHandlers2(rpc: MoleculeRpc) extends MoleculeLogging with SerializationUtils {
//
//  def handleQuery(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
//    val (proxy, elements, limit) =
//      Unpickle[(ConnProxy, List[Element], Option[Int])].fromBytes(args)
//    rpc
//      .query[Any](proxy, elements, limit)
//      .map {
//        case Right(result) => Right(PickleTpls(elements, false).pickleQuery(result))
//        case Left(err)     => Left(err)
//      }
//  }
//
//  def handleQueryStream(args: ByteBuffer) = ???
//
//
//  def handleQueryOffset(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
//    //      handleErrors {
//    //        val (proxy, elements, limit, offset) = Unpickle[(ConnProxy, List[Element], Option[Int], Int)]
//    //          .fromBytes(args: ByteBuffer)
//    //        rpc.queryOffset[Any](proxy, elements, limit, offset).map(result =>
//    //          PickleTpls(elements, false).pickleOffset(result)
//    //        )
//    //      }
//    val (proxy, elements, limit, offset) = Unpickle[(ConnProxy, List[Element], Option[Int], Int)]
//      .fromBytes(args: ByteBuffer)
//    rpc
//      .queryOffset[Any](proxy, elements, limit, offset)
//      .map {
//        case Right((tpls, limit, more)) => Right(PickleTpls(elements, false).pickleOffset(tpls, limit, more))
//        case Left(err)                  => Left(err)
//      }
//  }
//
//  def handleQueryCursor(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
//    //      handleErrors {
//    //        val (proxy, elements, limit, cursor) = Unpickle[(ConnProxy, List[Element], Option[Int], String)]
//    //          .fromBytes(args: ByteBuffer)
//    //        rpc.queryCursor[Any](proxy, elements, limit, cursor).map(result =>
//    //          PickleTpls(elements, false).pickleCursor(result)
//    //        )
//    //      }
//    val (proxy, elements, limit, cursor) = Unpickle[(ConnProxy, List[Element], Option[Int], String)]
//      .fromBytes(args: ByteBuffer)
//    rpc
//      .queryCursor[Any](proxy, elements, limit, cursor)
//      .map {
//        case Right((tpls, cursor, more)) => Right(PickleTpls(elements, false).pickleCursor(tpls, cursor, more))
//        case Left(err)                   => Left(err)
//      }
//  }
//
//  def handleSave(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
//    //    handleErrors {
//    //      val (proxy, elements) = Unpickle[(ConnProxy, List[Element])]
//    //        .fromBytes(args: ByteBuffer)
//    //      rpc.save(proxy, elements).map(either =>
//    //        Pickle.intoBytes[Either[MoleculeError, TxReport]](either).toArray
//    //      )
//    //    }
//    val (proxy, elements) = Unpickle[(ConnProxy, List[Element])].fromBytes(args)
//    rpc
//      .save(proxy, elements)
//      .map {
//        case Right(txReport) => Right(Pickle.intoBytes[TxReport](txReport))
//        case Left(err)       => Left(err)
//      }
//  }
//
//  def handleInsert(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
//    //      handleErrors {
//    //        val (proxy, tplElements, tplsSerialized) =
//    //          Unpickle[(ConnProxy, List[Element], ByteBuffer)]
//    //            .fromBytes(args: ByteBuffer)
//    //        rpc.insert(proxy, tplElements, tplsSerialized).map(either =>
//    //          Pickle.intoBytes[Either[MoleculeError, TxReport]](either).toArray
//    //        )
//    //      }
//
//    val (proxy, tplElements, tplsSerialized) =
//      Unpickle[(ConnProxy, List[Element], ByteBuffer)].fromBytes(args: ByteBuffer)
//    rpc
//      .insert(proxy, tplElements, tplsSerialized)
//      .map {
//        case Right(txReport) => Right(Pickle.intoBytes[TxReport](txReport))
//        case Left(err)       => Left(err)
//      }
//  }
//
//  def handleUpdate(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
//    //      handleErrors {
//    //        val (proxy, elements, isUpsert) =
//    //          Unpickle[(ConnProxy, List[Element], Boolean)]
//    //            .fromBytes(args: ByteBuffer)
//    //        rpc.update(proxy, elements, isUpsert).map(either =>
//    //          Pickle.intoBytes[Either[MoleculeError, TxReport]](either).toArray
//    //        )
//    //      }
//    val (proxy, elements, isUpsert) =
//      Unpickle[(ConnProxy, List[Element], Boolean)]
//        .fromBytes(args: ByteBuffer)
//    rpc
//      .update(proxy, elements, isUpsert)
//      .map {
//        case Right(txReport) => Right(Pickle.intoBytes[TxReport](txReport))
//        case Left(err)       => Left(err)
//      }
//  }
//
//  def handleDelete(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
//    //      handleErrors {
//    //        val (proxy, elements) =
//    //          Unpickle[(ConnProxy, List[Element])]
//    //            .fromBytes(args: ByteBuffer)
//    //        rpc.delete(proxy, elements).map(either =>
//    //          Pickle.intoBytes[Either[MoleculeError, TxReport]](either).toArray
//    //        )
//    //      }
//
//    val (proxy, elements) =
//      Unpickle[(ConnProxy, List[Element])]
//        .fromBytes(args: ByteBuffer)
//    rpc
//      .delete(proxy, elements)
//      .map {
//        case Right(txReport) => Right(Pickle.intoBytes[TxReport](txReport))
//        case Left(err)       => Left(err)
//      }
//  }
//
//
//  //  private type Dummy = Int
//  //  private val msg = "Pickling on server unexpectedly failed: "
//  //
//  //  private def left(err: MoleculeError): Future[ByteBuffer] = {
//  //    Future(
//  //      Pickle.intoBytes[Either[MoleculeError, Dummy]](Left(err)).toArray
//  //    )
//  //  }
//  //
//  //  private def handleErrors(body: => Future[ByteBuffer]): Future[ByteBuffer] = try {
//  //    body
//  //      .recoverWith {
//  //        case e: ModelError       =>
//  //          logger.error(e.toString)
//  //          left(e.copy(message = msg + e.message))
//  //        case e: ValidationErrors =>
//  //          logger.error(e.toString)
//  //          left(e)
//  //        case e: InsertErrors     =>
//  //          logger.trace(e)
//  //          left(e.copy(message = Some(msg + e.msg)))
//  //        case e: ExecutionError   =>
//  //          logger.trace(e)
//  //          left(e.copy(message = msg + e.message))
//  //        case e: Throwable        =>
//  //          logger.error(e.toString)
//  //          logger.error(e.getStackTrace.mkString("\n"))
//  //          left(ExecutionError(msg + e.toString))
//  //      }
//  //  } catch {
//  //    case e: ModelError       =>
//  //      logger.trace(e)
//  //      left(e.copy(message = msg + e.message))
//  //    case e: ValidationErrors =>
//  //      logger.trace(e)
//  //      left(e)
//  //    case e: InsertErrors     =>
//  //      logger.trace(e)
//  //      left(e.copy(message = Some(msg + e.msg)))
//  //    case e: ExecutionError   =>
//  //      logger.trace(e)
//  //      left(e.copy(message = msg + e.message))
//  //    case e: Throwable        =>
//  //      logger.error(e.toString)
//  //      logger.error(e.getStackTrace.mkString("\n"))
//  //      left(ExecutionError(msg + e.toString))
//  //  }
//}
