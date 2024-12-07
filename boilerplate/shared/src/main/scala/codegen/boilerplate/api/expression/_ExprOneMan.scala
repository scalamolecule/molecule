package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase


object _ExprOneMan extends BoilerplateGenBase("ExprOneMan", "/api/expression") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.api.expression
       |
       |import molecule.base.ast._
       |import molecule.boilerplate.api._
       |import molecule.boilerplate.ast.Model._
       |import scala.language.higherKinds
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val attrExprs = if (arity == 22) {
      s"""
         |  def apply[ns1[_], tx[_]](a: ModelOps_0[t, ns1, X2] with CardOne): Ns1[${`A..V`}, t] with SortAttrs_22[${`A..V`}, t, Ns1] = _attrSortTac(Eq , a)
         |  def not  [ns1[_], tx[_]](a: ModelOps_0[t, ns1, X2] with CardOne): Ns1[${`A..V`}, t] with SortAttrs_22[${`A..V`}, t, Ns1] = _attrSortTac(Neq, a)
         |  def <    [ns1[_], tx[_]](a: ModelOps_0[t, ns1, X2] with CardOne): Ns1[${`A..V`}, t] with SortAttrs_22[${`A..V`}, t, Ns1] = _attrSortTac(Lt , a)
         |  def <=   [ns1[_], tx[_]](a: ModelOps_0[t, ns1, X2] with CardOne): Ns1[${`A..V`}, t] with SortAttrs_22[${`A..V`}, t, Ns1] = _attrSortTac(Le , a)
         |  def >    [ns1[_], tx[_]](a: ModelOps_0[t, ns1, X2] with CardOne): Ns1[${`A..V`}, t] with SortAttrs_22[${`A..V`}, t, Ns1] = _attrSortTac(Gt , a)
         |  def >=   [ns1[_], tx[_]](a: ModelOps_0[t, ns1, X2] with CardOne): Ns1[${`A..V`}, t] with SortAttrs_22[${`A..V`}, t, Ns1] = _attrSortTac(Ge , a)""".stripMargin
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
         |  def apply(                ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(NoValue, Nil         )
         |  def apply(v    : t, vs: t*): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
         |  def apply(vs   : Seq[t]   ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(Eq     , vs          )
         |  def not  (v    : t, vs: t*): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
         |  def not  (vs   : Seq[t]   ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(Neq    , vs          )
         |  def <    (upper: t        ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(Lt     , Seq(upper)  )
         |  def <=   (upper: t        ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(Le     , Seq(upper)  )
         |  def >    (lower: t        ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(Gt     , Seq(lower)  )
         |  def >=   (lower: t        ): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(Ge     , Seq(lower)  )
         |  $attrExprs
         |}
         |trait $fileName_${arity}_String[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends $fileName_$arity[${`A..V, `}t, Ns1, Ns2] {
         |  def startsWith(prefix: t)               : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(StartsWith                     , Seq(prefix)            )
         |  def endsWith  (suffix: t)               : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(EndsWith                       , Seq(suffix)            )
         |  def contains  (needle: t)               : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(Contains                       , Seq(needle)            )
         |  def matches   (regex : t)               : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(Matches                        , Seq(regex)             )
         |  def +         (str   : t)               : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(AttrOp.Append                  , Seq(str)               )
         |  def prepend   (str   : t)               : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(AttrOp.Prepend                 , Seq(str)               )
         |  def substring (start: Int, length: Int) : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(AttrOp.SubString(start, length), Nil                    )
         |  def replaceAll(regex: t, replacement: t): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(AttrOp.ReplaceAll              , Seq(regex, replacement))
         |  def toLower                             : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(AttrOp.ToLower                 , Nil                    )
         |  def toUpper                             : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(AttrOp.ToUpper                 , Nil                    )
         |}
         |trait $fileName_${arity}_Integer[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends $fileName_${arity}_Number[${`A..V, `}t, Ns1, Ns2] {
         |  def even                       : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(Even         , Nil                    )
         |  def odd                        : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(Odd          , Nil                    )
         |}
         |trait $fileName_${arity}_Decimal[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends $fileName_${arity}_Number[${`A..V, `}t, Ns1, Ns2] {
         |  def ceil                       : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(AttrOp.Ceil  , Nil                    )
         |  def floor                      : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(AttrOp.Floor , Nil                    )
         |}
         |trait $fileName_${arity}_Number[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends $fileName_$arity[${`A..V, `}t, Ns1, Ns2] {
         |  def %(divider: t, remainder: t): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(Remainder    , Seq(divider, remainder))
         |  def +(v: t)                    : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(AttrOp.Plus  , Seq(v)                 )
         |  def -(v: t)                    : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(AttrOp.Minus , Seq(v)                 )
         |  def *(v: t)                    : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(AttrOp.Times , Seq(v)                 )
         |  def /(v: t)                    : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(AttrOp.Divide, Seq(v)                 )
         |  def %(divider: t)              : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(AttrOp.Modulo, Seq(divider)           )
         |  def negate                     : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(AttrOp.Negate, Nil                    )
         |  def abs                        : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(AttrOp.Abs   , Nil                    )
         |  def absNeg                     : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(AttrOp.AbsNeg, Nil                    )
         |}
         |trait $fileName_${arity}_Boolean[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends $fileName_$arity[${`A..V, `}t, Ns1, Ns2] {
         |  def &&(bool: t): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(AttrOp.And, Seq(bool))
         |  def ||(bool: t): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(AttrOp.Or , Seq(bool))
         |  def !          : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] with CardOne = _exprOneMan(AttrOp.Not, Nil      )
         |}""".stripMargin
  }
}
