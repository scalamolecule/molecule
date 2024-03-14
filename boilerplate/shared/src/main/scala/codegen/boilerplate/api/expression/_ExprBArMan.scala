package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase


object _ExprBArMan extends BoilerplateGenBase("ExprBArMan", "/api/expression") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.api.expression
       |
       |import molecule.base.ast._
       |import molecule.boilerplate.api._
       |import molecule.boilerplate.ast.Model._
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |
         |trait $fileName_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends ExprBArTacOps_$arity[${`A..V`}, t, Ns1, Ns2]
         |    with Aggregates_$arity[${`A..V`}, t, Ns1] {
         |  def apply(            ): Ns1[${`A..V`}, t] = _exprBAr(Eq, Nil    )
         |  def apply(ba: Array[t]): Ns1[${`A..V`}, t] = _exprBAr(Eq, Seq(ba))
         |}""".stripMargin
  }
}
