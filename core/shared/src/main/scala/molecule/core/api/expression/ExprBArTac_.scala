// GENERATED CODE ********************************
package molecule.core.api.expression

import molecule.core.ast.DataModel._
import scala.language.higherKinds


trait ExprBArTacOps_0[t, Entity1[_], Entity2[_, _]] {
  protected def _exprBAr(op: Op, byteArray: Array[t]): Entity1[t] = ???
}

trait ExprBArTac_0[t, Entity1[_], Entity2[_, _]]
  extends ExprBArTacOps_0[t, Entity1, Entity2] {
  def apply(                   ): Entity1[t] = _exprBAr(NoValue, Array.empty[Byte].asInstanceOf[Array[t]])
  def apply(byteArray: Array[t]): Entity1[t] = _exprBAr(Eq     , byteArray                               )
  def not  (byteArray: Array[t]): Entity1[t] = _exprBAr(Neq    , byteArray                               )
}


trait ExprBArTacOps_1[A, t, Entity1[_, _], Entity2[_, _, _]] {
  protected def _exprBAr(op: Op, byteArray: Array[t]): Entity1[A, t] = ???
}

trait ExprBArTac_1[A, t, Entity1[_, _], Entity2[_, _, _]]
  extends ExprBArTacOps_1[A, t, Entity1, Entity2] {
  def apply(                   ): Entity1[A, t] = _exprBAr(NoValue, Array.empty[Byte].asInstanceOf[Array[t]])
  def apply(byteArray: Array[t]): Entity1[A, t] = _exprBAr(Eq     , byteArray                               )
  def not  (byteArray: Array[t]): Entity1[A, t] = _exprBAr(Neq    , byteArray                               )
}


trait ExprBArTacOps_2[A, B, t, Entity1[_, _, _], Entity2[_, _, _, _]] {
  protected def _exprBAr(op: Op, byteArray: Array[t]): Entity1[A, B, t] = ???
}

trait ExprBArTac_2[A, B, t, Entity1[_, _, _], Entity2[_, _, _, _]]
  extends ExprBArTacOps_2[A, B, t, Entity1, Entity2] {
  def apply(                   ): Entity1[A, B, t] = _exprBAr(NoValue, Array.empty[Byte].asInstanceOf[Array[t]])
  def apply(byteArray: Array[t]): Entity1[A, B, t] = _exprBAr(Eq     , byteArray                               )
  def not  (byteArray: Array[t]): Entity1[A, B, t] = _exprBAr(Neq    , byteArray                               )
}


trait ExprBArTacOps_3[A, B, C, t, Entity1[_, _, _, _], Entity2[_, _, _, _, _]] {
  protected def _exprBAr(op: Op, byteArray: Array[t]): Entity1[A, B, C, t] = ???
}

trait ExprBArTac_3[A, B, C, t, Entity1[_, _, _, _], Entity2[_, _, _, _, _]]
  extends ExprBArTacOps_3[A, B, C, t, Entity1, Entity2] {
  def apply(                   ): Entity1[A, B, C, t] = _exprBAr(NoValue, Array.empty[Byte].asInstanceOf[Array[t]])
  def apply(byteArray: Array[t]): Entity1[A, B, C, t] = _exprBAr(Eq     , byteArray                               )
  def not  (byteArray: Array[t]): Entity1[A, B, C, t] = _exprBAr(Neq    , byteArray                               )
}


trait ExprBArTacOps_4[A, B, C, D, t, Entity1[_, _, _, _, _], Entity2[_, _, _, _, _, _]] {
  protected def _exprBAr(op: Op, byteArray: Array[t]): Entity1[A, B, C, D, t] = ???
}

trait ExprBArTac_4[A, B, C, D, t, Entity1[_, _, _, _, _], Entity2[_, _, _, _, _, _]]
  extends ExprBArTacOps_4[A, B, C, D, t, Entity1, Entity2] {
  def apply(                   ): Entity1[A, B, C, D, t] = _exprBAr(NoValue, Array.empty[Byte].asInstanceOf[Array[t]])
  def apply(byteArray: Array[t]): Entity1[A, B, C, D, t] = _exprBAr(Eq     , byteArray                               )
  def not  (byteArray: Array[t]): Entity1[A, B, C, D, t] = _exprBAr(Neq    , byteArray                               )
}


trait ExprBArTacOps_5[A, B, C, D, E, t, Entity1[_, _, _, _, _, _], Entity2[_, _, _, _, _, _, _]] {
  protected def _exprBAr(op: Op, byteArray: Array[t]): Entity1[A, B, C, D, E, t] = ???
}

trait ExprBArTac_5[A, B, C, D, E, t, Entity1[_, _, _, _, _, _], Entity2[_, _, _, _, _, _, _]]
  extends ExprBArTacOps_5[A, B, C, D, E, t, Entity1, Entity2] {
  def apply(                   ): Entity1[A, B, C, D, E, t] = _exprBAr(NoValue, Array.empty[Byte].asInstanceOf[Array[t]])
  def apply(byteArray: Array[t]): Entity1[A, B, C, D, E, t] = _exprBAr(Eq     , byteArray                               )
  def not  (byteArray: Array[t]): Entity1[A, B, C, D, E, t] = _exprBAr(Neq    , byteArray                               )
}


trait ExprBArTacOps_6[A, B, C, D, E, F, t, Entity1[_, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _]] {
  protected def _exprBAr(op: Op, byteArray: Array[t]): Entity1[A, B, C, D, E, F, t] = ???
}

trait ExprBArTac_6[A, B, C, D, E, F, t, Entity1[_, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _]]
  extends ExprBArTacOps_6[A, B, C, D, E, F, t, Entity1, Entity2] {
  def apply(                   ): Entity1[A, B, C, D, E, F, t] = _exprBAr(NoValue, Array.empty[Byte].asInstanceOf[Array[t]])
  def apply(byteArray: Array[t]): Entity1[A, B, C, D, E, F, t] = _exprBAr(Eq     , byteArray                               )
  def not  (byteArray: Array[t]): Entity1[A, B, C, D, E, F, t] = _exprBAr(Neq    , byteArray                               )
}


trait ExprBArTacOps_7[A, B, C, D, E, F, G, t, Entity1[_, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _]] {
  protected def _exprBAr(op: Op, byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, t] = ???
}

trait ExprBArTac_7[A, B, C, D, E, F, G, t, Entity1[_, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _]]
  extends ExprBArTacOps_7[A, B, C, D, E, F, G, t, Entity1, Entity2] {
  def apply(                   ): Entity1[A, B, C, D, E, F, G, t] = _exprBAr(NoValue, Array.empty[Byte].asInstanceOf[Array[t]])
  def apply(byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, t] = _exprBAr(Eq     , byteArray                               )
  def not  (byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, t] = _exprBAr(Neq    , byteArray                               )
}


trait ExprBArTacOps_8[A, B, C, D, E, F, G, H, t, Entity1[_, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _]] {
  protected def _exprBAr(op: Op, byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, t] = ???
}

trait ExprBArTac_8[A, B, C, D, E, F, G, H, t, Entity1[_, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprBArTacOps_8[A, B, C, D, E, F, G, H, t, Entity1, Entity2] {
  def apply(                   ): Entity1[A, B, C, D, E, F, G, H, t] = _exprBAr(NoValue, Array.empty[Byte].asInstanceOf[Array[t]])
  def apply(byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, t] = _exprBAr(Eq     , byteArray                               )
  def not  (byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, t] = _exprBAr(Neq    , byteArray                               )
}


trait ExprBArTacOps_9[A, B, C, D, E, F, G, H, I, t, Entity1[_, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprBAr(op: Op, byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, t] = ???
}

trait ExprBArTac_9[A, B, C, D, E, F, G, H, I, t, Entity1[_, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprBArTacOps_9[A, B, C, D, E, F, G, H, I, t, Entity1, Entity2] {
  def apply(                   ): Entity1[A, B, C, D, E, F, G, H, I, t] = _exprBAr(NoValue, Array.empty[Byte].asInstanceOf[Array[t]])
  def apply(byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, t] = _exprBAr(Eq     , byteArray                               )
  def not  (byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, t] = _exprBAr(Neq    , byteArray                               )
}


trait ExprBArTacOps_10[A, B, C, D, E, F, G, H, I, J, t, Entity1[_, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprBAr(op: Op, byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, t] = ???
}

trait ExprBArTac_10[A, B, C, D, E, F, G, H, I, J, t, Entity1[_, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprBArTacOps_10[A, B, C, D, E, F, G, H, I, J, t, Entity1, Entity2] {
  def apply(                   ): Entity1[A, B, C, D, E, F, G, H, I, J, t] = _exprBAr(NoValue, Array.empty[Byte].asInstanceOf[Array[t]])
  def apply(byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, t] = _exprBAr(Eq     , byteArray                               )
  def not  (byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, t] = _exprBAr(Neq    , byteArray                               )
}


trait ExprBArTacOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprBAr(op: Op, byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] = ???
}

trait ExprBArTac_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprBArTacOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1, Entity2] {
  def apply(                   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprBAr(NoValue, Array.empty[Byte].asInstanceOf[Array[t]])
  def apply(byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprBAr(Eq     , byteArray                               )
  def not  (byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprBAr(Neq    , byteArray                               )
}


trait ExprBArTacOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprBAr(op: Op, byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] = ???
}

trait ExprBArTac_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprBArTacOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1, Entity2] {
  def apply(                   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprBAr(NoValue, Array.empty[Byte].asInstanceOf[Array[t]])
  def apply(byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprBAr(Eq     , byteArray                               )
  def not  (byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprBAr(Neq    , byteArray                               )
}


trait ExprBArTacOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprBAr(op: Op, byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = ???
}

trait ExprBArTac_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprBArTacOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1, Entity2] {
  def apply(                   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprBAr(NoValue, Array.empty[Byte].asInstanceOf[Array[t]])
  def apply(byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprBAr(Eq     , byteArray                               )
  def not  (byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprBAr(Neq    , byteArray                               )
}


trait ExprBArTacOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprBAr(op: Op, byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = ???
}

trait ExprBArTac_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprBArTacOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1, Entity2] {
  def apply(                   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprBAr(NoValue, Array.empty[Byte].asInstanceOf[Array[t]])
  def apply(byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprBAr(Eq     , byteArray                               )
  def not  (byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprBAr(Neq    , byteArray                               )
}


trait ExprBArTacOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprBAr(op: Op, byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = ???
}

trait ExprBArTac_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprBArTacOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1, Entity2] {
  def apply(                   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprBAr(NoValue, Array.empty[Byte].asInstanceOf[Array[t]])
  def apply(byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprBAr(Eq     , byteArray                               )
  def not  (byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprBAr(Neq    , byteArray                               )
}


trait ExprBArTacOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprBAr(op: Op, byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = ???
}

trait ExprBArTac_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprBArTacOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1, Entity2] {
  def apply(                   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprBAr(NoValue, Array.empty[Byte].asInstanceOf[Array[t]])
  def apply(byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprBAr(Eq     , byteArray                               )
  def not  (byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprBAr(Neq    , byteArray                               )
}


trait ExprBArTacOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprBAr(op: Op, byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = ???
}

trait ExprBArTac_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprBArTacOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1, Entity2] {
  def apply(                   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprBAr(NoValue, Array.empty[Byte].asInstanceOf[Array[t]])
  def apply(byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprBAr(Eq     , byteArray                               )
  def not  (byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprBAr(Neq    , byteArray                               )
}


trait ExprBArTacOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprBAr(op: Op, byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = ???
}

trait ExprBArTac_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprBArTacOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1, Entity2] {
  def apply(                   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprBAr(NoValue, Array.empty[Byte].asInstanceOf[Array[t]])
  def apply(byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprBAr(Eq     , byteArray                               )
  def not  (byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprBAr(Neq    , byteArray                               )
}


trait ExprBArTacOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprBAr(op: Op, byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = ???
}

trait ExprBArTac_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprBArTacOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1, Entity2] {
  def apply(                   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprBAr(NoValue, Array.empty[Byte].asInstanceOf[Array[t]])
  def apply(byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprBAr(Eq     , byteArray                               )
  def not  (byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprBAr(Neq    , byteArray                               )
}


trait ExprBArTacOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprBAr(op: Op, byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = ???
}

trait ExprBArTac_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprBArTacOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1, Entity2] {
  def apply(                   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprBAr(NoValue, Array.empty[Byte].asInstanceOf[Array[t]])
  def apply(byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprBAr(Eq     , byteArray                               )
  def not  (byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprBAr(Neq    , byteArray                               )
}


trait ExprBArTacOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprBAr(op: Op, byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = ???
}

trait ExprBArTac_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprBArTacOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1, Entity2] {
  def apply(                   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprBAr(NoValue, Array.empty[Byte].asInstanceOf[Array[t]])
  def apply(byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprBAr(Eq     , byteArray                               )
  def not  (byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprBAr(Neq    , byteArray                               )
}


trait ExprBArTacOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _exprBAr(op: Op, byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = ???
}

trait ExprBArTac_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprBArTacOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1, Entity2] {
  def apply(                   ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprBAr(NoValue, Array.empty[Byte].asInstanceOf[Array[t]])
  def apply(byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprBAr(Eq     , byteArray                               )
  def not  (byteArray: Array[t]): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprBAr(Neq    , byteArray                               )
}
