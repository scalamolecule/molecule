// GENERATED CODE ********************************
package molecule.core.action

import molecule.boilerplate.ast.Model.Element

case class Insert_1[A](elements0: List[Element]) {
  final def apply(a: A, as: A*): Insert = InsertTpls(elements0, (a +: as).map(a => Tuple1(a)))
  final def apply(tpls: Seq[A]): Insert = InsertTpls(elements0, tpls.map(a => Tuple1(a)))
}

case class Insert_2[A, B](elements0: List[Element]) {
  final def apply(a: A, b: B)                : Insert = InsertTpls(elements0, Seq((a, b)))
  final def apply(tpl: (A, B), more: (A, B)*): Insert = InsertTpls(elements0, tpl +: more)
  final def apply(tpls: Seq[(A, B)])         : Insert = InsertTpls(elements0, tpls)
}

case class Insert_3[A, B, C](elements0: List[Element]) {
  final def apply(a: A, b: B, c: C)                : Insert = InsertTpls(elements0, Seq((a, b, c)))
  final def apply(tpl: (A, B, C), more: (A, B, C)*): Insert = InsertTpls(elements0, tpl +: more)
  final def apply(tpls: Seq[(A, B, C)])            : Insert = InsertTpls(elements0, tpls)
}

case class Insert_4[A, B, C, D](elements0: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D)                : Insert = InsertTpls(elements0, Seq((a, b, c, d)))
  final def apply(tpl: (A, B, C, D), more: (A, B, C, D)*): Insert = InsertTpls(elements0, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D)])               : Insert = InsertTpls(elements0, tpls)
}

case class Insert_5[A, B, C, D, E](elements0: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E)                : Insert = InsertTpls(elements0, Seq((a, b, c, d, e)))
  final def apply(tpl: (A, B, C, D, E), more: (A, B, C, D, E)*): Insert = InsertTpls(elements0, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E)])                  : Insert = InsertTpls(elements0, tpls)
}

case class Insert_6[A, B, C, D, E, F](elements0: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F)                : Insert = InsertTpls(elements0, Seq((a, b, c, d, e, f)))
  final def apply(tpl: (A, B, C, D, E, F), more: (A, B, C, D, E, F)*): Insert = InsertTpls(elements0, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F)])                     : Insert = InsertTpls(elements0, tpls)
}

case class Insert_7[A, B, C, D, E, F, G](elements0: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G)                : Insert = InsertTpls(elements0, Seq((a, b, c, d, e, f, g)))
  final def apply(tpl: (A, B, C, D, E, F, G), more: (A, B, C, D, E, F, G)*): Insert = InsertTpls(elements0, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G)])                        : Insert = InsertTpls(elements0, tpls)
}

case class Insert_8[A, B, C, D, E, F, G, H](elements0: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H)                : Insert = InsertTpls(elements0, Seq((a, b, c, d, e, f, g, h)))
  final def apply(tpl: (A, B, C, D, E, F, G, H), more: (A, B, C, D, E, F, G, H)*): Insert = InsertTpls(elements0, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H)])                           : Insert = InsertTpls(elements0, tpls)
}

case class Insert_9[A, B, C, D, E, F, G, H, I](elements0: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I)                : Insert = InsertTpls(elements0, Seq((a, b, c, d, e, f, g, h, i)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I), more: (A, B, C, D, E, F, G, H, I)*): Insert = InsertTpls(elements0, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I)])                              : Insert = InsertTpls(elements0, tpls)
}

case class Insert_10[A, B, C, D, E, F, G, H, I, J](elements0: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J)                : Insert = InsertTpls(elements0, Seq((a, b, c, d, e, f, g, h, i, j)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J), more: (A, B, C, D, E, F, G, H, I, J)*): Insert = InsertTpls(elements0, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J)])                                 : Insert = InsertTpls(elements0, tpls)
}

case class Insert_11[A, B, C, D, E, F, G, H, I, J, K](elements0: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K)                : Insert = InsertTpls(elements0, Seq((a, b, c, d, e, f, g, h, i, j, k)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K), more: (A, B, C, D, E, F, G, H, I, J, K)*): Insert = InsertTpls(elements0, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K)])                                    : Insert = InsertTpls(elements0, tpls)
}

case class Insert_12[A, B, C, D, E, F, G, H, I, J, K, L](elements0: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L)                : Insert = InsertTpls(elements0, Seq((a, b, c, d, e, f, g, h, i, j, k, l)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L), more: (A, B, C, D, E, F, G, H, I, J, K, L)*): Insert = InsertTpls(elements0, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L)])                                       : Insert = InsertTpls(elements0, tpls)
}

case class Insert_13[A, B, C, D, E, F, G, H, I, J, K, L, M](elements0: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M)                : Insert = InsertTpls(elements0, Seq((a, b, c, d, e, f, g, h, i, j, k, l, m)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M), more: (A, B, C, D, E, F, G, H, I, J, K, L, M)*): Insert = InsertTpls(elements0, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M)])                                          : Insert = InsertTpls(elements0, tpls)
}

case class Insert_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N](elements0: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N)                : Insert = InsertTpls(elements0, Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N)*): Insert = InsertTpls(elements0, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N)])                                             : Insert = InsertTpls(elements0, tpls)
}

case class Insert_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](elements0: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O)                : Insert = InsertTpls(elements0, Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O)*): Insert = InsertTpls(elements0, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O)])                                                : Insert = InsertTpls(elements0, tpls)
}

case class Insert_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](elements0: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P)                : Insert = InsertTpls(elements0, Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P)*): Insert = InsertTpls(elements0, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P)])                                                   : Insert = InsertTpls(elements0, tpls)
}

case class Insert_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](elements0: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q)                : Insert = InsertTpls(elements0, Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q)*): Insert = InsertTpls(elements0, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q)])                                                      : Insert = InsertTpls(elements0, tpls)
}

case class Insert_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](elements0: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R)                : Insert = InsertTpls(elements0, Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R)*): Insert = InsertTpls(elements0, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R)])                                                         : Insert = InsertTpls(elements0, tpls)
}

case class Insert_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](elements0: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S)                : Insert = InsertTpls(elements0, Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S)*): Insert = InsertTpls(elements0, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S)])                                                            : Insert = InsertTpls(elements0, tpls)
}

case class Insert_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](elements0: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S, t: T)                : Insert = InsertTpls(elements0, Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T)*): Insert = InsertTpls(elements0, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T)])                                                               : Insert = InsertTpls(elements0, tpls)
}

case class Insert_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](elements0: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S, t: T, u: U)                : Insert = InsertTpls(elements0, Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U)*): Insert = InsertTpls(elements0, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U)])                                                                  : Insert = InsertTpls(elements0, tpls)
}

case class Insert_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](elements0: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S, t: T, u: U, v: V)                : Insert = InsertTpls(elements0, Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V)*): Insert = InsertTpls(elements0, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V)])                                                                     : Insert = InsertTpls(elements0, tpls)
}
