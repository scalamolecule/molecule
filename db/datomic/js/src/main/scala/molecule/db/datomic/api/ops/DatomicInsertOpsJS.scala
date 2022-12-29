package molecule.db.datomic.api.ops

import molecule.base.util.exceptions.MoleculeException
import molecule.core.api.ops.InsertOps
import molecule.core.api.{Connection, TxReport}
import zio.ZIO
import scala.concurrent.{ExecutionContext, Future}


class DatomicInsertOpsJS(edn: String) extends InsertOps {

  override def run: ZIO[Connection, MoleculeException, TxReport] = ???

  override def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = {
    Future {
      try {
        ???
      } catch {
        case e: Throwable => Future.failed(e)
      }
    }.flatten
  }
}
