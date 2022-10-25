package codegen.db.datomic.api

import codegen.DatomicGenBase

object _DatomicInsert extends DatomicGenBase( "DatomicInsert", "/api") {

  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.datomic.api
       |
       |import molecule.boilerplate.markers.namespaceMarkers._
       |import molecule.core.api.Save._
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
