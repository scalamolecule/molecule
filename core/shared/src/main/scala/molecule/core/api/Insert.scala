// GENERATED CODE ********************************
package molecule.core.api

trait Insert {
  private[core] def _insertOp(tpls: Seq[Product]): InsertOps
}

object Insert  {

  trait Insert_1[A] extends Insert {
    def apply(a: A, as: A*): InsertOps = _insertOp((a +: as).map(a => Tuple1(a)))
    def apply(tpls: Seq[A]): InsertOps = _insertOp(tpls.map(a => Tuple1(a)))
  }

  trait Insert_2[A, B] extends Insert {
    def apply(a: A, b: B)                : InsertOps = _insertOp(Seq((a, b)))
    def apply(tpl: (A, B), more: (A, B)*): InsertOps = _insertOp(tpl +: more)
    def apply(tpls: Seq[(A, B)])         : InsertOps = _insertOp(tpls)
  }

  trait Insert_3[A, B, C] extends Insert {
    def apply(a: A, b: B, c: C)                : InsertOps = _insertOp(Seq((a, b, c)))
    def apply(tpl: (A, B, C), more: (A, B, C)*): InsertOps = _insertOp(tpl +: more)
    def apply(tpls: Seq[(A, B, C)])            : InsertOps = _insertOp(tpls)
  }

  trait Insert_4[A, B, C, D] extends Insert {
    def apply(a: A, b: B, c: C, d: D)                : InsertOps = _insertOp(Seq((a, b, c, d)))
    def apply(tpl: (A, B, C, D), more: (A, B, C, D)*): InsertOps = _insertOp(tpl +: more)
    def apply(tpls: Seq[(A, B, C, D)])               : InsertOps = _insertOp(tpls)
  }

  trait Insert_5[A, B, C, D, E] extends Insert {
    def apply(a: A, b: B, c: C, d: D, e: E)                : InsertOps = _insertOp(Seq((a, b, c, d, e)))
    def apply(tpl: (A, B, C, D, E), more: (A, B, C, D, E)*): InsertOps = _insertOp(tpl +: more)
    def apply(tpls: Seq[(A, B, C, D, E)])                  : InsertOps = _insertOp(tpls)
  }

  trait Insert_6[A, B, C, D, E, F] extends Insert {
    def apply(a: A, b: B, c: C, d: D, e: E, f: F)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f)))
    def apply(tpl: (A, B, C, D, E, F), more: (A, B, C, D, E, F)*): InsertOps = _insertOp(tpl +: more)
    def apply(tpls: Seq[(A, B, C, D, E, F)])                     : InsertOps = _insertOp(tpls)
  }

  trait Insert_7[A, B, C, D, E, F, G] extends Insert {
    def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f, g)))
    def apply(tpl: (A, B, C, D, E, F, G), more: (A, B, C, D, E, F, G)*): InsertOps = _insertOp(tpl +: more)
    def apply(tpls: Seq[(A, B, C, D, E, F, G)])                        : InsertOps = _insertOp(tpls)
  }

  trait Insert_8[A, B, C, D, E, F, G, H] extends Insert {
    def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f, g, h)))
    def apply(tpl: (A, B, C, D, E, F, G, H), more: (A, B, C, D, E, F, G, H)*): InsertOps = _insertOp(tpl +: more)
    def apply(tpls: Seq[(A, B, C, D, E, F, G, H)])                           : InsertOps = _insertOp(tpls)
  }

  trait Insert_9[A, B, C, D, E, F, G, H, I] extends Insert {
    def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f, g, h, i)))
    def apply(tpl: (A, B, C, D, E, F, G, H, I), more: (A, B, C, D, E, F, G, H, I)*): InsertOps = _insertOp(tpl +: more)
    def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I)])                              : InsertOps = _insertOp(tpls)
  }

  trait Insert_10[A, B, C, D, E, F, G, H, I, J] extends Insert {
    def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j)))
    def apply(tpl: (A, B, C, D, E, F, G, H, I, J), more: (A, B, C, D, E, F, G, H, I, J)*): InsertOps = _insertOp(tpl +: more)
    def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J)])                                 : InsertOps = _insertOp(tpls)
  }

  trait Insert_11[A, B, C, D, E, F, G, H, I, J, K] extends Insert {
    def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k)))
    def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K), more: (A, B, C, D, E, F, G, H, I, J, K)*): InsertOps = _insertOp(tpl +: more)
    def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K)])                                    : InsertOps = _insertOp(tpls)
  }

  trait Insert_12[A, B, C, D, E, F, G, H, I, J, K, L] extends Insert {
    def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l)))
    def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L), more: (A, B, C, D, E, F, G, H, I, J, K, L)*): InsertOps = _insertOp(tpl +: more)
    def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L)])                                       : InsertOps = _insertOp(tpls)
  }

  trait Insert_13[A, B, C, D, E, F, G, H, I, J, K, L, M] extends Insert {
    def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m)))
    def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M), more: (A, B, C, D, E, F, G, H, I, J, K, L, M)*): InsertOps = _insertOp(tpl +: more)
    def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M)])                                          : InsertOps = _insertOp(tpls)
  }

  trait Insert_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N] extends Insert {
    def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n)))
    def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N)*): InsertOps = _insertOp(tpl +: more)
    def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N)])                                             : InsertOps = _insertOp(tpls)
  }

  trait Insert_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] extends Insert {
    def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o)))
    def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O)*): InsertOps = _insertOp(tpl +: more)
    def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O)])                                                : InsertOps = _insertOp(tpls)
  }

  trait Insert_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] extends Insert {
    def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p)))
    def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P)*): InsertOps = _insertOp(tpl +: more)
    def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P)])                                                   : InsertOps = _insertOp(tpls)
  }

  trait Insert_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] extends Insert {
    def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q)))
    def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q)*): InsertOps = _insertOp(tpl +: more)
    def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q)])                                                      : InsertOps = _insertOp(tpls)
  }

  trait Insert_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] extends Insert {
    def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r)))
    def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R)*): InsertOps = _insertOp(tpl +: more)
    def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R)])                                                         : InsertOps = _insertOp(tpls)
  }

  trait Insert_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] extends Insert {
    def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s)))
    def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S)*): InsertOps = _insertOp(tpl +: more)
    def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S)])                                                            : InsertOps = _insertOp(tpls)
  }

  trait Insert_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] extends Insert {
    def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S, t: T)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t)))
    def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T)*): InsertOps = _insertOp(tpl +: more)
    def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T)])                                                               : InsertOps = _insertOp(tpls)
  }

  trait Insert_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] extends Insert {
    def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S, t: T, u: U)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u)))
    def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U)*): InsertOps = _insertOp(tpl +: more)
    def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U)])                                                                  : InsertOps = _insertOp(tpls)
  }

  trait Insert_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] extends Insert {
    def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S, t: T, u: U, v: V)                : InsertOps = _insertOp(Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v)))
    def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V)*): InsertOps = _insertOp(tpl +: more)
    def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V)])                                                                     : InsertOps = _insertOp(tpls)
  }
}