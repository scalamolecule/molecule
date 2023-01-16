package molecule.db.datomic.api.action

import molecule.boilerplate.ast.Model._
import molecule.core.api.action.UpdateApi
import molecule.core.api.{Connection, TxReport}
import molecule.db.datomic.facade.DatomicConn_JS
import scala.concurrent.{ExecutionContext, Future}

class DatomicUpdateApiImpl(
  elements: List[Element],
  isUpsert: Boolean,
  isMultiple: Boolean,
) extends UpdateApi {

  // Modifiers
  override def multiple: UpdateApi = new DatomicUpdateApiImpl(elements, isUpsert, true)

  override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = {
    val conn = conn0.asInstanceOf[DatomicConn_JS]
    conn.rpc.update(conn.proxy, elements, isUpsert, isMultiple).future
  }
}
