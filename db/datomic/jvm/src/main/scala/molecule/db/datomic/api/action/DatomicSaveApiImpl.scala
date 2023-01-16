package molecule.db.datomic.api.action

import molecule.base.util.exceptions.MoleculeError
import molecule.boilerplate.ast.Model._
import molecule.core.api.action.SaveApi
import molecule.core.api.{Connection, TxReport}
import molecule.core.transaction.Save
import molecule.db.datomic.facade.DatomicConn_JVM
import molecule.db.datomic.transaction.Save_stmts
import zio.ZIO
import scala.concurrent.{ExecutionContext, Future}

class DatomicSaveApiImpl(elements: List[Element]) extends SaveApi {

  override def run: ZIO[Connection, MoleculeError, TxReport] = ???

  override def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = tryFuture {
    val stmts = (new Save() with Save_stmts).getStmts(elements)
    conn.asInstanceOf[DatomicConn_JVM].transact(stmts)
  }
}
