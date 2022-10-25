package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase

object _ExprMapOpt extends BoilerplateGenBase( "exprMapO", "/api/expression") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |molecule.boilerplate.api.expression
       |
       |import molecule.boilerplate.api.sortAttrs._
       |
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |trait ${fileName}_$arity[Attr, t, ${`A..V`}, $nsIn]
         |  extends SortAttrs_$arity[Attr, t, ${`A..V`}, Ns] {
         |  def apply(some: Option[Map[String, t]]): $nsOut = ???
         |}""".stripMargin
  }
}
