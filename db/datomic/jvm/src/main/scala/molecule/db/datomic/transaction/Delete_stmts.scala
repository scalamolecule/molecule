package molecule.db.datomic.transaction

import datomic.Peer
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.{Delete, Delete2Data}
import molecule.db.datomic.facade.DatomicConn_JVM
import molecule.db.datomic.query.DatomicModel2Query

trait Delete_stmts extends DatomicTxBase_JVM with Delete2Data with MoleculeLogging { self: Delete =>

  def getStmtsData(
    conn: DatomicConn_JVM,
    elements: List[Element],
    isMultiple: Boolean
  ): Data = {
    initTxBase(elements)
    val (eids, filterElements) = resolve(elements, Nil, Nil, true)

    if (!isMultiple && eids.length > 1)
      throw MoleculeException(
        s"Please provide explicit `delete.multiple` to delete multiple entities " +
          s"(found ${eids.length} matching entities)."
      )

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
      val count   = eidRows.size()
      if (!isMultiple && count > 1) {
        throw MoleculeException(
          s"Please provide explicit `delete.multiple` to delete multiple entities " +
            s"(found $count matching entities)."
        )
      }
      eidRows.forEach(eidRow => addRetractEntityStmt(eidRow.get(0)))
    }

    val deleteStrs = "DELETE:" +: elements :+ "" :+ stmts.toArray().mkString("\n")
    logger.debug(deleteStrs.mkString("\n").trim)

    stmts
  }
}