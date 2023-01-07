package molecule.db.datomic.api.ops

import molecule.boilerplate.ast.Model._
import molecule.core.api.ops.InsertOps
import molecule.core.api.{Connection, TxReport}
import molecule.core.marshalling.pack.Tpls2DTO
import molecule.db.datomic.facade.DatomicConn_JS
import scala.concurrent.{ExecutionContext, Future}

class DatomicInsertOpsJS(elements: Seq[Element], tpls: Seq[Product]) extends InsertOps {

  override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = {
    Future { // (catch exceptions before rpc call)
      val conn                      = conn0.asInstanceOf[DatomicConn_JS]
      val (tplElements, txElements) = splitElements(elements)
      val tplData                   = Tpls2DTO(tplElements, tpls).pack
      conn.rpc.insert(conn.proxy, tplElements, tplData, txElements).future
    }.flatten
  }
}
