package molecule.db.core.marshalling

import java.nio.ByteBuffer
import molecule.base.error.MoleculeError
import molecule.core.dataModel.*
import molecule.db.core.spi.TxReport
import scala.concurrent.Future


/**
 * Unified shared RPC API for client/backend (JS/JVM)
 */
trait MoleculeRpc {

  def query[Tpl](
    proxy: ConnProxy,
    dataModel: DataModel,
    limit: Option[Int],
    bindValues: List[Value]
  ): Future[Either[MoleculeError, List[Tpl]]]

  def queryOffset[Tpl](
    proxy: ConnProxy,
    dataModel: DataModel,
    limit: Option[Int],
    offset: Int,
    bindValues: List[Value]
  ): Future[Either[MoleculeError, (List[Tpl], Int, Boolean)]]

  def queryCursor[Tpl](
    proxy: ConnProxy,
    dataModel: DataModel,
    limit: Option[Int],
    cursor: String,
    bindValues: List[Value]
  ): Future[Either[MoleculeError, (List[Tpl], String, Boolean)]]

  def subscribe[Tpl](
    proxy: ConnProxy,
    dataModel: DataModel,
    limit: Option[Int],
    callback: List[Tpl] => Unit
  ): Future[Unit]

  def unsubscribe(
    proxy: ConnProxy,
    dataModel: DataModel
  ): Future[Either[MoleculeError, Unit]]

  def save(
    proxy: ConnProxy,
    dataModel: DataModel
  ): Future[Either[MoleculeError, TxReport]]

  def insert(
    proxy: ConnProxy,
    tpldataModel: DataModel,
    tplsSerialized: ByteBuffer
  ): Future[Either[MoleculeError, TxReport]]

  def update(
    proxy: ConnProxy,
    dataModel: DataModel,
    isUpsert: Boolean = false
  ): Future[Either[MoleculeError, TxReport]]

  def delete(
    proxy: ConnProxy,
    dataModel: DataModel
  ): Future[Either[MoleculeError, TxReport]]
}
