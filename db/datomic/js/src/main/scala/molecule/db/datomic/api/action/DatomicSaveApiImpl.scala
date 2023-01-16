package molecule.db.datomic.api.action

import molecule.boilerplate.ast.Model._
import molecule.core.api.action.SaveApi
import molecule.core.api.{Connection, TxReport}
import molecule.db.datomic.facade.DatomicConn_JS
import scala.concurrent._

class DatomicSaveApiImpl(elements: List[Element]) extends SaveApi {

  override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = {
    val conn = conn0.asInstanceOf[DatomicConn_JS]
    conn.rpc.save(conn.proxy, elements).future
  }
}
