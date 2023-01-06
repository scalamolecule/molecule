package molecule.db.datomic.api.ops

import molecule.boilerplate.ast.Model._
import molecule.core.api.ops.SaveOps
import molecule.core.api.{Connection, TxReport}
import molecule.db.datomic.facade.DatomicConn_JS
import scala.concurrent._

class DatomicSaveOpsImpl(elements: Seq[Element]) extends SaveOps {

  override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = {
    val conn = conn0.asInstanceOf[DatomicConn_JS]
    conn.rpc.save(conn.proxy, elements).future
  }
}
