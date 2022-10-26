package molecule.db.datomic.api

import java.util.{List => jList}
import molecule.base.util.exceptions.MoleculeException
import molecule.core.api.{Connection, InsertOps, TxReport}
import molecule.db.datomic.facade.Conn_Peer
import zio.ZIO
import scala.concurrent.{ExecutionContext, Future}

class DatomicInsertOps(stmts: jList[jList[_]]) extends InsertOps {

  override def run: ZIO[Connection, MoleculeException, TxReport] = ???

  override def transactAsync(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = ???

  override def transact(implicit conn: Connection): TxReport = {
    conn.asInstanceOf[Conn_Peer].transact(stmts)
  }
}
