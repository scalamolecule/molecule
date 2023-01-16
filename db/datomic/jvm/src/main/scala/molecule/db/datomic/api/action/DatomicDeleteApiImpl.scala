package molecule.db.datomic.api.action

import molecule.boilerplate.ast.Model._
import molecule.core.api.action.DeleteApi
import molecule.core.api.{Connection, TxReport}
import molecule.core.transaction.Delete
import molecule.db.datomic.facade.DatomicConn_JVM
import molecule.db.datomic.transaction.Delete_stmts
import scala.concurrent.{ExecutionContext, Future}

class DatomicDeleteApiImpl(
  elements: List[Element],
  isMultiple: Boolean = false
) extends DeleteApi {

  // Modifiers
  override def multiple: DeleteApi = new DatomicDeleteApiImpl(elements, true)

  // Actions
  override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = tryFuture {
    val conn  = conn0.asInstanceOf[DatomicConn_JVM]
    val stmts = (new Delete with Delete_stmts).getStmtsData(conn, elements, isMultiple)
    conn.transact(stmts)
  }
}
