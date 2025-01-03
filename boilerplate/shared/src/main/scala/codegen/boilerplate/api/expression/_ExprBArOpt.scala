package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase


object _ExprBArOpt extends BoilerplateGenBase( "ExprBArOpt", "/api/expression") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.api.expression
       |
       |import molecule.boilerplate.ast.DataModel._
       |import scala.language.higherKinds
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] {
         |  protected def _exprBArOpt(op: Op, optByteArray: Option[Array[t]]): Ns1[${`A..V`}, t] = ???
         |}
         |
         |trait $fileName_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t, Ns1, Ns2]{
         |  def apply(byteArray: Option[Array[t]]): Ns1[${`A..V`}, t] = _exprBArOpt(Eq, byteArray)
         |}""".stripMargin
  }
}
