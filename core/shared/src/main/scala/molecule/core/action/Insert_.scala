// GENERATED CODE ********************************
package molecule.core.action

import molecule.core.ast.DataModel.Element

case class Insert_1[A](elements: List[Element]) {
  final def apply(a: A, as: A*) = Insert(elements, (a +: as).map(a => Tuple1(a)))
  final def apply(tpls: Seq[A]) = Insert(elements, tpls.map(a => Tuple1(a)))
}

case class Insert_2[A, B](elements: List[Element]) {
  final def apply(a: A, b: B)                 = Insert(elements, Seq((a, b)))
  final def apply(tpl: (A, B), more: (A, B)*) = Insert(elements, tpl +: more)
  final def apply(tpls: Seq[(A, B)])          = Insert(elements, tpls)
}

case class Insert_3[A, B, C](elements: List[Element]) {
  final def apply(a: A, b: B, c: C)                 = Insert(elements, Seq((a, b, c)))
  final def apply(tpl: (A, B, C), more: (A, B, C)*) = Insert(elements, tpl +: more)
  final def apply(tpls: Seq[(A, B, C)])             = Insert(elements, tpls)
}

case class Insert_4[A, B, C, D](elements: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D)                 = Insert(elements, Seq((a, b, c, d)))
  final def apply(tpl: (A, B, C, D), more: (A, B, C, D)*) = Insert(elements, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D)])                = Insert(elements, tpls)
}

case class Insert_5[A, B, C, D, E](elements: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E)                 = Insert(elements, Seq((a, b, c, d, e)))
  final def apply(tpl: (A, B, C, D, E), more: (A, B, C, D, E)*) = Insert(elements, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E)])                   = Insert(elements, tpls)
}

case class Insert_6[A, B, C, D, E, F](elements: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F)                 = Insert(elements, Seq((a, b, c, d, e, f)))
  final def apply(tpl: (A, B, C, D, E, F), more: (A, B, C, D, E, F)*) = Insert(elements, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F)])                      = Insert(elements, tpls)
}

case class Insert_7[A, B, C, D, E, F, G](elements: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G)                 = Insert(elements, Seq((a, b, c, d, e, f, g)))
  final def apply(tpl: (A, B, C, D, E, F, G), more: (A, B, C, D, E, F, G)*) = Insert(elements, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G)])                         = Insert(elements, tpls)
}

case class Insert_8[A, B, C, D, E, F, G, H](elements: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H)                 = Insert(elements, Seq((a, b, c, d, e, f, g, h)))
  final def apply(tpl: (A, B, C, D, E, F, G, H), more: (A, B, C, D, E, F, G, H)*) = Insert(elements, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H)])                            = Insert(elements, tpls)
}

case class Insert_9[A, B, C, D, E, F, G, H, I](elements: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I)                 = Insert(elements, Seq((a, b, c, d, e, f, g, h, i)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I), more: (A, B, C, D, E, F, G, H, I)*) = Insert(elements, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I)])                               = Insert(elements, tpls)
}

case class Insert_10[A, B, C, D, E, F, G, H, I, J](elements: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J)                 = Insert(elements, Seq((a, b, c, d, e, f, g, h, i, j)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J), more: (A, B, C, D, E, F, G, H, I, J)*) = Insert(elements, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J)])                                  = Insert(elements, tpls)
}

case class Insert_11[A, B, C, D, E, F, G, H, I, J, K](elements: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K)                 = Insert(elements, Seq((a, b, c, d, e, f, g, h, i, j, k)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K), more: (A, B, C, D, E, F, G, H, I, J, K)*) = Insert(elements, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K)])                                     = Insert(elements, tpls)
}

case class Insert_12[A, B, C, D, E, F, G, H, I, J, K, L](elements: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L)                 = Insert(elements, Seq((a, b, c, d, e, f, g, h, i, j, k, l)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L), more: (A, B, C, D, E, F, G, H, I, J, K, L)*) = Insert(elements, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L)])                                        = Insert(elements, tpls)
}

case class Insert_13[A, B, C, D, E, F, G, H, I, J, K, L, M](elements: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M)                 = Insert(elements, Seq((a, b, c, d, e, f, g, h, i, j, k, l, m)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M), more: (A, B, C, D, E, F, G, H, I, J, K, L, M)*) = Insert(elements, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M)])                                           = Insert(elements, tpls)
}

case class Insert_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N](elements: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N)                 = Insert(elements, Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N)*) = Insert(elements, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N)])                                              = Insert(elements, tpls)
}

case class Insert_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](elements: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O)                 = Insert(elements, Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O)*) = Insert(elements, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O)])                                                 = Insert(elements, tpls)
}

case class Insert_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](elements: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P)                 = Insert(elements, Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P)*) = Insert(elements, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P)])                                                    = Insert(elements, tpls)
}

case class Insert_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](elements: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q)                 = Insert(elements, Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q)*) = Insert(elements, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q)])                                                       = Insert(elements, tpls)
}

case class Insert_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](elements: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R)                 = Insert(elements, Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R)*) = Insert(elements, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R)])                                                          = Insert(elements, tpls)
}

case class Insert_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](elements: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S)                 = Insert(elements, Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S)*) = Insert(elements, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S)])                                                             = Insert(elements, tpls)
}

case class Insert_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](elements: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S, t: T)                 = Insert(elements, Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T)*) = Insert(elements, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T)])                                                                = Insert(elements, tpls)
}

case class Insert_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](elements: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S, t: T, u: U)                 = Insert(elements, Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U)*) = Insert(elements, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U)])                                                                   = Insert(elements, tpls)
}

case class Insert_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](elements: List[Element]) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S, t: T, u: U, v: V)                 = Insert(elements, Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V)*) = Insert(elements, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V)])                                                                      = Insert(elements, tpls)
}
