package molecule.db.datomic.api.ops

import molecule.base.util.exceptions.MoleculeException
import molecule.core.api.ops.InsertOps
import molecule.core.api.{Connection, TxReport}
import molecule.db.datomic.facade.DatomicConn_JVM
import molecule.db.datomic.transaction.InsertStmts
import zio.ZIO
import scala.concurrent.{ExecutionContext, Future}

class DatomicInsertOpsJVM(insertStmts: InsertStmts) extends InsertOps {

  override def run: ZIO[Connection, MoleculeException, TxReport] = ???

  override def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = {
    Future {
      try {
        val stmts = insertStmts.getStmts
        println("---")
        stmts.forEach(stmt => println(stmt))
        conn.asInstanceOf[DatomicConn_JVM].transact(stmts)
      } catch {
        case e: Throwable => Future.failed(e)
      }
    }.flatten
  }
}
