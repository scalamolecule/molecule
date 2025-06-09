package molecule.server.core

import java.nio.ByteBuffer
import boopickle.Default.*
import molecule.base.error.MoleculeError
import molecule.core.ast.DataModel
import molecule.core.util.MoleculeLogging
import molecule.db.core.marshalling.Boopicklers.*
import molecule.db.core.marshalling.serialize.PickleTpls
import molecule.db.core.marshalling.{ConnProxy, MoleculeEndpoints, MoleculeRpc}
import molecule.db.core.spi.TxReport
import molecule.db.core.util.Executor.*
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
    val (proxy, dataModel, limit) =
      Unpickle[(ConnProxy, DataModel, Option[Int])].fromBytes(args)
    rpc
      .query[Any](proxy, dataModel, limit)
      .map {
        case Right(result) => Right(PickleTpls(dataModel, false).getPickledTpls(result))
        case Left(err)     => Left(err)
      }
  }

  def executeQueryOffset(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
    val (proxy, dataModel, limit, offset) =
      Unpickle[(ConnProxy, DataModel, Option[Int], Int)].fromBytes(args: ByteBuffer)
    rpc
      .queryOffset[Any](proxy, dataModel, limit, offset)
      .map {
        case Right((tpls, limit, more)) =>
          Right(PickleTpls(dataModel, false).pickleOffset(tpls, limit, more))
        case Left(err)                  => Left(err)
      }
  }

  def executeQueryCursor(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
    val (proxy, dataModel, limit, cursor) =
      Unpickle[(ConnProxy, DataModel, Option[Int], String)].fromBytes(args: ByteBuffer)
    rpc
      .queryCursor[Any](proxy, dataModel, limit, cursor)
      .map {
        case Right((tpls, cursor, more)) =>
          Right(PickleTpls(dataModel, false).pickleCursor(tpls, cursor, more))
        case Left(err)                   => Left(err)
      }
  }

  def executeUnsubscribe(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
    val (proxy, dataModel) =
      Unpickle[(ConnProxy, DataModel)].fromBytes(args: ByteBuffer)
    rpc
      .unsubscribe(proxy, dataModel)
      .map {
        case Right(()) => Right(Pickle.intoBytes[Unit](()))
        case Left(err) => Left(err)
      }
  }

  def executeSave(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
    val (proxy, dataModel) = Unpickle[(ConnProxy, DataModel)].fromBytes(args)
    rpc
      .save(proxy, dataModel)
      .map {
        case Right(txReport) => Right(Pickle.intoBytes[TxReport](txReport))
        case Left(err)       => Left(err)
      }
  }

  def executeInsert(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
    val (proxy, tpldataModel, tplsSerialized) =
      Unpickle[(ConnProxy, DataModel, ByteBuffer)].fromBytes(args: ByteBuffer)
    rpc
      .insert(proxy, tpldataModel, tplsSerialized)
      .map {
        case Right(txReport) => Right(Pickle.intoBytes[TxReport](txReport))
        case Left(err)       => Left(err)
      }
  }

  def executeUpdate(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
    val (proxy, dataModel, isUpsert) =
      Unpickle[(ConnProxy, DataModel, Boolean)].fromBytes(args: ByteBuffer)
    rpc
      .update(proxy, dataModel, isUpsert)
      .map {
        case Right(txReport) => Right(Pickle.intoBytes[TxReport](txReport))
        case Left(err)       => Left(err)
      }
  }

  def executeDelete(args: ByteBuffer): Future[Either[MoleculeError, ByteBuffer]] = {
    val (proxy, dataModel) =
      Unpickle[(ConnProxy, DataModel)].fromBytes(args: ByteBuffer)
    rpc
      .delete(proxy, dataModel)
      .map {
        case Right(txReport) => Right(Pickle.intoBytes[TxReport](txReport))
        case Left(err)       => Left(err)
      }
  }
  }