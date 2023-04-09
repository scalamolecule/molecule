package codegen.core.action

import codegen.CoreGenBase

object _Actions extends CoreGenBase( "Actions", "/action") {

  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.core.action
       |
       |
       |trait Actions_00 {
       |  def delete: Delete
       |}
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |trait $fileName_$n0[${`A..V`}] {
         |  def save  : Save
         |  def insert: Insert_$arity[${`A..V`}]
         |  def query : Query[${`(A..V)`}]
         |  def update: Update
         |  def upsert: Update
         |  def delete: Delete
         |}""".stripMargin
  }
}
