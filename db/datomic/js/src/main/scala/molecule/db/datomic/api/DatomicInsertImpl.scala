package molecule.db.datomic.api

import molecule.boilerplate.ast.Model.Element
import molecule.core.api.Insert_
import molecule.db.datomic.api.action.DatomicInsertApiJS

// Platform-specific implementation
abstract class DatomicInsertImpl(elements: List[Element]) extends Insert_ {
  override def _insertOp(tpls: Seq[Product]): DatomicInsertApiJS = {
    new DatomicInsertApiJS(elements, tpls)
  }
}