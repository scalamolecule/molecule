package molecule.core.api.action

import molecule.core.api.Connection
import scala.concurrent.{ExecutionContext, Future}

trait QueryApi[Tpl] extends ApiOps {

  // Time
  def asOf(n: Int): QueryApi[Tpl] = ???
  def history: QueryApi[Tpl] = ???

  def take(n: Int): QueryApi[Tpl]
  def drop(n: Int): QueryApi[Tpl]
  def from(cursor: String): QueryApi[Tpl]

//  def run(implicit conn: Connection): ZIO[Connection, MoleculeException, Chunk[Tpl]]

  def get(implicit conn: Connection, ec: ExecutionContext): Future[List[Tpl]]

  def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = ???
}
