package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase


object _ExprMapTac extends BoilerplateGenBase("ExprMapTac", "/api/expression") {
  val content = {
    val traits = (0 to 22).map(arity => Trait(arity).body).mkString("\n")
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
         |trait ${fileName}Ops_$arity[${`A..V, `}t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends ExprBase {
         |  protected def _exprMap (op: Op, map : Map[String, t]): Ns1[${`A..V, `}t] = ???
         |  protected def _exprMapK(op: Op, keys: Seq[String]   ): Ns1[${`A..t, `}t] = ???
         |  protected def _exprMapV(op: Op, vs  : Seq[t]        ): Ns1[${`A..V, `}t] = ???
         |}
         |
         |trait $fileName_$arity[${`A..V, `}t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V, `}t, Ns1, Ns2] {
         |  def apply(                           ): Ns1[${`A..V, `}t] = _exprMap (NoValue, Map.empty[String, t])
         |  def apply(key : String, keys: String*): Ns1[${`A..t, `}t] = _exprMapK(Eq     , key +: keys         )
         |  def apply(keys: Seq[String]          ): Ns1[${`A..t, `}t] = _exprMapK(Eq     , keys                )
         |  def not  (key : String, keys: String*): Ns1[${`A..V, `}t] = _exprMap (Neq    , mapK[t](key +: keys))
         |  def not  (keys: Seq[String]          ): Ns1[${`A..V, `}t] = _exprMap (Neq    , mapK[t](keys)       )
         |  def has  (v : t, vs: t*              ): Ns1[${`A..V, `}t] = _exprMapV(Has    , v +: vs             )
         |  def has  (vs: Seq[t]                 ): Ns1[${`A..V, `}t] = _exprMapV(Has    , vs                  )
         |  def hasNo(v : t, vs: t*              ): Ns1[${`A..V, `}t] = _exprMapV(HasNo  , v +: vs             )
         |  def hasNo(vs: Seq[t]                 ): Ns1[${`A..V, `}t] = _exprMapV(HasNo  , vs                  )
         |}""".stripMargin
  }
}
