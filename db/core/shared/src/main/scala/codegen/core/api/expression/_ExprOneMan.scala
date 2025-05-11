package codegen.core.api.expression

import codegen.CoreGenBase


object _ExprOneMan extends CoreGenBase("ExprOneMan", "/api/expression") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.core.api.expression
       |
       |import molecule.db.base.ast.*
       |import molecule.db.core.api.*
       |import molecule.db.core.ast._
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val attrExprs = if (arity == 22) {
      s"""
         |  def apply[ns1[_], tx[_]](a: ModelOps_0[t, ns1, X2] & CardOne): Entity1[${`A..V`}, t] & SortAttrs_22[${`A..V`}, t, Entity1] = _attrSortTac(Eq , a)
         |  def not  [ns1[_], tx[_]](a: ModelOps_0[t, ns1, X2] & CardOne): Entity1[${`A..V`}, t] & SortAttrs_22[${`A..V`}, t, Entity1] = _attrSortTac(Neq, a)
         |  def <    [ns1[_], tx[_]](a: ModelOps_0[t, ns1, X2] & CardOne): Entity1[${`A..V`}, t] & SortAttrs_22[${`A..V`}, t, Entity1] = _attrSortTac(Lt , a)
         |  def <=   [ns1[_], tx[_]](a: ModelOps_0[t, ns1, X2] & CardOne): Entity1[${`A..V`}, t] & SortAttrs_22[${`A..V`}, t, Entity1] = _attrSortTac(Le , a)
         |  def >    [ns1[_], tx[_]](a: ModelOps_0[t, ns1, X2] & CardOne): Entity1[${`A..V`}, t] & SortAttrs_22[${`A..V`}, t, Entity1] = _attrSortTac(Gt , a)
         |  def >=   [ns1[_], tx[_]](a: ModelOps_0[t, ns1, X2] & CardOne): Entity1[${`A..V`}, t] & SortAttrs_22[${`A..V`}, t, Entity1] = _attrSortTac(Ge , a)""".stripMargin
    } else {
      s"""
         |  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[${`A..V`}, t] & SortAttrs${a0}[${`A..V`}, t, Entity1] = _attrSortTac(Eq , a)
         |  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[${`A..V`}, t] & SortAttrs${a0}[${`A..V`}, t, Entity1] = _attrSortTac(Neq, a)
         |  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[${`A..V`}, t] & SortAttrs${a0}[${`A..V`}, t, Entity1] = _attrSortTac(Lt , a)
         |  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[${`A..V`}, t] & SortAttrs${a0}[${`A..V`}, t, Entity1] = _attrSortTac(Le , a)
         |  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[${`A..V`}, t] & SortAttrs${a0}[${`A..V`}, t, Entity1] = _attrSortTac(Gt , a)
         |  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[${`A..V`}, t] & SortAttrs${a0}[${`A..V`}, t, Entity1] = _attrSortTac(Ge , a)
         |
         |  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[$V, t, ns1, ns2]): Entity2[${`A..V`}, $V, t] & SortAttrs${a1}[${`A..V`}, $V, t, Entity2] = _attrSortMan(Eq , a)
         |  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[$V, t, ns1, ns2]): Entity2[${`A..V`}, $V, t] & SortAttrs${a1}[${`A..V`}, $V, t, Entity2] = _attrSortMan(Neq, a)
         |  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[$V, t, ns1, ns2]): Entity2[${`A..V`}, $V, t] & SortAttrs${a1}[${`A..V`}, $V, t, Entity2] = _attrSortMan(Lt , a)
         |  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[$V, t, ns1, ns2]): Entity2[${`A..V`}, $V, t] & SortAttrs${a1}[${`A..V`}, $V, t, Entity2] = _attrSortMan(Le , a)
         |  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[$V, t, ns1, ns2]): Entity2[${`A..V`}, $V, t] & SortAttrs${a1}[${`A..V`}, $V, t, Entity2] = _attrSortMan(Gt , a)
         |  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[$V, t, ns1, ns2]): Entity2[${`A..V`}, $V, t] & SortAttrs${a1}[${`A..V`}, $V, t, Entity2] = _attrSortMan(Ge , a)""".stripMargin
    }

    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V`}, t, Entity1[${`_, _`}], Entity2[${`_, _, _`}]]
         |  extends ExprAttr_$arity[${`A..V, `}t, Entity1, Entity2] {
         |  protected def _exprOneMan(op: Op, vs: Seq[t]): Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = ???
         |}
         |
         |trait $fileName_$arity[${`A..V`}, t, Entity1[${`_, _`}], Entity2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t, Entity1, Entity2]
         |    with Aggregates_$arity[${`A..V`}, t, Entity1]
         |    with SortAttrs_$arity[${`A..V`}, t, Entity1] {
         |  def apply(                ): Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(NoValue, Nil         )
         |  def apply(v    : t, vs: t*): Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
         |  def apply(vs   : Seq[t]   ): Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(Eq     , vs          )
         |  def not  (v    : t, vs: t*): Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
         |  def not  (vs   : Seq[t]   ): Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(Neq    , vs          )
         |  def <    (upper: t        ): Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(Lt     , Seq(upper)  )
         |  def <=   (upper: t        ): Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(Le     , Seq(upper)  )
         |  def >    (lower: t        ): Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(Gt     , Seq(lower)  )
         |  def >=   (lower: t        ): Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(Ge     , Seq(lower)  )
         |  $attrExprs
         |}
         |trait $fileName_${arity}_String[${`A..V`}, t, Entity1[${`_, _`}], Entity2[${`_, _, _`}]] extends $fileName_$arity[${`A..V, `}t, Entity1, Entity2] {
         |  def startsWith(prefix: t)               : Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(StartsWith                  , Seq(prefix)            )
         |  def endsWith  (suffix: t)               : Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(EndsWith                    , Seq(suffix)            )
         |  def contains  (needle: t)               : Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(Contains                    , Seq(needle)            )
         |  def matches   (regex : t)               : Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(Matches                     , Seq(regex)             )
         |  def +         (str   : t)               : Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(AttrOp.Append               , Seq(str)               )
         |  def prepend   (str   : t)               : Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(AttrOp.Prepend              , Seq(str)               )
         |  def substring (start: Int, end: Int)    : Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(AttrOp.SubString(start, end), Nil                    )
         |  def replaceAll(regex: t, replacement: t): Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(AttrOp.ReplaceAll           , Seq(regex, replacement))
         |  def toLower                             : Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToLower              , Nil                    )
         |  def toUpper                             : Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToUpper              , Nil                    )
         |}
         |trait $fileName_${arity}_Integer[${`A..V`}, t, Entity1[${`_, _`}], Entity2[${`_, _, _`}]] extends $fileName_${arity}_Number[${`A..V, `}t, Entity1, Entity2] {
         |  def even                       : Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(Even         , Nil                    )
         |  def odd                        : Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(Odd          , Nil                    )
         |  def %(divider: t, remainder: t): Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(Remainder    , Seq(divider, remainder))
         |}
         |trait $fileName_${arity}_Decimal[${`A..V`}, t, Entity1[${`_, _`}], Entity2[${`_, _, _`}]] extends $fileName_${arity}_Number[${`A..V, `}t, Entity1, Entity2] {
         |  def ceil                       : Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(AttrOp.Ceil  , Nil                    )
         |  def floor                      : Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(AttrOp.Floor , Nil                    )
         |}
         |trait $fileName_${arity}_Number[${`A..V`}, t, Entity1[${`_, _`}], Entity2[${`_, _, _`}]] extends $fileName_$arity[${`A..V, `}t, Entity1, Entity2] {
         |  def +(v: t)                    : Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(AttrOp.Plus  , Seq(v)                 )
         |  def -(v: t)                    : Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(AttrOp.Minus , Seq(v)                 )
         |  def *(v: t)                    : Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(AttrOp.Times , Seq(v)                 )
         |  def /(v: t)                    : Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(AttrOp.Divide, Seq(v)                 )
         |  def negate                     : Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(AttrOp.Negate, Nil                    )
         |  def abs                        : Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(AttrOp.Abs   , Nil                    )
         |  def absNeg                     : Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(AttrOp.AbsNeg, Nil                    )
         |}
         |trait $fileName_${arity}_Boolean[${`A..V`}, t, Entity1[${`_, _`}], Entity2[${`_, _, _`}]] extends $fileName_$arity[${`A..V, `}t, Entity1, Entity2] {
         |  def &&(bool: t): Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(AttrOp.And, Seq(bool))
         |  def ||(bool: t): Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(AttrOp.Or , Seq(bool))
         |  def !          : Entity1[${`A..V`}, t] & SortAttrs_$arity[${`A..V`}, t, Entity1] & CardOne = _exprOneMan(AttrOp.Not, Nil      )
         |}""".stripMargin
  }
}
