package molecule.db.datomic.api.ops

import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.ast.Model._
import molecule.core.api.ops.SaveOps
import molecule.core.api.{Connection, TxReport}
import molecule.core.transaction.Save
import molecule.db.datomic.facade.DatomicConn_JS
import molecule.db.datomic.transaction.Save_edn
import zio.ZIO
import scala.concurrent._
//import molecule.core.util.Executor._


class DatomicSaveOpsImpl(elements: Seq[Element]) extends SaveOps {

  override def run: ZIO[Connection, MoleculeException, TxReport] = ???

  override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = {
    Future {
      try {
        val conn = conn0.asInstanceOf[DatomicConn_JS]
        val edn  = (new Save() with Save_edn).getEdn(elements)
        conn.rpc.transactEdn(conn.proxy, edn).toFuture
      } catch {
        case e: Throwable => Future.failed(e)
      }
    }.flatten
  }
}
