package molecule.db.datomic.api

import molecule.boilerplate.ast.Model.Element
import molecule.core.api.Insert_
import molecule.db.datomic.api.ops.DatomicInsertOpsJS

abstract class DatomicInsertImpl(elements: Seq[Element]) extends Insert_ {

  override def _insertOp(tpls: Seq[Product]): DatomicInsertOpsJS = {
    new DatomicInsertOpsJS(elements, tpls)
  }
}