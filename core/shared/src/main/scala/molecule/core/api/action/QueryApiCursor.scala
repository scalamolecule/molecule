package molecule.core.api.action

import molecule.core.api.Connection
import scala.concurrent.{ExecutionContext, Future}

trait QueryApiCursor[Tpl] extends ApiUtils {

  def limit(n: Int): QueryApiCursor[Tpl]

  def get(implicit conn: Connection, ec: ExecutionContext): Future[(List[Tpl], String, Boolean)]
}
