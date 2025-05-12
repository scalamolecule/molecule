// GENERATED CODE ********************************
package molecule.db.core.api.expression

import molecule.db.base.ast.*
import molecule.db.core.api.*
import molecule.db.core.ast._


trait ExprOneManOps_1[A, t, Entity1[_, _], Entity2[_, _, _]]
  extends ExprAttr_1[A, t, Entity1, Entity2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = ???
}

trait ExprOneMan_1[A, t, Entity1[_, _], Entity2[_, _, _]]
  extends ExprOneManOps_1[A, t, Entity1, Entity2]
    with Aggregates_1[A, t, Entity1]
    with SortAttrs_1[A, t, Entity1] {
  def apply(                ): Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, t] & SortAttrs_1[A, t, Entity1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, t] & SortAttrs_1[A, t, Entity1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, t] & SortAttrs_1[A, t, Entity1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, t] & SortAttrs_1[A, t, Entity1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, t] & SortAttrs_1[A, t, Entity1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, t] & SortAttrs_1[A, t, Entity1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[A, t, ns1, ns2]): Entity2[A, A, t] & SortAttrs_2[A, A, t, Entity2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[A, t, ns1, ns2]): Entity2[A, A, t] & SortAttrs_2[A, A, t, Entity2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[A, t, ns1, ns2]): Entity2[A, A, t] & SortAttrs_2[A, A, t, Entity2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[A, t, ns1, ns2]): Entity2[A, A, t] & SortAttrs_2[A, A, t, Entity2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[A, t, ns1, ns2]): Entity2[A, A, t] & SortAttrs_2[A, A, t, Entity2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[A, t, ns1, ns2]): Entity2[A, A, t] & SortAttrs_2[A, A, t, Entity2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_1_String[A, t, Entity1[_, _], Entity2[_, _, _]] extends ExprOneMan_1[A, t, Entity1, Entity2] {
  def startsWith(prefix: t)               : Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(StartsWith                  , Seq(prefix)            )
  def endsWith  (suffix: t)               : Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(EndsWith                    , Seq(suffix)            )
  def contains  (needle: t)               : Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(Contains                    , Seq(needle)            )
  def matches   (regex : t)               : Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(Matches                     , Seq(regex)             )
  def +         (str   : t)               : Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(AttrOp.Append               , Seq(str)               )
  def prepend   (str   : t)               : Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(AttrOp.Prepend              , Seq(str)               )
  def substring (start: Int, end: Int)    : Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(AttrOp.SubString(start, end), Nil                    )
  def replaceAll(regex: t, replacement: t): Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(AttrOp.ReplaceAll           , Seq(regex, replacement))
  def toLower                             : Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToLower              , Nil                    )
  def toUpper                             : Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToUpper              , Nil                    )
}
trait ExprOneMan_1_Integer[A, t, Entity1[_, _], Entity2[_, _, _]] extends ExprOneMan_1_Number[A, t, Entity1, Entity2] {
  def even                       : Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(Even         , Nil                    )
  def odd                        : Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(Odd          , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(Remainder    , Seq(divider, remainder))
}
trait ExprOneMan_1_Decimal[A, t, Entity1[_, _], Entity2[_, _, _]] extends ExprOneMan_1_Number[A, t, Entity1, Entity2] {
  def ceil                       : Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(AttrOp.Ceil  , Nil                    )
  def floor                      : Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(AttrOp.Floor , Nil                    )
}
trait ExprOneMan_1_Number[A, t, Entity1[_, _], Entity2[_, _, _]] extends ExprOneMan_1[A, t, Entity1, Entity2] {
  def +(v: t)                    : Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(AttrOp.Plus  , Seq(v)                 )
  def -(v: t)                    : Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(AttrOp.Minus , Seq(v)                 )
  def *(v: t)                    : Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(AttrOp.Times , Seq(v)                 )
  def /(v: t)                    : Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(AttrOp.Divide, Seq(v)                 )
  def negate                     : Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(AttrOp.Negate, Nil                    )
  def abs                        : Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(AttrOp.Abs   , Nil                    )
  def absNeg                     : Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(AttrOp.AbsNeg, Nil                    )
}
trait ExprOneMan_1_Boolean[A, t, Entity1[_, _], Entity2[_, _, _]] extends ExprOneMan_1[A, t, Entity1, Entity2] {
  def &&(bool: t): Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(AttrOp.And, Seq(bool))
  def ||(bool: t): Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(AttrOp.Or , Seq(bool))
  def !          : Entity1[A, t] & SortAttrs_1[A, t, Entity1] & CardOne = _exprOneMan(AttrOp.Not, Nil      )
}


trait ExprOneManOps_2[A, B, t, Entity1[_, _, _], Entity2[_, _, _, _]]
  extends ExprAttr_2[A, B, t, Entity1, Entity2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = ???
}

trait ExprOneMan_2[A, B, t, Entity1[_, _, _], Entity2[_, _, _, _]]
  extends ExprOneManOps_2[A, B, t, Entity1, Entity2]
    with Aggregates_2[A, B, t, Entity1]
    with SortAttrs_2[A, B, t, Entity1] {
  def apply(                ): Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[B, t, ns1, ns2]): Entity2[A, B, B, t] & SortAttrs_3[A, B, B, t, Entity2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[B, t, ns1, ns2]): Entity2[A, B, B, t] & SortAttrs_3[A, B, B, t, Entity2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[B, t, ns1, ns2]): Entity2[A, B, B, t] & SortAttrs_3[A, B, B, t, Entity2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[B, t, ns1, ns2]): Entity2[A, B, B, t] & SortAttrs_3[A, B, B, t, Entity2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[B, t, ns1, ns2]): Entity2[A, B, B, t] & SortAttrs_3[A, B, B, t, Entity2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[B, t, ns1, ns2]): Entity2[A, B, B, t] & SortAttrs_3[A, B, B, t, Entity2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_2_String[A, B, t, Entity1[_, _, _], Entity2[_, _, _, _]] extends ExprOneMan_2[A, B, t, Entity1, Entity2] {
  def startsWith(prefix: t)               : Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(StartsWith                  , Seq(prefix)            )
  def endsWith  (suffix: t)               : Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(EndsWith                    , Seq(suffix)            )
  def contains  (needle: t)               : Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(Contains                    , Seq(needle)            )
  def matches   (regex : t)               : Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(Matches                     , Seq(regex)             )
  def +         (str   : t)               : Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(AttrOp.Append               , Seq(str)               )
  def prepend   (str   : t)               : Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(AttrOp.Prepend              , Seq(str)               )
  def substring (start: Int, end: Int)    : Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(AttrOp.SubString(start, end), Nil                    )
  def replaceAll(regex: t, replacement: t): Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(AttrOp.ReplaceAll           , Seq(regex, replacement))
  def toLower                             : Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToLower              , Nil                    )
  def toUpper                             : Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToUpper              , Nil                    )
}
trait ExprOneMan_2_Integer[A, B, t, Entity1[_, _, _], Entity2[_, _, _, _]] extends ExprOneMan_2_Number[A, B, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(Even         , Nil                    )
  def odd                        : Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(Odd          , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(Remainder    , Seq(divider, remainder))
}
trait ExprOneMan_2_Decimal[A, B, t, Entity1[_, _, _], Entity2[_, _, _, _]] extends ExprOneMan_2_Number[A, B, t, Entity1, Entity2] {
  def ceil                       : Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(AttrOp.Ceil  , Nil                    )
  def floor                      : Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(AttrOp.Floor , Nil                    )
}
trait ExprOneMan_2_Number[A, B, t, Entity1[_, _, _], Entity2[_, _, _, _]] extends ExprOneMan_2[A, B, t, Entity1, Entity2] {
  def +(v: t)                    : Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(AttrOp.Plus  , Seq(v)                 )
  def -(v: t)                    : Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(AttrOp.Minus , Seq(v)                 )
  def *(v: t)                    : Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(AttrOp.Times , Seq(v)                 )
  def /(v: t)                    : Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(AttrOp.Divide, Seq(v)                 )
  def negate                     : Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(AttrOp.Negate, Nil                    )
  def abs                        : Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(AttrOp.Abs   , Nil                    )
  def absNeg                     : Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(AttrOp.AbsNeg, Nil                    )
}
trait ExprOneMan_2_Boolean[A, B, t, Entity1[_, _, _], Entity2[_, _, _, _]] extends ExprOneMan_2[A, B, t, Entity1, Entity2] {
  def &&(bool: t): Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(AttrOp.And, Seq(bool))
  def ||(bool: t): Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(AttrOp.Or , Seq(bool))
  def !          : Entity1[A, B, t] & SortAttrs_2[A, B, t, Entity1] & CardOne = _exprOneMan(AttrOp.Not, Nil      )
}


trait ExprOneManOps_3[A, B, C, t, Entity1[_, _, _, _], Entity2[_, _, _, _, _]]
  extends ExprAttr_3[A, B, C, t, Entity1, Entity2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = ???
}

trait ExprOneMan_3[A, B, C, t, Entity1[_, _, _, _], Entity2[_, _, _, _, _]]
  extends ExprOneManOps_3[A, B, C, t, Entity1, Entity2]
    with Aggregates_3[A, B, C, t, Entity1]
    with SortAttrs_3[A, B, C, t, Entity1] {
  def apply(                ): Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[C, t, ns1, ns2]): Entity2[A, B, C, C, t] & SortAttrs_4[A, B, C, C, t, Entity2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[C, t, ns1, ns2]): Entity2[A, B, C, C, t] & SortAttrs_4[A, B, C, C, t, Entity2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[C, t, ns1, ns2]): Entity2[A, B, C, C, t] & SortAttrs_4[A, B, C, C, t, Entity2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[C, t, ns1, ns2]): Entity2[A, B, C, C, t] & SortAttrs_4[A, B, C, C, t, Entity2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[C, t, ns1, ns2]): Entity2[A, B, C, C, t] & SortAttrs_4[A, B, C, C, t, Entity2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[C, t, ns1, ns2]): Entity2[A, B, C, C, t] & SortAttrs_4[A, B, C, C, t, Entity2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_3_String[A, B, C, t, Entity1[_, _, _, _], Entity2[_, _, _, _, _]] extends ExprOneMan_3[A, B, C, t, Entity1, Entity2] {
  def startsWith(prefix: t)               : Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(StartsWith                  , Seq(prefix)            )
  def endsWith  (suffix: t)               : Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(EndsWith                    , Seq(suffix)            )
  def contains  (needle: t)               : Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(Contains                    , Seq(needle)            )
  def matches   (regex : t)               : Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(Matches                     , Seq(regex)             )
  def +         (str   : t)               : Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(AttrOp.Append               , Seq(str)               )
  def prepend   (str   : t)               : Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(AttrOp.Prepend              , Seq(str)               )
  def substring (start: Int, end: Int)    : Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(AttrOp.SubString(start, end), Nil                    )
  def replaceAll(regex: t, replacement: t): Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(AttrOp.ReplaceAll           , Seq(regex, replacement))
  def toLower                             : Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToLower              , Nil                    )
  def toUpper                             : Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToUpper              , Nil                    )
}
trait ExprOneMan_3_Integer[A, B, C, t, Entity1[_, _, _, _], Entity2[_, _, _, _, _]] extends ExprOneMan_3_Number[A, B, C, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(Even         , Nil                    )
  def odd                        : Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(Odd          , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(Remainder    , Seq(divider, remainder))
}
trait ExprOneMan_3_Decimal[A, B, C, t, Entity1[_, _, _, _], Entity2[_, _, _, _, _]] extends ExprOneMan_3_Number[A, B, C, t, Entity1, Entity2] {
  def ceil                       : Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(AttrOp.Ceil  , Nil                    )
  def floor                      : Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(AttrOp.Floor , Nil                    )
}
trait ExprOneMan_3_Number[A, B, C, t, Entity1[_, _, _, _], Entity2[_, _, _, _, _]] extends ExprOneMan_3[A, B, C, t, Entity1, Entity2] {
  def +(v: t)                    : Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(AttrOp.Plus  , Seq(v)                 )
  def -(v: t)                    : Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(AttrOp.Minus , Seq(v)                 )
  def *(v: t)                    : Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(AttrOp.Times , Seq(v)                 )
  def /(v: t)                    : Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(AttrOp.Divide, Seq(v)                 )
  def negate                     : Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(AttrOp.Negate, Nil                    )
  def abs                        : Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(AttrOp.Abs   , Nil                    )
  def absNeg                     : Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(AttrOp.AbsNeg, Nil                    )
}
trait ExprOneMan_3_Boolean[A, B, C, t, Entity1[_, _, _, _], Entity2[_, _, _, _, _]] extends ExprOneMan_3[A, B, C, t, Entity1, Entity2] {
  def &&(bool: t): Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(AttrOp.And, Seq(bool))
  def ||(bool: t): Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(AttrOp.Or , Seq(bool))
  def !          : Entity1[A, B, C, t] & SortAttrs_3[A, B, C, t, Entity1] & CardOne = _exprOneMan(AttrOp.Not, Nil      )
}


trait ExprOneManOps_4[A, B, C, D, t, Entity1[_, _, _, _, _], Entity2[_, _, _, _, _, _]]
  extends ExprAttr_4[A, B, C, D, t, Entity1, Entity2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = ???
}

trait ExprOneMan_4[A, B, C, D, t, Entity1[_, _, _, _, _], Entity2[_, _, _, _, _, _]]
  extends ExprOneManOps_4[A, B, C, D, t, Entity1, Entity2]
    with Aggregates_4[A, B, C, D, t, Entity1]
    with SortAttrs_4[A, B, C, D, t, Entity1] {
  def apply(                ): Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[D, t, ns1, ns2]): Entity2[A, B, C, D, D, t] & SortAttrs_5[A, B, C, D, D, t, Entity2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[D, t, ns1, ns2]): Entity2[A, B, C, D, D, t] & SortAttrs_5[A, B, C, D, D, t, Entity2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[D, t, ns1, ns2]): Entity2[A, B, C, D, D, t] & SortAttrs_5[A, B, C, D, D, t, Entity2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[D, t, ns1, ns2]): Entity2[A, B, C, D, D, t] & SortAttrs_5[A, B, C, D, D, t, Entity2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[D, t, ns1, ns2]): Entity2[A, B, C, D, D, t] & SortAttrs_5[A, B, C, D, D, t, Entity2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[D, t, ns1, ns2]): Entity2[A, B, C, D, D, t] & SortAttrs_5[A, B, C, D, D, t, Entity2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_4_String[A, B, C, D, t, Entity1[_, _, _, _, _], Entity2[_, _, _, _, _, _]] extends ExprOneMan_4[A, B, C, D, t, Entity1, Entity2] {
  def startsWith(prefix: t)               : Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(StartsWith                  , Seq(prefix)            )
  def endsWith  (suffix: t)               : Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(EndsWith                    , Seq(suffix)            )
  def contains  (needle: t)               : Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(Contains                    , Seq(needle)            )
  def matches   (regex : t)               : Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(Matches                     , Seq(regex)             )
  def +         (str   : t)               : Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(AttrOp.Append               , Seq(str)               )
  def prepend   (str   : t)               : Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(AttrOp.Prepend              , Seq(str)               )
  def substring (start: Int, end: Int)    : Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(AttrOp.SubString(start, end), Nil                    )
  def replaceAll(regex: t, replacement: t): Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(AttrOp.ReplaceAll           , Seq(regex, replacement))
  def toLower                             : Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToLower              , Nil                    )
  def toUpper                             : Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToUpper              , Nil                    )
}
trait ExprOneMan_4_Integer[A, B, C, D, t, Entity1[_, _, _, _, _], Entity2[_, _, _, _, _, _]] extends ExprOneMan_4_Number[A, B, C, D, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(Even         , Nil                    )
  def odd                        : Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(Odd          , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(Remainder    , Seq(divider, remainder))
}
trait ExprOneMan_4_Decimal[A, B, C, D, t, Entity1[_, _, _, _, _], Entity2[_, _, _, _, _, _]] extends ExprOneMan_4_Number[A, B, C, D, t, Entity1, Entity2] {
  def ceil                       : Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(AttrOp.Ceil  , Nil                    )
  def floor                      : Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(AttrOp.Floor , Nil                    )
}
trait ExprOneMan_4_Number[A, B, C, D, t, Entity1[_, _, _, _, _], Entity2[_, _, _, _, _, _]] extends ExprOneMan_4[A, B, C, D, t, Entity1, Entity2] {
  def +(v: t)                    : Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(AttrOp.Plus  , Seq(v)                 )
  def -(v: t)                    : Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(AttrOp.Minus , Seq(v)                 )
  def *(v: t)                    : Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(AttrOp.Times , Seq(v)                 )
  def /(v: t)                    : Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(AttrOp.Divide, Seq(v)                 )
  def negate                     : Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(AttrOp.Negate, Nil                    )
  def abs                        : Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(AttrOp.Abs   , Nil                    )
  def absNeg                     : Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(AttrOp.AbsNeg, Nil                    )
}
trait ExprOneMan_4_Boolean[A, B, C, D, t, Entity1[_, _, _, _, _], Entity2[_, _, _, _, _, _]] extends ExprOneMan_4[A, B, C, D, t, Entity1, Entity2] {
  def &&(bool: t): Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(AttrOp.And, Seq(bool))
  def ||(bool: t): Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(AttrOp.Or , Seq(bool))
  def !          : Entity1[A, B, C, D, t] & SortAttrs_4[A, B, C, D, t, Entity1] & CardOne = _exprOneMan(AttrOp.Not, Nil      )
}


trait ExprOneManOps_5[A, B, C, D, E, t, Entity1[_, _, _, _, _, _], Entity2[_, _, _, _, _, _, _]]
  extends ExprAttr_5[A, B, C, D, E, t, Entity1, Entity2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = ???
}

trait ExprOneMan_5[A, B, C, D, E, t, Entity1[_, _, _, _, _, _], Entity2[_, _, _, _, _, _, _]]
  extends ExprOneManOps_5[A, B, C, D, E, t, Entity1, Entity2]
    with Aggregates_5[A, B, C, D, E, t, Entity1]
    with SortAttrs_5[A, B, C, D, E, t, Entity1] {
  def apply(                ): Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[E, t, ns1, ns2]): Entity2[A, B, C, D, E, E, t] & SortAttrs_6[A, B, C, D, E, E, t, Entity2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[E, t, ns1, ns2]): Entity2[A, B, C, D, E, E, t] & SortAttrs_6[A, B, C, D, E, E, t, Entity2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[E, t, ns1, ns2]): Entity2[A, B, C, D, E, E, t] & SortAttrs_6[A, B, C, D, E, E, t, Entity2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[E, t, ns1, ns2]): Entity2[A, B, C, D, E, E, t] & SortAttrs_6[A, B, C, D, E, E, t, Entity2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[E, t, ns1, ns2]): Entity2[A, B, C, D, E, E, t] & SortAttrs_6[A, B, C, D, E, E, t, Entity2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[E, t, ns1, ns2]): Entity2[A, B, C, D, E, E, t] & SortAttrs_6[A, B, C, D, E, E, t, Entity2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_5_String[A, B, C, D, E, t, Entity1[_, _, _, _, _, _], Entity2[_, _, _, _, _, _, _]] extends ExprOneMan_5[A, B, C, D, E, t, Entity1, Entity2] {
  def startsWith(prefix: t)               : Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(StartsWith                  , Seq(prefix)            )
  def endsWith  (suffix: t)               : Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(EndsWith                    , Seq(suffix)            )
  def contains  (needle: t)               : Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(Contains                    , Seq(needle)            )
  def matches   (regex : t)               : Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(Matches                     , Seq(regex)             )
  def +         (str   : t)               : Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(AttrOp.Append               , Seq(str)               )
  def prepend   (str   : t)               : Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(AttrOp.Prepend              , Seq(str)               )
  def substring (start: Int, end: Int)    : Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(AttrOp.SubString(start, end), Nil                    )
  def replaceAll(regex: t, replacement: t): Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(AttrOp.ReplaceAll           , Seq(regex, replacement))
  def toLower                             : Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToLower              , Nil                    )
  def toUpper                             : Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToUpper              , Nil                    )
}
trait ExprOneMan_5_Integer[A, B, C, D, E, t, Entity1[_, _, _, _, _, _], Entity2[_, _, _, _, _, _, _]] extends ExprOneMan_5_Number[A, B, C, D, E, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(Even         , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(Odd          , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(Remainder    , Seq(divider, remainder))
}
trait ExprOneMan_5_Decimal[A, B, C, D, E, t, Entity1[_, _, _, _, _, _], Entity2[_, _, _, _, _, _, _]] extends ExprOneMan_5_Number[A, B, C, D, E, t, Entity1, Entity2] {
  def ceil                       : Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(AttrOp.Ceil  , Nil                    )
  def floor                      : Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(AttrOp.Floor , Nil                    )
}
trait ExprOneMan_5_Number[A, B, C, D, E, t, Entity1[_, _, _, _, _, _], Entity2[_, _, _, _, _, _, _]] extends ExprOneMan_5[A, B, C, D, E, t, Entity1, Entity2] {
  def +(v: t)                    : Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(AttrOp.Plus  , Seq(v)                 )
  def -(v: t)                    : Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(AttrOp.Minus , Seq(v)                 )
  def *(v: t)                    : Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(AttrOp.Times , Seq(v)                 )
  def /(v: t)                    : Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(AttrOp.Divide, Seq(v)                 )
  def negate                     : Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(AttrOp.Negate, Nil                    )
  def abs                        : Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(AttrOp.Abs   , Nil                    )
  def absNeg                     : Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(AttrOp.AbsNeg, Nil                    )
}
trait ExprOneMan_5_Boolean[A, B, C, D, E, t, Entity1[_, _, _, _, _, _], Entity2[_, _, _, _, _, _, _]] extends ExprOneMan_5[A, B, C, D, E, t, Entity1, Entity2] {
  def &&(bool: t): Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(AttrOp.And, Seq(bool))
  def ||(bool: t): Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(AttrOp.Or , Seq(bool))
  def !          : Entity1[A, B, C, D, E, t] & SortAttrs_5[A, B, C, D, E, t, Entity1] & CardOne = _exprOneMan(AttrOp.Not, Nil      )
}


trait ExprOneManOps_6[A, B, C, D, E, F, t, Entity1[_, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _]]
  extends ExprAttr_6[A, B, C, D, E, F, t, Entity1, Entity2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = ???
}

trait ExprOneMan_6[A, B, C, D, E, F, t, Entity1[_, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _]]
  extends ExprOneManOps_6[A, B, C, D, E, F, t, Entity1, Entity2]
    with Aggregates_6[A, B, C, D, E, F, t, Entity1]
    with SortAttrs_6[A, B, C, D, E, F, t, Entity1] {
  def apply(                ): Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[F, t, ns1, ns2]): Entity2[A, B, C, D, E, F, F, t] & SortAttrs_7[A, B, C, D, E, F, F, t, Entity2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[F, t, ns1, ns2]): Entity2[A, B, C, D, E, F, F, t] & SortAttrs_7[A, B, C, D, E, F, F, t, Entity2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[F, t, ns1, ns2]): Entity2[A, B, C, D, E, F, F, t] & SortAttrs_7[A, B, C, D, E, F, F, t, Entity2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[F, t, ns1, ns2]): Entity2[A, B, C, D, E, F, F, t] & SortAttrs_7[A, B, C, D, E, F, F, t, Entity2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[F, t, ns1, ns2]): Entity2[A, B, C, D, E, F, F, t] & SortAttrs_7[A, B, C, D, E, F, F, t, Entity2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[F, t, ns1, ns2]): Entity2[A, B, C, D, E, F, F, t] & SortAttrs_7[A, B, C, D, E, F, F, t, Entity2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_6_String[A, B, C, D, E, F, t, Entity1[_, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _]] extends ExprOneMan_6[A, B, C, D, E, F, t, Entity1, Entity2] {
  def startsWith(prefix: t)               : Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(StartsWith                  , Seq(prefix)            )
  def endsWith  (suffix: t)               : Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(EndsWith                    , Seq(suffix)            )
  def contains  (needle: t)               : Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(Contains                    , Seq(needle)            )
  def matches   (regex : t)               : Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(Matches                     , Seq(regex)             )
  def +         (str   : t)               : Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(AttrOp.Append               , Seq(str)               )
  def prepend   (str   : t)               : Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(AttrOp.Prepend              , Seq(str)               )
  def substring (start: Int, end: Int)    : Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(AttrOp.SubString(start, end), Nil                    )
  def replaceAll(regex: t, replacement: t): Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(AttrOp.ReplaceAll           , Seq(regex, replacement))
  def toLower                             : Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToLower              , Nil                    )
  def toUpper                             : Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToUpper              , Nil                    )
}
trait ExprOneMan_6_Integer[A, B, C, D, E, F, t, Entity1[_, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _]] extends ExprOneMan_6_Number[A, B, C, D, E, F, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(Even         , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(Odd          , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(Remainder    , Seq(divider, remainder))
}
trait ExprOneMan_6_Decimal[A, B, C, D, E, F, t, Entity1[_, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _]] extends ExprOneMan_6_Number[A, B, C, D, E, F, t, Entity1, Entity2] {
  def ceil                       : Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(AttrOp.Ceil  , Nil                    )
  def floor                      : Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(AttrOp.Floor , Nil                    )
}
trait ExprOneMan_6_Number[A, B, C, D, E, F, t, Entity1[_, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _]] extends ExprOneMan_6[A, B, C, D, E, F, t, Entity1, Entity2] {
  def +(v: t)                    : Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(AttrOp.Plus  , Seq(v)                 )
  def -(v: t)                    : Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(AttrOp.Minus , Seq(v)                 )
  def *(v: t)                    : Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(AttrOp.Times , Seq(v)                 )
  def /(v: t)                    : Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(AttrOp.Divide, Seq(v)                 )
  def negate                     : Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(AttrOp.Negate, Nil                    )
  def abs                        : Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(AttrOp.Abs   , Nil                    )
  def absNeg                     : Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(AttrOp.AbsNeg, Nil                    )
}
trait ExprOneMan_6_Boolean[A, B, C, D, E, F, t, Entity1[_, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _]] extends ExprOneMan_6[A, B, C, D, E, F, t, Entity1, Entity2] {
  def &&(bool: t): Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(AttrOp.And, Seq(bool))
  def ||(bool: t): Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(AttrOp.Or , Seq(bool))
  def !          : Entity1[A, B, C, D, E, F, t] & SortAttrs_6[A, B, C, D, E, F, t, Entity1] & CardOne = _exprOneMan(AttrOp.Not, Nil      )
}


trait ExprOneManOps_7[A, B, C, D, E, F, G, t, Entity1[_, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _]]
  extends ExprAttr_7[A, B, C, D, E, F, G, t, Entity1, Entity2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = ???
}

trait ExprOneMan_7[A, B, C, D, E, F, G, t, Entity1[_, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_7[A, B, C, D, E, F, G, t, Entity1, Entity2]
    with Aggregates_7[A, B, C, D, E, F, G, t, Entity1]
    with SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[G, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, G, t] & SortAttrs_8[A, B, C, D, E, F, G, G, t, Entity2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[G, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, G, t] & SortAttrs_8[A, B, C, D, E, F, G, G, t, Entity2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[G, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, G, t] & SortAttrs_8[A, B, C, D, E, F, G, G, t, Entity2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[G, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, G, t] & SortAttrs_8[A, B, C, D, E, F, G, G, t, Entity2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[G, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, G, t] & SortAttrs_8[A, B, C, D, E, F, G, G, t, Entity2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[G, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, G, t] & SortAttrs_8[A, B, C, D, E, F, G, G, t, Entity2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_7_String[A, B, C, D, E, F, G, t, Entity1[_, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _]] extends ExprOneMan_7[A, B, C, D, E, F, G, t, Entity1, Entity2] {
  def startsWith(prefix: t)               : Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(StartsWith                  , Seq(prefix)            )
  def endsWith  (suffix: t)               : Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(EndsWith                    , Seq(suffix)            )
  def contains  (needle: t)               : Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(Contains                    , Seq(needle)            )
  def matches   (regex : t)               : Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(Matches                     , Seq(regex)             )
  def +         (str   : t)               : Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(AttrOp.Append               , Seq(str)               )
  def prepend   (str   : t)               : Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(AttrOp.Prepend              , Seq(str)               )
  def substring (start: Int, end: Int)    : Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(AttrOp.SubString(start, end), Nil                    )
  def replaceAll(regex: t, replacement: t): Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(AttrOp.ReplaceAll           , Seq(regex, replacement))
  def toLower                             : Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToLower              , Nil                    )
  def toUpper                             : Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToUpper              , Nil                    )
}
trait ExprOneMan_7_Integer[A, B, C, D, E, F, G, t, Entity1[_, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _]] extends ExprOneMan_7_Number[A, B, C, D, E, F, G, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(Even         , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(Odd          , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(Remainder    , Seq(divider, remainder))
}
trait ExprOneMan_7_Decimal[A, B, C, D, E, F, G, t, Entity1[_, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _]] extends ExprOneMan_7_Number[A, B, C, D, E, F, G, t, Entity1, Entity2] {
  def ceil                       : Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(AttrOp.Ceil  , Nil                    )
  def floor                      : Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(AttrOp.Floor , Nil                    )
}
trait ExprOneMan_7_Number[A, B, C, D, E, F, G, t, Entity1[_, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _]] extends ExprOneMan_7[A, B, C, D, E, F, G, t, Entity1, Entity2] {
  def +(v: t)                    : Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(AttrOp.Plus  , Seq(v)                 )
  def -(v: t)                    : Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(AttrOp.Minus , Seq(v)                 )
  def *(v: t)                    : Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(AttrOp.Times , Seq(v)                 )
  def /(v: t)                    : Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(AttrOp.Divide, Seq(v)                 )
  def negate                     : Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(AttrOp.Negate, Nil                    )
  def abs                        : Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(AttrOp.Abs   , Nil                    )
  def absNeg                     : Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(AttrOp.AbsNeg, Nil                    )
}
trait ExprOneMan_7_Boolean[A, B, C, D, E, F, G, t, Entity1[_, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _]] extends ExprOneMan_7[A, B, C, D, E, F, G, t, Entity1, Entity2] {
  def &&(bool: t): Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(AttrOp.And, Seq(bool))
  def ||(bool: t): Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(AttrOp.Or , Seq(bool))
  def !          : Entity1[A, B, C, D, E, F, G, t] & SortAttrs_7[A, B, C, D, E, F, G, t, Entity1] & CardOne = _exprOneMan(AttrOp.Not, Nil      )
}


trait ExprOneManOps_8[A, B, C, D, E, F, G, H, t, Entity1[_, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_8[A, B, C, D, E, F, G, H, t, Entity1, Entity2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = ???
}

trait ExprOneMan_8[A, B, C, D, E, F, G, H, t, Entity1[_, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_8[A, B, C, D, E, F, G, H, t, Entity1, Entity2]
    with Aggregates_8[A, B, C, D, E, F, G, H, t, Entity1]
    with SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[H, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, H, t] & SortAttrs_9[A, B, C, D, E, F, G, H, H, t, Entity2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[H, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, H, t] & SortAttrs_9[A, B, C, D, E, F, G, H, H, t, Entity2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[H, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, H, t] & SortAttrs_9[A, B, C, D, E, F, G, H, H, t, Entity2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[H, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, H, t] & SortAttrs_9[A, B, C, D, E, F, G, H, H, t, Entity2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[H, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, H, t] & SortAttrs_9[A, B, C, D, E, F, G, H, H, t, Entity2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[H, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, H, t] & SortAttrs_9[A, B, C, D, E, F, G, H, H, t, Entity2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_8_String[A, B, C, D, E, F, G, H, t, Entity1[_, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_8[A, B, C, D, E, F, G, H, t, Entity1, Entity2] {
  def startsWith(prefix: t)               : Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(StartsWith                  , Seq(prefix)            )
  def endsWith  (suffix: t)               : Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(EndsWith                    , Seq(suffix)            )
  def contains  (needle: t)               : Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(Contains                    , Seq(needle)            )
  def matches   (regex : t)               : Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(Matches                     , Seq(regex)             )
  def +         (str   : t)               : Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(AttrOp.Append               , Seq(str)               )
  def prepend   (str   : t)               : Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(AttrOp.Prepend              , Seq(str)               )
  def substring (start: Int, end: Int)    : Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(AttrOp.SubString(start, end), Nil                    )
  def replaceAll(regex: t, replacement: t): Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(AttrOp.ReplaceAll           , Seq(regex, replacement))
  def toLower                             : Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToLower              , Nil                    )
  def toUpper                             : Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToUpper              , Nil                    )
}
trait ExprOneMan_8_Integer[A, B, C, D, E, F, G, H, t, Entity1[_, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_8_Number[A, B, C, D, E, F, G, H, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(Even         , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(Odd          , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(Remainder    , Seq(divider, remainder))
}
trait ExprOneMan_8_Decimal[A, B, C, D, E, F, G, H, t, Entity1[_, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_8_Number[A, B, C, D, E, F, G, H, t, Entity1, Entity2] {
  def ceil                       : Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(AttrOp.Ceil  , Nil                    )
  def floor                      : Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(AttrOp.Floor , Nil                    )
}
trait ExprOneMan_8_Number[A, B, C, D, E, F, G, H, t, Entity1[_, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_8[A, B, C, D, E, F, G, H, t, Entity1, Entity2] {
  def +(v: t)                    : Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(AttrOp.Plus  , Seq(v)                 )
  def -(v: t)                    : Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(AttrOp.Minus , Seq(v)                 )
  def *(v: t)                    : Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(AttrOp.Times , Seq(v)                 )
  def /(v: t)                    : Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(AttrOp.Divide, Seq(v)                 )
  def negate                     : Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(AttrOp.Negate, Nil                    )
  def abs                        : Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(AttrOp.Abs   , Nil                    )
  def absNeg                     : Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(AttrOp.AbsNeg, Nil                    )
}
trait ExprOneMan_8_Boolean[A, B, C, D, E, F, G, H, t, Entity1[_, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_8[A, B, C, D, E, F, G, H, t, Entity1, Entity2] {
  def &&(bool: t): Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(AttrOp.And, Seq(bool))
  def ||(bool: t): Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(AttrOp.Or , Seq(bool))
  def !          : Entity1[A, B, C, D, E, F, G, H, t] & SortAttrs_8[A, B, C, D, E, F, G, H, t, Entity1] & CardOne = _exprOneMan(AttrOp.Not, Nil      )
}


trait ExprOneManOps_9[A, B, C, D, E, F, G, H, I, t, Entity1[_, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_9[A, B, C, D, E, F, G, H, I, t, Entity1, Entity2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = ???
}

trait ExprOneMan_9[A, B, C, D, E, F, G, H, I, t, Entity1[_, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_9[A, B, C, D, E, F, G, H, I, t, Entity1, Entity2]
    with Aggregates_9[A, B, C, D, E, F, G, H, I, t, Entity1]
    with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[I, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, I, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, I, t, Entity2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[I, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, I, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, I, t, Entity2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[I, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, I, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, I, t, Entity2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[I, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, I, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, I, t, Entity2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[I, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, I, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, I, t, Entity2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[I, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, I, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, I, t, Entity2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_9_String[A, B, C, D, E, F, G, H, I, t, Entity1[_, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_9[A, B, C, D, E, F, G, H, I, t, Entity1, Entity2] {
  def startsWith(prefix: t)               : Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(StartsWith                  , Seq(prefix)            )
  def endsWith  (suffix: t)               : Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(EndsWith                    , Seq(suffix)            )
  def contains  (needle: t)               : Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(Contains                    , Seq(needle)            )
  def matches   (regex : t)               : Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(Matches                     , Seq(regex)             )
  def +         (str   : t)               : Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(AttrOp.Append               , Seq(str)               )
  def prepend   (str   : t)               : Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(AttrOp.Prepend              , Seq(str)               )
  def substring (start: Int, end: Int)    : Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(AttrOp.SubString(start, end), Nil                    )
  def replaceAll(regex: t, replacement: t): Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(AttrOp.ReplaceAll           , Seq(regex, replacement))
  def toLower                             : Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToLower              , Nil                    )
  def toUpper                             : Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToUpper              , Nil                    )
}
trait ExprOneMan_9_Integer[A, B, C, D, E, F, G, H, I, t, Entity1[_, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_9_Number[A, B, C, D, E, F, G, H, I, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(Even         , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(Odd          , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(Remainder    , Seq(divider, remainder))
}
trait ExprOneMan_9_Decimal[A, B, C, D, E, F, G, H, I, t, Entity1[_, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_9_Number[A, B, C, D, E, F, G, H, I, t, Entity1, Entity2] {
  def ceil                       : Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(AttrOp.Ceil  , Nil                    )
  def floor                      : Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(AttrOp.Floor , Nil                    )
}
trait ExprOneMan_9_Number[A, B, C, D, E, F, G, H, I, t, Entity1[_, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_9[A, B, C, D, E, F, G, H, I, t, Entity1, Entity2] {
  def +(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(AttrOp.Plus  , Seq(v)                 )
  def -(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(AttrOp.Minus , Seq(v)                 )
  def *(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(AttrOp.Times , Seq(v)                 )
  def /(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(AttrOp.Divide, Seq(v)                 )
  def negate                     : Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(AttrOp.Negate, Nil                    )
  def abs                        : Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(AttrOp.Abs   , Nil                    )
  def absNeg                     : Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(AttrOp.AbsNeg, Nil                    )
}
trait ExprOneMan_9_Boolean[A, B, C, D, E, F, G, H, I, t, Entity1[_, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_9[A, B, C, D, E, F, G, H, I, t, Entity1, Entity2] {
  def &&(bool: t): Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(AttrOp.And, Seq(bool))
  def ||(bool: t): Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(AttrOp.Or , Seq(bool))
  def !          : Entity1[A, B, C, D, E, F, G, H, I, t] & SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Entity1] & CardOne = _exprOneMan(AttrOp.Not, Nil      )
}


trait ExprOneManOps_10[A, B, C, D, E, F, G, H, I, J, t, Entity1[_, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_10[A, B, C, D, E, F, G, H, I, J, t, Entity1, Entity2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = ???
}

trait ExprOneMan_10[A, B, C, D, E, F, G, H, I, J, t, Entity1[_, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_10[A, B, C, D, E, F, G, H, I, J, t, Entity1, Entity2]
    with Aggregates_10[A, B, C, D, E, F, G, H, I, J, t, Entity1]
    with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[J, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, J, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, J, t, Entity2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[J, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, J, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, J, t, Entity2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[J, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, J, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, J, t, Entity2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[J, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, J, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, J, t, Entity2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[J, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, J, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, J, t, Entity2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[J, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, J, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, J, t, Entity2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_10_String[A, B, C, D, E, F, G, H, I, J, t, Entity1[_, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_10[A, B, C, D, E, F, G, H, I, J, t, Entity1, Entity2] {
  def startsWith(prefix: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(StartsWith                  , Seq(prefix)            )
  def endsWith  (suffix: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(EndsWith                    , Seq(suffix)            )
  def contains  (needle: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(Contains                    , Seq(needle)            )
  def matches   (regex : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(Matches                     , Seq(regex)             )
  def +         (str   : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(AttrOp.Append               , Seq(str)               )
  def prepend   (str   : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(AttrOp.Prepend              , Seq(str)               )
  def substring (start: Int, end: Int)    : Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(AttrOp.SubString(start, end), Nil                    )
  def replaceAll(regex: t, replacement: t): Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(AttrOp.ReplaceAll           , Seq(regex, replacement))
  def toLower                             : Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToLower              , Nil                    )
  def toUpper                             : Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToUpper              , Nil                    )
}
trait ExprOneMan_10_Integer[A, B, C, D, E, F, G, H, I, J, t, Entity1[_, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_10_Number[A, B, C, D, E, F, G, H, I, J, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(Even         , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(Odd          , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(Remainder    , Seq(divider, remainder))
}
trait ExprOneMan_10_Decimal[A, B, C, D, E, F, G, H, I, J, t, Entity1[_, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_10_Number[A, B, C, D, E, F, G, H, I, J, t, Entity1, Entity2] {
  def ceil                       : Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(AttrOp.Ceil  , Nil                    )
  def floor                      : Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(AttrOp.Floor , Nil                    )
}
trait ExprOneMan_10_Number[A, B, C, D, E, F, G, H, I, J, t, Entity1[_, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_10[A, B, C, D, E, F, G, H, I, J, t, Entity1, Entity2] {
  def +(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(AttrOp.Plus  , Seq(v)                 )
  def -(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(AttrOp.Minus , Seq(v)                 )
  def *(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(AttrOp.Times , Seq(v)                 )
  def /(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(AttrOp.Divide, Seq(v)                 )
  def negate                     : Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(AttrOp.Negate, Nil                    )
  def abs                        : Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(AttrOp.Abs   , Nil                    )
  def absNeg                     : Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(AttrOp.AbsNeg, Nil                    )
}
trait ExprOneMan_10_Boolean[A, B, C, D, E, F, G, H, I, J, t, Entity1[_, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_10[A, B, C, D, E, F, G, H, I, J, t, Entity1, Entity2] {
  def &&(bool: t): Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(AttrOp.And, Seq(bool))
  def ||(bool: t): Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(AttrOp.Or , Seq(bool))
  def !          : Entity1[A, B, C, D, E, F, G, H, I, J, t] & SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Entity1] & CardOne = _exprOneMan(AttrOp.Not, Nil      )
}


trait ExprOneManOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1, Entity2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = ???
}

trait ExprOneMan_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1, Entity2]
    with Aggregates_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1]
    with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[K, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, K, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, K, t, Entity2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[K, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, K, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, K, t, Entity2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[K, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, K, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, K, t, Entity2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[K, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, K, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, K, t, Entity2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[K, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, K, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, K, t, Entity2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[K, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, K, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, K, t, Entity2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_11_String[A, B, C, D, E, F, G, H, I, J, K, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1, Entity2] {
  def startsWith(prefix: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(StartsWith                  , Seq(prefix)            )
  def endsWith  (suffix: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(EndsWith                    , Seq(suffix)            )
  def contains  (needle: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(Contains                    , Seq(needle)            )
  def matches   (regex : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(Matches                     , Seq(regex)             )
  def +         (str   : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(AttrOp.Append               , Seq(str)               )
  def prepend   (str   : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(AttrOp.Prepend              , Seq(str)               )
  def substring (start: Int, end: Int)    : Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(AttrOp.SubString(start, end), Nil                    )
  def replaceAll(regex: t, replacement: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(AttrOp.ReplaceAll           , Seq(regex, replacement))
  def toLower                             : Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToLower              , Nil                    )
  def toUpper                             : Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToUpper              , Nil                    )
}
trait ExprOneMan_11_Integer[A, B, C, D, E, F, G, H, I, J, K, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_11_Number[A, B, C, D, E, F, G, H, I, J, K, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(Even         , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(Odd          , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(Remainder    , Seq(divider, remainder))
}
trait ExprOneMan_11_Decimal[A, B, C, D, E, F, G, H, I, J, K, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_11_Number[A, B, C, D, E, F, G, H, I, J, K, t, Entity1, Entity2] {
  def ceil                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(AttrOp.Ceil  , Nil                    )
  def floor                      : Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(AttrOp.Floor , Nil                    )
}
trait ExprOneMan_11_Number[A, B, C, D, E, F, G, H, I, J, K, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1, Entity2] {
  def +(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(AttrOp.Plus  , Seq(v)                 )
  def -(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(AttrOp.Minus , Seq(v)                 )
  def *(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(AttrOp.Times , Seq(v)                 )
  def /(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(AttrOp.Divide, Seq(v)                 )
  def negate                     : Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(AttrOp.Negate, Nil                    )
  def abs                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(AttrOp.Abs   , Nil                    )
  def absNeg                     : Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(AttrOp.AbsNeg, Nil                    )
}
trait ExprOneMan_11_Boolean[A, B, C, D, E, F, G, H, I, J, K, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1, Entity2] {
  def &&(bool: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(AttrOp.And, Seq(bool))
  def ||(bool: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(AttrOp.Or , Seq(bool))
  def !          : Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1] & CardOne = _exprOneMan(AttrOp.Not, Nil      )
}


trait ExprOneManOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1, Entity2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = ???
}

trait ExprOneMan_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1, Entity2]
    with Aggregates_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1]
    with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[L, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, L, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, L, t, Entity2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[L, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, L, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, L, t, Entity2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[L, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, L, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, L, t, Entity2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[L, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, L, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, L, t, Entity2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[L, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, L, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, L, t, Entity2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[L, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, L, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, L, t, Entity2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_12_String[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1, Entity2] {
  def startsWith(prefix: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(StartsWith                  , Seq(prefix)            )
  def endsWith  (suffix: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(EndsWith                    , Seq(suffix)            )
  def contains  (needle: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(Contains                    , Seq(needle)            )
  def matches   (regex : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(Matches                     , Seq(regex)             )
  def +         (str   : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(AttrOp.Append               , Seq(str)               )
  def prepend   (str   : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(AttrOp.Prepend              , Seq(str)               )
  def substring (start: Int, end: Int)    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(AttrOp.SubString(start, end), Nil                    )
  def replaceAll(regex: t, replacement: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(AttrOp.ReplaceAll           , Seq(regex, replacement))
  def toLower                             : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToLower              , Nil                    )
  def toUpper                             : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToUpper              , Nil                    )
}
trait ExprOneMan_12_Integer[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_12_Number[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(Even         , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(Odd          , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(Remainder    , Seq(divider, remainder))
}
trait ExprOneMan_12_Decimal[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_12_Number[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1, Entity2] {
  def ceil                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(AttrOp.Ceil  , Nil                    )
  def floor                      : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(AttrOp.Floor , Nil                    )
}
trait ExprOneMan_12_Number[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1, Entity2] {
  def +(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(AttrOp.Plus  , Seq(v)                 )
  def -(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(AttrOp.Minus , Seq(v)                 )
  def *(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(AttrOp.Times , Seq(v)                 )
  def /(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(AttrOp.Divide, Seq(v)                 )
  def negate                     : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(AttrOp.Negate, Nil                    )
  def abs                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(AttrOp.Abs   , Nil                    )
  def absNeg                     : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(AttrOp.AbsNeg, Nil                    )
}
trait ExprOneMan_12_Boolean[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1, Entity2] {
  def &&(bool: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(AttrOp.And, Seq(bool))
  def ||(bool: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(AttrOp.Or , Seq(bool))
  def !          : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1] & CardOne = _exprOneMan(AttrOp.Not, Nil      )
}


trait ExprOneManOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1, Entity2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = ???
}

trait ExprOneMan_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1, Entity2]
    with Aggregates_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1]
    with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[M, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, M, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, M, t, Entity2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[M, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, M, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, M, t, Entity2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[M, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, M, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, M, t, Entity2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[M, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, M, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, M, t, Entity2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[M, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, M, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, M, t, Entity2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[M, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, M, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, M, t, Entity2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_13_String[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1, Entity2] {
  def startsWith(prefix: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(StartsWith                  , Seq(prefix)            )
  def endsWith  (suffix: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(EndsWith                    , Seq(suffix)            )
  def contains  (needle: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(Contains                    , Seq(needle)            )
  def matches   (regex : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(Matches                     , Seq(regex)             )
  def +         (str   : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(AttrOp.Append               , Seq(str)               )
  def prepend   (str   : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(AttrOp.Prepend              , Seq(str)               )
  def substring (start: Int, end: Int)    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(AttrOp.SubString(start, end), Nil                    )
  def replaceAll(regex: t, replacement: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(AttrOp.ReplaceAll           , Seq(regex, replacement))
  def toLower                             : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToLower              , Nil                    )
  def toUpper                             : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToUpper              , Nil                    )
}
trait ExprOneMan_13_Integer[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_13_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(Even         , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(Odd          , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(Remainder    , Seq(divider, remainder))
}
trait ExprOneMan_13_Decimal[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_13_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1, Entity2] {
  def ceil                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(AttrOp.Ceil  , Nil                    )
  def floor                      : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(AttrOp.Floor , Nil                    )
}
trait ExprOneMan_13_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1, Entity2] {
  def +(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(AttrOp.Plus  , Seq(v)                 )
  def -(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(AttrOp.Minus , Seq(v)                 )
  def *(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(AttrOp.Times , Seq(v)                 )
  def /(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(AttrOp.Divide, Seq(v)                 )
  def negate                     : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(AttrOp.Negate, Nil                    )
  def abs                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(AttrOp.Abs   , Nil                    )
  def absNeg                     : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(AttrOp.AbsNeg, Nil                    )
}
trait ExprOneMan_13_Boolean[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1, Entity2] {
  def &&(bool: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(AttrOp.And, Seq(bool))
  def ||(bool: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(AttrOp.Or , Seq(bool))
  def !          : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1] & CardOne = _exprOneMan(AttrOp.Not, Nil      )
}


trait ExprOneManOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1, Entity2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = ???
}

trait ExprOneMan_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1, Entity2]
    with Aggregates_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1]
    with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[N, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, N, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, N, t, Entity2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[N, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, N, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, N, t, Entity2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[N, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, N, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, N, t, Entity2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[N, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, N, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, N, t, Entity2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[N, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, N, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, N, t, Entity2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[N, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, N, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, N, t, Entity2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_14_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1, Entity2] {
  def startsWith(prefix: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(StartsWith                  , Seq(prefix)            )
  def endsWith  (suffix: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(EndsWith                    , Seq(suffix)            )
  def contains  (needle: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(Contains                    , Seq(needle)            )
  def matches   (regex : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(Matches                     , Seq(regex)             )
  def +         (str   : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(AttrOp.Append               , Seq(str)               )
  def prepend   (str   : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(AttrOp.Prepend              , Seq(str)               )
  def substring (start: Int, end: Int)    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(AttrOp.SubString(start, end), Nil                    )
  def replaceAll(regex: t, replacement: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(AttrOp.ReplaceAll           , Seq(regex, replacement))
  def toLower                             : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToLower              , Nil                    )
  def toUpper                             : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToUpper              , Nil                    )
}
trait ExprOneMan_14_Integer[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_14_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(Even         , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(Odd          , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(Remainder    , Seq(divider, remainder))
}
trait ExprOneMan_14_Decimal[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_14_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1, Entity2] {
  def ceil                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(AttrOp.Ceil  , Nil                    )
  def floor                      : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(AttrOp.Floor , Nil                    )
}
trait ExprOneMan_14_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1, Entity2] {
  def +(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(AttrOp.Plus  , Seq(v)                 )
  def -(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(AttrOp.Minus , Seq(v)                 )
  def *(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(AttrOp.Times , Seq(v)                 )
  def /(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(AttrOp.Divide, Seq(v)                 )
  def negate                     : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(AttrOp.Negate, Nil                    )
  def abs                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(AttrOp.Abs   , Nil                    )
  def absNeg                     : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(AttrOp.AbsNeg, Nil                    )
}
trait ExprOneMan_14_Boolean[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1, Entity2] {
  def &&(bool: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(AttrOp.And, Seq(bool))
  def ||(bool: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(AttrOp.Or , Seq(bool))
  def !          : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1] & CardOne = _exprOneMan(AttrOp.Not, Nil      )
}


trait ExprOneManOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1, Entity2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = ???
}

trait ExprOneMan_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1, Entity2]
    with Aggregates_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1]
    with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[O, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, O, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, O, t, Entity2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[O, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, O, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, O, t, Entity2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[O, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, O, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, O, t, Entity2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[O, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, O, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, O, t, Entity2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[O, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, O, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, O, t, Entity2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[O, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, O, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, O, t, Entity2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_15_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1, Entity2] {
  def startsWith(prefix: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(StartsWith                  , Seq(prefix)            )
  def endsWith  (suffix: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(EndsWith                    , Seq(suffix)            )
  def contains  (needle: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(Contains                    , Seq(needle)            )
  def matches   (regex : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(Matches                     , Seq(regex)             )
  def +         (str   : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(AttrOp.Append               , Seq(str)               )
  def prepend   (str   : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(AttrOp.Prepend              , Seq(str)               )
  def substring (start: Int, end: Int)    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(AttrOp.SubString(start, end), Nil                    )
  def replaceAll(regex: t, replacement: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(AttrOp.ReplaceAll           , Seq(regex, replacement))
  def toLower                             : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToLower              , Nil                    )
  def toUpper                             : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToUpper              , Nil                    )
}
trait ExprOneMan_15_Integer[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_15_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(Even         , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(Odd          , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(Remainder    , Seq(divider, remainder))
}
trait ExprOneMan_15_Decimal[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_15_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1, Entity2] {
  def ceil                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(AttrOp.Ceil  , Nil                    )
  def floor                      : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(AttrOp.Floor , Nil                    )
}
trait ExprOneMan_15_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1, Entity2] {
  def +(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(AttrOp.Plus  , Seq(v)                 )
  def -(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(AttrOp.Minus , Seq(v)                 )
  def *(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(AttrOp.Times , Seq(v)                 )
  def /(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(AttrOp.Divide, Seq(v)                 )
  def negate                     : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(AttrOp.Negate, Nil                    )
  def abs                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(AttrOp.Abs   , Nil                    )
  def absNeg                     : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(AttrOp.AbsNeg, Nil                    )
}
trait ExprOneMan_15_Boolean[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1, Entity2] {
  def &&(bool: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(AttrOp.And, Seq(bool))
  def ||(bool: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(AttrOp.Or , Seq(bool))
  def !          : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1] & CardOne = _exprOneMan(AttrOp.Not, Nil      )
}


trait ExprOneManOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1, Entity2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = ???
}

trait ExprOneMan_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1, Entity2]
    with Aggregates_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1]
    with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[P, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, P, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, P, t, Entity2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[P, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, P, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, P, t, Entity2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[P, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, P, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, P, t, Entity2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[P, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, P, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, P, t, Entity2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[P, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, P, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, P, t, Entity2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[P, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, P, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, P, t, Entity2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_16_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1, Entity2] {
  def startsWith(prefix: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(StartsWith                  , Seq(prefix)            )
  def endsWith  (suffix: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(EndsWith                    , Seq(suffix)            )
  def contains  (needle: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(Contains                    , Seq(needle)            )
  def matches   (regex : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(Matches                     , Seq(regex)             )
  def +         (str   : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(AttrOp.Append               , Seq(str)               )
  def prepend   (str   : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(AttrOp.Prepend              , Seq(str)               )
  def substring (start: Int, end: Int)    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(AttrOp.SubString(start, end), Nil                    )
  def replaceAll(regex: t, replacement: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(AttrOp.ReplaceAll           , Seq(regex, replacement))
  def toLower                             : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToLower              , Nil                    )
  def toUpper                             : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToUpper              , Nil                    )
}
trait ExprOneMan_16_Integer[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_16_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(Even         , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(Odd          , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(Remainder    , Seq(divider, remainder))
}
trait ExprOneMan_16_Decimal[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_16_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1, Entity2] {
  def ceil                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(AttrOp.Ceil  , Nil                    )
  def floor                      : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(AttrOp.Floor , Nil                    )
}
trait ExprOneMan_16_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1, Entity2] {
  def +(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(AttrOp.Plus  , Seq(v)                 )
  def -(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(AttrOp.Minus , Seq(v)                 )
  def *(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(AttrOp.Times , Seq(v)                 )
  def /(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(AttrOp.Divide, Seq(v)                 )
  def negate                     : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(AttrOp.Negate, Nil                    )
  def abs                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(AttrOp.Abs   , Nil                    )
  def absNeg                     : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(AttrOp.AbsNeg, Nil                    )
}
trait ExprOneMan_16_Boolean[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1, Entity2] {
  def &&(bool: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(AttrOp.And, Seq(bool))
  def ||(bool: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(AttrOp.Or , Seq(bool))
  def !          : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1] & CardOne = _exprOneMan(AttrOp.Not, Nil      )
}


trait ExprOneManOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1, Entity2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = ???
}

trait ExprOneMan_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1, Entity2]
    with Aggregates_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1]
    with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Q, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Q, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Q, t, Entity2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Q, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Q, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Q, t, Entity2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Q, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Q, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Q, t, Entity2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Q, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Q, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Q, t, Entity2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Q, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Q, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Q, t, Entity2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Q, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Q, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Q, t, Entity2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_17_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1, Entity2] {
  def startsWith(prefix: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(StartsWith                  , Seq(prefix)            )
  def endsWith  (suffix: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(EndsWith                    , Seq(suffix)            )
  def contains  (needle: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(Contains                    , Seq(needle)            )
  def matches   (regex : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(Matches                     , Seq(regex)             )
  def +         (str   : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(AttrOp.Append               , Seq(str)               )
  def prepend   (str   : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(AttrOp.Prepend              , Seq(str)               )
  def substring (start: Int, end: Int)    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(AttrOp.SubString(start, end), Nil                    )
  def replaceAll(regex: t, replacement: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(AttrOp.ReplaceAll           , Seq(regex, replacement))
  def toLower                             : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToLower              , Nil                    )
  def toUpper                             : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToUpper              , Nil                    )
}
trait ExprOneMan_17_Integer[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_17_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(Even         , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(Odd          , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(Remainder    , Seq(divider, remainder))
}
trait ExprOneMan_17_Decimal[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_17_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1, Entity2] {
  def ceil                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(AttrOp.Ceil  , Nil                    )
  def floor                      : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(AttrOp.Floor , Nil                    )
}
trait ExprOneMan_17_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1, Entity2] {
  def +(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(AttrOp.Plus  , Seq(v)                 )
  def -(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(AttrOp.Minus , Seq(v)                 )
  def *(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(AttrOp.Times , Seq(v)                 )
  def /(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(AttrOp.Divide, Seq(v)                 )
  def negate                     : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(AttrOp.Negate, Nil                    )
  def abs                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(AttrOp.Abs   , Nil                    )
  def absNeg                     : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(AttrOp.AbsNeg, Nil                    )
}
trait ExprOneMan_17_Boolean[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1, Entity2] {
  def &&(bool: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(AttrOp.And, Seq(bool))
  def ||(bool: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(AttrOp.Or , Seq(bool))
  def !          : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1] & CardOne = _exprOneMan(AttrOp.Not, Nil      )
}


trait ExprOneManOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1, Entity2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = ???
}

trait ExprOneMan_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1, Entity2]
    with Aggregates_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1]
    with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[R, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, R, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, R, t, Entity2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[R, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, R, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, R, t, Entity2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[R, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, R, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, R, t, Entity2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[R, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, R, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, R, t, Entity2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[R, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, R, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, R, t, Entity2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[R, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, R, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, R, t, Entity2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_18_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1, Entity2] {
  def startsWith(prefix: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(StartsWith                  , Seq(prefix)            )
  def endsWith  (suffix: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(EndsWith                    , Seq(suffix)            )
  def contains  (needle: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(Contains                    , Seq(needle)            )
  def matches   (regex : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(Matches                     , Seq(regex)             )
  def +         (str   : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(AttrOp.Append               , Seq(str)               )
  def prepend   (str   : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(AttrOp.Prepend              , Seq(str)               )
  def substring (start: Int, end: Int)    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(AttrOp.SubString(start, end), Nil                    )
  def replaceAll(regex: t, replacement: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(AttrOp.ReplaceAll           , Seq(regex, replacement))
  def toLower                             : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToLower              , Nil                    )
  def toUpper                             : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToUpper              , Nil                    )
}
trait ExprOneMan_18_Integer[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_18_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(Even         , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(Odd          , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(Remainder    , Seq(divider, remainder))
}
trait ExprOneMan_18_Decimal[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_18_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1, Entity2] {
  def ceil                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(AttrOp.Ceil  , Nil                    )
  def floor                      : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(AttrOp.Floor , Nil                    )
}
trait ExprOneMan_18_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1, Entity2] {
  def +(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(AttrOp.Plus  , Seq(v)                 )
  def -(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(AttrOp.Minus , Seq(v)                 )
  def *(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(AttrOp.Times , Seq(v)                 )
  def /(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(AttrOp.Divide, Seq(v)                 )
  def negate                     : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(AttrOp.Negate, Nil                    )
  def abs                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(AttrOp.Abs   , Nil                    )
  def absNeg                     : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(AttrOp.AbsNeg, Nil                    )
}
trait ExprOneMan_18_Boolean[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1, Entity2] {
  def &&(bool: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(AttrOp.And, Seq(bool))
  def ||(bool: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(AttrOp.Or , Seq(bool))
  def !          : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1] & CardOne = _exprOneMan(AttrOp.Not, Nil      )
}


trait ExprOneManOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1, Entity2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = ???
}

trait ExprOneMan_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1, Entity2]
    with Aggregates_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1]
    with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[S, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, S, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, S, t, Entity2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[S, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, S, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, S, t, Entity2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[S, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, S, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, S, t, Entity2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[S, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, S, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, S, t, Entity2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[S, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, S, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, S, t, Entity2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[S, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, S, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, S, t, Entity2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_19_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1, Entity2] {
  def startsWith(prefix: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(StartsWith                  , Seq(prefix)            )
  def endsWith  (suffix: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(EndsWith                    , Seq(suffix)            )
  def contains  (needle: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(Contains                    , Seq(needle)            )
  def matches   (regex : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(Matches                     , Seq(regex)             )
  def +         (str   : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(AttrOp.Append               , Seq(str)               )
  def prepend   (str   : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(AttrOp.Prepend              , Seq(str)               )
  def substring (start: Int, end: Int)    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(AttrOp.SubString(start, end), Nil                    )
  def replaceAll(regex: t, replacement: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(AttrOp.ReplaceAll           , Seq(regex, replacement))
  def toLower                             : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToLower              , Nil                    )
  def toUpper                             : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToUpper              , Nil                    )
}
trait ExprOneMan_19_Integer[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_19_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(Even         , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(Odd          , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(Remainder    , Seq(divider, remainder))
}
trait ExprOneMan_19_Decimal[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_19_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1, Entity2] {
  def ceil                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(AttrOp.Ceil  , Nil                    )
  def floor                      : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(AttrOp.Floor , Nil                    )
}
trait ExprOneMan_19_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1, Entity2] {
  def +(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(AttrOp.Plus  , Seq(v)                 )
  def -(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(AttrOp.Minus , Seq(v)                 )
  def *(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(AttrOp.Times , Seq(v)                 )
  def /(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(AttrOp.Divide, Seq(v)                 )
  def negate                     : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(AttrOp.Negate, Nil                    )
  def abs                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(AttrOp.Abs   , Nil                    )
  def absNeg                     : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(AttrOp.AbsNeg, Nil                    )
}
trait ExprOneMan_19_Boolean[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1, Entity2] {
  def &&(bool: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(AttrOp.And, Seq(bool))
  def ||(bool: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(AttrOp.Or , Seq(bool))
  def !          : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1] & CardOne = _exprOneMan(AttrOp.Not, Nil      )
}


trait ExprOneManOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1, Entity2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = ???
}

trait ExprOneMan_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1, Entity2]
    with Aggregates_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1]
    with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[T, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, T, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, T, t, Entity2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[T, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, T, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, T, t, Entity2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[T, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, T, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, T, t, Entity2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[T, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, T, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, T, t, Entity2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[T, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, T, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, T, t, Entity2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[T, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, T, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, T, t, Entity2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_20_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1, Entity2] {
  def startsWith(prefix: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(StartsWith                  , Seq(prefix)            )
  def endsWith  (suffix: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(EndsWith                    , Seq(suffix)            )
  def contains  (needle: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(Contains                    , Seq(needle)            )
  def matches   (regex : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(Matches                     , Seq(regex)             )
  def +         (str   : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(AttrOp.Append               , Seq(str)               )
  def prepend   (str   : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(AttrOp.Prepend              , Seq(str)               )
  def substring (start: Int, end: Int)    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(AttrOp.SubString(start, end), Nil                    )
  def replaceAll(regex: t, replacement: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(AttrOp.ReplaceAll           , Seq(regex, replacement))
  def toLower                             : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToLower              , Nil                    )
  def toUpper                             : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToUpper              , Nil                    )
}
trait ExprOneMan_20_Integer[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_20_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(Even         , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(Odd          , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(Remainder    , Seq(divider, remainder))
}
trait ExprOneMan_20_Decimal[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_20_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1, Entity2] {
  def ceil                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(AttrOp.Ceil  , Nil                    )
  def floor                      : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(AttrOp.Floor , Nil                    )
}
trait ExprOneMan_20_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1, Entity2] {
  def +(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(AttrOp.Plus  , Seq(v)                 )
  def -(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(AttrOp.Minus , Seq(v)                 )
  def *(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(AttrOp.Times , Seq(v)                 )
  def /(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(AttrOp.Divide, Seq(v)                 )
  def negate                     : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(AttrOp.Negate, Nil                    )
  def abs                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(AttrOp.Abs   , Nil                    )
  def absNeg                     : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(AttrOp.AbsNeg, Nil                    )
}
trait ExprOneMan_20_Boolean[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1, Entity2] {
  def &&(bool: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(AttrOp.And, Seq(bool))
  def ||(bool: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(AttrOp.Or , Seq(bool))
  def !          : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1] & CardOne = _exprOneMan(AttrOp.Not, Nil      )
}


trait ExprOneManOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1, Entity2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = ???
}

trait ExprOneMan_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1, Entity2]
    with Aggregates_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1]
    with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[U, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, U, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, U, t, Entity2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[U, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, U, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, U, t, Entity2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[U, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, U, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, U, t, Entity2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[U, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, U, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, U, t, Entity2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[U, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, U, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, U, t, Entity2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[U, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, U, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, U, t, Entity2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_21_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1, Entity2] {
  def startsWith(prefix: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(StartsWith                  , Seq(prefix)            )
  def endsWith  (suffix: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(EndsWith                    , Seq(suffix)            )
  def contains  (needle: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(Contains                    , Seq(needle)            )
  def matches   (regex : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(Matches                     , Seq(regex)             )
  def +         (str   : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(AttrOp.Append               , Seq(str)               )
  def prepend   (str   : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(AttrOp.Prepend              , Seq(str)               )
  def substring (start: Int, end: Int)    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(AttrOp.SubString(start, end), Nil                    )
  def replaceAll(regex: t, replacement: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(AttrOp.ReplaceAll           , Seq(regex, replacement))
  def toLower                             : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToLower              , Nil                    )
  def toUpper                             : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToUpper              , Nil                    )
}
trait ExprOneMan_21_Integer[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_21_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(Even         , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(Odd          , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(Remainder    , Seq(divider, remainder))
}
trait ExprOneMan_21_Decimal[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_21_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1, Entity2] {
  def ceil                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(AttrOp.Ceil  , Nil                    )
  def floor                      : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(AttrOp.Floor , Nil                    )
}
trait ExprOneMan_21_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1, Entity2] {
  def +(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(AttrOp.Plus  , Seq(v)                 )
  def -(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(AttrOp.Minus , Seq(v)                 )
  def *(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(AttrOp.Times , Seq(v)                 )
  def /(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(AttrOp.Divide, Seq(v)                 )
  def negate                     : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(AttrOp.Negate, Nil                    )
  def abs                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(AttrOp.Abs   , Nil                    )
  def absNeg                     : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(AttrOp.AbsNeg, Nil                    )
}
trait ExprOneMan_21_Boolean[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1, Entity2] {
  def &&(bool: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(AttrOp.And, Seq(bool))
  def ||(bool: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(AttrOp.Or , Seq(bool))
  def !          : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1] & CardOne = _exprOneMan(AttrOp.Not, Nil      )
}


trait ExprOneManOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprAttr_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1, Entity2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = ???
}

trait ExprOneMan_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1, Entity2]
    with Aggregates_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1]
    with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], tx[_]](a: ModelOps_0[t, ns1, X2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] = _attrSortTac(Eq , a)
  def not  [ns1[_], tx[_]](a: ModelOps_0[t, ns1, X2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] = _attrSortTac(Neq, a)
  def <    [ns1[_], tx[_]](a: ModelOps_0[t, ns1, X2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], tx[_]](a: ModelOps_0[t, ns1, X2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] = _attrSortTac(Le , a)
  def >    [ns1[_], tx[_]](a: ModelOps_0[t, ns1, X2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], tx[_]](a: ModelOps_0[t, ns1, X2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] = _attrSortTac(Ge , a)
}
trait ExprOneMan_22_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1, Entity2] {
  def startsWith(prefix: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(StartsWith                  , Seq(prefix)            )
  def endsWith  (suffix: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(EndsWith                    , Seq(suffix)            )
  def contains  (needle: t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(Contains                    , Seq(needle)            )
  def matches   (regex : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(Matches                     , Seq(regex)             )
  def +         (str   : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(AttrOp.Append               , Seq(str)               )
  def prepend   (str   : t)               : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(AttrOp.Prepend              , Seq(str)               )
  def substring (start: Int, end: Int)    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(AttrOp.SubString(start, end), Nil                    )
  def replaceAll(regex: t, replacement: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(AttrOp.ReplaceAll           , Seq(regex, replacement))
  def toLower                             : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToLower              , Nil                    )
  def toUpper                             : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(AttrOp.ToUpper              , Nil                    )
}
trait ExprOneMan_22_Integer[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_22_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(Even         , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(Odd          , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(Remainder    , Seq(divider, remainder))
}
trait ExprOneMan_22_Decimal[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_22_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1, Entity2] {
  def ceil                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(AttrOp.Ceil  , Nil                    )
  def floor                      : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(AttrOp.Floor , Nil                    )
}
trait ExprOneMan_22_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1, Entity2] {
  def +(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(AttrOp.Plus  , Seq(v)                 )
  def -(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(AttrOp.Minus , Seq(v)                 )
  def *(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(AttrOp.Times , Seq(v)                 )
  def /(v: t)                    : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(AttrOp.Divide, Seq(v)                 )
  def negate                     : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(AttrOp.Negate, Nil                    )
  def abs                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(AttrOp.Abs   , Nil                    )
  def absNeg                     : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(AttrOp.AbsNeg, Nil                    )
}
trait ExprOneMan_22_Boolean[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1, Entity2] {
  def &&(bool: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(AttrOp.And, Seq(bool))
  def ||(bool: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(AttrOp.Or , Seq(bool))
  def !          : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1] & CardOne = _exprOneMan(AttrOp.Not, Nil      )
}
