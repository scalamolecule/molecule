package molecule.core.api.ops

import molecule.core.api.Connection
import scala.concurrent.{ExecutionContext, Future}

trait QueryOps[Tpl] extends BaseOps {

  // Time
  def asOf(n: Int): QueryOps[Tpl] = ???
  def history: QueryOps[Tpl] = ???

  def take(n: Int): QueryOps[Tpl]
  def drop(n: Int): QueryOps[Tpl]
  def from(cursor: String): QueryOps[Tpl]

//  def run(implicit conn: Connection): ZIO[Connection, MoleculeException, Chunk[Tpl]]

  def get(implicit conn: Connection, ec: ExecutionContext): Future[List[Tpl]]

  def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = ???
}
