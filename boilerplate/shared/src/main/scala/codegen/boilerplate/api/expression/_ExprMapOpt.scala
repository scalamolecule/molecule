package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase


object _ExprMapOpt extends BoilerplateGenBase( "ExprMapOpt", "/api/expression") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.api.expression
       |
       |import molecule.boilerplate.ast.Model._
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends ExprBase {
         |  protected def _exprMapOpt(op: Op, key: String): Ns1[${`A..U, `}Option[t], t] = ???
         |}
         |
         |trait $fileName_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t, Ns1, Ns2]{
         |  def apply(key: String): Ns1[${`A..U, `}Option[t], t] = _exprMapOpt(Eq, key)
         |}""".stripMargin
  }
}
