package molecule.core.marshalling

import molecule.base.util.exceptions.MoleculeError
import molecule.boilerplate.ast.Model._
import molecule.core.api.TxReport
import scala.concurrent.Future

trait MoleculeRpc {

  def query[Tpl](
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, List[Tpl]]]

  def save(
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeError, TxReport]]

  def insert(
    proxy: ConnProxy,
    tplElements: List[Element],
    tplsSerialized: Array[Byte],
    txElements: List[Element],
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

//  // Connection pool ...............................
//
//  def clearConnPool: Future[Unit]
}
