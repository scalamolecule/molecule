package molecule.db.datomic.api.ops

import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.MoleculeModel._
import molecule.core.api.ops.SaveOps
import molecule.core.api.{Connection, TxReport}
import molecule.db.datomic.facade.DatomicConn_JS
import zio.ZIO
import scala.concurrent._


class DatomicSaveOpsImpl(elements: Seq[Element]) extends SaveOps {

  override def run: ZIO[Connection, MoleculeException, TxReport] = ???

  override def transact(implicit conn: Connection, ec: ExecutionContext): Future[TxReport] = {
        Future {
          try {
            conn.asInstanceOf[DatomicConn_JS].rpc.transactEdn("edn").flatMap {
              case Left(exc)       => Future.failed(exc)
              case Right(txReport) => Future(txReport)
            }
          } catch {
            case e: Throwable => Future.failed(e)
          }
        }.flatten
    conn.asInstanceOf[DatomicConn_JS].rpc.transactEdn("edn").flatMap {
      case Left(exc)       => Future.failed(exc)
      case Right(txReport) => Future(txReport)
    }
  }
}
