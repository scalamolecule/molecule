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
    val attrExprs = if (arity == 22) {
      s"""
         |  def apply[ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] = _attrTac(Eq   , a)
         |  def not  [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] = _attrTac(Neq  , a)
         |  def has  [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] = _attrTac(Has  , a)
         |  def hasNo[ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] = _attrTac(HasNo, a)
         |  def hasLt[ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] = _attrTac(HasLt, a)
         |  def hasLe[ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] = _attrTac(HasLe, a)
         |  def hasGt[ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] = _attrTac(HasGt, a)
         |  def hasGe[ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] = _attrTac(HasGe, a)""".stripMargin
    } else {
      s"""
         |  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V`}, t] = _attrTac(Eq   , a)
         |  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V`}, t] = _attrTac(Neq  , a)
         |  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V`}, t] = _attrTac(Has  , a)
         |  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V`}, t] = _attrTac(HasNo, a)
         |  def hasLt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V`}, t] = _attrTac(HasLt, a)
         |  def hasLe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V`}, t] = _attrTac(HasLe, a)
         |  def hasGt[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V`}, t] = _attrTac(HasGt, a)
         |  def hasGe[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V`}, t] = _attrTac(HasGe, a)
         |
         |  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V`}, X, t] = _attrMan(Eq   , a)
         |  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V`}, X, t] = _attrMan(Neq  , a)
         |  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V`}, X, t] = _attrMan(Has  , a)
         |  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V`}, X, t] = _attrMan(HasNo, a)
         |  def hasLt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V`}, X, t] = _attrMan(HasLt, a)
         |  def hasLe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V`}, X, t] = _attrMan(HasLe, a)
         |  def hasGt[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V`}, X, t] = _attrMan(HasGt, a)
         |  def hasGe[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V`}, X, t] = _attrMan(HasGe, a)""".stripMargin
    }
    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends ExprAttr_$arity[${`A..V, `}t, Ns1, Ns2] {
         |  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[${`A..V`}, t] = ???
         |}
         |
         |trait $fileName_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t, Ns1, Ns2]{
         |  def apply(v    : Option[t]          )(implicit x: X)            : Ns1[${`A..V`}, t] = _exprSetOpt(Eq   , v.map(v => Seq(Set(v)))    )
         |  def apply(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[${`A..V`}, t] = _exprSetOpt(Eq   , vs.map(_.map(v => Set(v))) )
         |  def apply(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[${`A..V`}, t] = _exprSetOpt(Eq   , set.map(set => Seq(set))   )
         |  def apply(sets : Option[Seq[Set[t]]])                           : Ns1[${`A..V`}, t] = _exprSetOpt(Eq   , sets                       )
         |  def not  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[${`A..V`}, t] = _exprSetOpt(Neq  , set.map(set => Seq(set))   )
         |  def not  (sets : Option[Seq[Set[t]]])                           : Ns1[${`A..V`}, t] = _exprSetOpt(Neq  , sets                       )
         |  def has  (v    : Option[t]          )(implicit x: X)            : Ns1[${`A..V`}, t] = _exprSetOpt(Has  , v.map(v => Seq(Set(v)))    )
         |  def has  (vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[${`A..V`}, t] = _exprSetOpt(Has  , vs.map(_.map(v => Set(v))) )
         |  def has  (set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[${`A..V`}, t] = _exprSetOpt(Has  , set.map(set => Seq(set))   )
         |  def has  (sets : Option[Seq[Set[t]]])                           : Ns1[${`A..V`}, t] = _exprSetOpt(Has  , sets                       )
         |  def hasNo(v    : Option[t]          )(implicit x: X)            : Ns1[${`A..V`}, t] = _exprSetOpt(HasNo, v.map(v => Seq(Set(v)))    )
         |  def hasNo(vs   : Option[Seq[t]]     )(implicit x: X, y: X)      : Ns1[${`A..V`}, t] = _exprSetOpt(HasNo, vs.map(_.map(v => Set(v))) )
         |  def hasNo(set  : Option[Set[t]]     )(implicit x: X, y: X, z: X): Ns1[${`A..V`}, t] = _exprSetOpt(HasNo, set.map(set => Seq(set))   )
         |  def hasNo(sets : Option[Seq[Set[t]]])                           : Ns1[${`A..V`}, t] = _exprSetOpt(HasNo, sets                       )
         |  def hasLt(upper: Option[t]          )                           : Ns1[${`A..V`}, t] = _exprSetOpt(HasLt, upper.map(v => Seq(Set(v))))
         |  def hasLe(upper: Option[t]          )                           : Ns1[${`A..V`}, t] = _exprSetOpt(HasLe, upper.map(v => Seq(Set(v))))
         |  def hasGt(lower: Option[t]          )                           : Ns1[${`A..V`}, t] = _exprSetOpt(HasGt, lower.map(v => Seq(Set(v))))
         |  def hasGe(lower: Option[t]          )                           : Ns1[${`A..V`}, t] = _exprSetOpt(HasGe, lower.map(v => Seq(Set(v))))
         |  $attrExprs
         |}""".stripMargin
  }
}
