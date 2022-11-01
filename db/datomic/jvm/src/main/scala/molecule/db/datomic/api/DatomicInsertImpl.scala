package molecule.db.datomic.api

import molecule.boilerplate.ast.MoleculeModel.Element
import molecule.core.api.Insert_
import molecule.db.datomic.transaction.InsertStmts

// Platform-specific implementation

class DatomicInsertImpl(elements: Seq[Element]) extends Insert_ {
  override def _insertOp(tpls: Seq[Product]): DatomicInsertOps =
    new DatomicInsertOps(
      new InsertStmts(elements, tpls).getStmts
    )
}