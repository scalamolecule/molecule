package molecule.db.datomic.api

import molecule.boilerplate.ast.MoleculeModel.Element
import molecule.core.api.Insert_
import molecule.db.datomic.api.ops.DatomicInsertOpsJS

abstract class DatomicInsertImpl(elements: Seq[Element]) extends Insert_ {

  override def _insertOp(tpls: Seq[Product]): DatomicInsertOpsJS = {
    val edn = ""
    new DatomicInsertOpsJS(edn)
  }
}