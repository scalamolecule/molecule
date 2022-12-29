package codegen.core.api

import codegen.CoreGenBase

object _MoleculeApi extends CoreGenBase( "MoleculeApi", "/api") {

  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.core.api
       |
       |import molecule.core.api.ops._
       |
       |
       |trait MoleculeApi_00 {
       |  def delete: DeleteOps
       |}
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |trait ${fileName}_$n0[${`A..V`}] {
         |  def save  : SaveOps
         |  def insert: Insert_$arity[${`A..V`}]
         |  def query : QueryOps[${`(A..V)`}]
         |  def update: UpdateOps
         |  def upsert: UpdateOps
         |  def delete: DeleteOps
         |}""".stripMargin
  }
}
