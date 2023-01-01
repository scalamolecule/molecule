package molecule.db.datomic.api.ops

import molecule.boilerplate.ast.Model._
import molecule.core.api.Connection
import molecule.core.api.ops.QueryOps
import molecule.core.marshalling.unpack.DTO2tpls
import molecule.core.util.JavaConversions
import molecule.db.datomic.facade.DatomicConn_JS
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
      case Right(dto) =>
        println("--------------")
        println("-4- " + dto.oneInt.toList)
        try {
//          Future(dto.oneInt.toList.asInstanceOf[List[Tpl]])
          Future(DTO2tpls(elements, dto).unpack)
        } catch {
          case e: Throwable => Future.failed(e)
        }

      case Left(exc)               => Future.failed(exc)
    }
  }

  override def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = ???
}
