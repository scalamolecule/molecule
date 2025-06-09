package boilerplate.db.core.api.expression

import boilerplate.db.core.CoreBase


object _ExprMapTac extends CoreBase("ExprMapTac", "/api/expression") {
  val content = {
    val traits = (0 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.core.api.expression
       |
       |import molecule.db.core.ast.*
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V, `}t, Entity1[${`_, _`}], Entity2[${`_, _, _`}]] extends ExprBase {
         |  protected def _exprMap (op: Op, map : Map[String, t]): Entity1[${`A..V, `}t] = ???
         |  protected def _exprMapK(op: Op, keys: Seq[String]   ): Entity1[${`A..t, `}t] = ???
         |  protected def _exprMapV(op: Op, vs  : Seq[t]        ): Entity1[${`A..V, `}t] = ???
         |}
         |
         |trait $fileName_$arity[${`A..V, `}t, Entity1[${`_, _`}], Entity2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V, `}t, Entity1, Entity2] {
         |  def apply(                           ): Entity1[${`A..V, `}t] = _exprMap (NoValue, Map.empty[String, t])
         |  def apply(key : String, keys: String*): Entity1[${`A..t, `}t] = _exprMapK(Eq     , Seq(key) ++ keys    )
         |  def apply(keys: Seq[String]          ): Entity1[${`A..t, `}t] = _exprMapK(Eq     , keys                )
         |  def not  (key : String, keys: String*): Entity1[${`A..t, `}t] = _exprMapK(Neq    , Seq(key) ++ keys    )
         |  def not  (keys: Seq[String]          ): Entity1[${`A..t, `}t] = _exprMapK(Neq    , keys                )
         |  def has  (v : t, vs: t*              ): Entity1[${`A..V, `}t] = _exprMapV(Has    , Seq(v) ++ vs        )
         |  def has  (vs: Seq[t]                 ): Entity1[${`A..V, `}t] = _exprMapV(Has    , vs                  )
         |  def hasNo(v : t, vs: t*              ): Entity1[${`A..V, `}t] = _exprMapV(HasNo  , Seq(v) ++ vs        )
         |  def hasNo(vs: Seq[t]                 ): Entity1[${`A..V, `}t] = _exprMapV(HasNo  , vs                  )
         |}""".stripMargin
  }
}
