package molecule.db.datomic.api.ops

import molecule.boilerplate.ast.Model._
import molecule.core.api.ops.DeleteOps
import molecule.core.api.{Connection, TxReport}
import molecule.core.transaction.Delete
import molecule.db.datomic.facade.DatomicConn_JVM
import molecule.db.datomic.transaction.{DatomicTxBase_JVM, Delete_stmts}
import scala.concurrent.{ExecutionContext, Future}

class DatomicDeleteOpsImpl(
  elements: Seq[Element],
  isMultiple: Boolean = false
) extends DatomicTxBase_JVM with DeleteOps {

  // Modifiers
  override def multiple: DeleteOps = new DatomicDeleteOpsImpl(elements, true)

  // Actions
  override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = {
    Future {
      val conn  = conn0.asInstanceOf[DatomicConn_JVM]
      val stmts = (new Delete with Delete_stmts).getStmtsData(conn, elements, isMultiple)
      conn.transact(stmts)
    }.flatten
  }
}
