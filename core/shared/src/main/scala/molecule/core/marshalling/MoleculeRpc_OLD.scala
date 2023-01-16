//package molecule.core.marshalling
//
//import molecule.base.util.exceptions.MoleculeError
//import molecule.boilerplate.ast.Model._
//import molecule.core.api.TxReport
//import scala.concurrent.Future
//
//trait MoleculeRpc_OLD {
//
//  def query(
//    proxy: ConnProxy,
//    elements: List[Element]
//  ): Future[Either[MoleculeError, DTO]]
//
////  def query2[Tpl](
////    proxy: ConnProxy,
////    elements: List[Element]
////  ): Future[Either[MoleculeException, List[Tpl]]]
//
//  def save(
//    proxy: ConnProxy,
//    elements: List[Element]
//  ): Future[Either[MoleculeError, TxReport]]
//
//  def insert(
//    proxy: ConnProxy,
//    tplElements: List[Element],
//    tplData: DTO,
//    txElements: List[Element],
//  ): Future[Either[MoleculeError, TxReport]]
//
//  def update(
//    proxy: ConnProxy,
//    elements: List[Element],
//    isUpsert: Boolean = false,
//    isMultiple: Boolean = false,
//  ): Future[Either[MoleculeError, TxReport]]
//
//  def delete(
//    proxy: ConnProxy,
//    elements: List[Element],
//    isMultiple: Boolean = false
//  ): Future[Either[MoleculeError, TxReport]]
//
//  def ping: Future[Int]
//
//  //  def transact(
//  //    elements: List[Element]
//  //  ): Future[Either[MoleculeException, TxReport]]
//  //
//  //  def insert(
//  //    proxy: ConnProxy,
//  //    edn: String,
//  //    uriAttrs: Set[String] = Set.empty[String]
//  //  ): Future[Either[MoleculeException, TxReport]]
//
//
//  // Connection pool ...............................
//
////  def clearConnPool: Future[Unit]
//}
