package molecule.core.marshalling

import java.nio.ByteBuffer
import boopickle.Default._
import molecule.base.error._
import molecule.core.ast.DataModel.Element
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling.serialize.PickleTpls
import molecule.core.spi.TxReport
import molecule.core.util.Executor._
import molecule.core.util.MoleculeLogging
import sttp.tapir._
import sttp.tapir.server.ServerEndpoint
import scala.concurrent.Future
import scala.language.implicitConversions

abstract class MoleculeServerEndpoints(rpc: MoleculeRpc)
  extends MoleculeEndpoints with MoleculeLogging {

  private val msg = "RPC failed on server: "

  // Execute Molecule action corresponding to each Tapir endpoint
  private def mkServerEndpoint(
    endpoint: PublicEndpoint[ByteBuffer, MoleculeError, ByteBuffer, Any],
    executeAction: ByteBuffer => Future[Either[MoleculeError, ByteBuffer]]
  ): ServerEndpoint[Any, Future] = {
    endpoint.serverLogic { args =>
      executeAction(args)
        .recover {
          case e: ModelError =>
            logger.warn(e.toString)
            Left(e.copy(message = msg + e.message))

          case e: ValidationErrors =>
            logger.warn(e.toString)
            Left(e)

          case e: InsertErrors =>
            logger.warn(e)
            Left(e.copy(message = Some(msg + e.msg)))

          case e: ExecutionError =>
            logger.error(e)
            Left(e.copy(message = msg + e.message))

          case e: Throwable =>
            logger.error(e.toString)
            logger.error(e.getStackTrace.mkString("\n"))
            Left(ExecutionError(msg + e.toString))
        }
    }
  }


  /** Tapir server endpoints to Molecule actions
   *
   * Add to Tapir backend server of your choice like this:
   *
   * val server = NettyFutureServer()
   *   .port(8080)
   *   .addEndpoints(moleculeServerEndpoints)
   *   ...
   */
  val moleculeServerEndpoints: List[ServerEndpoint[Any, Future]] = List(
    mkServerEndpoint(moleculeEndpoint_Query, executeQuery),
    mkServerEndpoint(moleculeEndpoint_QueryOffset, executeQueryOffset),
    mkServerEndpoint(moleculeEndpoint_QueryCursor, executeQueryCursor),
    mkServerEndpoint(moleculeEndpoint_QueryStream, executeQueryStream),
    mkServerEndpoint(moleculeEndpoint_Save, executeSave),
    mkServerEndpoint(moleculeEndpoint_Insert, executeInsert),
    mkServerEndpoint(moleculeEndpoint_Update, executeUpdate),
    mkServerEndpoint(moleculeEndpoint_Delete, executeDelete),
  )


  /** Server logic
   *
   * Pattern of each action:
   * 1. Unpickle arguments from client
   * 2. Execute action on server (shared MoleculeRpc actions come in handy here)
   * 3. Pickle result for transfer to client
   * */

  def executeQuery(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
    val (proxy, elements, limit) =
      Unpickle[(ConnProxy, List[Element], Option[Int])].fromBytes(args)
    rpc
      .query[Any](proxy, elements, limit)
      .map {
        case Right(result) => Right(PickleTpls(elements, false).getPickledTpls(result))
        case Left(err)     => Left(err)
      }
  }

  def executeQueryStream(args: ByteBuffer) = ???

  def executeQueryOffset(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
    val (proxy, elements, limit, offset) =
      Unpickle[(ConnProxy, List[Element], Option[Int], Int)].fromBytes(args: ByteBuffer)
    rpc
      .queryOffset[Any](proxy, elements, limit, offset)
      .map {
        case Right((tpls, limit, more)) =>
          Right(PickleTpls(elements, false).pickleOffset(tpls, limit, more))
        case Left(err)                  => Left(err)
      }
  }

  def executeQueryCursor(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
    val (proxy, elements, limit, cursor) =
      Unpickle[(ConnProxy, List[Element], Option[Int], String)].fromBytes(args: ByteBuffer)
    rpc
      .queryCursor[Any](proxy, elements, limit, cursor)
      .map {
        case Right((tpls, cursor, more)) =>
          Right(PickleTpls(elements, false).pickleCursor(tpls, cursor, more))
        case Left(err)                   => Left(err)
      }
  }

  def executeSave(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
    val (proxy, elements) = Unpickle[(ConnProxy, List[Element])].fromBytes(args)
    rpc
      .save(proxy, elements)
      .map {
        case Right(txReport) => Right(Pickle.intoBytes[TxReport](txReport))
        case Left(err)       => Left(err)
      }
  }

  def executeInsert(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
    val (proxy, tplElements, tplsSerialized) =
      Unpickle[(ConnProxy, List[Element], ByteBuffer)].fromBytes(args: ByteBuffer)
    rpc
      .insert(proxy, tplElements, tplsSerialized)
      .map {
        case Right(txReport) => Right(Pickle.intoBytes[TxReport](txReport))
        case Left(err)       => Left(err)
      }
  }

  def executeUpdate(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
    val (proxy, elements, isUpsert) =
      Unpickle[(ConnProxy, List[Element], Boolean)].fromBytes(args: ByteBuffer)
    rpc
      .update(proxy, elements, isUpsert)
      .map {
        case Right(txReport) => Right(Pickle.intoBytes[TxReport](txReport))
        case Left(err)       => Left(err)
      }
  }

  def executeDelete(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
    val (proxy, elements) =
      Unpickle[(ConnProxy, List[Element])].fromBytes(args: ByteBuffer)
    rpc
      .delete(proxy, elements)
      .map {
        case Right(txReport) => Right(Pickle.intoBytes[TxReport](txReport))
        case Left(err)       => Left(err)
      }
  }
}