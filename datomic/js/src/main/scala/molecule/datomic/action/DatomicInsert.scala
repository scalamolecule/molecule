package molecule.datomic.action

import molecule.boilerplate.ast.Model.Element
import molecule.core.action.{Action, Insert, Insert_}

abstract class DatomicInsert(private val elements0: List[Element])
  extends Action(elements0) with Insert_ {

  override def _insertOp(tpls: Seq[Product]): Insert = {
    DatomicInsert_JS(elements, tpls)
  }
}