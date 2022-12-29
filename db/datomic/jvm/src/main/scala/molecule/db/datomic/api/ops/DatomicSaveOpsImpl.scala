package molecule.db.datomic.api.ops

import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.MoleculeModel._
import molecule.core.api.ops.SaveOps
import molecule.core.api.{Connection, TxReport}
import molecule.db.datomic.facade.DatomicConn_JVM
import molecule.db.datomic.transaction.SaveStmts
import zio.ZIO
import scala.concurrent.{ExecutionContext, Future}

class DatomicSaveOpsImpl(elements: Seq[Element]) extends SaveOps {

  override def run: ZIO[Connection, MoleculeException, TxReport] = ???

  override def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = {
    Future {
      try {
        val stmts = new SaveStmts(elements).getStmts
        conn.asInstanceOf[DatomicConn_JVM].transact(stmts)
      } catch {
        case e: Throwable => Future.failed(e)
      }
    }.flatten
  }
}
