//package molecule.sql.core.transaction.strategy.update
//
//import java.sql.Connection
//import molecule.boilerplate.ast.Model.Element
//import molecule.sql.core.query.Model2SqlQuery
//import molecule.sql.core.transaction.strategy.SqlOps
//import scala.collection.mutable
//import scala.collection.mutable.ListBuffer
//
//case class UpdateIdsQuery(
//  parent: UpdateAction,
//  sqlConn: Connection,
//  sqlOps: SqlOps,
//  isUpsert: Boolean,
//  isRefUpdate: Boolean,
//  ns: String,
//  action: String,
//) extends UpdateAction(parent, sqlConn, sqlOps, isUpsert, ns) {
//
//  cols += s"$ns.id"
////  private[transaction] var nsCount        = 1
////  private[transaction] val filterElements = ListBuffer.empty[Element]
////  private[transaction] val joins          = ListBuffer.empty[String]
//  private[transaction] var refIds         = Array.empty[mutable.ArrayBuilder[Long]]
//
//
//  override def rootAction: UpdateAction = parent.rootAction
//
//  override def execute(): Unit = {
//    // Retrieve and assign ref ids
//    curStmt
//  }
//
//  override def curStmt: String = {
//    if (ids.nonEmpty && !isRefUpdate) {
//      // No need to query if we have ids and no refs
//      refIds = Array(new mutable.ArrayBuilder.ofLong().addAll(ids))
//      "Ids supplied: " + ids
//
//    } else {
//      // Query for ids of each namespace
//      val idCols   = cols.mkString(",\n  ")
//      val joinType = if (isUpsert) "LEFT" else "INNER"
//      val refJoins = if (joins.isEmpty) "" else
//        joins.mkString(s"\n  $joinType JOIN ", s"\n  $joinType JOIN ", "")
//      val clauses  = m2q(filterElements).getWhereClauses2.mkString(" AND\n  ")
//      val query    =
//        s"""SELECT DISTINCT
//           |  $idCols
//           |FROM $ns$refJoins
//           |WHERE
//           |  $clauses""".stripMargin
//
//      refIds = new Array[mutable.ArrayBuilder[Long]](nsCount)
//        .map(_ => new mutable.ArrayBuilder.ofLong())
//
//      val resultSet = sqlConn.prepareStatement(query).executeQuery()
//      var nsIndex   = 0
//      while (resultSet.next()) {
//        nsIndex = 0
//        while (nsIndex < nsCount) {
//          refIds(nsIndex) += resultSet.getLong(nsIndex + 1)
//          nsIndex += 1
//        }
//      }
//      //      refIds.foreach(x => println(x.result().mkString("Array(", ", ", ")")))
//      query
//    }
//  }
//
//  override def render(indent: Int): String = {
//    recurseRender(indent, action)
//  }
//}
