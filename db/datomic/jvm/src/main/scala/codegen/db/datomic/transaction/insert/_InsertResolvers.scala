package codegen.db.datomic.transaction.insert

import codegen.DatomicGenBaseJVM

object _InsertResolvers extends DatomicGenBaseJVM( "InsertStmtsBuilder", "/transaction/insert") {

  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.datomic.transaction.insert
       |
       |import molecule.boilerplate.ast.MoleculeModel._
       |
       |object DatomicInsert {
       |$traits
       |}""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |  class DatomicInsert_$arity[${`A..V`}](molecule: Molecule_$n0[${`A..V`}])
         |    extends DatomicInsertImpl(molecule.elements) with Insert_$arity[${`A..V`}]""".stripMargin
  }
}
