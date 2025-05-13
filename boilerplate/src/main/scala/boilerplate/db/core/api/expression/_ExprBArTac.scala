package boilerplate.db.core.api.expression

import boilerplate.db.core.CoreGenBase


object _ExprBArTac extends CoreGenBase("ExprBArTac", "/api/expression") {
  val content = {
    val traits = (0 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.core.api.expression
       |
       |import molecule.db.core.ast._
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V, `}t, Entity1[${`_, _`}], Entity2[${`_, _, _`}]] {
         |  protected def _exprBAr(op: Op, byteArray: Array[t]): Entity1[${`A..V, `}t] = ???
         |}
         |
         |trait $fileName_$arity[${`A..V, `}t, Entity1[${`_, _`}], Entity2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V, `}t, Entity1, Entity2] {
         |  def apply(                   ): Entity1[${`A..V, `}t] = _exprBAr(NoValue, Array.empty[Byte].asInstanceOf[Array[t]])
         |  def apply(byteArray: Array[t]): Entity1[${`A..V, `}t] = _exprBAr(Eq     , byteArray                               )
         |  def not  (byteArray: Array[t]): Entity1[${`A..V, `}t] = _exprBAr(Neq    , byteArray                               )
         |}""".stripMargin
  }
}
