package molecule.db.datomic.api

import molecule.boilerplate.ast.MoleculeModel.Element
import molecule.core.api.Insert_
import molecule.db.datomic.transaction.InsertStmts

// Platform-specific implementation

class DatomicInsertImpl(elements: Seq[Element]) extends Insert_ {

  override def _insertOp(data: Seq[Product]): DatomicInsertOps = {

    val stmts = new InsertStmts(elements, data).getStmts

    println("\n--- INSERT --------------------------------------------------------")
    elements.foreach(println)
    println("---")
    stmts.forEach(stmt => println(stmt))

    new DatomicInsertOps(stmts)
  }
}