package codegen.db.datomic.api

import codegen.DatomicGenBase

object _DatomicMoleculeApi extends DatomicGenBase("DatomicMoleculeApi", "/api") {

  val content = {
    val classes = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.datomic.api
       |
       |import molecule.boilerplate.markers.namespaceMarkers._
       |import molecule.core.api.MoleculeApi._
       |import molecule.db.datomic.api.DatomicInsert._
       |
       |object DatomicMoleculeApi {
       |$classes
       |}""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val pad  = if (arity >= 10) " " else ""
    val body =
      s"""
         |  class DatomicMoleculeApi_$n0[${`A..V`}](molecule: Molecule_$n0[${`A..V`}]) extends MoleculeApi_$n0[${`A..V`}] {
         |    override def insert : DatomicInsert_$arity[${`A..V`}] = new DatomicInsert_$arity[${`A..V`}](molecule)
         |    override def save   : DatomicInsert_$arity[${`A..V`}] = new DatomicInsert_$arity[${`A..V`}](molecule)
         |    override def update : DatomicInsert_$arity[${`A..V`}] = new DatomicInsert_$arity[${`A..V`}](molecule)
         |    override def retract: DatomicInsert_$arity[${`A..V`}] = new DatomicInsert_$arity[${`A..V`}](molecule)
         |    override def delete : DatomicInsert_$arity[${`A..V`}] = new DatomicInsert_$arity[${`A..V`}](molecule)
         |    override def query  : DatomicQuery[(${`A..V`})]$pad  = new DatomicQuery[(${`A..V`})](molecule.elements)
         |  }""".stripMargin
  }
}
