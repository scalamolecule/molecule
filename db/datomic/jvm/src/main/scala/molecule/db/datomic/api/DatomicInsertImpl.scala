package molecule.db.datomic.api

import molecule.boilerplate.ast.MoleculeModel.Element
import molecule.core.api.Insert
import molecule.db.datomic.base.StmtsBuilder

// Platform-specific implementation

class DatomicInsertImpl(elements: Seq[Element]) extends Insert {
  override def _insertOp(tpls: Seq[Product]): DatomicInsertOps =
    new DatomicInsertOps(new StmtsBuilder(elements, tpls).getStmts)
}