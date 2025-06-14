// GENERATED CODE ********************************
package molecule.db.core.api.expression

import molecule.base.metaModel.CardOne
import molecule.core.dataModel.*
import molecule.core.dataModel.Keywords.qm
import molecule.db.core.api.*


trait ExprOneTacOps_0[t, Entity1[_], Entity2[_, _]] extends ExprAttr_0[t, Entity1, Entity2] {
  protected def _exprOneTac(op: Op, vs: Seq[t], binding: Boolean = false): Entity1[t] & CardOne = ???
}

trait ExprOneTac_0[t, Entity1[_], Entity2[_, _]] extends ExprOneTacOps_0[t, Entity1, Entity2] {
  def apply(                ): Entity1[t] & CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[t] & CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[t] & CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[t] & CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[t] & CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Entity1[t] & CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[t] & CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[t] & CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[t] & CardOne = _exprOneTac(Ge     , Seq(lower)  )

  def apply(v    : qm): Entity1[t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v    : qm): Entity1[t] & CardOne = _exprOneTac(Neq, Nil, true)
  def <    (upper: qm): Entity1[t] & CardOne = _exprOneTac(Lt , Nil, true)
  def <=   (upper: qm): Entity1[t] & CardOne = _exprOneTac(Le , Nil, true)
  def >    (lower: qm): Entity1[t] & CardOne = _exprOneTac(Gt , Nil, true)
  def >=   (lower: qm): Entity1[t] & CardOne = _exprOneTac(Ge , Nil, true)
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[t, t] = _attrMan(Ge , a)
}

trait ExprOneTac_0_String[t, Entity1[_], Entity2[_, _]] extends ExprOneTac_0[t, Entity1, Entity2] {
  def startsWith(prefix: t): Entity1[t] & CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t): Entity1[t] & CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t): Entity1[t] & CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t): Entity1[t] & CardOne = _exprOneTac(Matches   , Seq(regex) )

  def startsWith(prefix: qm): Entity1[t] & CardOne = _exprOneTac(StartsWith, Nil, true)
  def endsWith  (suffix: qm): Entity1[t] & CardOne = _exprOneTac(EndsWith  , Nil, true)
  def contains  (needle: qm): Entity1[t] & CardOne = _exprOneTac(Contains  , Nil, true)
  def matches   (regex : qm): Entity1[t] & CardOne = _exprOneTac(Matches   , Nil, true)
}

trait ExprOneTac_0_Enum[t, Entity1[_], Entity2[_, _]] extends ExprOneTacOps_0[t, Entity1, Entity2] {
  def apply(             ): Entity1[t] & CardOne = _exprOneTac(NoValue, Nil                                      )
  def apply(v : t, vs: t*): Entity1[t] & CardOne = _exprOneTac(Eq     , (v +: vs).map(_.toString.asInstanceOf[t]))
  def apply(vs: Seq[t]   ): Entity1[t] & CardOne = _exprOneTac(Eq     , vs       .map(_.toString.asInstanceOf[t]))
  def not  (v : t, vs: t*): Entity1[t] & CardOne = _exprOneTac(Neq    , (v +: vs).map(_.toString.asInstanceOf[t]))
  def not  (vs: Seq[t]   ): Entity1[t] & CardOne = _exprOneTac(Neq    , vs       .map(_.toString.asInstanceOf[t]))

  def apply(v : qm): Entity1[t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v : qm): Entity1[t] & CardOne = _exprOneTac(Neq, Nil, true)
}

trait ExprOneTac_0_Integer[t, Entity1[_], Entity2[_, _]] extends ExprOneTac_0[t, Entity1, Entity2] {
  def even                       : Entity1[t] & CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Entity1[t] & CardOne = _exprOneTac(Odd       , Nil                    )
  def %(divider: t, remainder: t): Entity1[t] & CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
}


trait ExprOneTacOps_1[A, t, Entity1[_, _], Entity2[_, _, _]] extends ExprAttr_1[A, t, Entity1, Entity2] {
  protected def _exprOneTac(op: Op, vs: Seq[t], binding: Boolean = false): Entity1[A, t] & CardOne = ???
}

trait ExprOneTac_1[A, t, Entity1[_, _], Entity2[_, _, _]] extends ExprOneTacOps_1[A, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, t] & CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, t] & CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, t] & CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, t] & CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, t] & CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, t] & CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, t] & CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, t] & CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, t] & CardOne = _exprOneTac(Ge     , Seq(lower)  )

  def apply(v    : qm): Entity1[A, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v    : qm): Entity1[A, t] & CardOne = _exprOneTac(Neq, Nil, true)
  def <    (upper: qm): Entity1[A, t] & CardOne = _exprOneTac(Lt , Nil, true)
  def <=   (upper: qm): Entity1[A, t] & CardOne = _exprOneTac(Le , Nil, true)
  def >    (lower: qm): Entity1[A, t] & CardOne = _exprOneTac(Gt , Nil, true)
  def >=   (lower: qm): Entity1[A, t] & CardOne = _exprOneTac(Ge , Nil, true)
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, t, t] = _attrMan(Ge , a)
}

trait ExprOneTac_1_String[A, t, Entity1[_, _], Entity2[_, _, _]] extends ExprOneTac_1[A, t, Entity1, Entity2] {
  def startsWith(prefix: t): Entity1[A, t] & CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t): Entity1[A, t] & CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t): Entity1[A, t] & CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t): Entity1[A, t] & CardOne = _exprOneTac(Matches   , Seq(regex) )

  def startsWith(prefix: qm): Entity1[A, t] & CardOne = _exprOneTac(StartsWith, Nil, true)
  def endsWith  (suffix: qm): Entity1[A, t] & CardOne = _exprOneTac(EndsWith  , Nil, true)
  def contains  (needle: qm): Entity1[A, t] & CardOne = _exprOneTac(Contains  , Nil, true)
  def matches   (regex : qm): Entity1[A, t] & CardOne = _exprOneTac(Matches   , Nil, true)
}

trait ExprOneTac_1_Enum[A, t, Entity1[_, _], Entity2[_, _, _]] extends ExprOneTacOps_1[A, t, Entity1, Entity2] {
  def apply(             ): Entity1[A, t] & CardOne = _exprOneTac(NoValue, Nil                                      )
  def apply(v : t, vs: t*): Entity1[A, t] & CardOne = _exprOneTac(Eq     , (v +: vs).map(_.toString.asInstanceOf[t]))
  def apply(vs: Seq[t]   ): Entity1[A, t] & CardOne = _exprOneTac(Eq     , vs       .map(_.toString.asInstanceOf[t]))
  def not  (v : t, vs: t*): Entity1[A, t] & CardOne = _exprOneTac(Neq    , (v +: vs).map(_.toString.asInstanceOf[t]))
  def not  (vs: Seq[t]   ): Entity1[A, t] & CardOne = _exprOneTac(Neq    , vs       .map(_.toString.asInstanceOf[t]))

  def apply(v : qm): Entity1[A, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v : qm): Entity1[A, t] & CardOne = _exprOneTac(Neq, Nil, true)
}

trait ExprOneTac_1_Integer[A, t, Entity1[_, _], Entity2[_, _, _]] extends ExprOneTac_1[A, t, Entity1, Entity2] {
  def even                       : Entity1[A, t] & CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Entity1[A, t] & CardOne = _exprOneTac(Odd       , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, t] & CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
}


trait ExprOneTacOps_2[A, B, t, Entity1[_, _, _], Entity2[_, _, _, _]] extends ExprAttr_2[A, B, t, Entity1, Entity2] {
  protected def _exprOneTac(op: Op, vs: Seq[t], binding: Boolean = false): Entity1[A, B, t] & CardOne = ???
}

trait ExprOneTac_2[A, B, t, Entity1[_, _, _], Entity2[_, _, _, _]] extends ExprOneTacOps_2[A, B, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, t] & CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, t] & CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, t] & CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, t] & CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, t] & CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, t] & CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, t] & CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, t] & CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, t] & CardOne = _exprOneTac(Ge     , Seq(lower)  )

  def apply(v    : qm): Entity1[A, B, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v    : qm): Entity1[A, B, t] & CardOne = _exprOneTac(Neq, Nil, true)
  def <    (upper: qm): Entity1[A, B, t] & CardOne = _exprOneTac(Lt , Nil, true)
  def <=   (upper: qm): Entity1[A, B, t] & CardOne = _exprOneTac(Le , Nil, true)
  def >    (lower: qm): Entity1[A, B, t] & CardOne = _exprOneTac(Gt , Nil, true)
  def >=   (lower: qm): Entity1[A, B, t] & CardOne = _exprOneTac(Ge , Nil, true)
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, t, t] = _attrMan(Ge , a)
}

trait ExprOneTac_2_String[A, B, t, Entity1[_, _, _], Entity2[_, _, _, _]] extends ExprOneTac_2[A, B, t, Entity1, Entity2] {
  def startsWith(prefix: t): Entity1[A, B, t] & CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t): Entity1[A, B, t] & CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t): Entity1[A, B, t] & CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t): Entity1[A, B, t] & CardOne = _exprOneTac(Matches   , Seq(regex) )

  def startsWith(prefix: qm): Entity1[A, B, t] & CardOne = _exprOneTac(StartsWith, Nil, true)
  def endsWith  (suffix: qm): Entity1[A, B, t] & CardOne = _exprOneTac(EndsWith  , Nil, true)
  def contains  (needle: qm): Entity1[A, B, t] & CardOne = _exprOneTac(Contains  , Nil, true)
  def matches   (regex : qm): Entity1[A, B, t] & CardOne = _exprOneTac(Matches   , Nil, true)
}

trait ExprOneTac_2_Enum[A, B, t, Entity1[_, _, _], Entity2[_, _, _, _]] extends ExprOneTacOps_2[A, B, t, Entity1, Entity2] {
  def apply(             ): Entity1[A, B, t] & CardOne = _exprOneTac(NoValue, Nil                                      )
  def apply(v : t, vs: t*): Entity1[A, B, t] & CardOne = _exprOneTac(Eq     , (v +: vs).map(_.toString.asInstanceOf[t]))
  def apply(vs: Seq[t]   ): Entity1[A, B, t] & CardOne = _exprOneTac(Eq     , vs       .map(_.toString.asInstanceOf[t]))
  def not  (v : t, vs: t*): Entity1[A, B, t] & CardOne = _exprOneTac(Neq    , (v +: vs).map(_.toString.asInstanceOf[t]))
  def not  (vs: Seq[t]   ): Entity1[A, B, t] & CardOne = _exprOneTac(Neq    , vs       .map(_.toString.asInstanceOf[t]))

  def apply(v : qm): Entity1[A, B, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v : qm): Entity1[A, B, t] & CardOne = _exprOneTac(Neq, Nil, true)
}

trait ExprOneTac_2_Integer[A, B, t, Entity1[_, _, _], Entity2[_, _, _, _]] extends ExprOneTac_2[A, B, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, t] & CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Entity1[A, B, t] & CardOne = _exprOneTac(Odd       , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, t] & CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
}


trait ExprOneTacOps_3[A, B, C, t, Entity1[_, _, _, _], Entity2[_, _, _, _, _]] extends ExprAttr_3[A, B, C, t, Entity1, Entity2] {
  protected def _exprOneTac(op: Op, vs: Seq[t], binding: Boolean = false): Entity1[A, B, C, t] & CardOne = ???
}

trait ExprOneTac_3[A, B, C, t, Entity1[_, _, _, _], Entity2[_, _, _, _, _]] extends ExprOneTacOps_3[A, B, C, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, t] & CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, t] & CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, t] & CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, t] & CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, t] & CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, t] & CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, t] & CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, t] & CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, t] & CardOne = _exprOneTac(Ge     , Seq(lower)  )

  def apply(v    : qm): Entity1[A, B, C, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v    : qm): Entity1[A, B, C, t] & CardOne = _exprOneTac(Neq, Nil, true)
  def <    (upper: qm): Entity1[A, B, C, t] & CardOne = _exprOneTac(Lt , Nil, true)
  def <=   (upper: qm): Entity1[A, B, C, t] & CardOne = _exprOneTac(Le , Nil, true)
  def >    (lower: qm): Entity1[A, B, C, t] & CardOne = _exprOneTac(Gt , Nil, true)
  def >=   (lower: qm): Entity1[A, B, C, t] & CardOne = _exprOneTac(Ge , Nil, true)
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, t, t] = _attrMan(Ge , a)
}

trait ExprOneTac_3_String[A, B, C, t, Entity1[_, _, _, _], Entity2[_, _, _, _, _]] extends ExprOneTac_3[A, B, C, t, Entity1, Entity2] {
  def startsWith(prefix: t): Entity1[A, B, C, t] & CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t): Entity1[A, B, C, t] & CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t): Entity1[A, B, C, t] & CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t): Entity1[A, B, C, t] & CardOne = _exprOneTac(Matches   , Seq(regex) )

  def startsWith(prefix: qm): Entity1[A, B, C, t] & CardOne = _exprOneTac(StartsWith, Nil, true)
  def endsWith  (suffix: qm): Entity1[A, B, C, t] & CardOne = _exprOneTac(EndsWith  , Nil, true)
  def contains  (needle: qm): Entity1[A, B, C, t] & CardOne = _exprOneTac(Contains  , Nil, true)
  def matches   (regex : qm): Entity1[A, B, C, t] & CardOne = _exprOneTac(Matches   , Nil, true)
}

trait ExprOneTac_3_Enum[A, B, C, t, Entity1[_, _, _, _], Entity2[_, _, _, _, _]] extends ExprOneTacOps_3[A, B, C, t, Entity1, Entity2] {
  def apply(             ): Entity1[A, B, C, t] & CardOne = _exprOneTac(NoValue, Nil                                      )
  def apply(v : t, vs: t*): Entity1[A, B, C, t] & CardOne = _exprOneTac(Eq     , (v +: vs).map(_.toString.asInstanceOf[t]))
  def apply(vs: Seq[t]   ): Entity1[A, B, C, t] & CardOne = _exprOneTac(Eq     , vs       .map(_.toString.asInstanceOf[t]))
  def not  (v : t, vs: t*): Entity1[A, B, C, t] & CardOne = _exprOneTac(Neq    , (v +: vs).map(_.toString.asInstanceOf[t]))
  def not  (vs: Seq[t]   ): Entity1[A, B, C, t] & CardOne = _exprOneTac(Neq    , vs       .map(_.toString.asInstanceOf[t]))

  def apply(v : qm): Entity1[A, B, C, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v : qm): Entity1[A, B, C, t] & CardOne = _exprOneTac(Neq, Nil, true)
}

trait ExprOneTac_3_Integer[A, B, C, t, Entity1[_, _, _, _], Entity2[_, _, _, _, _]] extends ExprOneTac_3[A, B, C, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, t] & CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Entity1[A, B, C, t] & CardOne = _exprOneTac(Odd       , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, t] & CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
}


trait ExprOneTacOps_4[A, B, C, D, t, Entity1[_, _, _, _, _], Entity2[_, _, _, _, _, _]] extends ExprAttr_4[A, B, C, D, t, Entity1, Entity2] {
  protected def _exprOneTac(op: Op, vs: Seq[t], binding: Boolean = false): Entity1[A, B, C, D, t] & CardOne = ???
}

trait ExprOneTac_4[A, B, C, D, t, Entity1[_, _, _, _, _], Entity2[_, _, _, _, _, _]] extends ExprOneTacOps_4[A, B, C, D, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, t] & CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, t] & CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, t] & CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, t] & CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, t] & CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, t] & CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, t] & CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, t] & CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, t] & CardOne = _exprOneTac(Ge     , Seq(lower)  )

  def apply(v    : qm): Entity1[A, B, C, D, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v    : qm): Entity1[A, B, C, D, t] & CardOne = _exprOneTac(Neq, Nil, true)
  def <    (upper: qm): Entity1[A, B, C, D, t] & CardOne = _exprOneTac(Lt , Nil, true)
  def <=   (upper: qm): Entity1[A, B, C, D, t] & CardOne = _exprOneTac(Le , Nil, true)
  def >    (lower: qm): Entity1[A, B, C, D, t] & CardOne = _exprOneTac(Gt , Nil, true)
  def >=   (lower: qm): Entity1[A, B, C, D, t] & CardOne = _exprOneTac(Ge , Nil, true)
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, t, t] = _attrMan(Ge , a)
}

trait ExprOneTac_4_String[A, B, C, D, t, Entity1[_, _, _, _, _], Entity2[_, _, _, _, _, _]] extends ExprOneTac_4[A, B, C, D, t, Entity1, Entity2] {
  def startsWith(prefix: t): Entity1[A, B, C, D, t] & CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t): Entity1[A, B, C, D, t] & CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t): Entity1[A, B, C, D, t] & CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t): Entity1[A, B, C, D, t] & CardOne = _exprOneTac(Matches   , Seq(regex) )

  def startsWith(prefix: qm): Entity1[A, B, C, D, t] & CardOne = _exprOneTac(StartsWith, Nil, true)
  def endsWith  (suffix: qm): Entity1[A, B, C, D, t] & CardOne = _exprOneTac(EndsWith  , Nil, true)
  def contains  (needle: qm): Entity1[A, B, C, D, t] & CardOne = _exprOneTac(Contains  , Nil, true)
  def matches   (regex : qm): Entity1[A, B, C, D, t] & CardOne = _exprOneTac(Matches   , Nil, true)
}

trait ExprOneTac_4_Enum[A, B, C, D, t, Entity1[_, _, _, _, _], Entity2[_, _, _, _, _, _]] extends ExprOneTacOps_4[A, B, C, D, t, Entity1, Entity2] {
  def apply(             ): Entity1[A, B, C, D, t] & CardOne = _exprOneTac(NoValue, Nil                                      )
  def apply(v : t, vs: t*): Entity1[A, B, C, D, t] & CardOne = _exprOneTac(Eq     , (v +: vs).map(_.toString.asInstanceOf[t]))
  def apply(vs: Seq[t]   ): Entity1[A, B, C, D, t] & CardOne = _exprOneTac(Eq     , vs       .map(_.toString.asInstanceOf[t]))
  def not  (v : t, vs: t*): Entity1[A, B, C, D, t] & CardOne = _exprOneTac(Neq    , (v +: vs).map(_.toString.asInstanceOf[t]))
  def not  (vs: Seq[t]   ): Entity1[A, B, C, D, t] & CardOne = _exprOneTac(Neq    , vs       .map(_.toString.asInstanceOf[t]))

  def apply(v : qm): Entity1[A, B, C, D, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v : qm): Entity1[A, B, C, D, t] & CardOne = _exprOneTac(Neq, Nil, true)
}

trait ExprOneTac_4_Integer[A, B, C, D, t, Entity1[_, _, _, _, _], Entity2[_, _, _, _, _, _]] extends ExprOneTac_4[A, B, C, D, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, t] & CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Entity1[A, B, C, D, t] & CardOne = _exprOneTac(Odd       , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, t] & CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
}


trait ExprOneTacOps_5[A, B, C, D, E, t, Entity1[_, _, _, _, _, _], Entity2[_, _, _, _, _, _, _]] extends ExprAttr_5[A, B, C, D, E, t, Entity1, Entity2] {
  protected def _exprOneTac(op: Op, vs: Seq[t], binding: Boolean = false): Entity1[A, B, C, D, E, t] & CardOne = ???
}

trait ExprOneTac_5[A, B, C, D, E, t, Entity1[_, _, _, _, _, _], Entity2[_, _, _, _, _, _, _]] extends ExprOneTacOps_5[A, B, C, D, E, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, t] & CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, t] & CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, t] & CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, t] & CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, t] & CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, t] & CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, t] & CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, t] & CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, t] & CardOne = _exprOneTac(Ge     , Seq(lower)  )

  def apply(v    : qm): Entity1[A, B, C, D, E, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v    : qm): Entity1[A, B, C, D, E, t] & CardOne = _exprOneTac(Neq, Nil, true)
  def <    (upper: qm): Entity1[A, B, C, D, E, t] & CardOne = _exprOneTac(Lt , Nil, true)
  def <=   (upper: qm): Entity1[A, B, C, D, E, t] & CardOne = _exprOneTac(Le , Nil, true)
  def >    (lower: qm): Entity1[A, B, C, D, E, t] & CardOne = _exprOneTac(Gt , Nil, true)
  def >=   (lower: qm): Entity1[A, B, C, D, E, t] & CardOne = _exprOneTac(Ge , Nil, true)
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, t, t] = _attrMan(Ge , a)
}

trait ExprOneTac_5_String[A, B, C, D, E, t, Entity1[_, _, _, _, _, _], Entity2[_, _, _, _, _, _, _]] extends ExprOneTac_5[A, B, C, D, E, t, Entity1, Entity2] {
  def startsWith(prefix: t): Entity1[A, B, C, D, E, t] & CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t): Entity1[A, B, C, D, E, t] & CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t): Entity1[A, B, C, D, E, t] & CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t): Entity1[A, B, C, D, E, t] & CardOne = _exprOneTac(Matches   , Seq(regex) )

  def startsWith(prefix: qm): Entity1[A, B, C, D, E, t] & CardOne = _exprOneTac(StartsWith, Nil, true)
  def endsWith  (suffix: qm): Entity1[A, B, C, D, E, t] & CardOne = _exprOneTac(EndsWith  , Nil, true)
  def contains  (needle: qm): Entity1[A, B, C, D, E, t] & CardOne = _exprOneTac(Contains  , Nil, true)
  def matches   (regex : qm): Entity1[A, B, C, D, E, t] & CardOne = _exprOneTac(Matches   , Nil, true)
}

trait ExprOneTac_5_Enum[A, B, C, D, E, t, Entity1[_, _, _, _, _, _], Entity2[_, _, _, _, _, _, _]] extends ExprOneTacOps_5[A, B, C, D, E, t, Entity1, Entity2] {
  def apply(             ): Entity1[A, B, C, D, E, t] & CardOne = _exprOneTac(NoValue, Nil                                      )
  def apply(v : t, vs: t*): Entity1[A, B, C, D, E, t] & CardOne = _exprOneTac(Eq     , (v +: vs).map(_.toString.asInstanceOf[t]))
  def apply(vs: Seq[t]   ): Entity1[A, B, C, D, E, t] & CardOne = _exprOneTac(Eq     , vs       .map(_.toString.asInstanceOf[t]))
  def not  (v : t, vs: t*): Entity1[A, B, C, D, E, t] & CardOne = _exprOneTac(Neq    , (v +: vs).map(_.toString.asInstanceOf[t]))
  def not  (vs: Seq[t]   ): Entity1[A, B, C, D, E, t] & CardOne = _exprOneTac(Neq    , vs       .map(_.toString.asInstanceOf[t]))

  def apply(v : qm): Entity1[A, B, C, D, E, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v : qm): Entity1[A, B, C, D, E, t] & CardOne = _exprOneTac(Neq, Nil, true)
}

trait ExprOneTac_5_Integer[A, B, C, D, E, t, Entity1[_, _, _, _, _, _], Entity2[_, _, _, _, _, _, _]] extends ExprOneTac_5[A, B, C, D, E, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, t] & CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, t] & CardOne = _exprOneTac(Odd       , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, t] & CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
}


trait ExprOneTacOps_6[A, B, C, D, E, F, t, Entity1[_, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _]] extends ExprAttr_6[A, B, C, D, E, F, t, Entity1, Entity2] {
  protected def _exprOneTac(op: Op, vs: Seq[t], binding: Boolean = false): Entity1[A, B, C, D, E, F, t] & CardOne = ???
}

trait ExprOneTac_6[A, B, C, D, E, F, t, Entity1[_, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _]] extends ExprOneTacOps_6[A, B, C, D, E, F, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, t] & CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, t] & CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, t] & CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, t] & CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, t] & CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, t] & CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, t] & CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, t] & CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, t] & CardOne = _exprOneTac(Ge     , Seq(lower)  )

  def apply(v    : qm): Entity1[A, B, C, D, E, F, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v    : qm): Entity1[A, B, C, D, E, F, t] & CardOne = _exprOneTac(Neq, Nil, true)
  def <    (upper: qm): Entity1[A, B, C, D, E, F, t] & CardOne = _exprOneTac(Lt , Nil, true)
  def <=   (upper: qm): Entity1[A, B, C, D, E, F, t] & CardOne = _exprOneTac(Le , Nil, true)
  def >    (lower: qm): Entity1[A, B, C, D, E, F, t] & CardOne = _exprOneTac(Gt , Nil, true)
  def >=   (lower: qm): Entity1[A, B, C, D, E, F, t] & CardOne = _exprOneTac(Ge , Nil, true)
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, t, t] = _attrMan(Ge , a)
}

trait ExprOneTac_6_String[A, B, C, D, E, F, t, Entity1[_, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _]] extends ExprOneTac_6[A, B, C, D, E, F, t, Entity1, Entity2] {
  def startsWith(prefix: t): Entity1[A, B, C, D, E, F, t] & CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t): Entity1[A, B, C, D, E, F, t] & CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t): Entity1[A, B, C, D, E, F, t] & CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t): Entity1[A, B, C, D, E, F, t] & CardOne = _exprOneTac(Matches   , Seq(regex) )

  def startsWith(prefix: qm): Entity1[A, B, C, D, E, F, t] & CardOne = _exprOneTac(StartsWith, Nil, true)
  def endsWith  (suffix: qm): Entity1[A, B, C, D, E, F, t] & CardOne = _exprOneTac(EndsWith  , Nil, true)
  def contains  (needle: qm): Entity1[A, B, C, D, E, F, t] & CardOne = _exprOneTac(Contains  , Nil, true)
  def matches   (regex : qm): Entity1[A, B, C, D, E, F, t] & CardOne = _exprOneTac(Matches   , Nil, true)
}

trait ExprOneTac_6_Enum[A, B, C, D, E, F, t, Entity1[_, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _]] extends ExprOneTacOps_6[A, B, C, D, E, F, t, Entity1, Entity2] {
  def apply(             ): Entity1[A, B, C, D, E, F, t] & CardOne = _exprOneTac(NoValue, Nil                                      )
  def apply(v : t, vs: t*): Entity1[A, B, C, D, E, F, t] & CardOne = _exprOneTac(Eq     , (v +: vs).map(_.toString.asInstanceOf[t]))
  def apply(vs: Seq[t]   ): Entity1[A, B, C, D, E, F, t] & CardOne = _exprOneTac(Eq     , vs       .map(_.toString.asInstanceOf[t]))
  def not  (v : t, vs: t*): Entity1[A, B, C, D, E, F, t] & CardOne = _exprOneTac(Neq    , (v +: vs).map(_.toString.asInstanceOf[t]))
  def not  (vs: Seq[t]   ): Entity1[A, B, C, D, E, F, t] & CardOne = _exprOneTac(Neq    , vs       .map(_.toString.asInstanceOf[t]))

  def apply(v : qm): Entity1[A, B, C, D, E, F, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v : qm): Entity1[A, B, C, D, E, F, t] & CardOne = _exprOneTac(Neq, Nil, true)
}

trait ExprOneTac_6_Integer[A, B, C, D, E, F, t, Entity1[_, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _]] extends ExprOneTac_6[A, B, C, D, E, F, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, t] & CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, t] & CardOne = _exprOneTac(Odd       , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, t] & CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
}


trait ExprOneTacOps_7[A, B, C, D, E, F, G, t, Entity1[_, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _]] extends ExprAttr_7[A, B, C, D, E, F, G, t, Entity1, Entity2] {
  protected def _exprOneTac(op: Op, vs: Seq[t], binding: Boolean = false): Entity1[A, B, C, D, E, F, G, t] & CardOne = ???
}

trait ExprOneTac_7[A, B, C, D, E, F, G, t, Entity1[_, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _]] extends ExprOneTacOps_7[A, B, C, D, E, F, G, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, t] & CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, t] & CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, t] & CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, t] & CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, t] & CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, G, t] & CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, G, t] & CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, G, t] & CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, G, t] & CardOne = _exprOneTac(Ge     , Seq(lower)  )

  def apply(v    : qm): Entity1[A, B, C, D, E, F, G, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v    : qm): Entity1[A, B, C, D, E, F, G, t] & CardOne = _exprOneTac(Neq, Nil, true)
  def <    (upper: qm): Entity1[A, B, C, D, E, F, G, t] & CardOne = _exprOneTac(Lt , Nil, true)
  def <=   (upper: qm): Entity1[A, B, C, D, E, F, G, t] & CardOne = _exprOneTac(Le , Nil, true)
  def >    (lower: qm): Entity1[A, B, C, D, E, F, G, t] & CardOne = _exprOneTac(Gt , Nil, true)
  def >=   (lower: qm): Entity1[A, B, C, D, E, F, G, t] & CardOne = _exprOneTac(Ge , Nil, true)
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, t, t] = _attrMan(Ge , a)
}

trait ExprOneTac_7_String[A, B, C, D, E, F, G, t, Entity1[_, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _]] extends ExprOneTac_7[A, B, C, D, E, F, G, t, Entity1, Entity2] {
  def startsWith(prefix: t): Entity1[A, B, C, D, E, F, G, t] & CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t): Entity1[A, B, C, D, E, F, G, t] & CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t): Entity1[A, B, C, D, E, F, G, t] & CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t): Entity1[A, B, C, D, E, F, G, t] & CardOne = _exprOneTac(Matches   , Seq(regex) )

  def startsWith(prefix: qm): Entity1[A, B, C, D, E, F, G, t] & CardOne = _exprOneTac(StartsWith, Nil, true)
  def endsWith  (suffix: qm): Entity1[A, B, C, D, E, F, G, t] & CardOne = _exprOneTac(EndsWith  , Nil, true)
  def contains  (needle: qm): Entity1[A, B, C, D, E, F, G, t] & CardOne = _exprOneTac(Contains  , Nil, true)
  def matches   (regex : qm): Entity1[A, B, C, D, E, F, G, t] & CardOne = _exprOneTac(Matches   , Nil, true)
}

trait ExprOneTac_7_Enum[A, B, C, D, E, F, G, t, Entity1[_, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _]] extends ExprOneTacOps_7[A, B, C, D, E, F, G, t, Entity1, Entity2] {
  def apply(             ): Entity1[A, B, C, D, E, F, G, t] & CardOne = _exprOneTac(NoValue, Nil                                      )
  def apply(v : t, vs: t*): Entity1[A, B, C, D, E, F, G, t] & CardOne = _exprOneTac(Eq     , (v +: vs).map(_.toString.asInstanceOf[t]))
  def apply(vs: Seq[t]   ): Entity1[A, B, C, D, E, F, G, t] & CardOne = _exprOneTac(Eq     , vs       .map(_.toString.asInstanceOf[t]))
  def not  (v : t, vs: t*): Entity1[A, B, C, D, E, F, G, t] & CardOne = _exprOneTac(Neq    , (v +: vs).map(_.toString.asInstanceOf[t]))
  def not  (vs: Seq[t]   ): Entity1[A, B, C, D, E, F, G, t] & CardOne = _exprOneTac(Neq    , vs       .map(_.toString.asInstanceOf[t]))

  def apply(v : qm): Entity1[A, B, C, D, E, F, G, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v : qm): Entity1[A, B, C, D, E, F, G, t] & CardOne = _exprOneTac(Neq, Nil, true)
}

trait ExprOneTac_7_Integer[A, B, C, D, E, F, G, t, Entity1[_, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _]] extends ExprOneTac_7[A, B, C, D, E, F, G, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, G, t] & CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, G, t] & CardOne = _exprOneTac(Odd       , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, G, t] & CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
}


trait ExprOneTacOps_8[A, B, C, D, E, F, G, H, t, Entity1[_, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _]] extends ExprAttr_8[A, B, C, D, E, F, G, H, t, Entity1, Entity2] {
  protected def _exprOneTac(op: Op, vs: Seq[t], binding: Boolean = false): Entity1[A, B, C, D, E, F, G, H, t] & CardOne = ???
}

trait ExprOneTac_8[A, B, C, D, E, F, G, H, t, Entity1[_, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _]] extends ExprOneTacOps_8[A, B, C, D, E, F, G, H, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, t] & CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, t] & CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, t] & CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, t] & CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, t] & CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, G, H, t] & CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, G, H, t] & CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, G, H, t] & CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, G, H, t] & CardOne = _exprOneTac(Ge     , Seq(lower)  )

  def apply(v    : qm): Entity1[A, B, C, D, E, F, G, H, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v    : qm): Entity1[A, B, C, D, E, F, G, H, t] & CardOne = _exprOneTac(Neq, Nil, true)
  def <    (upper: qm): Entity1[A, B, C, D, E, F, G, H, t] & CardOne = _exprOneTac(Lt , Nil, true)
  def <=   (upper: qm): Entity1[A, B, C, D, E, F, G, H, t] & CardOne = _exprOneTac(Le , Nil, true)
  def >    (lower: qm): Entity1[A, B, C, D, E, F, G, H, t] & CardOne = _exprOneTac(Gt , Nil, true)
  def >=   (lower: qm): Entity1[A, B, C, D, E, F, G, H, t] & CardOne = _exprOneTac(Ge , Nil, true)
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, t, t] = _attrMan(Ge , a)
}

trait ExprOneTac_8_String[A, B, C, D, E, F, G, H, t, Entity1[_, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_8[A, B, C, D, E, F, G, H, t, Entity1, Entity2] {
  def startsWith(prefix: t): Entity1[A, B, C, D, E, F, G, H, t] & CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t): Entity1[A, B, C, D, E, F, G, H, t] & CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t): Entity1[A, B, C, D, E, F, G, H, t] & CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t): Entity1[A, B, C, D, E, F, G, H, t] & CardOne = _exprOneTac(Matches   , Seq(regex) )

  def startsWith(prefix: qm): Entity1[A, B, C, D, E, F, G, H, t] & CardOne = _exprOneTac(StartsWith, Nil, true)
  def endsWith  (suffix: qm): Entity1[A, B, C, D, E, F, G, H, t] & CardOne = _exprOneTac(EndsWith  , Nil, true)
  def contains  (needle: qm): Entity1[A, B, C, D, E, F, G, H, t] & CardOne = _exprOneTac(Contains  , Nil, true)
  def matches   (regex : qm): Entity1[A, B, C, D, E, F, G, H, t] & CardOne = _exprOneTac(Matches   , Nil, true)
}

trait ExprOneTac_8_Enum[A, B, C, D, E, F, G, H, t, Entity1[_, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _]] extends ExprOneTacOps_8[A, B, C, D, E, F, G, H, t, Entity1, Entity2] {
  def apply(             ): Entity1[A, B, C, D, E, F, G, H, t] & CardOne = _exprOneTac(NoValue, Nil                                      )
  def apply(v : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, t] & CardOne = _exprOneTac(Eq     , (v +: vs).map(_.toString.asInstanceOf[t]))
  def apply(vs: Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, t] & CardOne = _exprOneTac(Eq     , vs       .map(_.toString.asInstanceOf[t]))
  def not  (v : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, t] & CardOne = _exprOneTac(Neq    , (v +: vs).map(_.toString.asInstanceOf[t]))
  def not  (vs: Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, t] & CardOne = _exprOneTac(Neq    , vs       .map(_.toString.asInstanceOf[t]))

  def apply(v : qm): Entity1[A, B, C, D, E, F, G, H, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v : qm): Entity1[A, B, C, D, E, F, G, H, t] & CardOne = _exprOneTac(Neq, Nil, true)
}

trait ExprOneTac_8_Integer[A, B, C, D, E, F, G, H, t, Entity1[_, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_8[A, B, C, D, E, F, G, H, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, G, H, t] & CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, G, H, t] & CardOne = _exprOneTac(Odd       , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, G, H, t] & CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
}


trait ExprOneTacOps_9[A, B, C, D, E, F, G, H, I, t, Entity1[_, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_9[A, B, C, D, E, F, G, H, I, t, Entity1, Entity2] {
  protected def _exprOneTac(op: Op, vs: Seq[t], binding: Boolean = false): Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = ???
}

trait ExprOneTac_9[A, B, C, D, E, F, G, H, I, t, Entity1[_, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTacOps_9[A, B, C, D, E, F, G, H, I, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = _exprOneTac(Ge     , Seq(lower)  )

  def apply(v    : qm): Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v    : qm): Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = _exprOneTac(Neq, Nil, true)
  def <    (upper: qm): Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = _exprOneTac(Lt , Nil, true)
  def <=   (upper: qm): Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = _exprOneTac(Le , Nil, true)
  def >    (lower: qm): Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = _exprOneTac(Gt , Nil, true)
  def >=   (lower: qm): Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = _exprOneTac(Ge , Nil, true)
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, t, t] = _attrMan(Ge , a)
}

trait ExprOneTac_9_String[A, B, C, D, E, F, G, H, I, t, Entity1[_, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_9[A, B, C, D, E, F, G, H, I, t, Entity1, Entity2] {
  def startsWith(prefix: t): Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t): Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t): Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t): Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = _exprOneTac(Matches   , Seq(regex) )

  def startsWith(prefix: qm): Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = _exprOneTac(StartsWith, Nil, true)
  def endsWith  (suffix: qm): Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = _exprOneTac(EndsWith  , Nil, true)
  def contains  (needle: qm): Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = _exprOneTac(Contains  , Nil, true)
  def matches   (regex : qm): Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = _exprOneTac(Matches   , Nil, true)
}

trait ExprOneTac_9_Enum[A, B, C, D, E, F, G, H, I, t, Entity1[_, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTacOps_9[A, B, C, D, E, F, G, H, I, t, Entity1, Entity2] {
  def apply(             ): Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = _exprOneTac(NoValue, Nil                                      )
  def apply(v : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = _exprOneTac(Eq     , (v +: vs).map(_.toString.asInstanceOf[t]))
  def apply(vs: Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = _exprOneTac(Eq     , vs       .map(_.toString.asInstanceOf[t]))
  def not  (v : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = _exprOneTac(Neq    , (v +: vs).map(_.toString.asInstanceOf[t]))
  def not  (vs: Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = _exprOneTac(Neq    , vs       .map(_.toString.asInstanceOf[t]))

  def apply(v : qm): Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v : qm): Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = _exprOneTac(Neq, Nil, true)
}

trait ExprOneTac_9_Integer[A, B, C, D, E, F, G, H, I, t, Entity1[_, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_9[A, B, C, D, E, F, G, H, I, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = _exprOneTac(Odd       , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, G, H, I, t] & CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
}


trait ExprOneTacOps_10[A, B, C, D, E, F, G, H, I, J, t, Entity1[_, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_10[A, B, C, D, E, F, G, H, I, J, t, Entity1, Entity2] {
  protected def _exprOneTac(op: Op, vs: Seq[t], binding: Boolean = false): Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = ???
}

trait ExprOneTac_10[A, B, C, D, E, F, G, H, I, J, t, Entity1[_, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTacOps_10[A, B, C, D, E, F, G, H, I, J, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = _exprOneTac(Ge     , Seq(lower)  )

  def apply(v    : qm): Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v    : qm): Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = _exprOneTac(Neq, Nil, true)
  def <    (upper: qm): Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = _exprOneTac(Lt , Nil, true)
  def <=   (upper: qm): Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = _exprOneTac(Le , Nil, true)
  def >    (lower: qm): Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = _exprOneTac(Gt , Nil, true)
  def >=   (lower: qm): Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = _exprOneTac(Ge , Nil, true)
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, t, t] = _attrMan(Ge , a)
}

trait ExprOneTac_10_String[A, B, C, D, E, F, G, H, I, J, t, Entity1[_, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_10[A, B, C, D, E, F, G, H, I, J, t, Entity1, Entity2] {
  def startsWith(prefix: t): Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t): Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t): Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t): Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = _exprOneTac(Matches   , Seq(regex) )

  def startsWith(prefix: qm): Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = _exprOneTac(StartsWith, Nil, true)
  def endsWith  (suffix: qm): Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = _exprOneTac(EndsWith  , Nil, true)
  def contains  (needle: qm): Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = _exprOneTac(Contains  , Nil, true)
  def matches   (regex : qm): Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = _exprOneTac(Matches   , Nil, true)
}

trait ExprOneTac_10_Enum[A, B, C, D, E, F, G, H, I, J, t, Entity1[_, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTacOps_10[A, B, C, D, E, F, G, H, I, J, t, Entity1, Entity2] {
  def apply(             ): Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = _exprOneTac(NoValue, Nil                                      )
  def apply(v : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = _exprOneTac(Eq     , (v +: vs).map(_.toString.asInstanceOf[t]))
  def apply(vs: Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = _exprOneTac(Eq     , vs       .map(_.toString.asInstanceOf[t]))
  def not  (v : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = _exprOneTac(Neq    , (v +: vs).map(_.toString.asInstanceOf[t]))
  def not  (vs: Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = _exprOneTac(Neq    , vs       .map(_.toString.asInstanceOf[t]))

  def apply(v : qm): Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v : qm): Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = _exprOneTac(Neq, Nil, true)
}

trait ExprOneTac_10_Integer[A, B, C, D, E, F, G, H, I, J, t, Entity1[_, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_10[A, B, C, D, E, F, G, H, I, J, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = _exprOneTac(Odd       , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, G, H, I, J, t] & CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
}


trait ExprOneTacOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1, Entity2] {
  protected def _exprOneTac(op: Op, vs: Seq[t], binding: Boolean = false): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = ???
}

trait ExprOneTac_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTacOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = _exprOneTac(Ge     , Seq(lower)  )

  def apply(v    : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v    : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = _exprOneTac(Neq, Nil, true)
  def <    (upper: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = _exprOneTac(Lt , Nil, true)
  def <=   (upper: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = _exprOneTac(Le , Nil, true)
  def >    (lower: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = _exprOneTac(Gt , Nil, true)
  def >=   (lower: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = _exprOneTac(Ge , Nil, true)
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, t, t] = _attrMan(Ge , a)
}

trait ExprOneTac_11_String[A, B, C, D, E, F, G, H, I, J, K, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1, Entity2] {
  def startsWith(prefix: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = _exprOneTac(Matches   , Seq(regex) )

  def startsWith(prefix: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = _exprOneTac(StartsWith, Nil, true)
  def endsWith  (suffix: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = _exprOneTac(EndsWith  , Nil, true)
  def contains  (needle: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = _exprOneTac(Contains  , Nil, true)
  def matches   (regex : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = _exprOneTac(Matches   , Nil, true)
}

trait ExprOneTac_11_Enum[A, B, C, D, E, F, G, H, I, J, K, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTacOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1, Entity2] {
  def apply(             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = _exprOneTac(NoValue, Nil                                      )
  def apply(v : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = _exprOneTac(Eq     , (v +: vs).map(_.toString.asInstanceOf[t]))
  def apply(vs: Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = _exprOneTac(Eq     , vs       .map(_.toString.asInstanceOf[t]))
  def not  (v : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = _exprOneTac(Neq    , (v +: vs).map(_.toString.asInstanceOf[t]))
  def not  (vs: Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = _exprOneTac(Neq    , vs       .map(_.toString.asInstanceOf[t]))

  def apply(v : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = _exprOneTac(Neq, Nil, true)
}

trait ExprOneTac_11_Integer[A, B, C, D, E, F, G, H, I, J, K, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = _exprOneTac(Odd       , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] & CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
}


trait ExprOneTacOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1, Entity2] {
  protected def _exprOneTac(op: Op, vs: Seq[t], binding: Boolean = false): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = ???
}

trait ExprOneTac_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTacOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = _exprOneTac(Ge     , Seq(lower)  )

  def apply(v    : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v    : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = _exprOneTac(Neq, Nil, true)
  def <    (upper: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = _exprOneTac(Lt , Nil, true)
  def <=   (upper: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = _exprOneTac(Le , Nil, true)
  def >    (lower: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = _exprOneTac(Gt , Nil, true)
  def >=   (lower: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = _exprOneTac(Ge , Nil, true)
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, t, t] = _attrMan(Ge , a)
}

trait ExprOneTac_12_String[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1, Entity2] {
  def startsWith(prefix: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = _exprOneTac(Matches   , Seq(regex) )

  def startsWith(prefix: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = _exprOneTac(StartsWith, Nil, true)
  def endsWith  (suffix: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = _exprOneTac(EndsWith  , Nil, true)
  def contains  (needle: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = _exprOneTac(Contains  , Nil, true)
  def matches   (regex : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = _exprOneTac(Matches   , Nil, true)
}

trait ExprOneTac_12_Enum[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTacOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1, Entity2] {
  def apply(             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = _exprOneTac(NoValue, Nil                                      )
  def apply(v : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = _exprOneTac(Eq     , (v +: vs).map(_.toString.asInstanceOf[t]))
  def apply(vs: Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = _exprOneTac(Eq     , vs       .map(_.toString.asInstanceOf[t]))
  def not  (v : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = _exprOneTac(Neq    , (v +: vs).map(_.toString.asInstanceOf[t]))
  def not  (vs: Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = _exprOneTac(Neq    , vs       .map(_.toString.asInstanceOf[t]))

  def apply(v : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = _exprOneTac(Neq, Nil, true)
}

trait ExprOneTac_12_Integer[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = _exprOneTac(Odd       , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] & CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
}


trait ExprOneTacOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1, Entity2] {
  protected def _exprOneTac(op: Op, vs: Seq[t], binding: Boolean = false): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = ???
}

trait ExprOneTac_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTacOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = _exprOneTac(Ge     , Seq(lower)  )

  def apply(v    : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v    : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = _exprOneTac(Neq, Nil, true)
  def <    (upper: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = _exprOneTac(Lt , Nil, true)
  def <=   (upper: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = _exprOneTac(Le , Nil, true)
  def >    (lower: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = _exprOneTac(Gt , Nil, true)
  def >=   (lower: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = _exprOneTac(Ge , Nil, true)
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, t, t] = _attrMan(Ge , a)
}

trait ExprOneTac_13_String[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1, Entity2] {
  def startsWith(prefix: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = _exprOneTac(Matches   , Seq(regex) )

  def startsWith(prefix: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = _exprOneTac(StartsWith, Nil, true)
  def endsWith  (suffix: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = _exprOneTac(EndsWith  , Nil, true)
  def contains  (needle: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = _exprOneTac(Contains  , Nil, true)
  def matches   (regex : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = _exprOneTac(Matches   , Nil, true)
}

trait ExprOneTac_13_Enum[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTacOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1, Entity2] {
  def apply(             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = _exprOneTac(NoValue, Nil                                      )
  def apply(v : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = _exprOneTac(Eq     , (v +: vs).map(_.toString.asInstanceOf[t]))
  def apply(vs: Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = _exprOneTac(Eq     , vs       .map(_.toString.asInstanceOf[t]))
  def not  (v : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = _exprOneTac(Neq    , (v +: vs).map(_.toString.asInstanceOf[t]))
  def not  (vs: Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = _exprOneTac(Neq    , vs       .map(_.toString.asInstanceOf[t]))

  def apply(v : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = _exprOneTac(Neq, Nil, true)
}

trait ExprOneTac_13_Integer[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = _exprOneTac(Odd       , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] & CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
}


trait ExprOneTacOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1, Entity2] {
  protected def _exprOneTac(op: Op, vs: Seq[t], binding: Boolean = false): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = ???
}

trait ExprOneTac_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTacOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = _exprOneTac(Ge     , Seq(lower)  )

  def apply(v    : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v    : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = _exprOneTac(Neq, Nil, true)
  def <    (upper: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = _exprOneTac(Lt , Nil, true)
  def <=   (upper: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = _exprOneTac(Le , Nil, true)
  def >    (lower: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = _exprOneTac(Gt , Nil, true)
  def >=   (lower: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = _exprOneTac(Ge , Nil, true)
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, t] = _attrMan(Ge , a)
}

trait ExprOneTac_14_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1, Entity2] {
  def startsWith(prefix: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = _exprOneTac(Matches   , Seq(regex) )

  def startsWith(prefix: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = _exprOneTac(StartsWith, Nil, true)
  def endsWith  (suffix: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = _exprOneTac(EndsWith  , Nil, true)
  def contains  (needle: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = _exprOneTac(Contains  , Nil, true)
  def matches   (regex : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = _exprOneTac(Matches   , Nil, true)
}

trait ExprOneTac_14_Enum[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTacOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1, Entity2] {
  def apply(             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = _exprOneTac(NoValue, Nil                                      )
  def apply(v : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = _exprOneTac(Eq     , (v +: vs).map(_.toString.asInstanceOf[t]))
  def apply(vs: Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = _exprOneTac(Eq     , vs       .map(_.toString.asInstanceOf[t]))
  def not  (v : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = _exprOneTac(Neq    , (v +: vs).map(_.toString.asInstanceOf[t]))
  def not  (vs: Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = _exprOneTac(Neq    , vs       .map(_.toString.asInstanceOf[t]))

  def apply(v : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = _exprOneTac(Neq, Nil, true)
}

trait ExprOneTac_14_Integer[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = _exprOneTac(Odd       , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] & CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
}


trait ExprOneTacOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1, Entity2] {
  protected def _exprOneTac(op: Op, vs: Seq[t], binding: Boolean = false): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = ???
}

trait ExprOneTac_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTacOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = _exprOneTac(Ge     , Seq(lower)  )

  def apply(v    : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v    : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = _exprOneTac(Neq, Nil, true)
  def <    (upper: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = _exprOneTac(Lt , Nil, true)
  def <=   (upper: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = _exprOneTac(Le , Nil, true)
  def >    (lower: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = _exprOneTac(Gt , Nil, true)
  def >=   (lower: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = _exprOneTac(Ge , Nil, true)
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, t] = _attrMan(Ge , a)
}

trait ExprOneTac_15_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1, Entity2] {
  def startsWith(prefix: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = _exprOneTac(Matches   , Seq(regex) )

  def startsWith(prefix: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = _exprOneTac(StartsWith, Nil, true)
  def endsWith  (suffix: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = _exprOneTac(EndsWith  , Nil, true)
  def contains  (needle: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = _exprOneTac(Contains  , Nil, true)
  def matches   (regex : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = _exprOneTac(Matches   , Nil, true)
}

trait ExprOneTac_15_Enum[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTacOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1, Entity2] {
  def apply(             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = _exprOneTac(NoValue, Nil                                      )
  def apply(v : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = _exprOneTac(Eq     , (v +: vs).map(_.toString.asInstanceOf[t]))
  def apply(vs: Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = _exprOneTac(Eq     , vs       .map(_.toString.asInstanceOf[t]))
  def not  (v : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = _exprOneTac(Neq    , (v +: vs).map(_.toString.asInstanceOf[t]))
  def not  (vs: Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = _exprOneTac(Neq    , vs       .map(_.toString.asInstanceOf[t]))

  def apply(v : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = _exprOneTac(Neq, Nil, true)
}

trait ExprOneTac_15_Integer[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = _exprOneTac(Odd       , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] & CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
}


trait ExprOneTacOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1, Entity2] {
  protected def _exprOneTac(op: Op, vs: Seq[t], binding: Boolean = false): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = ???
}

trait ExprOneTac_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTacOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = _exprOneTac(Ge     , Seq(lower)  )

  def apply(v    : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v    : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = _exprOneTac(Neq, Nil, true)
  def <    (upper: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = _exprOneTac(Lt , Nil, true)
  def <=   (upper: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = _exprOneTac(Le , Nil, true)
  def >    (lower: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = _exprOneTac(Gt , Nil, true)
  def >=   (lower: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = _exprOneTac(Ge , Nil, true)
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, t] = _attrMan(Ge , a)
}

trait ExprOneTac_16_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1, Entity2] {
  def startsWith(prefix: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = _exprOneTac(Matches   , Seq(regex) )

  def startsWith(prefix: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = _exprOneTac(StartsWith, Nil, true)
  def endsWith  (suffix: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = _exprOneTac(EndsWith  , Nil, true)
  def contains  (needle: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = _exprOneTac(Contains  , Nil, true)
  def matches   (regex : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = _exprOneTac(Matches   , Nil, true)
}

trait ExprOneTac_16_Enum[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTacOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1, Entity2] {
  def apply(             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = _exprOneTac(NoValue, Nil                                      )
  def apply(v : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = _exprOneTac(Eq     , (v +: vs).map(_.toString.asInstanceOf[t]))
  def apply(vs: Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = _exprOneTac(Eq     , vs       .map(_.toString.asInstanceOf[t]))
  def not  (v : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = _exprOneTac(Neq    , (v +: vs).map(_.toString.asInstanceOf[t]))
  def not  (vs: Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = _exprOneTac(Neq    , vs       .map(_.toString.asInstanceOf[t]))

  def apply(v : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = _exprOneTac(Neq, Nil, true)
}

trait ExprOneTac_16_Integer[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = _exprOneTac(Odd       , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] & CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
}


trait ExprOneTacOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1, Entity2] {
  protected def _exprOneTac(op: Op, vs: Seq[t], binding: Boolean = false): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = ???
}

trait ExprOneTac_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTacOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = _exprOneTac(Ge     , Seq(lower)  )

  def apply(v    : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v    : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = _exprOneTac(Neq, Nil, true)
  def <    (upper: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = _exprOneTac(Lt , Nil, true)
  def <=   (upper: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = _exprOneTac(Le , Nil, true)
  def >    (lower: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = _exprOneTac(Gt , Nil, true)
  def >=   (lower: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = _exprOneTac(Ge , Nil, true)
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, t] = _attrMan(Ge , a)
}

trait ExprOneTac_17_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1, Entity2] {
  def startsWith(prefix: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = _exprOneTac(Matches   , Seq(regex) )

  def startsWith(prefix: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = _exprOneTac(StartsWith, Nil, true)
  def endsWith  (suffix: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = _exprOneTac(EndsWith  , Nil, true)
  def contains  (needle: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = _exprOneTac(Contains  , Nil, true)
  def matches   (regex : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = _exprOneTac(Matches   , Nil, true)
}

trait ExprOneTac_17_Enum[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTacOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1, Entity2] {
  def apply(             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = _exprOneTac(NoValue, Nil                                      )
  def apply(v : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = _exprOneTac(Eq     , (v +: vs).map(_.toString.asInstanceOf[t]))
  def apply(vs: Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = _exprOneTac(Eq     , vs       .map(_.toString.asInstanceOf[t]))
  def not  (v : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = _exprOneTac(Neq    , (v +: vs).map(_.toString.asInstanceOf[t]))
  def not  (vs: Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = _exprOneTac(Neq    , vs       .map(_.toString.asInstanceOf[t]))

  def apply(v : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = _exprOneTac(Neq, Nil, true)
}

trait ExprOneTac_17_Integer[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = _exprOneTac(Odd       , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] & CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
}


trait ExprOneTacOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1, Entity2] {
  protected def _exprOneTac(op: Op, vs: Seq[t], binding: Boolean = false): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = ???
}

trait ExprOneTac_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTacOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = _exprOneTac(Ge     , Seq(lower)  )

  def apply(v    : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v    : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = _exprOneTac(Neq, Nil, true)
  def <    (upper: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = _exprOneTac(Lt , Nil, true)
  def <=   (upper: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = _exprOneTac(Le , Nil, true)
  def >    (lower: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = _exprOneTac(Gt , Nil, true)
  def >=   (lower: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = _exprOneTac(Ge , Nil, true)
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, t] = _attrMan(Ge , a)
}

trait ExprOneTac_18_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1, Entity2] {
  def startsWith(prefix: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = _exprOneTac(Matches   , Seq(regex) )

  def startsWith(prefix: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = _exprOneTac(StartsWith, Nil, true)
  def endsWith  (suffix: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = _exprOneTac(EndsWith  , Nil, true)
  def contains  (needle: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = _exprOneTac(Contains  , Nil, true)
  def matches   (regex : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = _exprOneTac(Matches   , Nil, true)
}

trait ExprOneTac_18_Enum[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTacOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1, Entity2] {
  def apply(             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = _exprOneTac(NoValue, Nil                                      )
  def apply(v : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = _exprOneTac(Eq     , (v +: vs).map(_.toString.asInstanceOf[t]))
  def apply(vs: Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = _exprOneTac(Eq     , vs       .map(_.toString.asInstanceOf[t]))
  def not  (v : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = _exprOneTac(Neq    , (v +: vs).map(_.toString.asInstanceOf[t]))
  def not  (vs: Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = _exprOneTac(Neq    , vs       .map(_.toString.asInstanceOf[t]))

  def apply(v : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = _exprOneTac(Neq, Nil, true)
}

trait ExprOneTac_18_Integer[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = _exprOneTac(Odd       , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] & CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
}


trait ExprOneTacOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1, Entity2] {
  protected def _exprOneTac(op: Op, vs: Seq[t], binding: Boolean = false): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = ???
}

trait ExprOneTac_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTacOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = _exprOneTac(Ge     , Seq(lower)  )

  def apply(v    : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v    : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = _exprOneTac(Neq, Nil, true)
  def <    (upper: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = _exprOneTac(Lt , Nil, true)
  def <=   (upper: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = _exprOneTac(Le , Nil, true)
  def >    (lower: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = _exprOneTac(Gt , Nil, true)
  def >=   (lower: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = _exprOneTac(Ge , Nil, true)
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, t] = _attrMan(Ge , a)
}

trait ExprOneTac_19_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1, Entity2] {
  def startsWith(prefix: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = _exprOneTac(Matches   , Seq(regex) )

  def startsWith(prefix: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = _exprOneTac(StartsWith, Nil, true)
  def endsWith  (suffix: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = _exprOneTac(EndsWith  , Nil, true)
  def contains  (needle: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = _exprOneTac(Contains  , Nil, true)
  def matches   (regex : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = _exprOneTac(Matches   , Nil, true)
}

trait ExprOneTac_19_Enum[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTacOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1, Entity2] {
  def apply(             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = _exprOneTac(NoValue, Nil                                      )
  def apply(v : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = _exprOneTac(Eq     , (v +: vs).map(_.toString.asInstanceOf[t]))
  def apply(vs: Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = _exprOneTac(Eq     , vs       .map(_.toString.asInstanceOf[t]))
  def not  (v : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = _exprOneTac(Neq    , (v +: vs).map(_.toString.asInstanceOf[t]))
  def not  (vs: Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = _exprOneTac(Neq    , vs       .map(_.toString.asInstanceOf[t]))

  def apply(v : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = _exprOneTac(Neq, Nil, true)
}

trait ExprOneTac_19_Integer[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = _exprOneTac(Odd       , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] & CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
}


trait ExprOneTacOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1, Entity2] {
  protected def _exprOneTac(op: Op, vs: Seq[t], binding: Boolean = false): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = ???
}

trait ExprOneTac_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTacOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = _exprOneTac(Ge     , Seq(lower)  )

  def apply(v    : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v    : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = _exprOneTac(Neq, Nil, true)
  def <    (upper: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = _exprOneTac(Lt , Nil, true)
  def <=   (upper: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = _exprOneTac(Le , Nil, true)
  def >    (lower: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = _exprOneTac(Gt , Nil, true)
  def >=   (lower: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = _exprOneTac(Ge , Nil, true)
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, t] = _attrMan(Ge , a)
}

trait ExprOneTac_20_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1, Entity2] {
  def startsWith(prefix: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = _exprOneTac(Matches   , Seq(regex) )

  def startsWith(prefix: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = _exprOneTac(StartsWith, Nil, true)
  def endsWith  (suffix: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = _exprOneTac(EndsWith  , Nil, true)
  def contains  (needle: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = _exprOneTac(Contains  , Nil, true)
  def matches   (regex : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = _exprOneTac(Matches   , Nil, true)
}

trait ExprOneTac_20_Enum[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTacOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1, Entity2] {
  def apply(             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = _exprOneTac(NoValue, Nil                                      )
  def apply(v : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = _exprOneTac(Eq     , (v +: vs).map(_.toString.asInstanceOf[t]))
  def apply(vs: Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = _exprOneTac(Eq     , vs       .map(_.toString.asInstanceOf[t]))
  def not  (v : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = _exprOneTac(Neq    , (v +: vs).map(_.toString.asInstanceOf[t]))
  def not  (vs: Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = _exprOneTac(Neq    , vs       .map(_.toString.asInstanceOf[t]))

  def apply(v : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = _exprOneTac(Neq, Nil, true)
}

trait ExprOneTac_20_Integer[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = _exprOneTac(Odd       , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] & CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
}


trait ExprOneTacOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1, Entity2] {
  protected def _exprOneTac(op: Op, vs: Seq[t], binding: Boolean = false): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = ???
}

trait ExprOneTac_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTacOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = _exprOneTac(Ge     , Seq(lower)  )

  def apply(v    : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v    : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = _exprOneTac(Neq, Nil, true)
  def <    (upper: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = _exprOneTac(Lt , Nil, true)
  def <=   (upper: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = _exprOneTac(Le , Nil, true)
  def >    (lower: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = _exprOneTac(Gt , Nil, true)
  def >=   (lower: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = _exprOneTac(Ge , Nil, true)
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Eq , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Neq, a)
  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Lt , a)
  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Le , a)
  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Gt , a)
  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Ge , a)

  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, t] = _attrMan(Eq , a)
  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, t] = _attrMan(Neq, a)
  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, t] = _attrMan(Lt , a)
  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, t] = _attrMan(Le , a)
  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, t] = _attrMan(Gt , a)
  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, t] = _attrMan(Ge , a)
}

trait ExprOneTac_21_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1, Entity2] {
  def startsWith(prefix: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = _exprOneTac(Matches   , Seq(regex) )

  def startsWith(prefix: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = _exprOneTac(StartsWith, Nil, true)
  def endsWith  (suffix: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = _exprOneTac(EndsWith  , Nil, true)
  def contains  (needle: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = _exprOneTac(Contains  , Nil, true)
  def matches   (regex : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = _exprOneTac(Matches   , Nil, true)
}

trait ExprOneTac_21_Enum[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTacOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1, Entity2] {
  def apply(             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = _exprOneTac(NoValue, Nil                                      )
  def apply(v : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = _exprOneTac(Eq     , (v +: vs).map(_.toString.asInstanceOf[t]))
  def apply(vs: Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = _exprOneTac(Eq     , vs       .map(_.toString.asInstanceOf[t]))
  def not  (v : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = _exprOneTac(Neq    , (v +: vs).map(_.toString.asInstanceOf[t]))
  def not  (vs: Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = _exprOneTac(Neq    , vs       .map(_.toString.asInstanceOf[t]))

  def apply(v : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = _exprOneTac(Neq, Nil, true)
}

trait ExprOneTac_21_Integer[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = _exprOneTac(Odd       , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] & CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
}


trait ExprOneTacOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1, Entity2] {
  protected def _exprOneTac(op: Op, vs: Seq[t], binding: Boolean = false): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = ???
}

trait ExprOneTac_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTacOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1, Entity2] {
  def apply(                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = _exprOneTac(NoValue, Nil         )
  def apply(v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = _exprOneTac(Eq     , vs          )
  def not  (v    : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = _exprOneTac(Neq    , vs          )
  def <    (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: t        ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = _exprOneTac(Ge     , Seq(lower)  )

  def apply(v    : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v    : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = _exprOneTac(Neq, Nil, true)
  def <    (upper: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = _exprOneTac(Lt , Nil, true)
  def <=   (upper: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = _exprOneTac(Le , Nil, true)
  def >    (lower: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = _exprOneTac(Gt , Nil, true)
  def >=   (lower: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = _exprOneTac(Ge , Nil, true)
  
  def apply[ns1[_]](a: ModelOps_0[t, ns1, X2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Eq , a)
  def not  [ns1[_]](a: ModelOps_0[t, ns1, X2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Neq, a)
  def <    [ns1[_]](a: ModelOps_0[t, ns1, X2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Lt , a)
  def <=   [ns1[_]](a: ModelOps_0[t, ns1, X2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Le , a)
  def >    [ns1[_]](a: ModelOps_0[t, ns1, X2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Gt , a)
  def >=   [ns1[_]](a: ModelOps_0[t, ns1, X2] & CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Ge , a)
}

trait ExprOneTac_22_String[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1, Entity2] {
  def startsWith(prefix: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = _exprOneTac(Matches   , Seq(regex) )

  def startsWith(prefix: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = _exprOneTac(StartsWith, Nil, true)
  def endsWith  (suffix: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = _exprOneTac(EndsWith  , Nil, true)
  def contains  (needle: qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = _exprOneTac(Contains  , Nil, true)
  def matches   (regex : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = _exprOneTac(Matches   , Nil, true)
}

trait ExprOneTac_22_Enum[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTacOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1, Entity2] {
  def apply(             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = _exprOneTac(NoValue, Nil                                      )
  def apply(v : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = _exprOneTac(Eq     , (v +: vs).map(_.toString.asInstanceOf[t]))
  def apply(vs: Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = _exprOneTac(Eq     , vs       .map(_.toString.asInstanceOf[t]))
  def not  (v : t, vs: t*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = _exprOneTac(Neq    , (v +: vs).map(_.toString.asInstanceOf[t]))
  def not  (vs: Seq[t]   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = _exprOneTac(Neq    , vs       .map(_.toString.asInstanceOf[t]))

  def apply(v : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = _exprOneTac(Eq , Nil, true)
  def not  (v : qm): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = _exprOneTac(Neq, Nil, true)
}

trait ExprOneTac_22_Integer[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprOneTac_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1, Entity2] {
  def even                       : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = _exprOneTac(Even      , Nil                    )
  def odd                        : Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = _exprOneTac(Odd       , Nil                    )
  def %(divider: t, remainder: t): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] & CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
}
