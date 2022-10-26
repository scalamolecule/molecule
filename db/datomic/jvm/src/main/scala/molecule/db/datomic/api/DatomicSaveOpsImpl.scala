package molecule.db.datomic.api

import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.MoleculeModel._
import molecule.core.api.{Connection, SaveOps, TxReport}
import molecule.db.datomic.facade.Conn_Peer
import molecule.db.datomic.transaction.SaveStmts
import zio.ZIO
import scala.concurrent.{ExecutionContext, Future}

class DatomicSaveOpsImpl(elements: Seq[Element]) extends SaveOps {

  override def run: ZIO[Connection, MoleculeException, TxReport] = ???

  override def transact(implicit conn: Connection): TxReport = {
    conn.asInstanceOf[Conn_Peer].transact(
      new SaveStmts(elements).getStmts
    )
  }
  override def transactAsync(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = ???
}
