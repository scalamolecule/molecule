package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase


object _ExprSetOpt extends BoilerplateGenBase( "ExprSetOpt", "/api/expression") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.api.expression
       |
       |import molecule.boilerplate.api._
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
         |  protected def _attrTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[${`A..V`},    t] = ???
         |  protected def _attrMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V`}, X, t] = ???""".stripMargin
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
         |  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V`}, t] = _attrTac(Appl, a)
         |  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V`}, t] = _attrTac(Not , a)
         |  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V`}, t] = _attrTac(Lt  , a)
         |  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V`}, t] = _attrTac(Le  , a)
         |  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V`}, t] = _attrTac(Gt  , a)
         |  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V`}, t] = _attrTac(Ge  , a)
         |
         |  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V`}, X, t] = _attrMan(Appl, a)
         |  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V`}, X, t] = _attrMan(Not , a)
         |  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V`}, X, t] = _attrMan(Lt  , a)
         |  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V`}, X, t] = _attrMan(Le  , a)
         |  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V`}, X, t] = _attrMan(Gt  , a)
         |  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V`}, X, t] = _attrMan(Ge  , a)""".stripMargin
    }
    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends ExprBase {
         |  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[${`A..V`}, t] = ???
         |  $attrResolvers
         |}
         |
         |trait $fileName_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t, Ns1, Ns2]{
         |  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[${`A..V`}, t] = _exprSetOpt(Appl, v.map(v => Seq(Set(v)))    )
         |  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[${`A..V`}, t] = _exprSetOpt(Appl, vs.map(_.map(v => Set(v))) )
         |  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[${`A..V`}, t] = _exprSetOpt(Appl, set.map(set => Seq(set))   )
         |  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[${`A..V`}, t] = _exprSetOpt(Appl, sets                       )
         |  def not  (v    : Option[t]          )(implicit x: X)            : Ns1[${`A..V`}, t] = _exprSetOpt(Not , v.map(v => Seq(Set(v)))    )
         |  def not  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[${`A..V`}, t] = _exprSetOpt(Not , vs.map(_.map(v => Set(v))) )
         |  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[${`A..V`}, t] = _exprSetOpt(Not , set.map(set => Seq(set))   )
         |  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[${`A..V`}, t] = _exprSetOpt(Not , sets                       )
         |  def ==   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[${`A..V`}, t] = _exprSetOpt(Eq  , set.map(set => Seq(set))   )
         |  def ==   (sets : Option[Seq[Set[t]]])                           : Ns1[${`A..V`}, t] = _exprSetOpt(Eq  , sets                       )
         |  def !=   (set  : Option[Set[t]]     )(implicit x: X)            : Ns1[${`A..V`}, t] = _exprSetOpt(Neq , set.map(set => Seq(set))   )
         |  def !=   (sets : Option[Seq[Set[t]]])                           : Ns1[${`A..V`}, t] = _exprSetOpt(Neq , sets                       )
         |  def <    (upper: Option[t]          )                           : Ns1[${`A..V`}, t] = _exprSetOpt(Lt  , upper.map(v => Seq(Set(v))))
         |  def <=   (upper: Option[t]          )                           : Ns1[${`A..V`}, t] = _exprSetOpt(Le  , upper.map(v => Seq(Set(v))))
         |  def >    (lower: Option[t]          )                           : Ns1[${`A..V`}, t] = _exprSetOpt(Gt  , lower.map(v => Seq(Set(v))))
         |  def >=   (lower: Option[t]          )                           : Ns1[${`A..V`}, t] = _exprSetOpt(Ge  , lower.map(v => Seq(Set(v))))
         |  $attrExprs
         |}""".stripMargin
  }
}
