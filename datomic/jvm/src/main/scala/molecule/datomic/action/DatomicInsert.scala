package molecule.datomic.action

import molecule.boilerplate.ast.Model.Element
import molecule.core.action.{Insert, Insert_}

// Platform-specific implementation
abstract class DatomicInsert(elements: List[Element]) extends Insert_ {
  override def _insertOp(data: Seq[Product]): Insert = {
    DatomicInsert_JVM(elements, data)
  }
}