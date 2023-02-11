package molecule.db.datomic.action

import molecule.boilerplate.ast.Model._
import molecule.core.action.Save
import molecule.core.api.{Connection, TxReport}
import molecule.db.datomic.facade.DatomicConn_JS
import scala.concurrent._

case class DatomicSaveImpl(elements: List[Element]) extends Save
