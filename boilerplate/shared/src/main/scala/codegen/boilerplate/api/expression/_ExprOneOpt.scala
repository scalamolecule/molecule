package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase


object _ExprOneOpt extends BoilerplateGenBase( "ExprOneOpt", "/api/expression") {
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
         |  protected def _attrSortTac[ns1[_]](op: Op, a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] with SortAttrs_22[${`A..V`}, t, Ns1] = ???""".stripMargin
    } else {
      s"""
         |  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[${`A..V`},    t] with SortAttrs${_0}[${`A..V`},    t, Ns1] = ???
         |  protected def _attrSortMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V`}, X, t] with SortAttrs${_1}[${`A..V`}, X, t, Ns2] = ???""".stripMargin
    }

    val attrExprs = if (arity == 22) {
      s"""
         |  def apply[ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] with SortAttrs_22[${`A..V`}, t, Ns1] = _attrSortTac(Appl, a)
         |  def not  [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] with SortAttrs_22[${`A..V`}, t, Ns1] = _attrSortTac(Not , a)
         |  def <    [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] with SortAttrs_22[${`A..V`}, t, Ns1] = _attrSortTac(Lt  , a)
         |  def <=   [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] with SortAttrs_22[${`A..V`}, t, Ns1] = _attrSortTac(Le  , a)
         |  def >    [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] with SortAttrs_22[${`A..V`}, t, Ns1] = _attrSortTac(Gt  , a)
         |  def >=   [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2]): Ns1[${`A..V`}, t] with SortAttrs_22[${`A..V`}, t, Ns1] = _attrSortTac(Ge  , a)""".stripMargin
    } else {
      s"""
         |  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V`}, t] with SortAttrs${_0}[${`A..V`}, t, Ns1] = _attrSortTac(Appl, a)
         |  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V`}, t] with SortAttrs${_0}[${`A..V`}, t, Ns1] = _attrSortTac(Not , a)
         |  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V`}, t] with SortAttrs${_0}[${`A..V`}, t, Ns1] = _attrSortTac(Lt  , a)
         |  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V`}, t] with SortAttrs${_0}[${`A..V`}, t, Ns1] = _attrSortTac(Le  , a)
         |  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V`}, t] with SortAttrs${_0}[${`A..V`}, t, Ns1] = _attrSortTac(Gt  , a)
         |  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2]): Ns1[${`A..V`}, t] with SortAttrs${_0}[${`A..V`}, t, Ns1] = _attrSortTac(Ge  , a)
         |
         |  def apply[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V`}, X, t] with SortAttrs${_1}[${`A..V`}, X, t, Ns2] = _attrSortMan(Appl, a)
         |  def not  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V`}, X, t] with SortAttrs${_1}[${`A..V`}, X, t, Ns2] = _attrSortMan(Not , a)
         |  def <    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V`}, X, t] with SortAttrs${_1}[${`A..V`}, X, t, Ns2] = _attrSortMan(Lt  , a)
         |  def <=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V`}, X, t] with SortAttrs${_1}[${`A..V`}, X, t, Ns2] = _attrSortMan(Le  , a)
         |  def >    [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V`}, X, t] with SortAttrs${_1}[${`A..V`}, X, t, Ns2] = _attrSortMan(Gt  , a)
         |  def >=   [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2]): Ns2[${`A..V`}, X, t] with SortAttrs${_1}[${`A..V`}, X, t, Ns2] = _attrSortMan(Ge  , a)""".stripMargin
    }

    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends ExprBase {
         |  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = ???
         |  $attrResolvers
         |}
         |
         |trait $fileName_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t, Ns1, Ns2]
         |    with SortAttrs_$arity[${`A..V`}, t, Ns1] {
         |  def apply(v    : Option[t]     )(implicit x: X): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(Appl, v.map(Seq(_))    )
         |  def apply(vs   : Option[Seq[t]])               : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(Appl, vs               )
         |  def not  (v    : Option[t]     )(implicit x: X): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(Not , v.map(Seq(_))    )
         |  def not  (vs   : Option[Seq[t]])               : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(Not , vs               )
         |  def <    (upper: Option[t]     )               : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(Lt  , upper.map(Seq(_)))
         |  def <=   (upper: Option[t]     )               : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(Le  , upper.map(Seq(_)))
         |  def >    (lower: Option[t]     )               : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(Gt  , lower.map(Seq(_)))
         |  def >=   (lower: Option[t]     )               : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(Ge  , lower.map(Seq(_)))
         |  $attrExprs
         |}""".stripMargin
  }
}
