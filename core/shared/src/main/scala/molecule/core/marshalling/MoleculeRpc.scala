package molecule.core.marshalling

import molecule.base.error.MoleculeError
import molecule.boilerplate.ast.Model._
import molecule.core.spi.TxReport
import scala.concurrent.Future

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

  def save(
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, TxReport]]

  def insert(
    proxy: ConnProxy,
    tplElements: List[Element],
    tplsSerialized: Array[Byte]
  ): Future[Either[MoleculeError, TxReport]]

  def update(
    proxy: ConnProxy,
    // Elements before db keyword collision check
    elementsRaw: List[Element],
    // Elements with "_"-suffixed attribute names when colliding with db keyword
    elements: List[Element],
    isUpsert: Boolean = false
  ): Future[Either[MoleculeError, TxReport]]

  def delete(
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, TxReport]]
}
