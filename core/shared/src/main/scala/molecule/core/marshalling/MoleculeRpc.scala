package molecule.core.marshalling

import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.Model._
import molecule.core.api.TxReport
import scala.concurrent.Future

trait MoleculeRpc {

  def query(
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeException, DTO]]

  def save(
    proxy: ConnProxy,
    elements: List[Element]
  ): Future[Either[MoleculeException, TxReport]]

  def insert(
    proxy: ConnProxy,
    tplElements: List[Element],
    tplData: DTO,
    txElements: List[Element],
  ): Future[Either[MoleculeException, TxReport]]

  def update(
    proxy: ConnProxy,
    elements: List[Element],
    isUpsert: Boolean = false,
    isMultiple: Boolean = false,
  ): Future[Either[MoleculeException, TxReport]]

  def delete(
    proxy: ConnProxy,
    elements: List[Element],
    isMultiple: Boolean = false
  ): Future[Either[MoleculeException, TxReport]]


  //  def transact(
  //    elements: List[Element]
  //  ): Future[Either[MoleculeException, TxReport]]
  //
  //  def insert(
  //    proxy: ConnProxy,
  //    edn: String,
  //    uriAttrs: Set[String] = Set.empty[String]
  //  ): Future[Either[MoleculeException, TxReport]]


  // Connection pool ...............................

  def clearConnPool: Future[Unit]
}
