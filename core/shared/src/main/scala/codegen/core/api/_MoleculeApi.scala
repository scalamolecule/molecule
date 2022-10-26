package codegen.core.api

import codegen.CoreGenBase

object _MoleculeApi extends CoreGenBase( "MoleculeApi", "/api") {

  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.core.api
       |
       |import molecule.core.api.Insert._
       |
       |object MoleculeApi {
       |$traits
       |}""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |  trait MoleculeApi_$n0[${`A..V`}] {
         |    def insert: Insert_$arity[${`A..V`}]
         |    def save  : SaveOps
         |    def update: Insert_$arity[${`A..V`}]
         |    def delete: Insert_$arity[${`A..V`}]
         |    def query : QueryOps[${`(A..V)`}]
         |  }""".stripMargin
  }
}
