package molecule.db.datomic.api

import molecule.boilerplate.ast.MoleculeModel.Element
import molecule.core.api.Insert_
import molecule.db.datomic.api.ops.DatomicInsertOpsJVM
import molecule.db.datomic.transaction.InsertStmts

// Platform-specific implementation

class DatomicInsertImpl(elements: Seq[Element]) extends Insert_ {

  override def _insertOp(data: Seq[Product]): DatomicInsertOpsJVM = {

    println("\n--- INSERT --------------------------------------------------------")
    elements.foreach(println)

    new DatomicInsertOpsJVM(new InsertStmts(elements, data))
  }
}