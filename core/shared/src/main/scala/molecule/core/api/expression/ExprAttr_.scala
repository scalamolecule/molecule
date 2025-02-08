// GENERATED CODE ********************************
package molecule.core.api.expression

import molecule.base.ast._
import molecule.core.api._
import molecule.core.ast.DataModel._
import scala.language.higherKinds


trait ExprAttr_0[t, Entity1[_], Entity2[_, _]] extends ExprBase {
  protected def _attrTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Entity1[   t] = ???
  protected def _attrMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Entity2[X, t] = ???
}

trait ExprAttr_1[A, t, Entity1[_, _], Entity2[_, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne): Entity1[A,    t] with SortAttrs_1[A,    t, Entity1] = ???
  protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[A, t, ns1, ns2]             ): Entity2[A, A, t] with SortAttrs_2[A, A, t, Entity2] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ): Entity1[A,    t] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ): Entity2[A, X, t] = ???
}

trait ExprAttr_2[A, B, t, Entity1[_, _, _], Entity2[_, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne): Entity1[A, B,    t] with SortAttrs_2[A, B,    t, Entity1] = ???
  protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[B, t, ns1, ns2]             ): Entity2[A, B, B, t] with SortAttrs_3[A, B, B, t, Entity2] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ): Entity1[A, B,    t] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ): Entity2[A, B, X, t] = ???
}

trait ExprAttr_3[A, B, C, t, Entity1[_, _, _, _], Entity2[_, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne): Entity1[A, B, C,    t] with SortAttrs_3[A, B, C,    t, Entity1] = ???
  protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[C, t, ns1, ns2]             ): Entity2[A, B, C, C, t] with SortAttrs_4[A, B, C, C, t, Entity2] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ): Entity1[A, B, C,    t] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ): Entity2[A, B, C, X, t] = ???
}

trait ExprAttr_4[A, B, C, D, t, Entity1[_, _, _, _, _], Entity2[_, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne): Entity1[A, B, C, D,    t] with SortAttrs_4[A, B, C, D,    t, Entity1] = ???
  protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[D, t, ns1, ns2]             ): Entity2[A, B, C, D, D, t] with SortAttrs_5[A, B, C, D, D, t, Entity2] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ): Entity1[A, B, C, D,    t] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ): Entity2[A, B, C, D, X, t] = ???
}

trait ExprAttr_5[A, B, C, D, E, t, Entity1[_, _, _, _, _, _], Entity2[_, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne): Entity1[A, B, C, D, E,    t] with SortAttrs_5[A, B, C, D, E,    t, Entity1] = ???
  protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[E, t, ns1, ns2]             ): Entity2[A, B, C, D, E, E, t] with SortAttrs_6[A, B, C, D, E, E, t, Entity2] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ): Entity1[A, B, C, D, E,    t] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ): Entity2[A, B, C, D, E, X, t] = ???
}

trait ExprAttr_6[A, B, C, D, E, F, t, Entity1[_, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne): Entity1[A, B, C, D, E, F,    t] with SortAttrs_6[A, B, C, D, E, F,    t, Entity1] = ???
  protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[F, t, ns1, ns2]             ): Entity2[A, B, C, D, E, F, F, t] with SortAttrs_7[A, B, C, D, E, F, F, t, Entity2] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ): Entity1[A, B, C, D, E, F,    t] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ): Entity2[A, B, C, D, E, F, X, t] = ???
}

trait ExprAttr_7[A, B, C, D, E, F, G, t, Entity1[_, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne): Entity1[A, B, C, D, E, F, G,    t] with SortAttrs_7[A, B, C, D, E, F, G,    t, Entity1] = ???
  protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[G, t, ns1, ns2]             ): Entity2[A, B, C, D, E, F, G, G, t] with SortAttrs_8[A, B, C, D, E, F, G, G, t, Entity2] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ): Entity1[A, B, C, D, E, F, G,    t] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ): Entity2[A, B, C, D, E, F, G, X, t] = ???
}

trait ExprAttr_8[A, B, C, D, E, F, G, H, t, Entity1[_, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne): Entity1[A, B, C, D, E, F, G, H,    t] with SortAttrs_8[A, B, C, D, E, F, G, H,    t, Entity1] = ???
  protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[H, t, ns1, ns2]             ): Entity2[A, B, C, D, E, F, G, H, H, t] with SortAttrs_9[A, B, C, D, E, F, G, H, H, t, Entity2] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ): Entity1[A, B, C, D, E, F, G, H,    t] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ): Entity2[A, B, C, D, E, F, G, H, X, t] = ???
}

trait ExprAttr_9[A, B, C, D, E, F, G, H, I, t, Entity1[_, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne): Entity1[A, B, C, D, E, F, G, H, I,    t] with SortAttrs_9[A, B, C, D, E, F, G, H, I,    t, Entity1] = ???
  protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[I, t, ns1, ns2]             ): Entity2[A, B, C, D, E, F, G, H, I, I, t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, I, t, Entity2] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ): Entity1[A, B, C, D, E, F, G, H, I,    t] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ): Entity2[A, B, C, D, E, F, G, H, I, X, t] = ???
}

trait ExprAttr_10[A, B, C, D, E, F, G, H, I, J, t, Entity1[_, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne): Entity1[A, B, C, D, E, F, G, H, I, J,    t] with SortAttrs_10[A, B, C, D, E, F, G, H, I, J,    t, Entity1] = ???
  protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[J, t, ns1, ns2]             ): Entity2[A, B, C, D, E, F, G, H, I, J, J, t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, J, t, Entity2] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ): Entity1[A, B, C, D, E, F, G, H, I, J,    t] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ): Entity2[A, B, C, D, E, F, G, H, I, J, X, t] = ???
}

trait ExprAttr_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K,    t] with SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K,    t, Entity1] = ???
  protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[K, t, ns1, ns2]             ): Entity2[A, B, C, D, E, F, G, H, I, J, K, K, t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, K, t, Entity2] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ): Entity1[A, B, C, D, E, F, G, H, I, J, K,    t] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ): Entity2[A, B, C, D, E, F, G, H, I, J, K, X, t] = ???
}

trait ExprAttr_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L,    t] with SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L,    t, Entity1] = ???
  protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[L, t, ns1, ns2]             ): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, L, t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, L, t, Entity2] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L,    t] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, X, t] = ???
}

trait ExprAttr_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M,    t] with SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M,    t, Entity1] = ???
  protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[M, t, ns1, ns2]             ): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, M, t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, M, t, Entity2] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M,    t] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, X, t] = ???
}

trait ExprAttr_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N,    t] with SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N,    t, Entity1] = ???
  protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[N, t, ns1, ns2]             ): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, N, t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, N, t, Entity2] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N,    t] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X, t] = ???
}

trait ExprAttr_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O,    t] with SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O,    t, Entity1] = ???
  protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[O, t, ns1, ns2]             ): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, O, t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, O, t, Entity2] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O,    t] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X, t] = ???
}

trait ExprAttr_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P,    t] with SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P,    t, Entity1] = ???
  protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[P, t, ns1, ns2]             ): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, P, t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, P, t, Entity2] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P,    t] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X, t] = ???
}

trait ExprAttr_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q,    t] with SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q,    t, Entity1] = ???
  protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[Q, t, ns1, ns2]             ): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Q, t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Q, t, Entity2] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q,    t] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X, t] = ???
}

trait ExprAttr_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R,    t] with SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R,    t, Entity1] = ???
  protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[R, t, ns1, ns2]             ): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, R, t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, R, t, Entity2] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R,    t] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X, t] = ???
}

trait ExprAttr_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S,    t] with SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S,    t, Entity1] = ???
  protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[S, t, ns1, ns2]             ): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, S, t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, S, t, Entity2] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S,    t] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X, t] = ???
}

trait ExprAttr_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T,    t] with SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T,    t, Entity1] = ???
  protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[T, t, ns1, ns2]             ): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, T, t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, T, t, Entity2] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T,    t] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, t] = ???
}

trait ExprAttr_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U,    t] with SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U,    t, Entity1] = ???
  protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[U, t, ns1, ns2]             ): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, U, t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, U, t, Entity2] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U,    t] = ???
  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ): Entity2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X, t] = ???
}

trait ExprAttr_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprBase {
  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V,    t] with SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V,    t, Entity1] = ???
  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V,    t] = ???
}