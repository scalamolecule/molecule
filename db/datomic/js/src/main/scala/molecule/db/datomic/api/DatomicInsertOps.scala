package molecule.db.datomic.api

import java.util.{List => jList}
import molecule.base.util.exceptions.MoleculeException
import molecule.core.api.{Connection, InsertOps, TxReport}
import zio.ZIO
import scala.concurrent.{ExecutionContext, Future}


class DatomicInsertOps(edn: String) extends InsertOps {
  override def transact(implicit conn: Connection): TxReport = {
//    conn.asInstanceOf[Connection].transact(stmts)
    new TxReport{
      override def eids: List[Long] = List(123)
    }
  }
  //    ??? // conn.transact(rows)
  override def run: ZIO[Connection, MoleculeException, TxReport] = ???
  override def transactAsync(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = ???
}
