package molecule.db.datomic.api.ops

import molecule.boilerplate.ast.Model._
import molecule.core.api.ops.UpdateOps
import molecule.core.api.{Connection, TxReport}
import molecule.core.transaction.Update
import molecule.db.datomic.facade.DatomicConn_JVM
import molecule.db.datomic.transaction.{DatomicTxBase_JVM, Update_stmts}
import scala.concurrent.{ExecutionContext, Future}

class DatomicUpdateOpsImpl(
  elements: List[Element],
  isUpsert: Boolean = false,
  isMultiple: Boolean = false,
) extends UpdateOps with DatomicTxBase_JVM {

  // Modifiers
  override def multiple: UpdateOps = new DatomicUpdateOpsImpl(elements, isUpsert, true)

  // Actions
  override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = {
    Future {
      val conn  = conn0.asInstanceOf[DatomicConn_JVM]
      val stmts = (new Update(conn.proxy.uniqueAttrs, isUpsert, isMultiple) with Update_stmts)
        .getStmts(conn, elements)
      conn.transact(stmts)
    }.flatten
  }
}
