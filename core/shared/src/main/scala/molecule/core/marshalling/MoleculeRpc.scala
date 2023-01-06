package molecule.core.marshalling

import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.Model._
import molecule.core.api.TxReport
import scala.concurrent.Future

trait MoleculeRpc {

  def query(
    proxy: ConnProxy,
    elements: Seq[Element]
  ): Future[Either[MoleculeException, DTO]]

  def save(
    proxy: ConnProxy,
    elements: Seq[Element]
  ): Future[Either[MoleculeException, TxReport]]

  def insert(
    proxy: ConnProxy,
    tplElements: Seq[Element],
    tplData: DTO,
    txElements: Seq[Element],
  ): Future[Either[MoleculeException, TxReport]]

  def update(
    proxy: ConnProxy,
    elements: Seq[Element],
    isUpsert: Boolean = false,
    isMultiple: Boolean = false,
  ): Future[Either[MoleculeException, TxReport]]

  def delete(
    proxy: ConnProxy,
    elements: Seq[Element],
    isMultiple: Boolean = false
  ): Future[Either[MoleculeException, TxReport]]


  //  def transact(
  //    elements: Seq[Element]
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
