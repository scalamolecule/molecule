package molecule.db.datomic.api.ops

import molecule.boilerplate.ast.Model._
import molecule.core.api.ops.UpdateOps
import molecule.core.api.{Connection, TxReport}
import molecule.db.datomic.facade.DatomicConn_JS
import scala.concurrent.{ExecutionContext, Future}

class DatomicUpdateOpsImpl(
  elements: Seq[Element],
  isUpsert: Boolean,
  isMultiple: Boolean,
) extends UpdateOps {

  // Modifiers
  override def multiple: UpdateOps = new DatomicUpdateOpsImpl(elements, isUpsert, true)

  override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = {
    val conn = conn0.asInstanceOf[DatomicConn_JS]
    conn.rpc.update(conn.proxy, elements, isUpsert, isMultiple).future
  }
}
