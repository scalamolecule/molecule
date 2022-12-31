package molecule.db.datomic.api.ops

import datomic.Peer
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.Model._
import molecule.core.api.ops.DeleteOps
import molecule.core.api.{Connection, TxReport}
import molecule.core.transaction.Delete
import molecule.core.util.fns
import molecule.db.datomic.facade.DatomicConn_JVM
import molecule.db.datomic.transaction.{DatomicTxBase_JVM, Delete_stmts}
import zio.ZIO
import scala.concurrent.{ExecutionContext, Future}

class DatomicDeleteOpsImpl(
  elements: Seq[Element],
  isMultiple: Boolean = false
) extends DatomicTxBase_JVM with DeleteOps {

  // Modifiers
  override def multiple: DeleteOps = new DatomicDeleteOpsImpl(elements, true)


  // Actions

  override def run: ZIO[Connection, MoleculeException, TxReport] = ???

  override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = {
    Future {
      try {
        initTxBase(elements)
        val conn                        = conn0.asInstanceOf[DatomicConn_JVM]
        val (eids, filterQuery, inputs) = (new Delete with Delete_stmts).getStmtsData(elements, isMultiple)
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
