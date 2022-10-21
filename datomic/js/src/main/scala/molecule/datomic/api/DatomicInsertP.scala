package molecule.datomic.api
//package molecule.datomic.api

import java.util.{List => jList}
import molecule.boilerplate.ast.moleculeModel.Element
import molecule.boilerplate.markers.namespaceMarkers.Molecule_02
import molecule.core.api.Insert.Insert_2
import molecule.core.api.{Connection, Insert}



abstract class DatomicInsertP(elements: Seq[Element]) extends Insert {

  override def _insertOp(tpls: Seq[Product]): DatomicInsertOps = {
//    val tpl2stmts: Product => String = ??? //new StmtsBuilder(elements)
//    val stmts     = new java.util.ArrayList[jList[_]](tpls.length)
//    tpls.foreach(tpl => stmts.add(tpl2stmts(tpl)))
//    println(stmts)
    val edn = ""
    new DatomicInsertOps(edn)
  }
}