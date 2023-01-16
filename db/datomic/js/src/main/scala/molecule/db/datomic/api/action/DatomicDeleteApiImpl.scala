package molecule.db.datomic.api.action

import molecule.boilerplate.ast.Model._
import molecule.core.api.action.DeleteApi
import molecule.core.api.{Connection, TxReport}
import molecule.db.datomic.facade.DatomicConn_JS
import scala.concurrent.{ExecutionContext, Future}

class DatomicDeleteApiImpl(
  elements: List[Element],
  isMultiple: Boolean
) extends DeleteApi {

  override def multiple: DeleteApi = new DatomicDeleteApiImpl(elements, true)

  override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = {
    val conn = conn0.asInstanceOf[DatomicConn_JS]
    conn.rpc.delete(conn.proxy, elements, isMultiple).future
  }
}
