package molecule.datomic.action

import molecule.boilerplate.ast.Model.Element
import molecule.core.action.{Action, Insert, Insert_}

abstract class DatomicInsert(elements0: List[Element]) extends Insert_ {

  override def _insertOp(tpls0: Seq[Product]): DatomicInsert_JVM = {
    DatomicInsert_JVM(elements0, tpls0)
  }
}