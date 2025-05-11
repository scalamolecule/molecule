package molecule.server.core

import java.nio.ByteBuffer
import boopickle.Default.*
import molecule.db.core.ast.Element
import molecule.db.core.marshalling.Boopicklers.*
import molecule.db.core.util.Executor.*
import molecule.db.base.error.MoleculeError
import molecule.db.core.marshalling.{ConnProxy, MoleculeEndpoints, MoleculeRpc}
import molecule.db.core.marshalling.serialize.PickleTpls
import molecule.db.core.spi.TxReport
import molecule.db.core.util.MoleculeLogging
import scala.concurrent.Future


abstract class Execution(rpc: MoleculeRpc)
  extends MoleculeEndpoints with MoleculeLogging {

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

  def executeUnsubscribe(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
    val (proxy, elements) =
      Unpickle[(ConnProxy, List[Element])].fromBytes(args: ByteBuffer)
    rpc
      .unsubscribe(proxy, elements)
      .map {
        case Right(()) => Right(Pickle.intoBytes[Unit](()))
        case Left(err) => Left(err)
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