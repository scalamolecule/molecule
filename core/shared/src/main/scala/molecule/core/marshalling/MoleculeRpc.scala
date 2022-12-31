package molecule.core.marshalling

import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.Model._
import molecule.core.api.TxReport
import scala.concurrent.Future


trait MoleculeRpc {

  def transact(
    elements: Seq[Element]
  ): Future[Either[MoleculeException, TxReport]]

  def transactEdn(
    proxy: ConnProxy,
    edn: String,
    uriAttrs: Set[String] = Set.empty[String]
  ): Future[Either[MoleculeException, TxReport]]

  def query(
    proxy: ConnProxy,
    elements: Seq[Element]
  ): Future[Either[MoleculeException, (Seq[Element], List[List[Int]])]]

  //  def transact(
  //    proxy: ConnProxy,
  //    stmtsEdn: String,
  //    uriAttrs: Set[String]
  //  ): Future[TxReportRPC]
  //
  //  def transact(
  //    proxy: ConnProxy,
  //    stmtsData: (String, Set[String])
  //  ): Future[TxReportRPC] = transact(proxy, stmtsData._1, stmtsData._2)
  //
  //  def query2packed(
  //    proxy: ConnProxy,
  //    datalogQuery: String,
  //    rules: Seq[String],
  //    l: Seq[(Int, String, String)],
  //    ll: Seq[(Int, String, Seq[String])],
  //    lll: Seq[(Int, String, Seq[Seq[String]])],
  //    limit: Int,
  //    offset: Int,
  //    cursor: String,
  //    cursorQuery: String,
  //    obj: Obj,
  //    nestedLevels: Int,
  //    isOptNested: Boolean,
  //    refIndexes: List[List[Int]],
  //    tacitIndexes: List[List[Int]],
  //    sortCoordinates: List[List[SortCoordinate]]
  //  ): Future[(String, String, Int)]
  //
  //  def schemaHistoryQuery2packed(
  //    proxy: ConnProxy,
  //    datalogQuery: String,
  //    obj: Obj,
  //    schemaAttrs: Seq[SchemaAttr],
  //    sortCoordinates: List[List[SortCoordinate]]
  //  ): Future[(String, Int)]
  //
  //  def index2packed(
  //    proxy: ConnProxy,
  //    api: String,
  //    index: String,
  //    indexArgs: IndexArgs,
  //    attrs: Seq[String],
  //    sortCoordinates: List[List[SortCoordinate]]
  //  ): Future[(String, String, Int)]
  //
  //
  //  // Schema ...............................
  //
  //  def getAttrValues(
  //    proxy: ConnProxy,
  //    datalogQuery: String,
  //    card: Int,
  //    tpe: String
  //  ): Future[List[String]]
  //
  //
  //  def getEntityAttrKeys(
  //    proxy: ConnProxy,
  //    datalogQuery: String
  //  ): Future[List[String]]
  //
  //  def getEnumHistory(proxy: ConnProxy): Future[List[(String, Int, Long, Date, String, Boolean)]]
  //
  //
  //  // Entity api ....................................
  //
  //  def rawValue(proxy: ConnProxy, eid: Long, attr: String): Future[String]
  //
  //  def asMap(proxy: ConnProxy, eid: Long, depth: Int, maxDepth: Int): Future[String]
  //
  //  def asList(proxy: ConnProxy, eid: Long, depth: Int, maxDepth: Int): Future[String]
  //
  //  def attrs(proxy: ConnProxy, eid: Long): Future[List[String]]
  //
  //  def apply(proxy: ConnProxy, eid: Long, attr: String): Future[String]
  //
  //  @PathName("apply-many") // sloth wants this for overloaded method name
  //  def apply(proxy: ConnProxy, eid: Long, attrs: List[String]): Future[List[String]]
  //
  //  def graphDepth(proxy: ConnProxy, eid: Long, maxDepth: Int): Future[String]
  //
  //  def graphCode(proxy: ConnProxy, eid: Long, maxDepth: Int): Future[String]
  //
  //
  //  // Connection pool ...............................
  //
  //  def clearConnPool: Future[Unit]
  //
  //
  //  // Helpers ...............................
  //
  //  def basisT(proxy: ConnProxy): Future[Long]
}
