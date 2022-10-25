package codegen.core.api

import codegen.CoreGenBase

object _MoleculeApi extends CoreGenBase( "MoleculeApi", "/api") {

  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.core.api
       |
       |import molecule.core.api.Save._
       |
       |object MoleculeApi {
       |$traits
       |}""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |  trait MoleculeApi_$n0[${`A..V`}] {
         |    def insert : Insert_$arity[${`A..V`}]
         |    def save   : Insert_$arity[${`A..V`}]
         |    def update : Insert_$arity[${`A..V`}]
         |    def retract: Insert_$arity[${`A..V`}]
         |    def delete : Insert_$arity[${`A..V`}]
         |    def query  : Query[(${`A..V`})]
         |  }""".stripMargin
  }
}
