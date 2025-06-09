// GENERATED CODE ********************************
package molecule.db.core.api.expression

import molecule.core.ast.*


trait ExprMapOptOps_1[A, t, Entity1[_, _], Entity2[_, _, _]] extends ExprBase {
  protected def _exprMapOpt(op: Op, map: Option[Map[String, t]]): Entity1[A,         t] = ???
  protected def _exprMapOpK(op: Op, key: String                ): Entity1[Option[t], t] = ???
}

trait ExprMapOpt_1[A, t, Entity1[_, _], Entity2[_, _, _]]
  extends ExprMapOptOps_1[A, t, Entity1, Entity2]{
  def apply(map: Option[Map[String, t]]): Entity1[A,         t] = _exprMapOpt(Eq , map)
  def apply(key: String                ): Entity1[Option[t], t] = _exprMapOpK(Has, key)
}


trait ExprMapOptOps_2[A, B, t, Entity1[_, _, _], Entity2[_, _, _, _]] extends ExprBase {
  protected def _exprMapOpt(op: Op, map: Option[Map[String, t]]): Entity1[A, B,         t] = ???
  protected def _exprMapOpK(op: Op, key: String                ): Entity1[A, Option[t], t] = ???
}

trait ExprMapOpt_2[A, B, t, Entity1[_, _, _], Entity2[_, _, _, _]]
  extends ExprMapOptOps_2[A, B, t, Entity1, Entity2]{
  def apply(map: Option[Map[String, t]]): Entity1[A, B,         t] = _exprMapOpt(Eq , map)
  def apply(key: String                ): Entity1[A, Option[t], t] = _exprMapOpK(Has, key)
}


trait ExprMapOptOps_3[A, B, C, t, Entity1[_, _, _, _], Entity2[_, _, _, _, _]] extends ExprBase {
  protected def _exprMapOpt(op: Op, map: Option[Map[String, t]]): Entity1[A, B, C,         t] = ???
  protected def _exprMapOpK(op: Op, key: String                ): Entity1[A, B, Option[t], t] = ???
}

trait ExprMapOpt_3[A, B, C, t, Entity1[_, _, _, _], Entity2[_, _, _, _, _]]
  extends ExprMapOptOps_3[A, B, C, t, Entity1, Entity2]{
  def apply(map: Option[Map[String, t]]): Entity1[A, B, C,         t] = _exprMapOpt(Eq , map)
  def apply(key: String                ): Entity1[A, B, Option[t], t] = _exprMapOpK(Has, key)
}


trait ExprMapOptOps_4[A, B, C, D, t, Entity1[_, _, _, _, _], Entity2[_, _, _, _, _, _]] extends ExprBase {
  protected def _exprMapOpt(op: Op, map: Option[Map[String, t]]): Entity1[A, B, C, D,         t] = ???
  protected def _exprMapOpK(op: Op, key: String                ): Entity1[A, B, C, Option[t], t] = ???
}

trait ExprMapOpt_4[A, B, C, D, t, Entity1[_, _, _, _, _], Entity2[_, _, _, _, _, _]]
  extends ExprMapOptOps_4[A, B, C, D, t, Entity1, Entity2]{
  def apply(map: Option[Map[String, t]]): Entity1[A, B, C, D,         t] = _exprMapOpt(Eq , map)
  def apply(key: String                ): Entity1[A, B, C, Option[t], t] = _exprMapOpK(Has, key)
}


trait ExprMapOptOps_5[A, B, C, D, E, t, Entity1[_, _, _, _, _, _], Entity2[_, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMapOpt(op: Op, map: Option[Map[String, t]]): Entity1[A, B, C, D, E,         t] = ???
  protected def _exprMapOpK(op: Op, key: String                ): Entity1[A, B, C, D, Option[t], t] = ???
}

trait ExprMapOpt_5[A, B, C, D, E, t, Entity1[_, _, _, _, _, _], Entity2[_, _, _, _, _, _, _]]
  extends ExprMapOptOps_5[A, B, C, D, E, t, Entity1, Entity2]{
  def apply(map: Option[Map[String, t]]): Entity1[A, B, C, D, E,         t] = _exprMapOpt(Eq , map)
  def apply(key: String                ): Entity1[A, B, C, D, Option[t], t] = _exprMapOpK(Has, key)
}


trait ExprMapOptOps_6[A, B, C, D, E, F, t, Entity1[_, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMapOpt(op: Op, map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F,         t] = ???
  protected def _exprMapOpK(op: Op, key: String                ): Entity1[A, B, C, D, E, Option[t], t] = ???
}

trait ExprMapOpt_6[A, B, C, D, E, F, t, Entity1[_, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_6[A, B, C, D, E, F, t, Entity1, Entity2]{
  def apply(map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F,         t] = _exprMapOpt(Eq , map)
  def apply(key: String                ): Entity1[A, B, C, D, E, Option[t], t] = _exprMapOpK(Has, key)
}


trait ExprMapOptOps_7[A, B, C, D, E, F, G, t, Entity1[_, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMapOpt(op: Op, map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F, G,         t] = ???
  protected def _exprMapOpK(op: Op, key: String                ): Entity1[A, B, C, D, E, F, Option[t], t] = ???
}

trait ExprMapOpt_7[A, B, C, D, E, F, G, t, Entity1[_, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_7[A, B, C, D, E, F, G, t, Entity1, Entity2]{
  def apply(map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F, G,         t] = _exprMapOpt(Eq , map)
  def apply(key: String                ): Entity1[A, B, C, D, E, F, Option[t], t] = _exprMapOpK(Has, key)
}


trait ExprMapOptOps_8[A, B, C, D, E, F, G, H, t, Entity1[_, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMapOpt(op: Op, map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F, G, H,         t] = ???
  protected def _exprMapOpK(op: Op, key: String                ): Entity1[A, B, C, D, E, F, G, Option[t], t] = ???
}

trait ExprMapOpt_8[A, B, C, D, E, F, G, H, t, Entity1[_, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_8[A, B, C, D, E, F, G, H, t, Entity1, Entity2]{
  def apply(map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F, G, H,         t] = _exprMapOpt(Eq , map)
  def apply(key: String                ): Entity1[A, B, C, D, E, F, G, Option[t], t] = _exprMapOpK(Has, key)
}


trait ExprMapOptOps_9[A, B, C, D, E, F, G, H, I, t, Entity1[_, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMapOpt(op: Op, map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F, G, H, I,         t] = ???
  protected def _exprMapOpK(op: Op, key: String                ): Entity1[A, B, C, D, E, F, G, H, Option[t], t] = ???
}

trait ExprMapOpt_9[A, B, C, D, E, F, G, H, I, t, Entity1[_, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_9[A, B, C, D, E, F, G, H, I, t, Entity1, Entity2]{
  def apply(map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F, G, H, I,         t] = _exprMapOpt(Eq , map)
  def apply(key: String                ): Entity1[A, B, C, D, E, F, G, H, Option[t], t] = _exprMapOpK(Has, key)
}


trait ExprMapOptOps_10[A, B, C, D, E, F, G, H, I, J, t, Entity1[_, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMapOpt(op: Op, map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F, G, H, I, J,         t] = ???
  protected def _exprMapOpK(op: Op, key: String                ): Entity1[A, B, C, D, E, F, G, H, I, Option[t], t] = ???
}

trait ExprMapOpt_10[A, B, C, D, E, F, G, H, I, J, t, Entity1[_, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_10[A, B, C, D, E, F, G, H, I, J, t, Entity1, Entity2]{
  def apply(map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F, G, H, I, J,         t] = _exprMapOpt(Eq , map)
  def apply(key: String                ): Entity1[A, B, C, D, E, F, G, H, I, Option[t], t] = _exprMapOpK(Has, key)
}


trait ExprMapOptOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMapOpt(op: Op, map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K,         t] = ???
  protected def _exprMapOpK(op: Op, key: String                ): Entity1[A, B, C, D, E, F, G, H, I, J, Option[t], t] = ???
}

trait ExprMapOpt_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1, Entity2]{
  def apply(map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K,         t] = _exprMapOpt(Eq , map)
  def apply(key: String                ): Entity1[A, B, C, D, E, F, G, H, I, J, Option[t], t] = _exprMapOpK(Has, key)
}


trait ExprMapOptOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMapOpt(op: Op, map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L,         t] = ???
  protected def _exprMapOpK(op: Op, key: String                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, Option[t], t] = ???
}

trait ExprMapOpt_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1, Entity2]{
  def apply(map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L,         t] = _exprMapOpt(Eq , map)
  def apply(key: String                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, Option[t], t] = _exprMapOpK(Has, key)
}


trait ExprMapOptOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMapOpt(op: Op, map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M,         t] = ???
  protected def _exprMapOpK(op: Op, key: String                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, Option[t], t] = ???
}

trait ExprMapOpt_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1, Entity2]{
  def apply(map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M,         t] = _exprMapOpt(Eq , map)
  def apply(key: String                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, Option[t], t] = _exprMapOpK(Has, key)
}


trait ExprMapOptOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMapOpt(op: Op, map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N,         t] = ???
  protected def _exprMapOpK(op: Op, key: String                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, Option[t], t] = ???
}

trait ExprMapOpt_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1, Entity2]{
  def apply(map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N,         t] = _exprMapOpt(Eq , map)
  def apply(key: String                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, Option[t], t] = _exprMapOpK(Has, key)
}


trait ExprMapOptOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMapOpt(op: Op, map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O,         t] = ???
  protected def _exprMapOpK(op: Op, key: String                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Option[t], t] = ???
}

trait ExprMapOpt_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1, Entity2]{
  def apply(map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O,         t] = _exprMapOpt(Eq , map)
  def apply(key: String                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Option[t], t] = _exprMapOpK(Has, key)
}


trait ExprMapOptOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMapOpt(op: Op, map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P,         t] = ???
  protected def _exprMapOpK(op: Op, key: String                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Option[t], t] = ???
}

trait ExprMapOpt_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1, Entity2]{
  def apply(map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P,         t] = _exprMapOpt(Eq , map)
  def apply(key: String                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Option[t], t] = _exprMapOpK(Has, key)
}


trait ExprMapOptOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMapOpt(op: Op, map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q,         t] = ???
  protected def _exprMapOpK(op: Op, key: String                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Option[t], t] = ???
}

trait ExprMapOpt_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1, Entity2]{
  def apply(map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q,         t] = _exprMapOpt(Eq , map)
  def apply(key: String                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Option[t], t] = _exprMapOpK(Has, key)
}


trait ExprMapOptOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMapOpt(op: Op, map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R,         t] = ???
  protected def _exprMapOpK(op: Op, key: String                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Option[t], t] = ???
}

trait ExprMapOpt_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1, Entity2]{
  def apply(map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R,         t] = _exprMapOpt(Eq , map)
  def apply(key: String                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Option[t], t] = _exprMapOpK(Has, key)
}


trait ExprMapOptOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMapOpt(op: Op, map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S,         t] = ???
  protected def _exprMapOpK(op: Op, key: String                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Option[t], t] = ???
}

trait ExprMapOpt_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1, Entity2]{
  def apply(map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S,         t] = _exprMapOpt(Eq , map)
  def apply(key: String                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Option[t], t] = _exprMapOpK(Has, key)
}


trait ExprMapOptOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMapOpt(op: Op, map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T,         t] = ???
  protected def _exprMapOpK(op: Op, key: String                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Option[t], t] = ???
}

trait ExprMapOpt_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1, Entity2]{
  def apply(map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T,         t] = _exprMapOpt(Eq , map)
  def apply(key: String                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Option[t], t] = _exprMapOpK(Has, key)
}


trait ExprMapOptOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMapOpt(op: Op, map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U,         t] = ???
  protected def _exprMapOpK(op: Op, key: String                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Option[t], t] = ???
}

trait ExprMapOpt_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1, Entity2]{
  def apply(map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U,         t] = _exprMapOpt(Eq , map)
  def apply(key: String                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Option[t], t] = _exprMapOpK(Has, key)
}


trait ExprMapOptOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _exprMapOpt(op: Op, map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V,         t] = ???
  protected def _exprMapOpK(op: Op, key: String                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Option[t], t] = ???
}

trait ExprMapOpt_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1, Entity2]{
  def apply(map: Option[Map[String, t]]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V,         t] = _exprMapOpt(Eq , map)
  def apply(key: String                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Option[t], t] = _exprMapOpK(Has, key)
}
