package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase


object _ExprOneMan extends BoilerplateGenBase("ExprOneMan", "/api/expression") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.api.expression
       |
       |import molecule.base.ast.SchemaAST._
       |import molecule.boilerplate.api._
       |import molecule.boilerplate.ast.Model._
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val attrExprs = if (arity == 22) {
      s"""
         |  def apply[ns1[_]](a: ModelOps_0[t, ns1, Dummy_2] with CardOne): Ns1[${`A..V`}, t] with SortAttrs_22[${`A..V`}, t, Ns1] = _attrSortTac(Eq , a)
         |  def not  [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2] with CardOne): Ns1[${`A..V`}, t] with SortAttrs_22[${`A..V`}, t, Ns1] = _attrSortTac(Neq, a)
         |  def <    [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2] with CardOne): Ns1[${`A..V`}, t] with SortAttrs_22[${`A..V`}, t, Ns1] = _attrSortTac(Lt , a)
         |  def <=   [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2] with CardOne): Ns1[${`A..V`}, t] with SortAttrs_22[${`A..V`}, t, Ns1] = _attrSortTac(Le , a)
         |  def >    [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2] with CardOne): Ns1[${`A..V`}, t] with SortAttrs_22[${`A..V`}, t, Ns1] = _attrSortTac(Gt , a)
         |  def >=   [ns1[_]](a: ModelOps_0[t, ns1, Dummy_2] with CardOne): Ns1[${`A..V`}, t] with SortAttrs_22[${`A..V`}, t, Ns1] = _attrSortTac(Ge , a)""".stripMargin
    } else {
      s"""
         |  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[${`A..V`}, t] with SortAttrs${_0}[${`A..V`}, t, Ns1] = _attrSortTac(Eq , a)
         |  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[${`A..V`}, t] with SortAttrs${_0}[${`A..V`}, t, Ns1] = _attrSortTac(Neq, a)
         |  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[${`A..V`}, t] with SortAttrs${_0}[${`A..V`}, t, Ns1] = _attrSortTac(Lt , a)
         |  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[${`A..V`}, t] with SortAttrs${_0}[${`A..V`}, t, Ns1] = _attrSortTac(Le , a)
         |  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[${`A..V`}, t] with SortAttrs${_0}[${`A..V`}, t, Ns1] = _attrSortTac(Gt , a)
         |  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[${`A..V`}, t] with SortAttrs${_0}[${`A..V`}, t, Ns1] = _attrSortTac(Ge , a)
         |
         |  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[$V, t, ns1, ns2]): Ns2[${`A..V`}, $V, t] with SortAttrs${_1}[${`A..V`}, $V, t, Ns2] = _attrSortMan(Eq , a)
         |  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[$V, t, ns1, ns2]): Ns2[${`A..V`}, $V, t] with SortAttrs${_1}[${`A..V`}, $V, t, Ns2] = _attrSortMan(Neq, a)
         |  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[$V, t, ns1, ns2]): Ns2[${`A..V`}, $V, t] with SortAttrs${_1}[${`A..V`}, $V, t, Ns2] = _attrSortMan(Lt , a)
         |  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[$V, t, ns1, ns2]): Ns2[${`A..V`}, $V, t] with SortAttrs${_1}[${`A..V`}, $V, t, Ns2] = _attrSortMan(Le , a)
         |  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[$V, t, ns1, ns2]): Ns2[${`A..V`}, $V, t] with SortAttrs${_1}[${`A..V`}, $V, t, Ns2] = _attrSortMan(Gt , a)
         |  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[$V, t, ns1, ns2]): Ns2[${`A..V`}, $V, t] with SortAttrs${_1}[${`A..V`}, $V, t, Ns2] = _attrSortMan(Ge , a)""".stripMargin
    }

    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends ExprAttr_$arity[${`A..V, `}t, Ns1, Ns2] {
         |  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = ???
         |}
         |
         |trait $fileName_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t, Ns1, Ns2]
         |    with Aggregates_$arity[${`A..V`}, t, Ns1]
         |    with SortAttrs_$arity[${`A..V`}, t, Ns1] {
         |  def apply(                ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(Eq , Nil       )
         |  def apply(v    : t, vs: t*): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(Eq , v +: vs   )
         |  def apply(vs   : Seq[t]   ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(Eq , vs        )
         |  def not  (v    : t, vs: t*): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(Neq, v +: vs   )
         |  def not  (vs   : Seq[t]   ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(Neq, vs        )
         |  def <    (upper: t        ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(Lt , Seq(upper))
         |  def <=   (upper: t        ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(Le , Seq(upper))
         |  def >    (lower: t        ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(Gt , Seq(lower))
         |  def >=   (lower: t        ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(Ge , Seq(lower))
         |  $attrExprs
         |}
         |trait $fileName_${arity}_String[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends $fileName_$arity[${`A..V, `}t, Ns1, Ns2] {
         |  def startsWith(prefix: t              ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(StartsWith, Seq(prefix)                                                       )
         |  def endsWith  (suffix: t              ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(EndsWith  , Seq(suffix)                                                       )
         |  def contains  (needle: t              ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(Contains  , Seq(needle)                                                       )
         |  def matches   (regex : t              ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(Matches   , Seq(regex)                                                        )
         |  def take      (n     : Int            ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(Take      , Seq(n.toString.asInstanceOf[t])                                   )
         |  def takeRight (n     : Int            ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(TakeRight , Seq(n.toString.asInstanceOf[t])                                   )
         |  def drop      (n     : Int            ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(Drop      , Seq(n.toString.asInstanceOf[t])                                   )
         |  def dropRight (n     : Int            ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(DropRight , Seq(n.toString.asInstanceOf[t])                                   )
         |  def substring (start : Int, end: Int  ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(SubString , Seq(start.toString.asInstanceOf[t], end.toString.asInstanceOf[t]) )
         |  def slice     (from  : Int, until: Int): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(Slice     , Seq(from.toString.asInstanceOf[t], until.toString.asInstanceOf[t]))
         |}
         |trait $fileName_${arity}_Number[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends $fileName_$arity[${`A..V, `}t, Ns1, Ns2] {
         |  def %(divider: t, remainder: t        ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(Remainder , Seq(divider, remainder))
         |  def even                               : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(Even      , Nil                    )
         |  def odd                                : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(Odd       , Nil                    )
         |}""".stripMargin
//         |trait $fileName_${arity}_Decimal[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends $fileName_$arity[${`A..V, `}t, Ns1, Ns2] {
//         |  def whole                              : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(Whole     , Nil)
//         |  def fractional                         : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(Fractional, Nil)
//         |}""".stripMargin
  }
}
