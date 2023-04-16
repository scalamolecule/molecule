// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.boilerplate.api._
import molecule.boilerplate.ast.Model._


trait ExprAttr_0[t, Ns1[_], Ns2[_, _]] extends ExprBase {
  protected def _attrTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[   t] = ???
  protected def _attrMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[X, t] = ???
}

trait ExprAttr_1[A, t, Ns1[_, _], Ns2[_, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A,    t] with SortAttrs_1[A,    t, Ns1] = ???
  protected def _attrSortMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, X, t] with SortAttrs_2[A, X, t, Ns2] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A,    t] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, X, t] = ???
}

trait ExprAttr_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B,    t] with SortAttrs_2[A, B,    t, Ns1] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B,    t] = ???
  protected def _attrSortMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, X, t] with SortAttrs_3[A, B, X, t, Ns2] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, X, t] = ???
}

trait ExprAttr_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C,    t] with SortAttrs_3[A, B, C,    t, Ns1] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C,    t] = ???
  protected def _attrSortMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, X, t] with SortAttrs_4[A, B, C, X, t, Ns2] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, X, t] = ???
}

trait ExprAttr_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D,    t] with SortAttrs_4[A, B, C, D,    t, Ns1] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D,    t] = ???
  protected def _attrSortMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, X, t] with SortAttrs_5[A, B, C, D, X, t, Ns2] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, X, t] = ???
}

trait ExprAttr_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E,    t] with SortAttrs_5[A, B, C, D, E,    t, Ns1] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E,    t] = ???
  protected def _attrSortMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, X, t] with SortAttrs_6[A, B, C, D, E, X, t, Ns2] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, X, t] = ???
}

trait ExprAttr_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F,    t] with SortAttrs_6[A, B, C, D, E, F,    t, Ns1] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F,    t] = ???
  protected def _attrSortMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, X, t] with SortAttrs_7[A, B, C, D, E, F, X, t, Ns2] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, X, t] = ???
}

trait ExprAttr_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F, G,    t] with SortAttrs_7[A, B, C, D, E, F, G,    t, Ns1] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F, G,    t] = ???
  protected def _attrSortMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, X, t] with SortAttrs_8[A, B, C, D, E, F, G, X, t, Ns2] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, X, t] = ???
}

trait ExprAttr_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H,    t] with SortAttrs_8[A, B, C, D, E, F, G, H,    t, Ns1] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H,    t] = ???
  protected def _attrSortMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, X, t] with SortAttrs_9[A, B, C, D, E, F, G, H, X, t, Ns2] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, X, t] = ???
}

trait ExprAttr_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I,    t] with SortAttrs_9[A, B, C, D, E, F, G, H, I,    t, Ns1] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I,    t] = ???
  protected def _attrSortMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, X, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, X, t, Ns2] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, X, t] = ???
}

trait ExprAttr_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J,    t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J,    t, Ns1] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J,    t] = ???
  protected def _attrSortMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, X, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, X, t, Ns2] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, X, t] = ???
}

trait ExprAttr_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K,    t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K,    t, Ns1] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K,    t] = ???
  protected def _attrSortMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, X, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, X, t, Ns2] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, X, t] = ???
}

trait ExprAttr_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L,    t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L,    t, Ns1] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L,    t] = ???
  protected def _attrSortMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, X, t, Ns2] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X, t] = ???
}

trait ExprAttr_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M,    t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M,    t, Ns1] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M,    t] = ???
  protected def _attrSortMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t, Ns2] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t] = ???
}

trait ExprAttr_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N,    t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N,    t, Ns1] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N,    t] = ???
  protected def _attrSortMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t, Ns2] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t] = ???
}

trait ExprAttr_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O,    t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O,    t, Ns1] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O,    t] = ???
  protected def _attrSortMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t, Ns2] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t] = ???
}

trait ExprAttr_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P,    t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P,    t, Ns1] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P,    t] = ???
  protected def _attrSortMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t, Ns2] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t] = ???
}

trait ExprAttr_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q,    t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q,    t, Ns1] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q,    t] = ???
  protected def _attrSortMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t, Ns2] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t] = ???
}

trait ExprAttr_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R,    t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R,    t, Ns1] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R,    t] = ???
  protected def _attrSortMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t, Ns2] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t] = ???
}

trait ExprAttr_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S,    t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S,    t, Ns1] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S,    t] = ???
  protected def _attrSortMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t, Ns2] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t] = ???
}

trait ExprAttr_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T,    t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T,    t, Ns1] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T,    t] = ???
  protected def _attrSortMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t, Ns2] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t] = ???
}

trait ExprAttr_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U,    t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U,    t, Ns1] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U,    t] = ???
  protected def _attrSortMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t, Ns2] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t] = ???
}

trait ExprAttr_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V,    t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V,    t, Ns1] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V,    t] = ???
}