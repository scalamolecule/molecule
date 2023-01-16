package molecule.db.datomic.api.action

import molecule.boilerplate.ast.Model._
import molecule.core.api.action.UpdateApi
import molecule.core.api.{Connection, TxReport}
import molecule.core.transaction.Update
import molecule.db.datomic.facade.DatomicConn_JVM
import molecule.db.datomic.transaction.{DatomicTxBase_JVM, Update_stmts}
import scala.concurrent.{ExecutionContext, Future}

class DatomicUpdateApiImpl(
  elements: List[Element],
  isUpsert: Boolean = false,
  isMultiple: Boolean = false,
) extends UpdateApi with DatomicTxBase_JVM {

  // Modifiers
  override def multiple: UpdateApi = new DatomicUpdateApiImpl(elements, isUpsert, true)

  // Actions
  override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = tryFuture {
    val conn  = conn0.asInstanceOf[DatomicConn_JVM]
    val stmts = (new Update(conn.proxy.uniqueAttrs, isUpsert, isMultiple) with Update_stmts)
      .getStmts(conn, elements)
    conn.transact(stmts)
  }
}
