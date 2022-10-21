package molecule.datomic.api

import molecule.boilerplate.ast.moleculeModel.Element
import molecule.core.api.Insert
import molecule.datomic.base.StmtsBuilder;

class DatomicInsertP(elements: Seq[Element]) extends Insert {
  override def _insertOp(tpls: Seq[Product]): DatomicInsertOps =
    new DatomicInsertOps(new StmtsBuilder(elements, tpls).getStmts)
}