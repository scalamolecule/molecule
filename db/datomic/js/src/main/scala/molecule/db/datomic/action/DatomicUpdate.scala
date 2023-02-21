package molecule.db.datomic.action

import molecule.boilerplate.ast.Model._
import molecule.core.action.Update
import molecule.core.api.{Connection, TxReport}
import molecule.db.datomic.facade.DatomicConn_JS
import scala.concurrent.{ExecutionContext, Future}

case class DatomicUpdate(
  elements: List[Element],
  isUpsert: Boolean = false
) extends Update
