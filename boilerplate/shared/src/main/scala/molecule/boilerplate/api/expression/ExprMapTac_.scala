// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.boilerplate.ast.DataModel._
import scala.language.higherKinds


trait ExprMapTacOps_0[t, Ns1[_], Ns2[_, _]] extends ExprBase {
  protected def _exprMap (op: Op, map : Map[String, t]): Ns1[t] = ???
  protected def _exprMapK(op: Op, keys: Seq[String]   ): Ns1[t] = ???
  protected def _exprMapV(op: Op, vs  : Seq[t]        ): Ns1[t] = ???
}

trait ExprMapTac_0[t, Ns1[_], Ns2[_, _]]
  extends ExprMapTacOps_0[t, Ns1, Ns2] {
  def apply(                           ): Ns1[t] = _exprMap (NoValue, Map.empty[String, t])
  def apply(key : String, keys: String*): Ns1[t] = _exprMapK(Eq     , Seq(key) ++ keys    )
  def apply(keys: Seq[String]          ): Ns1[t] = _exprMapK(Eq     , keys                )
  def not  (key : String, keys: String*): Ns1[t] = _exprMapK(Neq    , Seq(key) ++ keys    )
  def not  (keys: Seq[String]          ): Ns1[t] = _exprMapK(Neq    , keys                )
  def has  (v : t, vs: t*              ): Ns1[t] = _exprMapV(Has    , Seq(v) ++ vs        )
  def has  (vs: Seq[t]                 ): Ns1[t] = _exprMapV(Has    , vs                  )
  def hasNo(v : t, vs: t*              ): Ns1[t] = _exprMapV(HasNo  , Seq(v) ++ vs        )
  def hasNo(vs: Seq[t]                 ): Ns1[t] = _exprMapV(HasNo  , vs                  )
}


trait ExprMapTacOps_1[A, t, Ns1[_, _], Ns2[_, _, _]] extends ExprBase {
  protected def _exprMap (op: Op, map : Map[String, t]): Ns1[A, t] = ???
  protected def _exprMapK(op: Op, keys: Seq[String]   ): Ns1[t, t] = ???
  protected def _exprMapV(op: Op, vs  : Seq[t]        ): Ns1[A, t] = ???
}

trait ExprMapTac_1[A, t, Ns1[_, _], Ns2[_, _, _]]
  extends ExprMapTacOps_1[A, t, Ns1, Ns2] {
  def apply(                           ): Ns1[A, t] = _exprMap (NoValue, Map.empty[String, t])
  def apply(key : String, keys: String*): Ns1[t, t] = _exprMapK(Eq     , Seq(key) ++ keys    )
  def apply(keys: Seq[String]          ): Ns1[t, t] = _exprMapK(Eq     , keys                )
  def not  (key : String, keys: String*): Ns1[t, t] = _exprMapK(Neq    , Seq(key) ++ keys    )
  def not  (keys: Seq[String]          ): Ns1[t, t] = _exprMapK(Neq    , keys                )
  def has  (v : t, vs: t*              ): Ns1[A, t] = _exprMapV(Has    , Seq(v) ++ vs        )
  def has  (vs: Seq[t]                 ): Ns1[A, t] = _exprMapV(Has    , vs                  )
  def hasNo(v : t, vs: t*              ): Ns1[A, t] = _exprMapV(HasNo  , Seq(v) ++ vs        )
  def hasNo(vs: Seq[t]                 ): Ns1[A, t] = _exprMapV(HasNo  , vs                  )
}


trait ExprMapTacOps_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]] extends ExprBase {
  protected def _exprMap (op: Op, map : Map[String, t]): Ns1[A, B, t] = ???
  protected def _exprMapK(op: Op, keys: Seq[String]   ): Ns1[A, t, t] = ???
  protected def _exprMapV(op: Op, vs  : Seq[t]        ): Ns1[A, B, t] = ???
}

trait ExprMapTac_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]]
  extends ExprMapTacOps_2[A, B, t, Ns1, Ns2] {
  def apply(                           ): Ns1[A, B, t] = _exprMap (NoValue, Map.empty[String, t])
  def apply(key : String, keys: String*): Ns1[A, t, t] = _exprMapK(Eq     , Seq(key) ++ keys    )
  def apply(keys: Seq[String]          ): Ns1[A, t, t] = _exprMapK(Eq     , keys                )
  def not  (key : String, keys: String*): Ns1[A, t, t] = _exprMapK(Neq    , Seq(key) ++ keys    )
  def not  (keys: Seq[String]          ): Ns1[A, t, t] = _exprMapK(Neq    , keys                )
  def has  (v : t, vs: t*              ): Ns1[A, B, t] = _exprMapV(Has    , Seq(v) ++ vs        )
  def has  (vs: Seq[t]                 ): Ns1[A, B, t] = _exprMapV(Has    , vs                  )
  def hasNo(v : t, vs: t*              ): Ns1[A, B, t] = _exprMapV(HasNo  , Seq(v) ++ vs        )
  def hasNo(vs: Seq[t]                 ): Ns1[A, B, t] = _exprMapV(HasNo  , vs                  )
}


trait ExprMapTacOps_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]] extends ExprBase {
  protected def _exprMap (op: Op, map : Map[String, t]): Ns1[A, B, C, t] = ???
  protected def _exprMapK(op: Op, keys: Seq[String]   ): Ns1[A, B, t, t] = ???
  protected def _exprMapV(op: Op, vs  : Seq[t]        ): Ns1[A, B, C, t] = ???
}

trait ExprMapTac_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]]
  extends ExprMapTacOps_3[A, B, C, t, Ns1, Ns2] {
  def apply(                           ): Ns1[A, B, C, t] = _exprMap (NoValue, Map.empty[String, t])
  def apply(key : String, keys: String*): Ns1[A, B, t, t] = _exprMapK(Eq     , Seq(key) ++ keys    )
  def apply(keys: Seq[String]          ): Ns1[A, B, t, t] = _exprMapK(Eq     , keys                )
  def not  (key : String, keys: String*): Ns1[A, B, t, t] = _exprMapK(Neq    , Seq(key) ++ keys    )
  def not  (keys: Seq[String]          ): Ns1[A, B, t, t] = _exprMapK(Neq    , keys                )
  def has  (v : t, vs: t*              ): Ns1[A, B, C, t] = _exprMapV(Has    , Seq(v) ++ vs        )
  def has  (vs: Seq[t]                 ): Ns1[A, B, C, t] = _exprMapV(Has    , vs                  )
  def hasNo(v : t, vs: t*              ): Ns1[A, B, C, t] = _exprMapV(HasNo  , Seq(v) ++ vs        )
  def hasNo(vs: Seq[t]                 ): Ns1[A, B, C, t] = _exprMapV(HasNo  , vs                  )
}


trait ExprMapTacOps_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]] extends ExprBase {
  protected def _exprMap (op: Op, map : Map[String, t]): Ns1[A, B, C, D, t] = ???
  protected def _exprMapK(op: Op, keys: Seq[String]   ): Ns1[A, B, C, t, t] = ???
  protected def _exprMapV(op: Op, vs  : Seq[t]        ): Ns1[A, B, C, D, t] = ???
}

trait ExprMapTac_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]]
  extends ExprMapTacOps_4[A, B, C, D, t, Ns1, Ns2] {
  def apply(                           ): Ns1[A, B, C, D, t] = _exprMap (NoValue, Map.empty[String, t])
  def apply(key : String, keys: String*): Ns1[A, B, C, t, t] = _exprMapK(Eq     , Seq(key) ++ keys    )
  def apply(keys: Seq[String]          ): Ns1[A, B, C, t, t] = _exprMapK(Eq     , keys                )
  def not  (key : String, keys: String*): Ns1[A, B, C, t, t] = _exprMapK(Neq    , Seq(key) ++ keys    )
  def not  (keys: Seq[String]          ): Ns1[A, B, C, t, t] = _exprMapK(Neq    , keys                )
  def has  (v : t, vs: t*              ): Ns1[A, B, C, D, t] = _exprMapV(Has    , Seq(v) ++ vs        )
  def has  (vs: Seq[t]                 ): Ns1[A, B, C, D, t] = _exprMapV(Has    , vs                  )
  def hasNo(v : t, vs: t*              ): Ns1[A, B, C, D, t] = _exprMapV(HasNo  , Seq(v) ++ vs        )
  def hasNo(vs: Seq[t]                 ): Ns1[A, B, C, D, t] = _exprMapV(HasNo  , vs                  )
}


trait ExprMapTacOps_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMap (op: Op, map : Map[String, t]): Ns1[A, B, C, D, E, t] = ???
  protected def _exprMapK(op: Op, keys: Seq[String]   ): Ns1[A, B, C, D, t, t] = ???
  protected def _exprMapV(op: Op, vs  : Seq[t]        ): Ns1[A, B, C, D, E, t] = ???
}

trait ExprMapTac_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]]
  extends ExprMapTacOps_5[A, B, C, D, E, t, Ns1, Ns2] {
  def apply(                           ): Ns1[A, B, C, D, E, t] = _exprMap (NoValue, Map.empty[String, t])
  def apply(key : String, keys: String*): Ns1[A, B, C, D, t, t] = _exprMapK(Eq     , Seq(key) ++ keys    )
  def apply(keys: Seq[String]          ): Ns1[A, B, C, D, t, t] = _exprMapK(Eq     , keys                )
  def not  (key : String, keys: String*): Ns1[A, B, C, D, t, t] = _exprMapK(Neq    , Seq(key) ++ keys    )
  def not  (keys: Seq[String]          ): Ns1[A, B, C, D, t, t] = _exprMapK(Neq    , keys                )
  def has  (v : t, vs: t*              ): Ns1[A, B, C, D, E, t] = _exprMapV(Has    , Seq(v) ++ vs        )
  def has  (vs: Seq[t]                 ): Ns1[A, B, C, D, E, t] = _exprMapV(Has    , vs                  )
  def hasNo(v : t, vs: t*              ): Ns1[A, B, C, D, E, t] = _exprMapV(HasNo  , Seq(v) ++ vs        )
  def hasNo(vs: Seq[t]                 ): Ns1[A, B, C, D, E, t] = _exprMapV(HasNo  , vs                  )
}


trait ExprMapTacOps_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMap (op: Op, map : Map[String, t]): Ns1[A, B, C, D, E, F, t] = ???
  protected def _exprMapK(op: Op, keys: Seq[String]   ): Ns1[A, B, C, D, E, t, t] = ???
  protected def _exprMapV(op: Op, vs  : Seq[t]        ): Ns1[A, B, C, D, E, F, t] = ???
}

trait ExprMapTac_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_6[A, B, C, D, E, F, t, Ns1, Ns2] {
  def apply(                           ): Ns1[A, B, C, D, E, F, t] = _exprMap (NoValue, Map.empty[String, t])
  def apply(key : String, keys: String*): Ns1[A, B, C, D, E, t, t] = _exprMapK(Eq     , Seq(key) ++ keys    )
  def apply(keys: Seq[String]          ): Ns1[A, B, C, D, E, t, t] = _exprMapK(Eq     , keys                )
  def not  (key : String, keys: String*): Ns1[A, B, C, D, E, t, t] = _exprMapK(Neq    , Seq(key) ++ keys    )
  def not  (keys: Seq[String]          ): Ns1[A, B, C, D, E, t, t] = _exprMapK(Neq    , keys                )
  def has  (v : t, vs: t*              ): Ns1[A, B, C, D, E, F, t] = _exprMapV(Has    , Seq(v) ++ vs        )
  def has  (vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, t] = _exprMapV(Has    , vs                  )
  def hasNo(v : t, vs: t*              ): Ns1[A, B, C, D, E, F, t] = _exprMapV(HasNo  , Seq(v) ++ vs        )
  def hasNo(vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, t] = _exprMapV(HasNo  , vs                  )
}


trait ExprMapTacOps_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMap (op: Op, map : Map[String, t]): Ns1[A, B, C, D, E, F, G, t] = ???
  protected def _exprMapK(op: Op, keys: Seq[String]   ): Ns1[A, B, C, D, E, F, t, t] = ???
  protected def _exprMapV(op: Op, vs  : Seq[t]        ): Ns1[A, B, C, D, E, F, G, t] = ???
}

trait ExprMapTac_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_7[A, B, C, D, E, F, G, t, Ns1, Ns2] {
  def apply(                           ): Ns1[A, B, C, D, E, F, G, t] = _exprMap (NoValue, Map.empty[String, t])
  def apply(key : String, keys: String*): Ns1[A, B, C, D, E, F, t, t] = _exprMapK(Eq     , Seq(key) ++ keys    )
  def apply(keys: Seq[String]          ): Ns1[A, B, C, D, E, F, t, t] = _exprMapK(Eq     , keys                )
  def not  (key : String, keys: String*): Ns1[A, B, C, D, E, F, t, t] = _exprMapK(Neq    , Seq(key) ++ keys    )
  def not  (keys: Seq[String]          ): Ns1[A, B, C, D, E, F, t, t] = _exprMapK(Neq    , keys                )
  def has  (v : t, vs: t*              ): Ns1[A, B, C, D, E, F, G, t] = _exprMapV(Has    , Seq(v) ++ vs        )
  def has  (vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, G, t] = _exprMapV(Has    , vs                  )
  def hasNo(v : t, vs: t*              ): Ns1[A, B, C, D, E, F, G, t] = _exprMapV(HasNo  , Seq(v) ++ vs        )
  def hasNo(vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, G, t] = _exprMapV(HasNo  , vs                  )
}


trait ExprMapTacOps_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMap (op: Op, map : Map[String, t]): Ns1[A, B, C, D, E, F, G, H, t] = ???
  protected def _exprMapK(op: Op, keys: Seq[String]   ): Ns1[A, B, C, D, E, F, G, t, t] = ???
  protected def _exprMapV(op: Op, vs  : Seq[t]        ): Ns1[A, B, C, D, E, F, G, H, t] = ???
}

trait ExprMapTac_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2] {
  def apply(                           ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMap (NoValue, Map.empty[String, t])
  def apply(key : String, keys: String*): Ns1[A, B, C, D, E, F, G, t, t] = _exprMapK(Eq     , Seq(key) ++ keys    )
  def apply(keys: Seq[String]          ): Ns1[A, B, C, D, E, F, G, t, t] = _exprMapK(Eq     , keys                )
  def not  (key : String, keys: String*): Ns1[A, B, C, D, E, F, G, t, t] = _exprMapK(Neq    , Seq(key) ++ keys    )
  def not  (keys: Seq[String]          ): Ns1[A, B, C, D, E, F, G, t, t] = _exprMapK(Neq    , keys                )
  def has  (v : t, vs: t*              ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapV(Has    , Seq(v) ++ vs        )
  def has  (vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapV(Has    , vs                  )
  def hasNo(v : t, vs: t*              ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapV(HasNo  , Seq(v) ++ vs        )
  def hasNo(vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapV(HasNo  , vs                  )
}


trait ExprMapTacOps_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMap (op: Op, map : Map[String, t]): Ns1[A, B, C, D, E, F, G, H, I, t] = ???
  protected def _exprMapK(op: Op, keys: Seq[String]   ): Ns1[A, B, C, D, E, F, G, H, t, t] = ???
  protected def _exprMapV(op: Op, vs  : Seq[t]        ): Ns1[A, B, C, D, E, F, G, H, I, t] = ???
}

trait ExprMapTac_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2] {
  def apply(                           ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMap (NoValue, Map.empty[String, t])
  def apply(key : String, keys: String*): Ns1[A, B, C, D, E, F, G, H, t, t] = _exprMapK(Eq     , Seq(key) ++ keys    )
  def apply(keys: Seq[String]          ): Ns1[A, B, C, D, E, F, G, H, t, t] = _exprMapK(Eq     , keys                )
  def not  (key : String, keys: String*): Ns1[A, B, C, D, E, F, G, H, t, t] = _exprMapK(Neq    , Seq(key) ++ keys    )
  def not  (keys: Seq[String]          ): Ns1[A, B, C, D, E, F, G, H, t, t] = _exprMapK(Neq    , keys                )
  def has  (v : t, vs: t*              ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapV(Has    , Seq(v) ++ vs        )
  def has  (vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapV(Has    , vs                  )
  def hasNo(v : t, vs: t*              ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapV(HasNo  , Seq(v) ++ vs        )
  def hasNo(vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapV(HasNo  , vs                  )
}


trait ExprMapTacOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMap (op: Op, map : Map[String, t]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = ???
  protected def _exprMapK(op: Op, keys: Seq[String]   ): Ns1[A, B, C, D, E, F, G, H, I, t, t] = ???
  protected def _exprMapV(op: Op, vs  : Seq[t]        ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = ???
}

trait ExprMapTac_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2] {
  def apply(                           ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMap (NoValue, Map.empty[String, t])
  def apply(key : String, keys: String*): Ns1[A, B, C, D, E, F, G, H, I, t, t] = _exprMapK(Eq     , Seq(key) ++ keys    )
  def apply(keys: Seq[String]          ): Ns1[A, B, C, D, E, F, G, H, I, t, t] = _exprMapK(Eq     , keys                )
  def not  (key : String, keys: String*): Ns1[A, B, C, D, E, F, G, H, I, t, t] = _exprMapK(Neq    , Seq(key) ++ keys    )
  def not  (keys: Seq[String]          ): Ns1[A, B, C, D, E, F, G, H, I, t, t] = _exprMapK(Neq    , keys                )
  def has  (v : t, vs: t*              ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapV(Has    , Seq(v) ++ vs        )
  def has  (vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapV(Has    , vs                  )
  def hasNo(v : t, vs: t*              ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapV(HasNo  , Seq(v) ++ vs        )
  def hasNo(vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapV(HasNo  , vs                  )
}


trait ExprMapTacOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMap (op: Op, map : Map[String, t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = ???
  protected def _exprMapK(op: Op, keys: Seq[String]   ): Ns1[A, B, C, D, E, F, G, H, I, J, t, t] = ???
  protected def _exprMapV(op: Op, vs  : Seq[t]        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = ???
}

trait ExprMapTac_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2] {
  def apply(                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMap (NoValue, Map.empty[String, t])
  def apply(key : String, keys: String*): Ns1[A, B, C, D, E, F, G, H, I, J, t, t] = _exprMapK(Eq     , Seq(key) ++ keys    )
  def apply(keys: Seq[String]          ): Ns1[A, B, C, D, E, F, G, H, I, J, t, t] = _exprMapK(Eq     , keys                )
  def not  (key : String, keys: String*): Ns1[A, B, C, D, E, F, G, H, I, J, t, t] = _exprMapK(Neq    , Seq(key) ++ keys    )
  def not  (keys: Seq[String]          ): Ns1[A, B, C, D, E, F, G, H, I, J, t, t] = _exprMapK(Neq    , keys                )
  def has  (v : t, vs: t*              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapV(Has    , Seq(v) ++ vs        )
  def has  (vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapV(Has    , vs                  )
  def hasNo(v : t, vs: t*              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapV(HasNo  , Seq(v) ++ vs        )
  def hasNo(vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapV(HasNo  , vs                  )
}


trait ExprMapTacOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMap (op: Op, map : Map[String, t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = ???
  protected def _exprMapK(op: Op, keys: Seq[String]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t, t] = ???
  protected def _exprMapV(op: Op, vs  : Seq[t]        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = ???
}

trait ExprMapTac_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2] {
  def apply(                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMap (NoValue, Map.empty[String, t])
  def apply(key : String, keys: String*): Ns1[A, B, C, D, E, F, G, H, I, J, K, t, t] = _exprMapK(Eq     , Seq(key) ++ keys    )
  def apply(keys: Seq[String]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t, t] = _exprMapK(Eq     , keys                )
  def not  (key : String, keys: String*): Ns1[A, B, C, D, E, F, G, H, I, J, K, t, t] = _exprMapK(Neq    , Seq(key) ++ keys    )
  def not  (keys: Seq[String]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t, t] = _exprMapK(Neq    , keys                )
  def has  (v : t, vs: t*              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapV(Has    , Seq(v) ++ vs        )
  def has  (vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapV(Has    , vs                  )
  def hasNo(v : t, vs: t*              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapV(HasNo  , Seq(v) ++ vs        )
  def hasNo(vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapV(HasNo  , vs                  )
}


trait ExprMapTacOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMap (op: Op, map : Map[String, t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = ???
  protected def _exprMapK(op: Op, keys: Seq[String]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t, t] = ???
  protected def _exprMapV(op: Op, vs  : Seq[t]        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = ???
}

trait ExprMapTac_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2] {
  def apply(                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMap (NoValue, Map.empty[String, t])
  def apply(key : String, keys: String*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t, t] = _exprMapK(Eq     , Seq(key) ++ keys    )
  def apply(keys: Seq[String]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t, t] = _exprMapK(Eq     , keys                )
  def not  (key : String, keys: String*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t, t] = _exprMapK(Neq    , Seq(key) ++ keys    )
  def not  (keys: Seq[String]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t, t] = _exprMapK(Neq    , keys                )
  def has  (v : t, vs: t*              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapV(Has    , Seq(v) ++ vs        )
  def has  (vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapV(Has    , vs                  )
  def hasNo(v : t, vs: t*              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapV(HasNo  , Seq(v) ++ vs        )
  def hasNo(vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapV(HasNo  , vs                  )
}


trait ExprMapTacOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMap (op: Op, map : Map[String, t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = ???
  protected def _exprMapK(op: Op, keys: Seq[String]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t, t] = ???
  protected def _exprMapV(op: Op, vs  : Seq[t]        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = ???
}

trait ExprMapTac_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2] {
  def apply(                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMap (NoValue, Map.empty[String, t])
  def apply(key : String, keys: String*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t, t] = _exprMapK(Eq     , Seq(key) ++ keys    )
  def apply(keys: Seq[String]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t, t] = _exprMapK(Eq     , keys                )
  def not  (key : String, keys: String*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t, t] = _exprMapK(Neq    , Seq(key) ++ keys    )
  def not  (keys: Seq[String]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t, t] = _exprMapK(Neq    , keys                )
  def has  (v : t, vs: t*              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapV(Has    , Seq(v) ++ vs        )
  def has  (vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapV(Has    , vs                  )
  def hasNo(v : t, vs: t*              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapV(HasNo  , Seq(v) ++ vs        )
  def hasNo(vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapV(HasNo  , vs                  )
}


trait ExprMapTacOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMap (op: Op, map : Map[String, t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = ???
  protected def _exprMapK(op: Op, keys: Seq[String]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, t] = ???
  protected def _exprMapV(op: Op, vs  : Seq[t]        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = ???
}

trait ExprMapTac_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2] {
  def apply(                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMap (NoValue, Map.empty[String, t])
  def apply(key : String, keys: String*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, t] = _exprMapK(Eq     , Seq(key) ++ keys    )
  def apply(keys: Seq[String]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, t] = _exprMapK(Eq     , keys                )
  def not  (key : String, keys: String*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, t] = _exprMapK(Neq    , Seq(key) ++ keys    )
  def not  (keys: Seq[String]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, t] = _exprMapK(Neq    , keys                )
  def has  (v : t, vs: t*              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapV(Has    , Seq(v) ++ vs        )
  def has  (vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapV(Has    , vs                  )
  def hasNo(v : t, vs: t*              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapV(HasNo  , Seq(v) ++ vs        )
  def hasNo(vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapV(HasNo  , vs                  )
}


trait ExprMapTacOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMap (op: Op, map : Map[String, t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = ???
  protected def _exprMapK(op: Op, keys: Seq[String]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, t] = ???
  protected def _exprMapV(op: Op, vs  : Seq[t]        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = ???
}

trait ExprMapTac_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2] {
  def apply(                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMap (NoValue, Map.empty[String, t])
  def apply(key : String, keys: String*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, t] = _exprMapK(Eq     , Seq(key) ++ keys    )
  def apply(keys: Seq[String]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, t] = _exprMapK(Eq     , keys                )
  def not  (key : String, keys: String*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, t] = _exprMapK(Neq    , Seq(key) ++ keys    )
  def not  (keys: Seq[String]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, t] = _exprMapK(Neq    , keys                )
  def has  (v : t, vs: t*              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapV(Has    , Seq(v) ++ vs        )
  def has  (vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapV(Has    , vs                  )
  def hasNo(v : t, vs: t*              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapV(HasNo  , Seq(v) ++ vs        )
  def hasNo(vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapV(HasNo  , vs                  )
}


trait ExprMapTacOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMap (op: Op, map : Map[String, t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = ???
  protected def _exprMapK(op: Op, keys: Seq[String]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, t] = ???
  protected def _exprMapV(op: Op, vs  : Seq[t]        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = ???
}

trait ExprMapTac_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2] {
  def apply(                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMap (NoValue, Map.empty[String, t])
  def apply(key : String, keys: String*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, t] = _exprMapK(Eq     , Seq(key) ++ keys    )
  def apply(keys: Seq[String]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, t] = _exprMapK(Eq     , keys                )
  def not  (key : String, keys: String*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, t] = _exprMapK(Neq    , Seq(key) ++ keys    )
  def not  (keys: Seq[String]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, t] = _exprMapK(Neq    , keys                )
  def has  (v : t, vs: t*              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapV(Has    , Seq(v) ++ vs        )
  def has  (vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapV(Has    , vs                  )
  def hasNo(v : t, vs: t*              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapV(HasNo  , Seq(v) ++ vs        )
  def hasNo(vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapV(HasNo  , vs                  )
}


trait ExprMapTacOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMap (op: Op, map : Map[String, t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = ???
  protected def _exprMapK(op: Op, keys: Seq[String]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, t] = ???
  protected def _exprMapV(op: Op, vs  : Seq[t]        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = ???
}

trait ExprMapTac_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2] {
  def apply(                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMap (NoValue, Map.empty[String, t])
  def apply(key : String, keys: String*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, t] = _exprMapK(Eq     , Seq(key) ++ keys    )
  def apply(keys: Seq[String]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, t] = _exprMapK(Eq     , keys                )
  def not  (key : String, keys: String*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, t] = _exprMapK(Neq    , Seq(key) ++ keys    )
  def not  (keys: Seq[String]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, t] = _exprMapK(Neq    , keys                )
  def has  (v : t, vs: t*              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapV(Has    , Seq(v) ++ vs        )
  def has  (vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapV(Has    , vs                  )
  def hasNo(v : t, vs: t*              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapV(HasNo  , Seq(v) ++ vs        )
  def hasNo(vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapV(HasNo  , vs                  )
}


trait ExprMapTacOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMap (op: Op, map : Map[String, t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = ???
  protected def _exprMapK(op: Op, keys: Seq[String]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, t] = ???
  protected def _exprMapV(op: Op, vs  : Seq[t]        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = ???
}

trait ExprMapTac_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2] {
  def apply(                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMap (NoValue, Map.empty[String, t])
  def apply(key : String, keys: String*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, t] = _exprMapK(Eq     , Seq(key) ++ keys    )
  def apply(keys: Seq[String]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, t] = _exprMapK(Eq     , keys                )
  def not  (key : String, keys: String*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, t] = _exprMapK(Neq    , Seq(key) ++ keys    )
  def not  (keys: Seq[String]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, t] = _exprMapK(Neq    , keys                )
  def has  (v : t, vs: t*              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapV(Has    , Seq(v) ++ vs        )
  def has  (vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapV(Has    , vs                  )
  def hasNo(v : t, vs: t*              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapV(HasNo  , Seq(v) ++ vs        )
  def hasNo(vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapV(HasNo  , vs                  )
}


trait ExprMapTacOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMap (op: Op, map : Map[String, t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = ???
  protected def _exprMapK(op: Op, keys: Seq[String]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, t] = ???
  protected def _exprMapV(op: Op, vs  : Seq[t]        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = ???
}

trait ExprMapTac_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2] {
  def apply(                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMap (NoValue, Map.empty[String, t])
  def apply(key : String, keys: String*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, t] = _exprMapK(Eq     , Seq(key) ++ keys    )
  def apply(keys: Seq[String]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, t] = _exprMapK(Eq     , keys                )
  def not  (key : String, keys: String*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, t] = _exprMapK(Neq    , Seq(key) ++ keys    )
  def not  (keys: Seq[String]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, t] = _exprMapK(Neq    , keys                )
  def has  (v : t, vs: t*              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapV(Has    , Seq(v) ++ vs        )
  def has  (vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapV(Has    , vs                  )
  def hasNo(v : t, vs: t*              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapV(HasNo  , Seq(v) ++ vs        )
  def hasNo(vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapV(HasNo  , vs                  )
}


trait ExprMapTacOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMap (op: Op, map : Map[String, t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = ???
  protected def _exprMapK(op: Op, keys: Seq[String]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, t] = ???
  protected def _exprMapV(op: Op, vs  : Seq[t]        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = ???
}

trait ExprMapTac_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2] {
  def apply(                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMap (NoValue, Map.empty[String, t])
  def apply(key : String, keys: String*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, t] = _exprMapK(Eq     , Seq(key) ++ keys    )
  def apply(keys: Seq[String]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, t] = _exprMapK(Eq     , keys                )
  def not  (key : String, keys: String*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, t] = _exprMapK(Neq    , Seq(key) ++ keys    )
  def not  (keys: Seq[String]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, t] = _exprMapK(Neq    , keys                )
  def has  (v : t, vs: t*              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapV(Has    , Seq(v) ++ vs        )
  def has  (vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapV(Has    , vs                  )
  def hasNo(v : t, vs: t*              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapV(HasNo  , Seq(v) ++ vs        )
  def hasNo(vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapV(HasNo  , vs                  )
}


trait ExprMapTacOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMap (op: Op, map : Map[String, t]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = ???
  protected def _exprMapK(op: Op, keys: Seq[String]   ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, t] = ???
  protected def _exprMapV(op: Op, vs  : Seq[t]        ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = ???
}

trait ExprMapTac_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2] {
  def apply(                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMap (NoValue, Map.empty[String, t])
  def apply(key : String, keys: String*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, t] = _exprMapK(Eq     , Seq(key) ++ keys    )
  def apply(keys: Seq[String]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, t] = _exprMapK(Eq     , keys                )
  def not  (key : String, keys: String*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, t] = _exprMapK(Neq    , Seq(key) ++ keys    )
  def not  (keys: Seq[String]          ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, t] = _exprMapK(Neq    , keys                )
  def has  (v : t, vs: t*              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapV(Has    , Seq(v) ++ vs        )
  def has  (vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapV(Has    , vs                  )
  def hasNo(v : t, vs: t*              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapV(HasNo  , Seq(v) ++ vs        )
  def hasNo(vs: Seq[t]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapV(HasNo  , vs                  )
}
