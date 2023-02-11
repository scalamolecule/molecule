package molecule.db.datomic.transaction

import datomic.Peer
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.{DeleteExtraction, DeleteOps}
import molecule.db.datomic.facade.DatomicConn_JVM
import molecule.db.datomic.query.DatomicModel2Query

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

    filterQuery.fold {
      eids.foreach(addRetractEntityStmt)
    } { query =>
      val db      = conn.peerConn.db()
      val eidRows = Peer.q(query, db +: inputs: _*)
      eidRows.forEach(eidRow => addRetractEntityStmt(eidRow.get(0)))
    }

    val deleteStrs = "DELETE:" +: elements :+ "" :+ stmts.toArray().mkString("\n")
    logger.debug(deleteStrs.mkString("\n").trim)

    stmts
  }
}