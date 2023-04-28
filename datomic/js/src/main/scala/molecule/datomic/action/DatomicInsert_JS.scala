package molecule.datomic.action

import molecule.boilerplate.ast.Model._
import molecule.core.action.Insert

case class DatomicInsert_JS(private val elements0: List[Element], private val tpls0: Seq[Product])
  extends Insert(elements0, tpls0)
