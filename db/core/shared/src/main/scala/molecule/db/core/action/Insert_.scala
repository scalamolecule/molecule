// GENERATED CODE ********************************
package molecule.db.core.action

import molecule.core.dataModel.DataModel

case class Insert_1[A](dataModel: DataModel) {
  final def apply(a: A, as: A*) = Insert(dataModel, (a +: as).map(a => Tuple1(a)))
  final def apply(tpls: Seq[A]) = Insert(dataModel, tpls.map(a => Tuple1(a)))
}

case class Insert_2[A, B](dataModel: DataModel) {
  final def apply(a: A, b: B)                 = Insert(dataModel, Seq((a, b)))
  final def apply(tpl: (A, B), more: (A, B)*) = Insert(dataModel, tpl +: more)
  final def apply(tpls: Seq[(A, B)])          = Insert(dataModel, tpls)
}

case class Insert_3[A, B, C](dataModel: DataModel) {
  final def apply(a: A, b: B, c: C)                 = Insert(dataModel, Seq((a, b, c)))
  final def apply(tpl: (A, B, C), more: (A, B, C)*) = Insert(dataModel, tpl +: more)
  final def apply(tpls: Seq[(A, B, C)])             = Insert(dataModel, tpls)
}

case class Insert_4[A, B, C, D](dataModel: DataModel) {
  final def apply(a: A, b: B, c: C, d: D)                 = Insert(dataModel, Seq((a, b, c, d)))
  final def apply(tpl: (A, B, C, D), more: (A, B, C, D)*) = Insert(dataModel, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D)])                = Insert(dataModel, tpls)
}

case class Insert_5[A, B, C, D, E](dataModel: DataModel) {
  final def apply(a: A, b: B, c: C, d: D, e: E)                 = Insert(dataModel, Seq((a, b, c, d, e)))
  final def apply(tpl: (A, B, C, D, E), more: (A, B, C, D, E)*) = Insert(dataModel, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E)])                   = Insert(dataModel, tpls)
}

case class Insert_6[A, B, C, D, E, F](dataModel: DataModel) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F)                 = Insert(dataModel, Seq((a, b, c, d, e, f)))
  final def apply(tpl: (A, B, C, D, E, F), more: (A, B, C, D, E, F)*) = Insert(dataModel, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F)])                      = Insert(dataModel, tpls)
}

case class Insert_7[A, B, C, D, E, F, G](dataModel: DataModel) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G)                 = Insert(dataModel, Seq((a, b, c, d, e, f, g)))
  final def apply(tpl: (A, B, C, D, E, F, G), more: (A, B, C, D, E, F, G)*) = Insert(dataModel, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G)])                         = Insert(dataModel, tpls)
}

case class Insert_8[A, B, C, D, E, F, G, H](dataModel: DataModel) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H)                 = Insert(dataModel, Seq((a, b, c, d, e, f, g, h)))
  final def apply(tpl: (A, B, C, D, E, F, G, H), more: (A, B, C, D, E, F, G, H)*) = Insert(dataModel, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H)])                            = Insert(dataModel, tpls)
}

case class Insert_9[A, B, C, D, E, F, G, H, I](dataModel: DataModel) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I)                 = Insert(dataModel, Seq((a, b, c, d, e, f, g, h, i)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I), more: (A, B, C, D, E, F, G, H, I)*) = Insert(dataModel, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I)])                               = Insert(dataModel, tpls)
}

case class Insert_10[A, B, C, D, E, F, G, H, I, J](dataModel: DataModel) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J)                 = Insert(dataModel, Seq((a, b, c, d, e, f, g, h, i, j)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J), more: (A, B, C, D, E, F, G, H, I, J)*) = Insert(dataModel, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J)])                                  = Insert(dataModel, tpls)
}

case class Insert_11[A, B, C, D, E, F, G, H, I, J, K](dataModel: DataModel) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K)                 = Insert(dataModel, Seq((a, b, c, d, e, f, g, h, i, j, k)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K), more: (A, B, C, D, E, F, G, H, I, J, K)*) = Insert(dataModel, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K)])                                     = Insert(dataModel, tpls)
}

case class Insert_12[A, B, C, D, E, F, G, H, I, J, K, L](dataModel: DataModel) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L)                 = Insert(dataModel, Seq((a, b, c, d, e, f, g, h, i, j, k, l)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L), more: (A, B, C, D, E, F, G, H, I, J, K, L)*) = Insert(dataModel, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L)])                                        = Insert(dataModel, tpls)
}

case class Insert_13[A, B, C, D, E, F, G, H, I, J, K, L, M](dataModel: DataModel) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M)                 = Insert(dataModel, Seq((a, b, c, d, e, f, g, h, i, j, k, l, m)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M), more: (A, B, C, D, E, F, G, H, I, J, K, L, M)*) = Insert(dataModel, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M)])                                           = Insert(dataModel, tpls)
}

case class Insert_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N](dataModel: DataModel) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N)                 = Insert(dataModel, Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N)*) = Insert(dataModel, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N)])                                              = Insert(dataModel, tpls)
}

case class Insert_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](dataModel: DataModel) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O)                 = Insert(dataModel, Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O)*) = Insert(dataModel, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O)])                                                 = Insert(dataModel, tpls)
}

case class Insert_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](dataModel: DataModel) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P)                 = Insert(dataModel, Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P)*) = Insert(dataModel, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P)])                                                    = Insert(dataModel, tpls)
}

case class Insert_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](dataModel: DataModel) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q)                 = Insert(dataModel, Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q)*) = Insert(dataModel, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q)])                                                       = Insert(dataModel, tpls)
}

case class Insert_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](dataModel: DataModel) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R)                 = Insert(dataModel, Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R)*) = Insert(dataModel, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R)])                                                          = Insert(dataModel, tpls)
}

case class Insert_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](dataModel: DataModel) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S)                 = Insert(dataModel, Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S)*) = Insert(dataModel, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S)])                                                             = Insert(dataModel, tpls)
}

case class Insert_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](dataModel: DataModel) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S, t: T)                 = Insert(dataModel, Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T)*) = Insert(dataModel, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T)])                                                                = Insert(dataModel, tpls)
}

case class Insert_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](dataModel: DataModel) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S, t: T, u: U)                 = Insert(dataModel, Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U)*) = Insert(dataModel, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U)])                                                                   = Insert(dataModel, tpls)
}

case class Insert_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](dataModel: DataModel) {
  final def apply(a: A, b: B, c: C, d: D, e: E, f: F, g: G, h: H, i: I, j: J, k: K, l: L, m: M, n: N, o: O, p: P, q: Q, r: R, s: S, t: T, u: U, v: V)                 = Insert(dataModel, Seq((a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v)))
  final def apply(tpl: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V), more: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V)*) = Insert(dataModel, tpl +: more)
  final def apply(tpls: Seq[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V)])                                                                      = Insert(dataModel, tpls)
}
