package codegen.core.api.expression

import codegen.CoreGenBase


object _ExprBArMan extends CoreGenBase("ExprBArMan", "/api/expression") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.core.api.expression
       |
       |import molecule.core.ast.DataModel._
       |import scala.language.higherKinds
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |
         |trait $fileName_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends ExprBArTacOps_$arity[${`A..V`}, t, Ns1, Ns2] {
         |  def apply(                   ): Ns1[${`A..V`}, t] = _exprBAr(NoValue, Array.empty[Byte].asInstanceOf[Array[t]])
         |  def apply(byteArray: Array[t]): Ns1[${`A..V`}, t] = _exprBAr(Eq     , byteArray                               )
         |  def not  (byteArray: Array[t]): Ns1[${`A..V`}, t] = _exprBAr(Neq    , byteArray                               )
         |}""".stripMargin
  }
}
