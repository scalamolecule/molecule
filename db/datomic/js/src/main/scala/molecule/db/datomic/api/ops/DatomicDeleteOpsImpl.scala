package molecule.db.datomic.api.ops

import molecule.boilerplate.ast.Model._
import molecule.core.api.ops.DeleteOps
import molecule.core.api.{Connection, TxReport}
import molecule.db.datomic.facade.DatomicConn_JS
import scala.concurrent.{ExecutionContext, Future}

class DatomicDeleteOpsImpl(
  elements: Seq[Element],
  isMultiple: Boolean
) extends DeleteOps {

  override def multiple: DeleteOps = new DatomicDeleteOpsImpl(elements, true)

  override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = {
    val conn = conn0.asInstanceOf[DatomicConn_JS]
    conn.rpc.delete(conn.proxy, elements, isMultiple).future
  }
}
