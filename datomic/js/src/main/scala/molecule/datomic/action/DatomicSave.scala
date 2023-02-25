package molecule.datomic.action

import molecule.boilerplate.ast.Model._
import molecule.core.action.Save
import molecule.core.api.{Connection, TxReport}
import scala.concurrent._

case class DatomicSave(elements: List[Element]) extends Save
