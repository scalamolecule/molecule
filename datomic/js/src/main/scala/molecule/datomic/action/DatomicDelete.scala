package molecule.datomic.action

import molecule.boilerplate.ast.Model._
import molecule.core.action.Delete
import molecule.core.api.{Connection, TxReport}
import scala.concurrent.{ExecutionContext, Future}

case class DatomicDelete(elements: List[Element]) extends Delete
