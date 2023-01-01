package molecule.db.datomic.api.ops

import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.Model._
import molecule.core.api.ops.InsertOps
import molecule.core.api.{Connection, TxReport}
import molecule.core.transaction.Insert
import molecule.db.datomic.facade.DatomicConn_JVM
import molecule.db.datomic.transaction.Insert_stmts
import zio.ZIO
import scala.concurrent.{ExecutionContext, Future}

class DatomicInsertOpsJVM(elements: Seq[Element], data: Seq[Product]) extends InsertOps {

  override def run: ZIO[Connection, MoleculeException, TxReport] = ???

  override def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = {
    Future {
      try {
        val stmts = (new Insert with Insert_stmts)
          .getStmts(elements, data)
        println("---")
        stmts.forEach(stmt => println(stmt))
        conn.asInstanceOf[DatomicConn_JVM].transact(stmts)
      } catch {
        case e: Throwable => Future.failed(e)
      }
    }.flatten
  }
}
