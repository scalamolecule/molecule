package molecule.db.datomic.api

import molecule.boilerplate.ast.MoleculeModel.Element
import molecule.core.api.Insert_
import molecule.db.datomic.transaction.InsertStmtsMaker

// Platform-specific implementation

class DatomicInsertImpl(elements: Seq[Element]) extends Insert_ {

  override def _insertOp(data: Seq[Product]): DatomicInsertOps = {

    println("\n--- INSERT --------------------------------------------------------")
    elements.foreach(println)

    new DatomicInsertOps(new InsertStmtsMaker(elements, data))
  }
}