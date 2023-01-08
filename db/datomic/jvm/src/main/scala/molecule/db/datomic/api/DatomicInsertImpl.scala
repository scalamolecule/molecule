package molecule.db.datomic.api

import molecule.boilerplate.ast.Model.Element
import molecule.core.api.Insert_
import molecule.db.datomic.api.ops.DatomicInsertOpsJVM

// Platform-specific implementation
abstract class DatomicInsertImpl(elements: List[Element]) extends Insert_ {
  override def _insertOp(data: Seq[Product]): DatomicInsertOpsJVM = {
    new DatomicInsertOpsJVM(elements, data)
  }
}