package codegen.core.api.expression

import codegen.CoreGenBase
import scala.language.higherKinds


object _ExprMapOpt extends CoreGenBase( "ExprMapOpt", "/api/expression") {
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
         |trait ${fileName}Ops_$arity[${`A..V`}, t, Entity1[${`_, _`}], Entity2[${`_, _, _`}]] extends ExprBase {
         |  protected def _exprMapOpt(op: Op, map: Option[Map[String, t]]): Entity1[${`A..V, `}        t] = ???
         |  protected def _exprMapOpK(op: Op, key: String                ): Entity1[${`A..U, `}Option[t], t] = ???
         |}
         |
         |trait $fileName_$arity[${`A..V`}, t, Entity1[${`_, _`}], Entity2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t, Entity1, Entity2]{
         |  def apply(map: Option[Map[String, t]]): Entity1[${`A..V, `}        t] = _exprMapOpt(Eq , map)
         |  def apply(key: String                ): Entity1[${`A..U, `}Option[t], t] = _exprMapOpK(Has, key)
         |}""".stripMargin
  }
}