package molecule.db.datomic.api.ops

import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.MoleculeModel._
import molecule.core.api.Connection
import molecule.core.api.ops.QueryOps
import molecule.core.util.JavaConversions
import zio.{Chunk, ZIO}
import scala.concurrent.{ExecutionContext, Future}


class DatomicQueryOpsImpl[Tpl](elements: Seq[Element])
  extends QueryOps[Tpl] with JavaConversions {


  override def take(n: Int): DatomicQueryOpsImpl[Tpl] = this
  override def drop(n: Int): DatomicQueryOpsImpl[Tpl] = this
  override def from(cursor: String): DatomicQueryOpsImpl[Tpl] = this


  //  override def run: ZIO[DataSource, Throwable, Chunk[Tpl]] = ZIO.succeed(Chunk.empty[Tpl]) // .provideEnvironment(conn)
  override def run(implicit conn: Connection): ZIO[Connection, MoleculeException, Chunk[Tpl]] = ???

  override def getAsync(implicit conn: Connection, ec: ExecutionContext): Future[List[Tpl]] = ???

  override def get(implicit conn: Connection): List[Tpl] = {
    //    val query                               = ""
    //    val inputs: Seq[AnyRef] = ???
    //    val rows: util.Collection[jList[AnyRef]] = Peer.q(query, inputs: _*)
    //    val row2tpls: Raw => Tpl = ???
    //    val tuples = List.newBuilder[Tpl]
    //    rows.forEach(row => tuples.addOne(row2tpls(row)))
    //    tuples.result()
    Nil
  }
  override def inspect(implicit conn: Connection): Unit = ???
}
