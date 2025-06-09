package boilerplate.db.core.api.expression

import boilerplate.db.core.DbCoreBase


object _ExprSetOpt extends DbCoreBase( "ExprSetOpt", "/api/expression") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.core.api.expression
       |
       |import molecule.core.ast.*
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V`}, t, Entity1[${`_, _`}], Entity2[${`_, _, _`}]] extends ExprAttr_$arity[${`A..V, `}t, Entity1, Entity2] {
         |  protected def _exprSetOpt(op: Op, optSet: Option[Set[t]]): Entity1[${`A..V`}, t] = ???
         |}
         |
         |trait $fileName_$arity[${`A..V`}, t, Entity1[${`_, _`}], Entity2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t, Entity1, Entity2] {
         |  def apply(optSet: Option[Set[t]]): Entity1[${`A..V`}, t] = _exprSetOpt(Eq, optSet)
         |}""".stripMargin
  }
}
