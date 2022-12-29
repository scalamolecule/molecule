package molecule.db.datomic.api.ops

import datomic.Peer
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.MoleculeModel._
import molecule.core.api.ops.DeleteOps
import molecule.core.api.{Connection, TxReport}
import molecule.db.datomic.facade.DatomicConn_JVM
import molecule.db.datomic.transaction.{DatomicTransactionBase, DeleteStmts}
import zio.ZIO
import scala.concurrent.{ExecutionContext, Future}

class DatomicDeleteOpsImpl(
  elements: Seq[Element],
  isMultiple: Boolean = false
) extends DatomicTransactionBase(elements) with DeleteOps {

  // Modifiers
  override def multiple: DeleteOps = new DatomicDeleteOpsImpl(elements, true)


  // Actions

  override def run: ZIO[Connection, MoleculeException, TxReport] = ???

  override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = {
    Future {
      try {
        val conn                        = conn0.asInstanceOf[DatomicConn_JVM]
        val (eids, filterQuery, inputs) = new DeleteStmts(conn.schema.uniqueAttrs, elements, isMultiple).resolve

        filterQuery.fold {
          eids.foreach(addRetractEntityStmt)
        } { query =>
          val db      = conn.peerConn.db()
          val eidRows = Peer.q(query, db +: inputs: _*)
          val count   = eidRows.size()
          if (!isMultiple && count > 1)
            multipleModifierMissing(count)
          eidRows.forEach(eidRow => addRetractEntityStmt(eidRow.get(0)))
        }
        println("\n\n--- DELETE -----------------------------------------------------------------------")
        elements.foreach(println)
        println("---")
        stmts.forEach(stmt => println(stmt))
        conn.transact(stmts)

      } catch {
        case e: Throwable => Future.failed(e)
      }
    }.flatten
  }
}
