package molecule.db.datomic.api

//package molecule.datomic.api

import molecule.boilerplate.ast.MoleculeModel.Element
import molecule.core.api.Insert_



abstract class DatomicInsertImpl(elements: Seq[Element]) extends Insert_ {

  override def _insertOp(tpls: Seq[Product]): DatomicInsertOps = {
//    val tpl2stmts: Product => String = ??? //new StmtsBuilder(elements)
//    val stmts     = new java.util.ArrayList[jList[_]](tpls.length)
//    tpls.foreach(tpl => stmts.add(tpl2stmts(tpl)))
//    println(stmts)
    val edn = ""
    new DatomicInsertOps(edn)
  }
}