package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase


object _ExprOneTac extends BoilerplateGenBase("ExprOneTac", "/api/expression") {
  val content = {
    val traits = (0 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.api.expression
       |
       |import molecule.boilerplate.api._
       |import molecule.boilerplate.api.Keywords.unify
       |import molecule.boilerplate.ast.Model._
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val attrResolvers = if (arity == 22) {
      s"""
         |  protected def _attrTac[ns1[_]](op: Op, a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] = ???""".stripMargin
    } else {
      s"""
         |  protected def _attrTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[${`A..V, `}   t] = ???
         |  protected def _attrMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V, `}X, t] = ???""".stripMargin
    }

    val attrExprs = if (arity == 22) {
      s"""
         |  def apply[ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] = _attrTac(Appl, a)
         |  def not  [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] = _attrTac(Not , a)
         |  def <    [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] = _attrTac(Lt  , a)
         |  def <=   [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] = _attrTac(Le  , a)
         |  def >    [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] = _attrTac(Gt  , a)
         |  def >=   [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] = _attrTac(Ge  , a)""".stripMargin
    } else {
      s"""
         |  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V, `}t] = _attrTac(Appl, a)
         |  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V, `}t] = _attrTac(Not , a)
         |  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V, `}t] = _attrTac(Lt  , a)
         |  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V, `}t] = _attrTac(Le  , a)
         |  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V, `}t] = _attrTac(Gt  , a)
         |  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V, `}t] = _attrTac(Ge  , a)
         |
         |  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V, `}X, t] = _attrMan(Appl, a)
         |  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V, `}X, t] = _attrMan(Not , a)
         |  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V, `}X, t] = _attrMan(Lt  , a)
         |  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V, `}X, t] = _attrMan(Le  , a)
         |  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V, `}X, t] = _attrMan(Gt  , a)
         |  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V, `}X, t] = _attrMan(Ge  , a)""".stripMargin
    }

    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V, `}t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] {
         |  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[${`A..V, `}t] = ???
         |  $attrResolvers
         |}
         |
         |trait $fileName_$arity[${`A..V, `}t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V, `}t, Ns1, Ns2] {
         |  def apply()                : Ns1[${`A..V, `}t] = _exprOneTac(NoValue, Nil       )
         |  def apply(unify: unify    ): Ns1[${`A..V, `}t] = _exprOneTac(Unify  , Nil       )
         |  def apply(v    : t, vs: t*): Ns1[${`A..V, `}t] = _exprOneTac(Appl   , v +: vs   )
         |  def apply(vs   : Seq[t]   ): Ns1[${`A..V, `}t] = _exprOneTac(Appl   , vs        )
         |  def not  (v    : t, vs: t*): Ns1[${`A..V, `}t] = _exprOneTac(Not    , v +: vs   )
         |  def not  (vs   : Seq[t]   ): Ns1[${`A..V, `}t] = _exprOneTac(Not    , vs        )
         |  def <    (upper: t        ): Ns1[${`A..V, `}t] = _exprOneTac(Lt     , Seq(upper))
         |  def <=   (upper: t        ): Ns1[${`A..V, `}t] = _exprOneTac(Le     , Seq(upper))
         |  def >    (lower: t        ): Ns1[${`A..V, `}t] = _exprOneTac(Gt     , Seq(lower))
         |  def >=   (lower: t        ): Ns1[${`A..V, `}t] = _exprOneTac(Ge     , Seq(lower))
         |  $attrExprs
         |}""".stripMargin
  }
}
