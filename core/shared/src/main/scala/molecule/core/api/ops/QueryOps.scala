package molecule.core.api.ops

import molecule.core.api.Connection
import scala.concurrent.{ExecutionContext, Future}

trait QueryOps[Tpl] {

  // Time
  def asOf(n: Int): QueryOps[Tpl] = ???
  def history: QueryOps[Tpl] = ???

  def take(n: Int): QueryOps[Tpl]
  def drop(n: Int): QueryOps[Tpl]
  def from(cursor: String): QueryOps[Tpl]

  //  List(1).slice(0, 2)


//  def run(implicit conn: Connection): ZIO[Connection, MoleculeException, Chunk[Tpl]]

  def get(implicit conn: Connection, ec: ExecutionContext): Future[List[Tpl]]

  def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit]
}
