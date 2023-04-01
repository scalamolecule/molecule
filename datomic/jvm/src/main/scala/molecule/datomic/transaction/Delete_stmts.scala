package molecule.datomic.transaction

import datomic.Peer
import molecule.base.error.ExecutionError
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.{DeleteExtraction, DeleteOps}
import molecule.datomic.facade.DatomicConn_JVM
import molecule.datomic.query.DatomicModel2Query
import scala.collection.mutable
import scala.jdk.CollectionConverters.{CollectionHasAsScala, IterableHasAsJava}

trait Delete_stmts extends DatomicTxBase_JVM with DeleteOps with MoleculeLogging { self: DeleteExtraction =>

  def getStmtsData(
    conn: DatomicConn_JVM,
    elements: List[Element]
  ): Data = {
    initTxBase(elements)
    val (eids, filterElements) = resolve(elements, Nil, Nil, true)

    val (filterQuery, inputs) = if (eids.isEmpty && filterElements.nonEmpty) {
      val filterElements1 = AttrOneManLong("_Generic", "e", V) +: filterElements
      val (query, inputs) = new DatomicModel2Query[Any](filterElements1).getEidQueryWithInputs
      (Some(query), inputs)
    } else {
      (None, Nil)
    }

    lazy val db = conn.peerConn.db()
    val eids1 = filterQuery.fold(eids)(query =>
      Peer.q(query, db +: inputs: _*).asScala.toList.map(_.get(0))
    )

    // Add retract stmts
    eids1.foreach(addRetractEntityStmt)

    // Prevent deleting mandatory referenced entities
    if (conn.proxy.hasMandatoryRefs) {
      val referrers = Peer.q(
        s"""[:find  ?ns ?attr ?refs
           | :in    $$ [?eids ...]
           | :where [?refs ?a ?eids]
           |        [?a :db/ident ?attrIdent]
           |        [(namespace ?attrIdent) ?ns]
           |        [(name ?attrIdent) ?attr]
           |]""".stripMargin, db, eids1.asJava
      )
      if (referrers.size() != 0) {
        val refIds = mutable.Map.empty[String, List[Long]]
        referrers.forEach { row =>
          val refAttr = s"${row.get(0)}.${row.get(1)}"
          refIds.get(refAttr).fold[Unit](
            refIds.addOne(refAttr -> List(row.get(2).asInstanceOf[Long]))
          )(curRefIds =>
            refIds(refAttr) = curRefIds :+ row.get(2).asInstanceOf[Long]
          )
        }
        if (refIds.nonEmpty) {
          val refAttrsStr = refIds.toList.sortBy(_._1).map {
            case (refAttr, ids) => s"$refAttr: List(${ids.mkString(", ")})"
          }
          throw ExecutionError(
            s"""Can't delete entities referenced by mandatory ref attributes of other entities:
               |  ${refAttrsStr.mkString("\n  ")}
               |""".stripMargin
          )
        }
      }
    }

    val deleteStrs = "DELETE:" +: elements :+ "" :+ stmts.toArray().mkString("\n")
    logger.debug(deleteStrs.mkString("\n").trim)

    stmts
  }
}