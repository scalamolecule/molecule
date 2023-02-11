package molecule.db.datomic.action

import molecule.boilerplate.ast.Model._
import molecule.core.action.Insert

case class DatomicInsertImpl_JS(elements: List[Element], tpls: Seq[Product]) extends Insert
