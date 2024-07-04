// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.base.ast._
import molecule.boilerplate.api._
import molecule.boilerplate.ast.Model._
import scala.language.higherKinds


trait ExprOneManOps_1[A, t, Ns1[_, _], Ns2[_, _, _]] extends ExprAttr_1[A, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, t] with SortAttrs_1[A, t, Ns1] with CardOne = ???
}

trait ExprOneMan_1[A, t, Ns1[_, _], Ns2[_, _, _]]
  extends ExprOneManOps_1[A, t, Ns1, Ns2]
    with Aggregates_1[A, t, Ns1]
    with SortAttrs_1[A, t, Ns1] {
  def apply(                ): Ns1[A, t] with SortAttrs_1[A, t, Ns1] with CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, t] with SortAttrs_1[A, t, Ns1] with CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, t] with SortAttrs_1[A, t, Ns1] with CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, t] with SortAttrs_1[A, t, Ns1] with CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, t] with SortAttrs_1[A, t, Ns1] with CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, t] with SortAttrs_1[A, t, Ns1] with CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, t] with SortAttrs_1[A, t, Ns1] with CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, t] with SortAttrs_1[A, t, Ns1] with CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, t] with SortAttrs_1[A, t, Ns1] with CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, t] with SortAttrs_1[A, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, t] with SortAttrs_1[A, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, t] with SortAttrs_1[A, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, t] with SortAttrs_1[A, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, t] with SortAttrs_1[A, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, t] with SortAttrs_1[A, t, Ns1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[A, t, ns1, ns2]): Ns2[A, A, t] with SortAttrs_2[A, A, t, Ns2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[A, t, ns1, ns2]): Ns2[A, A, t] with SortAttrs_2[A, A, t, Ns2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[A, t, ns1, ns2]): Ns2[A, A, t] with SortAttrs_2[A, A, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[A, t, ns1, ns2]): Ns2[A, A, t] with SortAttrs_2[A, A, t, Ns2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[A, t, ns1, ns2]): Ns2[A, A, t] with SortAttrs_2[A, A, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[A, t, ns1, ns2]): Ns2[A, A, t] with SortAttrs_2[A, A, t, Ns2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_1_String[A, t, Ns1[_, _], Ns2[_, _, _]] extends ExprOneMan_1[A, t, Ns1, Ns2] {
  def startsWith(prefix: t              ): Ns1[A, t] with SortAttrs_1[A, t, Ns1] with CardOne = _exprOneMan(StartsWith, Seq(prefix))
  def endsWith  (suffix: t              ): Ns1[A, t] with SortAttrs_1[A, t, Ns1] with CardOne = _exprOneMan(EndsWith  , Seq(suffix))
  def contains  (needle: t              ): Ns1[A, t] with SortAttrs_1[A, t, Ns1] with CardOne = _exprOneMan(Contains  , Seq(needle))
  def matches   (regex : t              ): Ns1[A, t] with SortAttrs_1[A, t, Ns1] with CardOne = _exprOneMan(Matches   , Seq(regex) )
}
trait ExprOneMan_1_Number[A, t, Ns1[_, _], Ns2[_, _, _]] extends ExprOneMan_1[A, t, Ns1, Ns2] {
  def %(divider: t, remainder: t        ): Ns1[A, t] with SortAttrs_1[A, t, Ns1] with CardOne = _exprOneMan(Remainder , Seq(divider, remainder))
  def even                               : Ns1[A, t] with SortAttrs_1[A, t, Ns1] with CardOne = _exprOneMan(Even      , Nil                    )
  def odd                                : Ns1[A, t] with SortAttrs_1[A, t, Ns1] with CardOne = _exprOneMan(Odd       , Nil                    )
}


trait ExprOneManOps_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]] extends ExprAttr_2[A, B, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] with CardOne = ???
}

trait ExprOneMan_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]]
  extends ExprOneManOps_2[A, B, t, Ns1, Ns2]
    with Aggregates_2[A, B, t, Ns1]
    with SortAttrs_2[A, B, t, Ns1] {
  def apply(                ): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] with CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] with CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] with CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] with CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] with CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] with CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] with CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] with CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] with CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[B, t, ns1, ns2]): Ns2[A, B, B, t] with SortAttrs_3[A, B, B, t, Ns2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[B, t, ns1, ns2]): Ns2[A, B, B, t] with SortAttrs_3[A, B, B, t, Ns2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[B, t, ns1, ns2]): Ns2[A, B, B, t] with SortAttrs_3[A, B, B, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[B, t, ns1, ns2]): Ns2[A, B, B, t] with SortAttrs_3[A, B, B, t, Ns2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[B, t, ns1, ns2]): Ns2[A, B, B, t] with SortAttrs_3[A, B, B, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[B, t, ns1, ns2]): Ns2[A, B, B, t] with SortAttrs_3[A, B, B, t, Ns2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_2_String[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]] extends ExprOneMan_2[A, B, t, Ns1, Ns2] {
  def startsWith(prefix: t              ): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] with CardOne = _exprOneMan(StartsWith, Seq(prefix))
  def endsWith  (suffix: t              ): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] with CardOne = _exprOneMan(EndsWith  , Seq(suffix))
  def contains  (needle: t              ): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] with CardOne = _exprOneMan(Contains  , Seq(needle))
  def matches   (regex : t              ): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] with CardOne = _exprOneMan(Matches   , Seq(regex) )
}
trait ExprOneMan_2_Number[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]] extends ExprOneMan_2[A, B, t, Ns1, Ns2] {
  def %(divider: t, remainder: t        ): Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] with CardOne = _exprOneMan(Remainder , Seq(divider, remainder))
  def even                               : Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] with CardOne = _exprOneMan(Even      , Nil                    )
  def odd                                : Ns1[A, B, t] with SortAttrs_2[A, B, t, Ns1] with CardOne = _exprOneMan(Odd       , Nil                    )
}


trait ExprOneManOps_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]] extends ExprAttr_3[A, B, C, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] with CardOne = ???
}

trait ExprOneMan_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]]
  extends ExprOneManOps_3[A, B, C, t, Ns1, Ns2]
    with Aggregates_3[A, B, C, t, Ns1]
    with SortAttrs_3[A, B, C, t, Ns1] {
  def apply(                ): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] with CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] with CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] with CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] with CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] with CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] with CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] with CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] with CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] with CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[C, t, ns1, ns2]): Ns2[A, B, C, C, t] with SortAttrs_4[A, B, C, C, t, Ns2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[C, t, ns1, ns2]): Ns2[A, B, C, C, t] with SortAttrs_4[A, B, C, C, t, Ns2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[C, t, ns1, ns2]): Ns2[A, B, C, C, t] with SortAttrs_4[A, B, C, C, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[C, t, ns1, ns2]): Ns2[A, B, C, C, t] with SortAttrs_4[A, B, C, C, t, Ns2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[C, t, ns1, ns2]): Ns2[A, B, C, C, t] with SortAttrs_4[A, B, C, C, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[C, t, ns1, ns2]): Ns2[A, B, C, C, t] with SortAttrs_4[A, B, C, C, t, Ns2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_3_String[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]] extends ExprOneMan_3[A, B, C, t, Ns1, Ns2] {
  def startsWith(prefix: t              ): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] with CardOne = _exprOneMan(StartsWith, Seq(prefix))
  def endsWith  (suffix: t              ): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] with CardOne = _exprOneMan(EndsWith  , Seq(suffix))
  def contains  (needle: t              ): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] with CardOne = _exprOneMan(Contains  , Seq(needle))
  def matches   (regex : t              ): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] with CardOne = _exprOneMan(Matches   , Seq(regex) )
}
trait ExprOneMan_3_Number[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]] extends ExprOneMan_3[A, B, C, t, Ns1, Ns2] {
  def %(divider: t, remainder: t        ): Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] with CardOne = _exprOneMan(Remainder , Seq(divider, remainder))
  def even                               : Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] with CardOne = _exprOneMan(Even      , Nil                    )
  def odd                                : Ns1[A, B, C, t] with SortAttrs_3[A, B, C, t, Ns1] with CardOne = _exprOneMan(Odd       , Nil                    )
}


trait ExprOneManOps_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]] extends ExprAttr_4[A, B, C, D, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] with CardOne = ???
}

trait ExprOneMan_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]]
  extends ExprOneManOps_4[A, B, C, D, t, Ns1, Ns2]
    with Aggregates_4[A, B, C, D, t, Ns1]
    with SortAttrs_4[A, B, C, D, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] with CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] with CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] with CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] with CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] with CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] with CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] with CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] with CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] with CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[D, t, ns1, ns2]): Ns2[A, B, C, D, D, t] with SortAttrs_5[A, B, C, D, D, t, Ns2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[D, t, ns1, ns2]): Ns2[A, B, C, D, D, t] with SortAttrs_5[A, B, C, D, D, t, Ns2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[D, t, ns1, ns2]): Ns2[A, B, C, D, D, t] with SortAttrs_5[A, B, C, D, D, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[D, t, ns1, ns2]): Ns2[A, B, C, D, D, t] with SortAttrs_5[A, B, C, D, D, t, Ns2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[D, t, ns1, ns2]): Ns2[A, B, C, D, D, t] with SortAttrs_5[A, B, C, D, D, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[D, t, ns1, ns2]): Ns2[A, B, C, D, D, t] with SortAttrs_5[A, B, C, D, D, t, Ns2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_4_String[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]] extends ExprOneMan_4[A, B, C, D, t, Ns1, Ns2] {
  def startsWith(prefix: t              ): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] with CardOne = _exprOneMan(StartsWith, Seq(prefix))
  def endsWith  (suffix: t              ): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] with CardOne = _exprOneMan(EndsWith  , Seq(suffix))
  def contains  (needle: t              ): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] with CardOne = _exprOneMan(Contains  , Seq(needle))
  def matches   (regex : t              ): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] with CardOne = _exprOneMan(Matches   , Seq(regex) )
}
trait ExprOneMan_4_Number[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]] extends ExprOneMan_4[A, B, C, D, t, Ns1, Ns2] {
  def %(divider: t, remainder: t        ): Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] with CardOne = _exprOneMan(Remainder , Seq(divider, remainder))
  def even                               : Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] with CardOne = _exprOneMan(Even      , Nil                    )
  def odd                                : Ns1[A, B, C, D, t] with SortAttrs_4[A, B, C, D, t, Ns1] with CardOne = _exprOneMan(Odd       , Nil                    )
}


trait ExprOneManOps_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]] extends ExprAttr_5[A, B, C, D, E, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] with CardOne = ???
}

trait ExprOneMan_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]]
  extends ExprOneManOps_5[A, B, C, D, E, t, Ns1, Ns2]
    with Aggregates_5[A, B, C, D, E, t, Ns1]
    with SortAttrs_5[A, B, C, D, E, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] with CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] with CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] with CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] with CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] with CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] with CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] with CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] with CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] with CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[E, t, ns1, ns2]): Ns2[A, B, C, D, E, E, t] with SortAttrs_6[A, B, C, D, E, E, t, Ns2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[E, t, ns1, ns2]): Ns2[A, B, C, D, E, E, t] with SortAttrs_6[A, B, C, D, E, E, t, Ns2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[E, t, ns1, ns2]): Ns2[A, B, C, D, E, E, t] with SortAttrs_6[A, B, C, D, E, E, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[E, t, ns1, ns2]): Ns2[A, B, C, D, E, E, t] with SortAttrs_6[A, B, C, D, E, E, t, Ns2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[E, t, ns1, ns2]): Ns2[A, B, C, D, E, E, t] with SortAttrs_6[A, B, C, D, E, E, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[E, t, ns1, ns2]): Ns2[A, B, C, D, E, E, t] with SortAttrs_6[A, B, C, D, E, E, t, Ns2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_5_String[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]] extends ExprOneMan_5[A, B, C, D, E, t, Ns1, Ns2] {
  def startsWith(prefix: t              ): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] with CardOne = _exprOneMan(StartsWith, Seq(prefix))
  def endsWith  (suffix: t              ): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] with CardOne = _exprOneMan(EndsWith  , Seq(suffix))
  def contains  (needle: t              ): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] with CardOne = _exprOneMan(Contains  , Seq(needle))
  def matches   (regex : t              ): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] with CardOne = _exprOneMan(Matches   , Seq(regex) )
}
trait ExprOneMan_5_Number[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]] extends ExprOneMan_5[A, B, C, D, E, t, Ns1, Ns2] {
  def %(divider: t, remainder: t        ): Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] with CardOne = _exprOneMan(Remainder , Seq(divider, remainder))
  def even                               : Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] with CardOne = _exprOneMan(Even      , Nil                    )
  def odd                                : Ns1[A, B, C, D, E, t] with SortAttrs_5[A, B, C, D, E, t, Ns1] with CardOne = _exprOneMan(Odd       , Nil                    )
}


trait ExprOneManOps_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]] extends ExprAttr_6[A, B, C, D, E, F, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] with CardOne = ???
}

trait ExprOneMan_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]]
  extends ExprOneManOps_6[A, B, C, D, E, F, t, Ns1, Ns2]
    with Aggregates_6[A, B, C, D, E, F, t, Ns1]
    with SortAttrs_6[A, B, C, D, E, F, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] with CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] with CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] with CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] with CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] with CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] with CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] with CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] with CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] with CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[F, t, ns1, ns2]): Ns2[A, B, C, D, E, F, F, t] with SortAttrs_7[A, B, C, D, E, F, F, t, Ns2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[F, t, ns1, ns2]): Ns2[A, B, C, D, E, F, F, t] with SortAttrs_7[A, B, C, D, E, F, F, t, Ns2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[F, t, ns1, ns2]): Ns2[A, B, C, D, E, F, F, t] with SortAttrs_7[A, B, C, D, E, F, F, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[F, t, ns1, ns2]): Ns2[A, B, C, D, E, F, F, t] with SortAttrs_7[A, B, C, D, E, F, F, t, Ns2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[F, t, ns1, ns2]): Ns2[A, B, C, D, E, F, F, t] with SortAttrs_7[A, B, C, D, E, F, F, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[F, t, ns1, ns2]): Ns2[A, B, C, D, E, F, F, t] with SortAttrs_7[A, B, C, D, E, F, F, t, Ns2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_6_String[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]] extends ExprOneMan_6[A, B, C, D, E, F, t, Ns1, Ns2] {
  def startsWith(prefix: t              ): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] with CardOne = _exprOneMan(StartsWith, Seq(prefix))
  def endsWith  (suffix: t              ): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] with CardOne = _exprOneMan(EndsWith  , Seq(suffix))
  def contains  (needle: t              ): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] with CardOne = _exprOneMan(Contains  , Seq(needle))
  def matches   (regex : t              ): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] with CardOne = _exprOneMan(Matches   , Seq(regex) )
}
trait ExprOneMan_6_Number[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]] extends ExprOneMan_6[A, B, C, D, E, F, t, Ns1, Ns2] {
  def %(divider: t, remainder: t        ): Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] with CardOne = _exprOneMan(Remainder , Seq(divider, remainder))
  def even                               : Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] with CardOne = _exprOneMan(Even      , Nil                    )
  def odd                                : Ns1[A, B, C, D, E, F, t] with SortAttrs_6[A, B, C, D, E, F, t, Ns1] with CardOne = _exprOneMan(Odd       , Nil                    )
}


trait ExprOneManOps_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]] extends ExprAttr_7[A, B, C, D, E, F, G, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] with CardOne = ???
}

trait ExprOneMan_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_7[A, B, C, D, E, F, G, t, Ns1, Ns2]
    with Aggregates_7[A, B, C, D, E, F, G, t, Ns1]
    with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] with CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] with CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] with CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] with CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] with CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] with CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] with CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] with CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] with CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[G, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, G, t] with SortAttrs_8[A, B, C, D, E, F, G, G, t, Ns2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[G, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, G, t] with SortAttrs_8[A, B, C, D, E, F, G, G, t, Ns2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[G, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, G, t] with SortAttrs_8[A, B, C, D, E, F, G, G, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[G, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, G, t] with SortAttrs_8[A, B, C, D, E, F, G, G, t, Ns2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[G, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, G, t] with SortAttrs_8[A, B, C, D, E, F, G, G, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[G, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, G, t] with SortAttrs_8[A, B, C, D, E, F, G, G, t, Ns2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_7_String[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]] extends ExprOneMan_7[A, B, C, D, E, F, G, t, Ns1, Ns2] {
  def startsWith(prefix: t              ): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] with CardOne = _exprOneMan(StartsWith, Seq(prefix))
  def endsWith  (suffix: t              ): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] with CardOne = _exprOneMan(EndsWith  , Seq(suffix))
  def contains  (needle: t              ): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] with CardOne = _exprOneMan(Contains  , Seq(needle))
  def matches   (regex : t              ): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] with CardOne = _exprOneMan(Matches   , Seq(regex) )
}
trait ExprOneMan_7_Number[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]] extends ExprOneMan_7[A, B, C, D, E, F, G, t, Ns1, Ns2] {
  def %(divider: t, remainder: t        ): Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] with CardOne = _exprOneMan(Remainder , Seq(divider, remainder))
  def even                               : Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] with CardOne = _exprOneMan(Even      , Nil                    )
  def odd                                : Ns1[A, B, C, D, E, F, G, t] with SortAttrs_7[A, B, C, D, E, F, G, t, Ns1] with CardOne = _exprOneMan(Odd       , Nil                    )
}


trait ExprOneManOps_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]] extends ExprAttr_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] with CardOne = ???
}

trait ExprOneMan_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2]
    with Aggregates_8[A, B, C, D, E, F, G, H, t, Ns1]
    with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] with CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] with CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] with CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] with CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] with CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] with CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] with CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] with CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] with CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[H, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, H, t] with SortAttrs_9[A, B, C, D, E, F, G, H, H, t, Ns2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[H, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, H, t] with SortAttrs_9[A, B, C, D, E, F, G, H, H, t, Ns2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[H, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, H, t] with SortAttrs_9[A, B, C, D, E, F, G, H, H, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[H, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, H, t] with SortAttrs_9[A, B, C, D, E, F, G, H, H, t, Ns2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[H, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, H, t] with SortAttrs_9[A, B, C, D, E, F, G, H, H, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[H, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, H, t] with SortAttrs_9[A, B, C, D, E, F, G, H, H, t, Ns2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_8_String[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2] {
  def startsWith(prefix: t              ): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] with CardOne = _exprOneMan(StartsWith, Seq(prefix))
  def endsWith  (suffix: t              ): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] with CardOne = _exprOneMan(EndsWith  , Seq(suffix))
  def contains  (needle: t              ): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] with CardOne = _exprOneMan(Contains  , Seq(needle))
  def matches   (regex : t              ): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] with CardOne = _exprOneMan(Matches   , Seq(regex) )
}
trait ExprOneMan_8_Number[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2] {
  def %(divider: t, remainder: t        ): Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] with CardOne = _exprOneMan(Remainder , Seq(divider, remainder))
  def even                               : Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] with CardOne = _exprOneMan(Even      , Nil                    )
  def odd                                : Ns1[A, B, C, D, E, F, G, H, t] with SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns1] with CardOne = _exprOneMan(Odd       , Nil                    )
}


trait ExprOneManOps_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] with CardOne = ???
}

trait ExprOneMan_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2]
    with Aggregates_9[A, B, C, D, E, F, G, H, I, t, Ns1]
    with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] with CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] with CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] with CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] with CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] with CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] with CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] with CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] with CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] with CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[I, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, I, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, I, t, Ns2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[I, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, I, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, I, t, Ns2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[I, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, I, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, I, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[I, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, I, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, I, t, Ns2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[I, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, I, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, I, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[I, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, I, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, I, t, Ns2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_9_String[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2] {
  def startsWith(prefix: t              ): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] with CardOne = _exprOneMan(StartsWith, Seq(prefix))
  def endsWith  (suffix: t              ): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] with CardOne = _exprOneMan(EndsWith  , Seq(suffix))
  def contains  (needle: t              ): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] with CardOne = _exprOneMan(Contains  , Seq(needle))
  def matches   (regex : t              ): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] with CardOne = _exprOneMan(Matches   , Seq(regex) )
}
trait ExprOneMan_9_Number[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2] {
  def %(divider: t, remainder: t        ): Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] with CardOne = _exprOneMan(Remainder , Seq(divider, remainder))
  def even                               : Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] with CardOne = _exprOneMan(Even      , Nil                    )
  def odd                                : Ns1[A, B, C, D, E, F, G, H, I, t] with SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns1] with CardOne = _exprOneMan(Odd       , Nil                    )
}


trait ExprOneManOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] with CardOne = ???
}

trait ExprOneMan_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2]
    with Aggregates_10[A, B, C, D, E, F, G, H, I, J, t, Ns1]
    with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] with CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] with CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] with CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] with CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] with CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] with CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] with CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] with CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] with CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[J, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, J, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, J, t, Ns2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[J, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, J, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, J, t, Ns2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[J, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, J, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, J, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[J, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, J, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, J, t, Ns2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[J, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, J, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, J, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[J, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, J, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, J, t, Ns2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_10_String[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2] {
  def startsWith(prefix: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] with CardOne = _exprOneMan(StartsWith, Seq(prefix))
  def endsWith  (suffix: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] with CardOne = _exprOneMan(EndsWith  , Seq(suffix))
  def contains  (needle: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] with CardOne = _exprOneMan(Contains  , Seq(needle))
  def matches   (regex : t              ): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] with CardOne = _exprOneMan(Matches   , Seq(regex) )
}
trait ExprOneMan_10_Number[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2] {
  def %(divider: t, remainder: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] with CardOne = _exprOneMan(Remainder , Seq(divider, remainder))
  def even                               : Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] with CardOne = _exprOneMan(Even      , Nil                    )
  def odd                                : Ns1[A, B, C, D, E, F, G, H, I, J, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] with CardOne = _exprOneMan(Odd       , Nil                    )
}


trait ExprOneManOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] with CardOne = ???
}

trait ExprOneMan_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2]
    with Aggregates_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1]
    with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] with CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] with CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] with CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] with CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] with CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] with CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] with CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] with CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] with CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[K, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, K, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, K, t, Ns2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[K, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, K, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, K, t, Ns2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[K, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, K, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, K, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[K, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, K, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, K, t, Ns2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[K, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, K, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, K, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[K, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, K, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, K, t, Ns2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_11_String[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2] {
  def startsWith(prefix: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] with CardOne = _exprOneMan(StartsWith, Seq(prefix))
  def endsWith  (suffix: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] with CardOne = _exprOneMan(EndsWith  , Seq(suffix))
  def contains  (needle: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] with CardOne = _exprOneMan(Contains  , Seq(needle))
  def matches   (regex : t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] with CardOne = _exprOneMan(Matches   , Seq(regex) )
}
trait ExprOneMan_11_Number[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2] {
  def %(divider: t, remainder: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] with CardOne = _exprOneMan(Remainder , Seq(divider, remainder))
  def even                               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] with CardOne = _exprOneMan(Even      , Nil                    )
  def odd                                : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] with CardOne = _exprOneMan(Odd       , Nil                    )
}


trait ExprOneManOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] with CardOne = ???
}

trait ExprOneMan_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2]
    with Aggregates_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1]
    with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] with CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] with CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] with CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] with CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] with CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] with CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] with CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] with CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] with CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[L, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, L, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, L, t, Ns2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[L, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, L, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, L, t, Ns2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[L, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, L, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, L, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[L, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, L, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, L, t, Ns2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[L, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, L, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, L, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[L, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, L, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, L, t, Ns2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_12_String[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2] {
  def startsWith(prefix: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] with CardOne = _exprOneMan(StartsWith, Seq(prefix))
  def endsWith  (suffix: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] with CardOne = _exprOneMan(EndsWith  , Seq(suffix))
  def contains  (needle: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] with CardOne = _exprOneMan(Contains  , Seq(needle))
  def matches   (regex : t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] with CardOne = _exprOneMan(Matches   , Seq(regex) )
}
trait ExprOneMan_12_Number[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2] {
  def %(divider: t, remainder: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] with CardOne = _exprOneMan(Remainder , Seq(divider, remainder))
  def even                               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] with CardOne = _exprOneMan(Even      , Nil                    )
  def odd                                : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] with CardOne = _exprOneMan(Odd       , Nil                    )
}


trait ExprOneManOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] with CardOne = ???
}

trait ExprOneMan_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2]
    with Aggregates_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1]
    with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] with CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] with CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] with CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] with CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] with CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] with CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] with CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] with CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] with CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[M, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, M, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, M, t, Ns2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[M, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, M, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, M, t, Ns2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[M, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, M, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, M, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[M, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, M, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, M, t, Ns2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[M, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, M, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, M, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[M, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, M, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, M, t, Ns2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_13_String[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2] {
  def startsWith(prefix: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] with CardOne = _exprOneMan(StartsWith, Seq(prefix))
  def endsWith  (suffix: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] with CardOne = _exprOneMan(EndsWith  , Seq(suffix))
  def contains  (needle: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] with CardOne = _exprOneMan(Contains  , Seq(needle))
  def matches   (regex : t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] with CardOne = _exprOneMan(Matches   , Seq(regex) )
}
trait ExprOneMan_13_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2] {
  def %(divider: t, remainder: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] with CardOne = _exprOneMan(Remainder , Seq(divider, remainder))
  def even                               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] with CardOne = _exprOneMan(Even      , Nil                    )
  def odd                                : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] with CardOne = _exprOneMan(Odd       , Nil                    )
}


trait ExprOneManOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] with CardOne = ???
}

trait ExprOneMan_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2]
    with Aggregates_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1]
    with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] with CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] with CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] with CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] with CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] with CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] with CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] with CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] with CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] with CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[N, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, N, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, N, t, Ns2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[N, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, N, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, N, t, Ns2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[N, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, N, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, N, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[N, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, N, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, N, t, Ns2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[N, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, N, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, N, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[N, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, N, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, N, t, Ns2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_14_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2] {
  def startsWith(prefix: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] with CardOne = _exprOneMan(StartsWith, Seq(prefix))
  def endsWith  (suffix: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] with CardOne = _exprOneMan(EndsWith  , Seq(suffix))
  def contains  (needle: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] with CardOne = _exprOneMan(Contains  , Seq(needle))
  def matches   (regex : t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] with CardOne = _exprOneMan(Matches   , Seq(regex) )
}
trait ExprOneMan_14_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2] {
  def %(divider: t, remainder: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] with CardOne = _exprOneMan(Remainder , Seq(divider, remainder))
  def even                               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] with CardOne = _exprOneMan(Even      , Nil                    )
  def odd                                : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] with CardOne = _exprOneMan(Odd       , Nil                    )
}


trait ExprOneManOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] with CardOne = ???
}

trait ExprOneMan_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2]
    with Aggregates_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1]
    with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] with CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] with CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] with CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] with CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] with CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] with CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] with CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] with CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] with CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[O, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, O, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, O, t, Ns2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[O, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, O, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, O, t, Ns2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[O, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, O, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, O, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[O, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, O, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, O, t, Ns2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[O, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, O, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, O, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[O, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, O, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, O, t, Ns2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_15_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2] {
  def startsWith(prefix: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] with CardOne = _exprOneMan(StartsWith, Seq(prefix))
  def endsWith  (suffix: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] with CardOne = _exprOneMan(EndsWith  , Seq(suffix))
  def contains  (needle: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] with CardOne = _exprOneMan(Contains  , Seq(needle))
  def matches   (regex : t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] with CardOne = _exprOneMan(Matches   , Seq(regex) )
}
trait ExprOneMan_15_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2] {
  def %(divider: t, remainder: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] with CardOne = _exprOneMan(Remainder , Seq(divider, remainder))
  def even                               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] with CardOne = _exprOneMan(Even      , Nil                    )
  def odd                                : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] with CardOne = _exprOneMan(Odd       , Nil                    )
}


trait ExprOneManOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] with CardOne = ???
}

trait ExprOneMan_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2]
    with Aggregates_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1]
    with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] with CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] with CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] with CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] with CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] with CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] with CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] with CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] with CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] with CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[P, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, P, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, P, t, Ns2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[P, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, P, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, P, t, Ns2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[P, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, P, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, P, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[P, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, P, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, P, t, Ns2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[P, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, P, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, P, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[P, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, P, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, P, t, Ns2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_16_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2] {
  def startsWith(prefix: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] with CardOne = _exprOneMan(StartsWith, Seq(prefix))
  def endsWith  (suffix: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] with CardOne = _exprOneMan(EndsWith  , Seq(suffix))
  def contains  (needle: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] with CardOne = _exprOneMan(Contains  , Seq(needle))
  def matches   (regex : t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] with CardOne = _exprOneMan(Matches   , Seq(regex) )
}
trait ExprOneMan_16_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2] {
  def %(divider: t, remainder: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] with CardOne = _exprOneMan(Remainder , Seq(divider, remainder))
  def even                               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] with CardOne = _exprOneMan(Even      , Nil                    )
  def odd                                : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] with CardOne = _exprOneMan(Odd       , Nil                    )
}


trait ExprOneManOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] with CardOne = ???
}

trait ExprOneMan_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2]
    with Aggregates_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1]
    with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] with CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] with CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] with CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] with CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] with CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] with CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] with CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] with CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] with CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Q, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Q, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Q, t, Ns2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Q, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Q, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Q, t, Ns2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Q, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Q, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Q, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Q, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Q, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Q, t, Ns2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Q, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Q, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Q, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Q, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Q, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Q, t, Ns2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_17_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2] {
  def startsWith(prefix: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] with CardOne = _exprOneMan(StartsWith, Seq(prefix))
  def endsWith  (suffix: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] with CardOne = _exprOneMan(EndsWith  , Seq(suffix))
  def contains  (needle: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] with CardOne = _exprOneMan(Contains  , Seq(needle))
  def matches   (regex : t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] with CardOne = _exprOneMan(Matches   , Seq(regex) )
}
trait ExprOneMan_17_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2] {
  def %(divider: t, remainder: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] with CardOne = _exprOneMan(Remainder , Seq(divider, remainder))
  def even                               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] with CardOne = _exprOneMan(Even      , Nil                    )
  def odd                                : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] with CardOne = _exprOneMan(Odd       , Nil                    )
}


trait ExprOneManOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] with CardOne = ???
}

trait ExprOneMan_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2]
    with Aggregates_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1]
    with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] with CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] with CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] with CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] with CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] with CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] with CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] with CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] with CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] with CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[R, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, R, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, R, t, Ns2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[R, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, R, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, R, t, Ns2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[R, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, R, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, R, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[R, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, R, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, R, t, Ns2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[R, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, R, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, R, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[R, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, R, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, R, t, Ns2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_18_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2] {
  def startsWith(prefix: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] with CardOne = _exprOneMan(StartsWith, Seq(prefix))
  def endsWith  (suffix: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] with CardOne = _exprOneMan(EndsWith  , Seq(suffix))
  def contains  (needle: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] with CardOne = _exprOneMan(Contains  , Seq(needle))
  def matches   (regex : t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] with CardOne = _exprOneMan(Matches   , Seq(regex) )
}
trait ExprOneMan_18_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2] {
  def %(divider: t, remainder: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] with CardOne = _exprOneMan(Remainder , Seq(divider, remainder))
  def even                               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] with CardOne = _exprOneMan(Even      , Nil                    )
  def odd                                : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] with CardOne = _exprOneMan(Odd       , Nil                    )
}


trait ExprOneManOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] with CardOne = ???
}

trait ExprOneMan_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2]
    with Aggregates_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1]
    with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] with CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] with CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] with CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] with CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] with CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] with CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] with CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] with CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] with CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[S, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, S, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, S, t, Ns2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[S, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, S, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, S, t, Ns2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[S, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, S, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, S, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[S, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, S, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, S, t, Ns2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[S, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, S, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, S, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[S, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, S, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, S, t, Ns2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_19_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2] {
  def startsWith(prefix: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] with CardOne = _exprOneMan(StartsWith, Seq(prefix))
  def endsWith  (suffix: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] with CardOne = _exprOneMan(EndsWith  , Seq(suffix))
  def contains  (needle: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] with CardOne = _exprOneMan(Contains  , Seq(needle))
  def matches   (regex : t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] with CardOne = _exprOneMan(Matches   , Seq(regex) )
}
trait ExprOneMan_19_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2] {
  def %(divider: t, remainder: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] with CardOne = _exprOneMan(Remainder , Seq(divider, remainder))
  def even                               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] with CardOne = _exprOneMan(Even      , Nil                    )
  def odd                                : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] with CardOne = _exprOneMan(Odd       , Nil                    )
}


trait ExprOneManOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] with CardOne = ???
}

trait ExprOneMan_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2]
    with Aggregates_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1]
    with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] with CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] with CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] with CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] with CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] with CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] with CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] with CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] with CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] with CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[T, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, T, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, T, t, Ns2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[T, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, T, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, T, t, Ns2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[T, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, T, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, T, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[T, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, T, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, T, t, Ns2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[T, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, T, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, T, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[T, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, T, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, T, t, Ns2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_20_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2] {
  def startsWith(prefix: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] with CardOne = _exprOneMan(StartsWith, Seq(prefix))
  def endsWith  (suffix: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] with CardOne = _exprOneMan(EndsWith  , Seq(suffix))
  def contains  (needle: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] with CardOne = _exprOneMan(Contains  , Seq(needle))
  def matches   (regex : t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] with CardOne = _exprOneMan(Matches   , Seq(regex) )
}
trait ExprOneMan_20_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2] {
  def %(divider: t, remainder: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] with CardOne = _exprOneMan(Remainder , Seq(divider, remainder))
  def even                               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] with CardOne = _exprOneMan(Even      , Nil                    )
  def odd                                : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] with CardOne = _exprOneMan(Odd       , Nil                    )
}


trait ExprOneManOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] with CardOne = ???
}

trait ExprOneMan_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2]
    with Aggregates_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1]
    with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] with CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] with CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] with CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] with CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] with CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] with CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] with CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] with CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] with CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] = _attrSortTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[U, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, U, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, U, t, Ns2] = _attrSortMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[U, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, U, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, U, t, Ns2] = _attrSortMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[U, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, U, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, U, t, Ns2] = _attrSortMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[U, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, U, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, U, t, Ns2] = _attrSortMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[U, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, U, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, U, t, Ns2] = _attrSortMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[U, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, U, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, U, t, Ns2] = _attrSortMan(Ge , a)
}
trait ExprOneMan_21_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2] {
  def startsWith(prefix: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] with CardOne = _exprOneMan(StartsWith, Seq(prefix))
  def endsWith  (suffix: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] with CardOne = _exprOneMan(EndsWith  , Seq(suffix))
  def contains  (needle: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] with CardOne = _exprOneMan(Contains  , Seq(needle))
  def matches   (regex : t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] with CardOne = _exprOneMan(Matches   , Seq(regex) )
}
trait ExprOneMan_21_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2] {
  def %(divider: t, remainder: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] with CardOne = _exprOneMan(Remainder , Seq(divider, remainder))
  def even                               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] with CardOne = _exprOneMan(Even      , Nil                    )
  def odd                                : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] with CardOne = _exprOneMan(Odd       , Nil                    )
}


trait ExprOneManOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2] {
  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] with CardOne = ???
}

trait ExprOneMan_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneManOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2]
    with Aggregates_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1]
    with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] with CardOne = _exprOneMan(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] with CardOne = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] with CardOne = _exprOneMan(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] with CardOne = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] with CardOne = _exprOneMan(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] with CardOne = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] with CardOne = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] with CardOne = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] with CardOne = _exprOneMan(Ge     , Seq(lower)  )
  
  def apply[ns1[_], tx[_]](a: ModelOps_0[t, ns1, X2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] = _attrSortTac(Eq , a)
  def not  [ns1[_], tx[_]](a: ModelOps_0[t, ns1, X2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] = _attrSortTac(Neq, a)
  def <    [ns1[_], tx[_]](a: ModelOps_0[t, ns1, X2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] = _attrSortTac(Lt , a)
  def <=   [ns1[_], tx[_]](a: ModelOps_0[t, ns1, X2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] = _attrSortTac(Le , a)
  def >    [ns1[_], tx[_]](a: ModelOps_0[t, ns1, X2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] = _attrSortTac(Gt , a)
  def >=   [ns1[_], tx[_]](a: ModelOps_0[t, ns1, X2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] = _attrSortTac(Ge , a)
}
trait ExprOneMan_22_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2] {
  def startsWith(prefix: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] with CardOne = _exprOneMan(StartsWith, Seq(prefix))
  def endsWith  (suffix: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] with CardOne = _exprOneMan(EndsWith  , Seq(suffix))
  def contains  (needle: t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] with CardOne = _exprOneMan(Contains  , Seq(needle))
  def matches   (regex : t              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] with CardOne = _exprOneMan(Matches   , Seq(regex) )
}
trait ExprOneMan_22_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneMan_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2] {
  def %(divider: t, remainder: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] with CardOne = _exprOneMan(Remainder , Seq(divider, remainder))
  def even                               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] with CardOne = _exprOneMan(Even      , Nil                    )
  def odd                                : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] with CardOne = _exprOneMan(Odd       , Nil                    )
}
