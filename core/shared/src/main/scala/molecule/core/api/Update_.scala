//// GENERATED CODE ********************************
//package molecule.core.api
//
//import molecule.core.api.ops.InsertOps
//
//trait Update_ {
//  // Implemented for each db and JS/JVM platform
//  private[core] def _insertOp(tpls: Seq[Product]): InsertOps
//}
//
//trait Update_1[A] extends Update_ {
//  def apply(a: A, as: A*): InsertOps = _insertOp((a +: as).map(a => Tuple1(a)))
//  def apply(tpls: Seq[A]): InsertOps = _insertOp(tpls.map(a => Tuple1(a)))
//}
//
//trait Update_2[A, B] extends Update_ {
//  def apply(a: A, b: B)                : InsertOps = _insertOp(Seq((a, b)))
//  def apply(tpl: (A, B), more: (A, B)*): InsertOps = _insertOp(tpl +: more)
//  def apply(tpls: Seq[(A, B)])         : InsertOps = _insertOp(tpls)
//}
//
//trait Update_3[A, B, C] extends Update_ {
//  def apply(a: A, b: B, c: C)                : InsertOps = _insertOp(Seq((a, b, c)))
//  def apply(tpl: (A, B, C), more: (A, B, C)*): InsertOps = _insertOp(tpl +: more)
//  def apply(tpls: Seq[(A, B, C)])            : InsertOps = _insertOp(tpls)
//}
//
//trait Update_4[A, B, C, D] extends Update_ {
//  def apply(a: A, b: B, c: C, d: D)                : InsertOps = _insertOp(Seq((a, b, c, d)))
//  def apply(tpl: (A, B, C, D), more: (A, B, C, D)*): InsertOps = _insertOp(tpl +: more)
//  def apply(tpls: Seq[(A, B, C, D)])               : InsertOps = _insertOp(tpls)
//}
//
//trait Update_5[A, B, C, D, E] extends Update_ {
//  def apply(a: A, b: B, c: C, d: D, e: E)                : InsertOps = _insertOp(Seq((a, b, c, d, e)))
//  def apply(tpl: (A, B, C, D, E), more: (A, B, C, D, E)*): InsertOps = _insertOp(tpl +: more)
//  def apply(tpls: Seq[(A, B, C, D, E)])                  : InsertOps = _insertOp(tpls)
//}
//
//trait Update_6[A, B, C, D, E, F] extends Update_ {
//  def apply(a: A, b: B, c: C, d: D, e: E, f: F)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f)))
//  def apply(tpl: (A, B, C, D, E, F), more: (A, B, C, D, E, F)*): InsertOps = _insertOp(tpl +: more)
//  def apply(tpls: Seq[(A, B, C, D, E, F)])                     : InsertOps = _insertOp(tpls)
//}
//
//trait Update_7[A, B, C, D, E, F, G] extends Update_ {
//  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f, g)))
//  def apply(tpl: (A, B, C, D, E, F, G), more: (A, B, C, D, E, F, G)*): InsertOps = _insertOp(tpl +: more)
//  def apply(tpls: Seq[(A, B, C, D, E, F, G)])                        : InsertOps = _insertOp(tpls)
//}
//
//trait Update_8[A, B, C, D, E, F, G, H] extends Update_ {
//  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f, g, h)))
//  def apply(tpl: (A, B, C, D, E, F, G, H), more: (A, B, C, D, E, F, G, H)*): InsertOps = _insertOp(tpl +: more)
//  def apply(tpls: Seq[(A, B, C, D, E, F, G, H)])                           : InsertOps = _insertOp(tpls)
//}
//
//trait Update_9[A, B, C, D, E, F, G, H, I] extends Update_ {
//  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f, g, h, i)))
//  def apply(tpl: (A, B, C, D, E, F, G, H, I), more: (A, B, C, D, E, F, G, H, I)*): InsertOps = _insertOp(tpl +: more)
//  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I)])                              : InsertOps = _insertOp(tpls)
//}
//
//trait Update_10[A, B, C, D, E, F, G, H, I, J] extends Update_ {
//  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j)))
//  def apply(tpl: (A, B, C, D, E, F, G, H, I, J), more: (A, B, C, D, E, F, G, H, I, J)*): InsertOps = _insertOp(tpl +: more)
//  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J)])                                 : InsertOps = _insertOp(tpls)
//}
//
//trait Update_11[A, B, C, D, E, F, G, H, I, J, K] extends Update_ {
//  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k)))
//  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K), more: (A, B, C, D, E, F, G, H, I, J, K)*): InsertOps = _insertOp(tpl +: more)
//  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K)])                                    : InsertOps = _insertOp(tpls)
//}
//
//trait Update_12[A, B, C, D, E, F, G, H, I, J, K, L] extends Update_ {
//  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l)))
//  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L), more: (A, B, C, D, E, F, G, H, I, J, K, L)*): InsertOps = _insertOp(tpl +: more)
//  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L)])                                       : InsertOps = _insertOp(tpls)
//}
//
//trait Update_13[A, B, C, D, E, F, G, H, I, J, K, L, M] extends Update_ {
//  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m)))
//  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M), more: (A, B, C, D, E, F, G, H, I, J, K, L, M)*): InsertOps = _insertOp(tpl +: more)
//  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M)])                                          : InsertOps = _insertOp(tpls)
//}
//
//trait Update_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N] extends Update_ {
//  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n)))
//  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N)*): InsertOps = _insertOp(tpl +: more)
//  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N)])                                             : InsertOps = _insertOp(tpls)
//}
//
//trait Update_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] extends Update_ {
//  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o)))
//  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O)*): InsertOps = _insertOp(tpl +: more)
//  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O)])                                                : InsertOps = _insertOp(tpls)
//}
//
//trait Update_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] extends Update_ {
//  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p)))
//  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P)*): InsertOps = _insertOp(tpl +: more)
//  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P)])                                                   : InsertOps = _insertOp(tpls)
//}
//
//trait Update_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] extends Update_ {
//  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q)))
//  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q)*): InsertOps = _insertOp(tpl +: more)
//  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q)])                                                      : InsertOps = _insertOp(tpls)
//}
//
//trait Update_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] extends Update_ {
//  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r)))
//  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R)*): InsertOps = _insertOp(tpl +: more)
//  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R)])                                                         : InsertOps = _insertOp(tpls)
//}
//
//trait Update_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] extends Update_ {
//  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s)))
//  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S)*): InsertOps = _insertOp(tpl +: more)
//  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S)])                                                            : InsertOps = _insertOp(tpls)
//}
//
//trait Update_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] extends Update_ {
//  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S, t: T)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t)))
//  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T)*): InsertOps = _insertOp(tpl +: more)
//  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T)])                                                               : InsertOps = _insertOp(tpls)
//}
//
//trait Update_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] extends Update_ {
//  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S, t: T, u: U)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u)))
//  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U)*): InsertOps = _insertOp(tpl +: more)
//  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U)])                                                                  : InsertOps = _insertOp(tpls)
//}
//
//trait Update_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] extends Update_ {
//  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S, t: T, u: U, v: V)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v)))
//  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V)*): InsertOps = _insertOp(tpl +: more)
//  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V)])                                                                     : InsertOps = _insertOp(tpls)
//}
