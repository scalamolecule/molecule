package molecule.core.api.action

import molecule.core.api.Connection
import scala.concurrent.{ExecutionContext, Future}

trait QueryApiOffset[Tpl] extends ApiUtils {

  def limit(n: Int): QueryApiOffset[Tpl]

  def get(implicit conn: Connection, ec: ExecutionContext): Future[(List[Tpl], Int)]
}
