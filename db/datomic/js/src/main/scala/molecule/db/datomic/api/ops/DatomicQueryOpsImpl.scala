package molecule.db.datomic.api.ops

import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.Model._
import molecule.core.api.Connection
import molecule.core.api.ops.QueryOps
import molecule.core.transaction.Save
import molecule.core.util.JavaConversions
import molecule.db.datomic.facade.DatomicConn_JS
import molecule.db.datomic.query.DatomicModel2Query
import molecule.db.datomic.transaction.Save_edn
import zio.{Chunk, ZIO}
import scala.concurrent.{ExecutionContext, Future}


class DatomicQueryOpsImpl[Tpl](elements: Seq[Element])
  extends QueryOps[Tpl] with JavaConversions {


  override def take(n: Int): DatomicQueryOpsImpl[Tpl] = this
  override def drop(n: Int): DatomicQueryOpsImpl[Tpl] = this
  override def from(cursor: String): DatomicQueryOpsImpl[Tpl] = this


  //  override def run: ZIO[DataSource, Throwable, Chunk[Tpl]] = ZIO.succeed(Chunk.empty[Tpl]) // .provideEnvironment(conn)
  //  override def run(implicit conn: Connection): ZIO[Connection, MoleculeException, Chunk[Tpl]] = ???

  override def get(implicit conn0: Connection, ec: ExecutionContext): Future[List[Tpl]] = {
    val conn = conn0.asInstanceOf[DatomicConn_JS]
    conn.rpc.query(conn.proxy, elements).flatMap {
      case Right(elementsWithData) => {
        try {
          Future(Nil)
        } catch {
          case e: Throwable => Future.failed(e)
        }
      }
      case Left(exc)               => Future.failed(exc)
    }
  }

  override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = ???
}
