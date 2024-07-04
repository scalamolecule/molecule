// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.base.ast._
import molecule.boilerplate.api._
import molecule.boilerplate.ast.Model._
import scala.language.higherKinds


trait ExprOneTacOps_0[t, Ns1[_], Ns2[_, _]] extends ExprAttr_0[t, Ns1, Ns2] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[t] with CardOne = ???
}

trait ExprOneTac_0[t, Ns1[_], Ns2[_, _]]
  extends ExprOneTacOps_0[t, Ns1, Ns2] {
  def apply(                ): Ns1[t] with CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[t] with CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[t] with CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[t] with CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[t] with CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Ns1[t] with CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[t] with CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[t] with CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[t] with CardOne = _exprOneTac(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[t, t] = _attrMan(Ge , a)
}
trait ExprOneTac_0_String[t, Ns1[_], Ns2[_, _]] extends ExprOneTac_0[t, Ns1, Ns2] {
  def startsWith(prefix: t      ): Ns1[t] with CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t      ): Ns1[t] with CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t      ): Ns1[t] with CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t      ): Ns1[t] with CardOne = _exprOneTac(Matches   , Seq(regex) )
}
trait ExprOneTac_0_Number[t, Ns1[_], Ns2[_, _]] extends ExprOneTac_0[t, Ns1, Ns2] {
  def %(divider: t, remainder: t): Ns1[t] with CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
  def even                       : Ns1[t] with CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Ns1[t] with CardOne = _exprOneTac(Odd       , Nil                    )
}


trait ExprOneTacOps_1[A, t, Ns1[_, _], Ns2[_, _, _]] extends ExprAttr_1[A, t, Ns1, Ns2] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, t] with CardOne = ???
}

trait ExprOneTac_1[A, t, Ns1[_, _], Ns2[_, _, _]]
  extends ExprOneTacOps_1[A, t, Ns1, Ns2] {
  def apply(                ): Ns1[A, t] with CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, t] with CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, t] with CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, t] with CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, t] with CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, t] with CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, t] with CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, t] with CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, t] with CardOne = _exprOneTac(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, t, t] = _attrMan(Ge , a)
}
trait ExprOneTac_1_String[A, t, Ns1[_, _], Ns2[_, _, _]] extends ExprOneTac_1[A, t, Ns1, Ns2] {
  def startsWith(prefix: t      ): Ns1[A, t] with CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t      ): Ns1[A, t] with CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t      ): Ns1[A, t] with CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t      ): Ns1[A, t] with CardOne = _exprOneTac(Matches   , Seq(regex) )
}
trait ExprOneTac_1_Number[A, t, Ns1[_, _], Ns2[_, _, _]] extends ExprOneTac_1[A, t, Ns1, Ns2] {
  def %(divider: t, remainder: t): Ns1[A, t] with CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
  def even                       : Ns1[A, t] with CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Ns1[A, t] with CardOne = _exprOneTac(Odd       , Nil                    )
}


trait ExprOneTacOps_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]] extends ExprAttr_2[A, B, t, Ns1, Ns2] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, t] with CardOne = ???
}

trait ExprOneTac_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]]
  extends ExprOneTacOps_2[A, B, t, Ns1, Ns2] {
  def apply(                ): Ns1[A, B, t] with CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, t] with CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, t] with CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, t] with CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, t] with CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, t] with CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, t] with CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, t] with CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, t] with CardOne = _exprOneTac(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, t, t] = _attrMan(Ge , a)
}
trait ExprOneTac_2_String[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]] extends ExprOneTac_2[A, B, t, Ns1, Ns2] {
  def startsWith(prefix: t      ): Ns1[A, B, t] with CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t      ): Ns1[A, B, t] with CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t      ): Ns1[A, B, t] with CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t      ): Ns1[A, B, t] with CardOne = _exprOneTac(Matches   , Seq(regex) )
}
trait ExprOneTac_2_Number[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]] extends ExprOneTac_2[A, B, t, Ns1, Ns2] {
  def %(divider: t, remainder: t): Ns1[A, B, t] with CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
  def even                       : Ns1[A, B, t] with CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Ns1[A, B, t] with CardOne = _exprOneTac(Odd       , Nil                    )
}


trait ExprOneTacOps_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]] extends ExprAttr_3[A, B, C, t, Ns1, Ns2] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, t] with CardOne = ???
}

trait ExprOneTac_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]]
  extends ExprOneTacOps_3[A, B, C, t, Ns1, Ns2] {
  def apply(                ): Ns1[A, B, C, t] with CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, t] with CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, t] with CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, t] with CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, t] with CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, t] with CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, t] with CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, t] with CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, t] with CardOne = _exprOneTac(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, t, t] = _attrMan(Ge , a)
}
trait ExprOneTac_3_String[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]] extends ExprOneTac_3[A, B, C, t, Ns1, Ns2] {
  def startsWith(prefix: t      ): Ns1[A, B, C, t] with CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t      ): Ns1[A, B, C, t] with CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t      ): Ns1[A, B, C, t] with CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t      ): Ns1[A, B, C, t] with CardOne = _exprOneTac(Matches   , Seq(regex) )
}
trait ExprOneTac_3_Number[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]] extends ExprOneTac_3[A, B, C, t, Ns1, Ns2] {
  def %(divider: t, remainder: t): Ns1[A, B, C, t] with CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
  def even                       : Ns1[A, B, C, t] with CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Ns1[A, B, C, t] with CardOne = _exprOneTac(Odd       , Nil                    )
}


trait ExprOneTacOps_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]] extends ExprAttr_4[A, B, C, D, t, Ns1, Ns2] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, t] with CardOne = ???
}

trait ExprOneTac_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]]
  extends ExprOneTacOps_4[A, B, C, D, t, Ns1, Ns2] {
  def apply(                ): Ns1[A, B, C, D, t] with CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, t] with CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, t] with CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, t] with CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, t] with CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, t] with CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, t] with CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, t] with CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, t] with CardOne = _exprOneTac(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, t, t] = _attrMan(Ge , a)
}
trait ExprOneTac_4_String[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]] extends ExprOneTac_4[A, B, C, D, t, Ns1, Ns2] {
  def startsWith(prefix: t      ): Ns1[A, B, C, D, t] with CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t      ): Ns1[A, B, C, D, t] with CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t      ): Ns1[A, B, C, D, t] with CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t      ): Ns1[A, B, C, D, t] with CardOne = _exprOneTac(Matches   , Seq(regex) )
}
trait ExprOneTac_4_Number[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]] extends ExprOneTac_4[A, B, C, D, t, Ns1, Ns2] {
  def %(divider: t, remainder: t): Ns1[A, B, C, D, t] with CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
  def even                       : Ns1[A, B, C, D, t] with CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Ns1[A, B, C, D, t] with CardOne = _exprOneTac(Odd       , Nil                    )
}


trait ExprOneTacOps_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]] extends ExprAttr_5[A, B, C, D, E, t, Ns1, Ns2] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, t] with CardOne = ???
}

trait ExprOneTac_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]]
  extends ExprOneTacOps_5[A, B, C, D, E, t, Ns1, Ns2] {
  def apply(                ): Ns1[A, B, C, D, E, t] with CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, t] with CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, t] with CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, t] with CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, t] with CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, t] with CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, t] with CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, t] with CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, t] with CardOne = _exprOneTac(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, t, t] = _attrMan(Ge , a)
}
trait ExprOneTac_5_String[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]] extends ExprOneTac_5[A, B, C, D, E, t, Ns1, Ns2] {
  def startsWith(prefix: t      ): Ns1[A, B, C, D, E, t] with CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t      ): Ns1[A, B, C, D, E, t] with CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t      ): Ns1[A, B, C, D, E, t] with CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t      ): Ns1[A, B, C, D, E, t] with CardOne = _exprOneTac(Matches   , Seq(regex) )
}
trait ExprOneTac_5_Number[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]] extends ExprOneTac_5[A, B, C, D, E, t, Ns1, Ns2] {
  def %(divider: t, remainder: t): Ns1[A, B, C, D, E, t] with CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
  def even                       : Ns1[A, B, C, D, E, t] with CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Ns1[A, B, C, D, E, t] with CardOne = _exprOneTac(Odd       , Nil                    )
}


trait ExprOneTacOps_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]] extends ExprAttr_6[A, B, C, D, E, F, t, Ns1, Ns2] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, t] with CardOne = ???
}

trait ExprOneTac_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_6[A, B, C, D, E, F, t, Ns1, Ns2] {
  def apply(                ): Ns1[A, B, C, D, E, F, t] with CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, t] with CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, t] with CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, t] with CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, t] with CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, t] with CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, t] with CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, t] with CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, t] with CardOne = _exprOneTac(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, t, t] = _attrMan(Ge , a)
}
trait ExprOneTac_6_String[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]] extends ExprOneTac_6[A, B, C, D, E, F, t, Ns1, Ns2] {
  def startsWith(prefix: t      ): Ns1[A, B, C, D, E, F, t] with CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t      ): Ns1[A, B, C, D, E, F, t] with CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t      ): Ns1[A, B, C, D, E, F, t] with CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t      ): Ns1[A, B, C, D, E, F, t] with CardOne = _exprOneTac(Matches   , Seq(regex) )
}
trait ExprOneTac_6_Number[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]] extends ExprOneTac_6[A, B, C, D, E, F, t, Ns1, Ns2] {
  def %(divider: t, remainder: t): Ns1[A, B, C, D, E, F, t] with CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
  def even                       : Ns1[A, B, C, D, E, F, t] with CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Ns1[A, B, C, D, E, F, t] with CardOne = _exprOneTac(Odd       , Nil                    )
}


trait ExprOneTacOps_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]] extends ExprAttr_7[A, B, C, D, E, F, G, t, Ns1, Ns2] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, t] with CardOne = ???
}

trait ExprOneTac_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_7[A, B, C, D, E, F, G, t, Ns1, Ns2] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, t] with CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, t] with CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, t] with CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, t] with CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, t] with CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, t] with CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, t] with CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, t] with CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, t] with CardOne = _exprOneTac(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, t, t] = _attrMan(Ge , a)
}
trait ExprOneTac_7_String[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]] extends ExprOneTac_7[A, B, C, D, E, F, G, t, Ns1, Ns2] {
  def startsWith(prefix: t      ): Ns1[A, B, C, D, E, F, G, t] with CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t      ): Ns1[A, B, C, D, E, F, G, t] with CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t      ): Ns1[A, B, C, D, E, F, G, t] with CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t      ): Ns1[A, B, C, D, E, F, G, t] with CardOne = _exprOneTac(Matches   , Seq(regex) )
}
trait ExprOneTac_7_Number[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]] extends ExprOneTac_7[A, B, C, D, E, F, G, t, Ns1, Ns2] {
  def %(divider: t, remainder: t): Ns1[A, B, C, D, E, F, G, t] with CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
  def even                       : Ns1[A, B, C, D, E, F, G, t] with CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Ns1[A, B, C, D, E, F, G, t] with CardOne = _exprOneTac(Odd       , Nil                    )
}


trait ExprOneTacOps_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]] extends ExprAttr_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, t] with CardOne = ???
}

trait ExprOneTac_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, t] with CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, t] with CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, t] with CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, t] with CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, t] with CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, t] with CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, t] with CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, t] with CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, t] with CardOne = _exprOneTac(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, t, t] = _attrMan(Ge , a)
}
trait ExprOneTac_8_String[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2] {
  def startsWith(prefix: t      ): Ns1[A, B, C, D, E, F, G, H, t] with CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t      ): Ns1[A, B, C, D, E, F, G, H, t] with CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t      ): Ns1[A, B, C, D, E, F, G, H, t] with CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t      ): Ns1[A, B, C, D, E, F, G, H, t] with CardOne = _exprOneTac(Matches   , Seq(regex) )
}
trait ExprOneTac_8_Number[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2] {
  def %(divider: t, remainder: t): Ns1[A, B, C, D, E, F, G, H, t] with CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
  def even                       : Ns1[A, B, C, D, E, F, G, H, t] with CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Ns1[A, B, C, D, E, F, G, H, t] with CardOne = _exprOneTac(Odd       , Nil                    )
}


trait ExprOneTacOps_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, t] with CardOne = ???
}

trait ExprOneTac_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, t] with CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, t] with CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, t] with CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, t] with CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, t] with CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, t] with CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, t] with CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, t] with CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, t] with CardOne = _exprOneTac(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, t, t] = _attrMan(Ge , a)
}
trait ExprOneTac_9_String[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2] {
  def startsWith(prefix: t      ): Ns1[A, B, C, D, E, F, G, H, I, t] with CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t      ): Ns1[A, B, C, D, E, F, G, H, I, t] with CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t      ): Ns1[A, B, C, D, E, F, G, H, I, t] with CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t      ): Ns1[A, B, C, D, E, F, G, H, I, t] with CardOne = _exprOneTac(Matches   , Seq(regex) )
}
trait ExprOneTac_9_Number[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2] {
  def %(divider: t, remainder: t): Ns1[A, B, C, D, E, F, G, H, I, t] with CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
  def even                       : Ns1[A, B, C, D, E, F, G, H, I, t] with CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Ns1[A, B, C, D, E, F, G, H, I, t] with CardOne = _exprOneTac(Odd       , Nil                    )
}


trait ExprOneTacOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, t] with CardOne = ???
}

trait ExprOneTac_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, t] with CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, t] with CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, t] with CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, t] with CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, t] with CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, t] with CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, t] with CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, t] with CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, t] with CardOne = _exprOneTac(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, t, t] = _attrMan(Ge , a)
}
trait ExprOneTac_10_String[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2] {
  def startsWith(prefix: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, t] with CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, t] with CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, t] with CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t      ): Ns1[A, B, C, D, E, F, G, H, I, J, t] with CardOne = _exprOneTac(Matches   , Seq(regex) )
}
trait ExprOneTac_10_Number[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2] {
  def %(divider: t, remainder: t): Ns1[A, B, C, D, E, F, G, H, I, J, t] with CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
  def even                       : Ns1[A, B, C, D, E, F, G, H, I, J, t] with CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Ns1[A, B, C, D, E, F, G, H, I, J, t] with CardOne = _exprOneTac(Odd       , Nil                    )
}


trait ExprOneTacOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with CardOne = ???
}

trait ExprOneTac_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with CardOne = _exprOneTac(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, t, t] = _attrMan(Ge , a)
}
trait ExprOneTac_11_String[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2] {
  def startsWith(prefix: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with CardOne = _exprOneTac(Matches   , Seq(regex) )
}
trait ExprOneTac_11_Number[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2] {
  def %(divider: t, remainder: t): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
  def even                       : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] with CardOne = _exprOneTac(Odd       , Nil                    )
}


trait ExprOneTacOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with CardOne = ???
}

trait ExprOneTac_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with CardOne = _exprOneTac(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, t, t] = _attrMan(Ge , a)
}
trait ExprOneTac_12_String[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2] {
  def startsWith(prefix: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with CardOne = _exprOneTac(Matches   , Seq(regex) )
}
trait ExprOneTac_12_Number[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2] {
  def %(divider: t, remainder: t): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
  def even                       : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] with CardOne = _exprOneTac(Odd       , Nil                    )
}


trait ExprOneTacOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with CardOne = ???
}

trait ExprOneTac_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with CardOne = _exprOneTac(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, t, t] = _attrMan(Ge , a)
}
trait ExprOneTac_13_String[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2] {
  def startsWith(prefix: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with CardOne = _exprOneTac(Matches   , Seq(regex) )
}
trait ExprOneTac_13_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2] {
  def %(divider: t, remainder: t): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
  def even                       : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] with CardOne = _exprOneTac(Odd       , Nil                    )
}


trait ExprOneTacOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with CardOne = ???
}

trait ExprOneTac_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with CardOne = _exprOneTac(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, t] = _attrMan(Ge , a)
}
trait ExprOneTac_14_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2] {
  def startsWith(prefix: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with CardOne = _exprOneTac(Matches   , Seq(regex) )
}
trait ExprOneTac_14_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2] {
  def %(divider: t, remainder: t): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
  def even                       : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] with CardOne = _exprOneTac(Odd       , Nil                    )
}


trait ExprOneTacOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with CardOne = ???
}

trait ExprOneTac_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with CardOne = _exprOneTac(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, t] = _attrMan(Ge , a)
}
trait ExprOneTac_15_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2] {
  def startsWith(prefix: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with CardOne = _exprOneTac(Matches   , Seq(regex) )
}
trait ExprOneTac_15_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2] {
  def %(divider: t, remainder: t): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
  def even                       : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] with CardOne = _exprOneTac(Odd       , Nil                    )
}


trait ExprOneTacOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with CardOne = ???
}

trait ExprOneTac_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with CardOne = _exprOneTac(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, t] = _attrMan(Ge , a)
}
trait ExprOneTac_16_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2] {
  def startsWith(prefix: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with CardOne = _exprOneTac(Matches   , Seq(regex) )
}
trait ExprOneTac_16_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2] {
  def %(divider: t, remainder: t): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
  def even                       : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] with CardOne = _exprOneTac(Odd       , Nil                    )
}


trait ExprOneTacOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with CardOne = ???
}

trait ExprOneTac_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with CardOne = _exprOneTac(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, t] = _attrMan(Ge , a)
}
trait ExprOneTac_17_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2] {
  def startsWith(prefix: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with CardOne = _exprOneTac(Matches   , Seq(regex) )
}
trait ExprOneTac_17_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2] {
  def %(divider: t, remainder: t): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
  def even                       : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] with CardOne = _exprOneTac(Odd       , Nil                    )
}


trait ExprOneTacOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with CardOne = ???
}

trait ExprOneTac_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with CardOne = _exprOneTac(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, t] = _attrMan(Ge , a)
}
trait ExprOneTac_18_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2] {
  def startsWith(prefix: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with CardOne = _exprOneTac(Matches   , Seq(regex) )
}
trait ExprOneTac_18_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2] {
  def %(divider: t, remainder: t): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
  def even                       : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] with CardOne = _exprOneTac(Odd       , Nil                    )
}


trait ExprOneTacOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with CardOne = ???
}

trait ExprOneTac_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with CardOne = _exprOneTac(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, t] = _attrMan(Ge , a)
}
trait ExprOneTac_19_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2] {
  def startsWith(prefix: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with CardOne = _exprOneTac(Matches   , Seq(regex) )
}
trait ExprOneTac_19_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2] {
  def %(divider: t, remainder: t): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
  def even                       : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] with CardOne = _exprOneTac(Odd       , Nil                    )
}


trait ExprOneTacOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with CardOne = ???
}

trait ExprOneTac_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with CardOne = _exprOneTac(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, t] = _attrMan(Ge , a)
}
trait ExprOneTac_20_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2] {
  def startsWith(prefix: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with CardOne = _exprOneTac(Matches   , Seq(regex) )
}
trait ExprOneTac_20_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2] {
  def %(divider: t, remainder: t): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
  def even                       : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] with CardOne = _exprOneTac(Odd       , Nil                    )
}


trait ExprOneTacOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with CardOne = ???
}

trait ExprOneTac_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with CardOne = _exprOneTac(Ge     , Seq(lower)  )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, t] = _attrMan(Ge , a)
}
trait ExprOneTac_21_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2] {
  def startsWith(prefix: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with CardOne = _exprOneTac(Matches   , Seq(regex) )
}
trait ExprOneTac_21_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2] {
  def %(divider: t, remainder: t): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
  def even                       : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] with CardOne = _exprOneTac(Odd       , Nil                    )
}


trait ExprOneTacOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2] {
  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with CardOne = ???
}

trait ExprOneTac_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprOneTacOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2] {
  def apply(                ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with CardOne = _exprOneTac(Ge     , Seq(lower)  )
  
  def apply[ns1[_]](a: ModelOps_0[t, ns1, X2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Eq , a)
  def not  [ns1[_]](a: ModelOps_0[t, ns1, X2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Neq, a)
  def <    [ns1[_]](a: ModelOps_0[t, ns1, X2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Lt , a)
  def <=   [ns1[_]](a: ModelOps_0[t, ns1, X2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Le , a)
  def >    [ns1[_]](a: ModelOps_0[t, ns1, X2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Gt , a)
  def >=   [ns1[_]](a: ModelOps_0[t, ns1, X2] with CardOne): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Ge , a)
}
trait ExprOneTac_22_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2] {
  def startsWith(prefix: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t      ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with CardOne = _exprOneTac(Matches   , Seq(regex) )
}
trait ExprOneTac_22_Number[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2] {
  def %(divider: t, remainder: t): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
  def even                       : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] with CardOne = _exprOneTac(Odd       , Nil                    )
}
