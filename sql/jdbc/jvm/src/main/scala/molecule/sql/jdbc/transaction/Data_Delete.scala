package molecule.sql.jdbc.transaction

import datomic.Peer
import molecule.base.error.ExecutionError
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.DeleteExtraction
import molecule.core.transaction.ops.DeleteOps
import molecule.sql.core.query.SqlModel2Query
import molecule.sql.jdbc.facade.JdbcConn_jvm
import scala.collection.mutable

trait Data_Delete extends JdbcBase_JVM with DeleteOps with MoleculeLogging { self: DeleteExtraction =>

  def getStmtsData(
    conn: JdbcConn_jvm,
    elements: List[Element],
    idIndex: Int = 0,
    debug: Boolean = true
  ): Int = { // todo
//    initTxBase(elements, idIndex)
//    val (ids, filterElements) = resolve(elements, Nil, Nil, true)
//
//    val (filterQuery, inputs) = if (ids.isEmpty && filterElements.nonEmpty) {
//      val filterElements1 = AttrOneManLong("_Generic", "id", V) +: filterElements
//      val (query, inputs) = new SqlModel2Query[Any](filterElements1).getEidQueryWithInputs
//      (Some(query), inputs)
//    } else {
//      (None, Nil)
//    }
//
//    lazy val db = conn.sqlConn.db()
//    val ids1 = filterQuery.fold(ids)(query =>
//      Peer.q(query, db +: inputs: _*).asScala.toList.map(_.get(0))
//    )
//
//    // Add retract stmts
//    ids1.foreach(addRetractEntityStmt)
//
//    // Prevent deleting mandatory referenced entities
//    if (conn.proxy.hasMandatoryRefs) {
//      val referrers = Peer.q(
//        s"""[:find  ?ns ?attr ?refs
//           | :in    $$ [?ids ...]
//           | :where [?refs ?a ?ids]
//           |        [?a :db/ident ?attrIdent]
//           |        [(namespace ?attrIdent) ?ns]
//           |        [(name ?attrIdent) ?attr]
//           |]""".stripMargin, db, ids1.asJava
//      )
//      if (referrers.size() != 0) {
//        val refIds = mutable.Map.empty[String, List[Long]]
//        referrers.forEach { row =>
//          val refAttr = s"${row.get(0)}.${row.get(1)}"
//          refIds.get(refAttr).fold[Unit](
//            refIds.addOne(refAttr -> List(row.get(2).asInstanceOf[Long]))
//          )(curRefIds =>
//            refIds(refAttr) = curRefIds :+ row.get(2).asInstanceOf[Long]
//          )
//        }
//        if (refIds.nonEmpty) {
//          val refAttrsStr = refIds.toList.sortBy(_._1).map {
//            case (refAttr, ids) => s"$refAttr: List(${ids.mkString(", ")})"
//          }
//          throw ExecutionError(
//            s"""Can't delete entities referenced by mandatory ref attributes of other entities:
//               |  ${refAttrsStr.mkString("\n  ")}
//               |""".stripMargin
//          )
//        }
//      }
//    }
//    if (debug) {
//      val deleteStrs = "DELETE:" +: elements :+ "" :+ stmts.toArray().mkString("\n")
//      logger.debug(deleteStrs.mkString("\n").trim)
//    }
//    stmts
    ???
  }
}