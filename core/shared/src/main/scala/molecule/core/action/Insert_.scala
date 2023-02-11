// GENERATED CODE ********************************
package molecule.core.action

trait Insert_ {
  // Implemented for each db and JS/JVM platform
  private[core] def _insertOp(tpls: Seq[Product]): Insert
}

trait Insert_1[A] extends Insert_ {
  def apply(a: A, as: A*): Insert = _insertOp((a +: as).map(a => Tuple1(a)))
  def apply(tpls: Seq[A]): Insert = _insertOp(tpls.map(a => Tuple1(a)))
}

trait Insert_2[A, B] extends Insert_ {
  def apply(a: A, b: B)                : Insert = _insertOp(Seq((a, b)))
  def apply(tpl: (A, B), more: (A, B)*): Insert = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B)])         : Insert = _insertOp(tpls)
}

trait Insert_3[A, B, C] extends Insert_ {
  def apply(a: A, b: B, c: C)                : Insert = _insertOp(Seq((a, b, c)))
  def apply(tpl: (A, B, C), more: (A, B, C)*): Insert = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C)])            : Insert = _insertOp(tpls)
}

trait Insert_4[A, B, C, D] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D)                : Insert = _insertOp(Seq((a, b, c, d)))
  def apply(tpl: (A, B, C, D), more: (A, B, C, D)*): Insert = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D)])               : Insert = _insertOp(tpls)
}

trait Insert_5[A, B, C, D, E] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E)                : Insert = _insertOp(Seq((a, b, c, d, e)))
  def apply(tpl: (A, B, C, D, E), more: (A, B, C, D, E)*): Insert = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E)])                  : Insert = _insertOp(tpls)
}

trait Insert_6[A, B, C, D, E, F] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F)                : Insert = _insertOp(Seq((a, b, c, d, e, f)))
  def apply(tpl: (A, B, C, D, E, F), more: (A, B, C, D, E, F)*): Insert = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F)])                     : Insert = _insertOp(tpls)
}

trait Insert_7[A, B, C, D, E, F, G] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G)                : Insert = _insertOp(Seq((a, b, c, d, e, f, g)))
  def apply(tpl: (A, B, C, D, E, F, G), more: (A, B, C, D, E, F, G)*): Insert = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F, G)])                        : Insert = _insertOp(tpls)
}

trait Insert_8[A, B, C, D, E, F, G, H] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H)                : Insert = _insertOp(Seq((a, b, c, d, e, f, g, h)))
  def apply(tpl: (A, B, C, D, E, F, G, H), more: (A, B, C, D, E, F, G, H)*): Insert = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F, G, H)])                           : Insert = _insertOp(tpls)
}

trait Insert_9[A, B, C, D, E, F, G, H, I] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I)                : Insert = _insertOp(Seq((a, b, c, d, e, f, g, h, i)))
  def apply(tpl: (A, B, C, D, E, F, G, H, I), more: (A, B, C, D, E, F, G, H, I)*): Insert = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I)])                              : Insert = _insertOp(tpls)
}

trait Insert_10[A, B, C, D, E, F, G, H, I, J] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J)                : Insert = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j)))
  def apply(tpl: (A, B, C, D, E, F, G, H, I, J), more: (A, B, C, D, E, F, G, H, I, J)*): Insert = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J)])                                 : Insert = _insertOp(tpls)
}

trait Insert_11[A, B, C, D, E, F, G, H, I, J, K] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K)                : Insert = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k)))
  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K), more: (A, B, C, D, E, F, G, H, I, J, K)*): Insert = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K)])                                    : Insert = _insertOp(tpls)
}

trait Insert_12[A, B, C, D, E, F, G, H, I, J, K, L] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L)                : Insert = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l)))
  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L), more: (A, B, C, D, E, F, G, H, I, J, K, L)*): Insert = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L)])                                       : Insert = _insertOp(tpls)
}

trait Insert_13[A, B, C, D, E, F, G, H, I, J, K, L, M] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M)                : Insert = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m)))
  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M), more: (A, B, C, D, E, F, G, H, I, J, K, L, M)*): Insert = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M)])                                          : Insert = _insertOp(tpls)
}

trait Insert_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N)                : Insert = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n)))
  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N)*): Insert = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N)])                                             : Insert = _insertOp(tpls)
}

trait Insert_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O)                : Insert = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o)))
  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O)*): Insert = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O)])                                                : Insert = _insertOp(tpls)
}

trait Insert_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P)                : Insert = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p)))
  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P)*): Insert = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P)])                                                   : Insert = _insertOp(tpls)
}

trait Insert_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q)                : Insert = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q)))
  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q)*): Insert = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q)])                                                      : Insert = _insertOp(tpls)
}

trait Insert_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R)                : Insert = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r)))
  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R)*): Insert = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R)])                                                         : Insert = _insertOp(tpls)
}

trait Insert_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S)                : Insert = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s)))
  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S)*): Insert = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S)])                                                            : Insert = _insertOp(tpls)
}

trait Insert_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S, t: T)                : Insert = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t)))
  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T)*): Insert = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T)])                                                               : Insert = _insertOp(tpls)
}

trait Insert_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S, t: T, u: U)                : Insert = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u)))
  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U)*): Insert = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U)])                                                                  : Insert = _insertOp(tpls)
}

trait Insert_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] extends Insert_ {
  def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S, t: T, u: U, v: V)                : Insert = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v)))
  def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V)*): Insert = _insertOp(tpl +: more)
  def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V)])                                                                     : Insert = _insertOp(tpls)
}
