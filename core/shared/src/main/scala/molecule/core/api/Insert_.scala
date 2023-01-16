// GENERATED CODE ********************************
package molecule.core.api

import molecule.core.api.action.InsertApi

trait Insert_ {
  // Implemented for each db and JS/JVM platform
  private[core] def _insertOp(tpls: Seq[Product]): InsertApi
}

trait Insert_1[A] extends Insert_ {
  def apply(a: A, as: A*): InsertApi = _insertOp((a +: as).map(a => Tuple1(a)))
  def apply(tpls: Seq[A]): InsertApi = _insertOp(tpls.map(a => Tuple1(a)))
}

trait Insert_2[A, B] extends Insert_ {
  def apply(a: A, b: B)                : InsertApi = _insertOp(Seq((a, b)))
  def apply(tpl: (A, B), more: (A, B)*): InsertApi = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B)])         : InsertApi = _insertOp(tpls)
}

trait Insert_3[A, B, C] extends Insert_ {
  def apply(a: A, b: B, c: C)                : InsertApi = _insertOp(Seq((a, b, c)))
  def apply(tpl: (A, B, C), more: (A, B, C)*): InsertApi = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C)])            : InsertApi = _insertOp(tpls)
}

trait Insert_4[A, B, C, D] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D)                : InsertApi = _insertOp(Seq((a, b, c, d)))
  def apply(tpl: (A, B, C, D), more: (A, B, C, D)*): InsertApi = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D)])               : InsertApi = _insertOp(tpls)
}

trait Insert_5[A, B, C, D, E] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E)                : InsertApi = _insertOp(Seq((a, b, c, d, e)))
  def apply(tpl: (A, B, C, D, E), more: (A, B, C, D, E)*): InsertApi = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E)])                  : InsertApi = _insertOp(tpls)
}

trait Insert_6[A, B, C, D, E, F] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F)                : InsertApi = _insertOp(Seq((a, b, c, d, e, f)))
  def apply(tpl: (A, B, C, D, E, F), more: (A, B, C, D, E, F)*): InsertApi = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F)])                     : InsertApi = _insertOp(tpls)
}

trait Insert_7[A, B, C, D, E, F, G] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G)                : InsertApi = _insertOp(Seq((a, b, c, d, e, f, g)))
  def apply(tpl: (A, B, C, D, E, F, G), more: (A, B, C, D, E, F, G)*): InsertApi = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F, G)])                        : InsertApi = _insertOp(tpls)
}

trait Insert_8[A, B, C, D, E, F, G, H] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H)                : InsertApi = _insertOp(Seq((a, b, c, d, e, f, g, h)))
  def apply(tpl: (A, B, C, D, E, F, G, H), more: (A, B, C, D, E, F, G, H)*): InsertApi = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F, G, H)])                           : InsertApi = _insertOp(tpls)
}

trait Insert_9[A, B, C, D, E, F, G, H, I] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I)                : InsertApi = _insertOp(Seq((a, b, c, d, e, f, g, h, i)))
  def apply(tpl: (A, B, C, D, E, F, G, H, I), more: (A, B, C, D, E, F, G, H, I)*): InsertApi = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I)])                              : InsertApi = _insertOp(tpls)
}

trait Insert_10[A, B, C, D, E, F, G, H, I, J] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J)                : InsertApi = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j)))
  def apply(tpl: (A, B, C, D, E, F, G, H, I, J), more: (A, B, C, D, E, F, G, H, I, J)*): InsertApi = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J)])                                 : InsertApi = _insertOp(tpls)
}

trait Insert_11[A, B, C, D, E, F, G, H, I, J, K] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K)                : InsertApi = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k)))
  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K), more: (A, B, C, D, E, F, G, H, I, J, K)*): InsertApi = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K)])                                    : InsertApi = _insertOp(tpls)
}

trait Insert_12[A, B, C, D, E, F, G, H, I, J, K, L] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L)                : InsertApi = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l)))
  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L), more: (A, B, C, D, E, F, G, H, I, J, K, L)*): InsertApi = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L)])                                       : InsertApi = _insertOp(tpls)
}

trait Insert_13[A, B, C, D, E, F, G, H, I, J, K, L, M] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M)                : InsertApi = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m)))
  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M), more: (A, B, C, D, E, F, G, H, I, J, K, L, M)*): InsertApi = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M)])                                          : InsertApi = _insertOp(tpls)
}

trait Insert_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N)                : InsertApi = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n)))
  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N)*): InsertApi = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N)])                                             : InsertApi = _insertOp(tpls)
}

trait Insert_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O)                : InsertApi = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o)))
  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O)*): InsertApi = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O)])                                                : InsertApi = _insertOp(tpls)
}

trait Insert_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P)                : InsertApi = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p)))
  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P)*): InsertApi = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P)])                                                   : InsertApi = _insertOp(tpls)
}

trait Insert_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q)                : InsertApi = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q)))
  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q)*): InsertApi = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q)])                                                      : InsertApi = _insertOp(tpls)
}

trait Insert_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R)                : InsertApi = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r)))
  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R)*): InsertApi = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R)])                                                         : InsertApi = _insertOp(tpls)
}

trait Insert_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S)                : InsertApi = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s)))
  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S)*): InsertApi = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S)])                                                            : InsertApi = _insertOp(tpls)
}

trait Insert_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S, t: T)                : InsertApi = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t)))
  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T)*): InsertApi = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T)])                                                               : InsertApi = _insertOp(tpls)
}

trait Insert_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S, t: T, u: U)                : InsertApi = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u)))
  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U)*): InsertApi = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U)])                                                                  : InsertApi = _insertOp(tpls)
}

trait Insert_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S, t: T, u: U, v: V)                : InsertApi = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v)))
  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V)*): InsertApi = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V)])                                                                     : InsertApi = _insertOp(tpls)
}
