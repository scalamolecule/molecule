package molecule.db.datomic.api

import molecule.base.util.exceptions.MoleculeException
import molecule.core.api.{Connection, InsertOps, TxReport}
import molecule.db.datomic.facade.Conn_Peer
import molecule.db.datomic.transaction.InsertStmtsMaker
import zio.ZIO
import scala.concurrent.{ExecutionContext, Future}

class DatomicInsertOps(insertStmtsMaker: InsertStmtsMaker) extends InsertOps {

  override def run: ZIO[Connection, MoleculeException, TxReport] = ???

  override def transactAsync(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = ???

  override def transact(implicit conn: Connection): TxReport = {
    val stmts = insertStmtsMaker.getStmts
    println("---")
    stmts.forEach(stmt => println(stmt))
    conn.asInstanceOf[Conn_Peer].transact(stmts)
  }
}
