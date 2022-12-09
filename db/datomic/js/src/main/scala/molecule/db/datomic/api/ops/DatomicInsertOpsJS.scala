package molecule.db.datomic.api.ops

import molecule.base.util.exceptions.MoleculeException
import molecule.core.api.ops.InsertOps
import molecule.core.api.{Connection, TxReport}
import zio.ZIO
import scala.concurrent.{ExecutionContext, Future}


class DatomicInsertOpsJS(edn: String) extends InsertOps {

  override def run: ZIO[Connection, MoleculeException, TxReport] = ???

  override def transactAsync(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = ???

  override def transact(implicit conn: Connection): TxReport = {
//    conn.asInstanceOf[Connection].transact(stmts)
    new TxReport{
      def tx = 42L
      def eids: List[Long] = List(123)
    }
  }
}
