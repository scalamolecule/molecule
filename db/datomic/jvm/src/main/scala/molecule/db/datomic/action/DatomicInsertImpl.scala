package molecule.db.datomic.action

import molecule.boilerplate.ast.Model.Element
import molecule.core.action.{Insert, Insert_}

// Platform-specific implementation
case class DatomicInsertImpl(elements: List[Element]) extends Insert_ {
  override def _insertOp(data: Seq[Product]): Insert = {
    DatomicInsertImpl_JVM(elements, data)
  }
}