package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase


object _ExprMapTac extends BoilerplateGenBase("ExprMapTac", "/api/expression") {
  val content = {
    val traits = (0 to 22).map(arity => Trait(arity).body).mkString("\n")
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
         |trait ${fileName}Ops_$arity[${`A..V, `}t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends ExprBase {
         |  protected def _exprMap (op: Op, map : Map[String, t]): Ns1[${`A..V, `}t] = ???
         |  protected def _exprMapK(op: Op, keys: Seq[String]   ): Ns1[${`A..t, `}t] = ???
         |  protected def _exprMapV(op: Op, vs  : Seq[t]        ): Ns1[${`A..V, `}t] = ???
         |}
         |
         |trait $fileName_$arity[${`A..V, `}t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V, `}t, Ns1, Ns2] {
         |  def apply(                           ): Ns1[${`A..V, `}t] = _exprMap (NoValue, Map.empty[String, t])
         |  def apply(key : String, keys: String*): Ns1[${`A..t, `}t] = _exprMapK(Eq     , Seq(key) ++ keys    )
         |  def apply(keys: Seq[String]          ): Ns1[${`A..t, `}t] = _exprMapK(Eq     , keys                )
         |  def not  (key : String, keys: String*): Ns1[${`A..t, `}t] = _exprMapK(Neq    , Seq(key) ++ keys    )
         |  def not  (keys: Seq[String]          ): Ns1[${`A..t, `}t] = _exprMapK(Neq    , keys                )
         |  def has  (v : t, vs: t*              ): Ns1[${`A..V, `}t] = _exprMapV(Has    , Seq(v) ++ vs        )
         |  def has  (vs: Seq[t]                 ): Ns1[${`A..V, `}t] = _exprMapV(Has    , vs                  )
         |  def hasNo(v : t, vs: t*              ): Ns1[${`A..V, `}t] = _exprMapV(HasNo  , Seq(v) ++ vs        )
         |  def hasNo(vs: Seq[t]                 ): Ns1[${`A..V, `}t] = _exprMapV(HasNo  , vs                  )
         |}""".stripMargin
  }
}
