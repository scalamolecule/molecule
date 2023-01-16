package molecule.db.datomic.api.action

import molecule.base.util.exceptions.MoleculeError
import molecule.boilerplate.ast.Model._
import molecule.core.api.action.InsertApi
import molecule.core.api.{Connection, TxReport}
import molecule.core.transaction.Insert
import molecule.db.datomic.facade.DatomicConn_JVM
import molecule.db.datomic.transaction.Insert_stmts
import zio.ZIO
import scala.concurrent.{ExecutionContext, Future}

class DatomicInsertApiJVM(elements: List[Element], tpls: Seq[Product]) extends InsertApi {

  override def run: ZIO[Connection, MoleculeError, TxReport] = ???

  override def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = tryFuture {
    val stmts = (new Insert with Insert_stmts).getStmts(elements, tpls)
    conn.asInstanceOf[DatomicConn_JVM].transact(stmts)
  }
}
