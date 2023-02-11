package molecule.db.datomic.action

import molecule.boilerplate.ast.Model.Element
import molecule.core.action.{Insert, Insert_}

// Platform-specific implementation
case class DatomicInsertImpl(elements: List[Element]) extends Insert_ {
  override def _insertOp(tpls: Seq[Product]): Insert = {
    DatomicInsertImpl_JS(elements, tpls)
  }
}