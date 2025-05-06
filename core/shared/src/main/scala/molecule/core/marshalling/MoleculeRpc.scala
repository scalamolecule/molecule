package molecule.core.marshalling

import java.nio.ByteBuffer
//import cats.effect.IO
import molecule.base.error.MoleculeError
import molecule.core.ast.DataModel.*
import molecule.core.spi.{Conn, TxReport}
//import zio.stream.ZStream
import scala.concurrent.Future


/**
 * Unified shared RPC API for client/backend (JS/JVM)
 */
trait MoleculeRpc {

  def query[Tpl](
    proxy: ConnProxy,
    elements: List[Element],
    limit: Option[Int]
  ): Future[Either[MoleculeError, List[Tpl]]]

  def queryOffset[Tpl](
    proxy: ConnProxy,
    elements: List[Element],
    limit: Option[Int],
    offset: Int
  ): Future[Either[MoleculeError, (List[Tpl], Int, Boolean)]]

  def queryCursor[Tpl](
    proxy: ConnProxy,
    elements: List[Element],
    limit: Option[Int],
    cursor: String
  ): Future[Either[MoleculeError, (List[Tpl], String, Boolean)]]

  def subscribe[Tpl](
    proxy: ConnProxy,
    elements: List[Element],
    limit: Option[Int],
    callback: List[Tpl] => Unit
  ): Future[Unit]

  def unsubscribe(
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, Unit]] = ???

  def save(
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, TxReport]]

  def insert(
    proxy: ConnProxy,
    tplElements: List[Element],
    tplsSerialized: ByteBuffer
  ): Future[Either[MoleculeError, TxReport]]

  def update(
    proxy: ConnProxy,
    elements: List[Element],
    isUpsert: Boolean = false
  ): Future[Either[MoleculeError, TxReport]]

  def delete(
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, TxReport]]
}
