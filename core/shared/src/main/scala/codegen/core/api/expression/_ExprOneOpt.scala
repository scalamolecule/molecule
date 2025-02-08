package codegen.core.api.expression

import codegen.CoreGenBase


object _ExprOneOpt extends CoreGenBase("ExprOneOpt", "/api/expression") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.core.api.expression
       |
       |import molecule.core.api._
       |import molecule.core.ast.DataModel._
       |import scala.language.higherKinds
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    // Doesn't seem to make sense to have none-optional filters on optional attributes...
    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V`}, t, Entity1[${`_, _`}], Entity2[${`_, _, _`}]] extends ExprAttr_$arity[${`A..V, `}t, Entity1, Entity2] {
         |  protected def _exprOneOpt(op: Op, opt: Option[t]): Entity1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Entity1] = ???
         |}
         |
         |trait $fileName_$arity[${`A..V`}, t, Entity1[${`_, _`}], Entity2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t, Entity1, Entity2]
         |    with SortAttrs_$arity[${`A..V`}, t, Entity1] {
         |  def apply(opt: Option[t]): Entity1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Entity1] = _exprOneOpt(Eq, opt)
         |}""".stripMargin
  }
}
