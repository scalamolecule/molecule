package molecule.core.api.action

import molecule.core.api.Connection
import scala.concurrent.{ExecutionContext, Future}

trait QueryApi[Tpl] extends ApiUtils {

  // Time
//  def asOf(n: Int): QueryApi[Tpl] = ???
//  def history: QueryApi[Tpl] = ???

  def limit(n: Int): QueryApi[Tpl]
  def offset(n: Int): QueryApiOffset[Tpl]
  def from(cursor: String): QueryApiCursor[Tpl]

//  def run(implicit conn: Connection): ZIO[Connection, MoleculeError, Chunk[Tpl]]

  def get(implicit conn: Connection, ec: ExecutionContext): Future[List[Tpl]]

//  def inspect(implicit conn: Connection, ec: ExecutionContext): Future[Unit] = ???
}
