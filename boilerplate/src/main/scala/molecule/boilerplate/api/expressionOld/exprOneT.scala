//package molecule.boilerplate.api.expression
//
//import molecule.boilerplate.api.Keywords.unify
//import molecule.boilerplate.markers.argKindMarkers._
//
//
//trait ExprOneT_0[Attr, t, Ns[_, _]] {
//  def apply()                : Ns[Attr, t]          = ???
//  def apply(unify: unify)    : Ns[Attr, t]          = ???
//  def apply(v    : t, vs: t*): Ns[Attr, t] with Vs  = ???
//  def apply(vs   : Seq[t])   : Ns[Attr, t] with CVs = ???
//  def ==   (v    : t)        : Ns[Attr, t] with Vs  = ???
//  def ==   (v    : t, vs: t*): Ns[Attr, t] with Vs  = ???
//  def ==   (vs   : Seq[t])   : Ns[Attr, t] with CVs = ???
//  def not  (v    : t, vs: t*): Ns[Attr, t] with Vs  = ???
//  def not  (vs   : Seq[t])   : Ns[Attr, t] with CVs = ???
//  def !=   (v    : t)        : Ns[Attr, t] with Vs  = ???
//  def !=   (v    : t, vs: t*): Ns[Attr, t] with Vs  = ???
//  def !=   (vs   : Seq[t])   : Ns[Attr, t] with CVs = ???
//  def <    (upper: t)        : Ns[Attr, t]          = ???
//  def <=   (upper: t)        : Ns[Attr, t]          = ???
//  def >    (lower: t)        : Ns[Attr, t]          = ???
//  def >=   (lower: t)        : Ns[Attr, t]          = ???
//}
//
//trait ExprOneT_1[Attr, t, A, Ns[_, _, _]] {
//  def apply()                : Ns[Attr, t, A]          = ???
//  def apply(unify: unify)    : Ns[Attr, t, A]          = ???
//  def apply(v    : t, vs: t*): Ns[Attr, t, A] with Vs  = ???
//  def apply(vs   : Seq[t])   : Ns[Attr, t, A] with CVs = ???
//  def ==   (v    : t)        : Ns[Attr, t, A] with Vs  = ???
//  def ==   (v    : t, vs: t*): Ns[Attr, t, A] with Vs  = ???
//  def ==   (vs   : Seq[t])   : Ns[Attr, t, A] with CVs = ???
//  def not  (v    : t, vs: t*): Ns[Attr, t, A] with Vs  = ???
//  def not  (vs   : Seq[t])   : Ns[Attr, t, A] with CVs = ???
//  def !=   (v    : t)        : Ns[Attr, t, A] with Vs  = ???
//  def !=   (v    : t, vs: t*): Ns[Attr, t, A] with Vs  = ???
//  def !=   (vs   : Seq[t])   : Ns[Attr, t, A] with CVs = ???
//  def <    (upper: t)        : Ns[Attr, t, A]          = ???
//  def <=   (upper: t)        : Ns[Attr, t, A]          = ???
//  def >    (lower: t)        : Ns[Attr, t, A]          = ???
//  def >=   (lower: t)        : Ns[Attr, t, A]          = ???
//}
//
//trait ExprOneT_2[Attr, t, A, B, Ns[_, _, _, _]] {
//  def apply()                : Ns[Attr, t, A, B]          = ???
//  def apply(unify: unify)    : Ns[Attr, t, A, B]          = ???
//  def apply(v    : t, vs: t*): Ns[Attr, t, A, B] with Vs  = ???
//  def apply(vs   : Seq[t])   : Ns[Attr, t, A, B] with CVs = ???
//  def ==   (v    : t)        : Ns[Attr, t, A, B] with Vs  = ???
//  def ==   (v    : t, vs: t*): Ns[Attr, t, A, B] with Vs  = ???
//  def ==   (vs   : Seq[t])   : Ns[Attr, t, A, B] with CVs = ???
//  def not  (v    : t, vs: t*): Ns[Attr, t, A, B] with Vs  = ???
//  def not  (vs   : Seq[t])   : Ns[Attr, t, A, B] with CVs = ???
//  def !=   (v    : t)        : Ns[Attr, t, A, B] with Vs  = ???
//  def !=   (v    : t, vs: t*): Ns[Attr, t, A, B] with Vs  = ???
//  def !=   (vs   : Seq[t])   : Ns[Attr, t, A, B] with CVs = ???
//  def <    (upper: t)        : Ns[Attr, t, A, B]          = ???
//  def <=   (upper: t)        : Ns[Attr, t, A, B]          = ???
//  def >    (lower: t)        : Ns[Attr, t, A, B]          = ???
//  def >=   (lower: t)        : Ns[Attr, t, A, B]          = ???
//}
//
//trait ExprOneT_3[Attr, t, A, B, C, Ns[_, _, _, _, _]] {
//  def apply()                : Ns[Attr, t, A, B, C]          = ???
//  def apply(unify: unify)    : Ns[Attr, t, A, B, C]          = ???
//  def apply(v    : t, vs: t*): Ns[Attr, t, A, B, C] with Vs  = ???
//  def apply(vs   : Seq[t])   : Ns[Attr, t, A, B, C] with CVs = ???
//  def ==   (v    : t)        : Ns[Attr, t, A, B, C] with Vs  = ???
//  def ==   (v    : t, vs: t*): Ns[Attr, t, A, B, C] with Vs  = ???
//  def ==   (vs   : Seq[t])   : Ns[Attr, t, A, B, C] with CVs = ???
//  def not  (v    : t, vs: t*): Ns[Attr, t, A, B, C] with Vs  = ???
//  def not  (vs   : Seq[t])   : Ns[Attr, t, A, B, C] with CVs = ???
//  def !=   (v    : t)        : Ns[Attr, t, A, B, C] with Vs  = ???
//  def !=   (v    : t, vs: t*): Ns[Attr, t, A, B, C] with Vs  = ???
//  def !=   (vs   : Seq[t])   : Ns[Attr, t, A, B, C] with CVs = ???
//  def <    (upper: t)        : Ns[Attr, t, A, B, C]          = ???
//  def <=   (upper: t)        : Ns[Attr, t, A, B, C]          = ???
//  def >    (lower: t)        : Ns[Attr, t, A, B, C]          = ???
//  def >=   (lower: t)        : Ns[Attr, t, A, B, C]          = ???
//}
//
//trait ExprOneT_4[Attr, t, A, B, C, D, Ns[_, _, _, _, _, _]] {
//  def apply()                : Ns[Attr, t, A, B, C, D]          = ???
//  def apply(unify: unify)    : Ns[Attr, t, A, B, C, D]          = ???
//  def apply(v    : t, vs: t*): Ns[Attr, t, A, B, C, D] with Vs  = ???
//  def apply(vs   : Seq[t])   : Ns[Attr, t, A, B, C, D] with CVs = ???
//  def ==   (v    : t)        : Ns[Attr, t, A, B, C, D] with Vs  = ???
//  def ==   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D] with Vs  = ???
//  def ==   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D] with CVs = ???
//  def not  (v    : t, vs: t*): Ns[Attr, t, A, B, C, D] with Vs  = ???
//  def not  (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D] with CVs = ???
//  def !=   (v    : t)        : Ns[Attr, t, A, B, C, D] with Vs  = ???
//  def !=   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D] with Vs  = ???
//  def !=   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D] with CVs = ???
//  def <    (upper: t)        : Ns[Attr, t, A, B, C, D]          = ???
//  def <=   (upper: t)        : Ns[Attr, t, A, B, C, D]          = ???
//  def >    (lower: t)        : Ns[Attr, t, A, B, C, D]          = ???
//  def >=   (lower: t)        : Ns[Attr, t, A, B, C, D]          = ???
//}
//
//trait ExprOneT_5[Attr, t, A, B, C, D, E, Ns[_, _, _, _, _, _, _]] {
//  def apply()                : Ns[Attr, t, A, B, C, D, E]          = ???
//  def apply(unify: unify)    : Ns[Attr, t, A, B, C, D, E]          = ???
//  def apply(v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E] with Vs  = ???
//  def apply(vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E] with CVs = ???
//  def ==   (v    : t)        : Ns[Attr, t, A, B, C, D, E] with Vs  = ???
//  def ==   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E] with Vs  = ???
//  def ==   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E] with CVs = ???
//  def not  (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E] with Vs  = ???
//  def not  (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E] with CVs = ???
//  def !=   (v    : t)        : Ns[Attr, t, A, B, C, D, E] with Vs  = ???
//  def !=   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E] with Vs  = ???
//  def !=   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E] with CVs = ???
//  def <    (upper: t)        : Ns[Attr, t, A, B, C, D, E]          = ???
//  def <=   (upper: t)        : Ns[Attr, t, A, B, C, D, E]          = ???
//  def >    (lower: t)        : Ns[Attr, t, A, B, C, D, E]          = ???
//  def >=   (lower: t)        : Ns[Attr, t, A, B, C, D, E]          = ???
//}
//
//trait ExprOneT_6[Attr, t, A, B, C, D, E, F, Ns[_, _, _, _, _, _, _, _]] {
//  def apply()                : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def apply(unify: unify)    : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def apply(v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F] with Vs  = ???
//  def apply(vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F] with CVs = ???
//  def ==   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F] with Vs  = ???
//  def ==   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F] with Vs  = ???
//  def ==   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F] with CVs = ???
//  def not  (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F] with Vs  = ???
//  def not  (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F] with CVs = ???
//  def !=   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F] with Vs  = ???
//  def !=   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F] with Vs  = ???
//  def !=   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F] with CVs = ???
//  def <    (upper: t)        : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def <=   (upper: t)        : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def >    (lower: t)        : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def >=   (lower: t)        : Ns[Attr, t, A, B, C, D, E, F]          = ???
//}
//
//trait ExprOneT_7[Attr, t, A, B, C, D, E, F, G, Ns[_, _, _, _, _, _, _, _, _]] {
//  def apply()                : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def apply(unify: unify)    : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def apply(v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G] with Vs  = ???
//  def apply(vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G] with CVs = ???
//  def ==   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F, G] with Vs  = ???
//  def ==   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G] with Vs  = ???
//  def ==   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G] with CVs = ???
//  def not  (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G] with Vs  = ???
//  def not  (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G] with CVs = ???
//  def !=   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F, G] with Vs  = ???
//  def !=   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G] with Vs  = ???
//  def !=   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G] with CVs = ???
//  def <    (upper: t)        : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def <=   (upper: t)        : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def >    (lower: t)        : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def >=   (lower: t)        : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//}
//
//trait ExprOneT_8[Attr, t, A, B, C, D, E, F, G, H, Ns[_, _, _, _, _, _, _, _, _, _]] {
//  def apply()                : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def apply(unify: unify)    : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def apply(v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H] with Vs  = ???
//  def apply(vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H] with CVs = ???
//  def ==   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F, G, H] with Vs  = ???
//  def ==   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H] with Vs  = ???
//  def ==   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H] with CVs = ???
//  def not  (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H] with Vs  = ???
//  def not  (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H] with CVs = ???
//  def !=   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F, G, H] with Vs  = ???
//  def !=   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H] with Vs  = ???
//  def !=   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H] with CVs = ???
//  def <    (upper: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def <=   (upper: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def >    (lower: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def >=   (lower: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//}
//
//trait ExprOneT_9[Attr, t, A, B, C, D, E, F, G, H, I, Ns[_, _, _, _, _, _, _, _, _, _, _]] {
//  def apply()                : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def apply(unify: unify)    : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def apply(v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Vs  = ???
//  def apply(vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with CVs = ???
//  def ==   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Vs  = ???
//  def ==   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Vs  = ???
//  def ==   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with CVs = ???
//  def not  (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Vs  = ???
//  def not  (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with CVs = ???
//  def !=   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Vs  = ???
//  def !=   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Vs  = ???
//  def !=   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with CVs = ???
//  def <    (upper: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def <=   (upper: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def >    (lower: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def >=   (lower: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//}
//
//trait ExprOneT_10[Attr, t, A, B, C, D, E, F, G, H, I, J, Ns[_, _, _, _, _, _, _, _, _, _, _, _]] {
//  def apply()                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def apply(unify: unify)    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def apply(v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Vs  = ???
//  def apply(vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with CVs = ???
//  def ==   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Vs  = ???
//  def ==   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Vs  = ???
//  def ==   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with CVs = ???
//  def not  (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Vs  = ???
//  def not  (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with CVs = ???
//  def !=   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Vs  = ???
//  def !=   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Vs  = ???
//  def !=   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with CVs = ???
//  def <    (upper: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def <=   (upper: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def >    (lower: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def >=   (lower: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//}
//
//trait ExprOneT_11[Attr, t, A, B, C, D, E, F, G, H, I, J, K, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _]] {
//  def apply()                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def apply(unify: unify)    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def apply(v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Vs  = ???
//  def apply(vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with CVs = ???
//  def ==   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Vs  = ???
//  def ==   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Vs  = ???
//  def ==   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with CVs = ???
//  def not  (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Vs  = ???
//  def not  (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with CVs = ???
//  def !=   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Vs  = ???
//  def !=   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Vs  = ???
//  def !=   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with CVs = ???
//  def <    (upper: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def <=   (upper: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def >    (lower: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def >=   (lower: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//}
//
//trait ExprOneT_12[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
//  def apply()                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def apply(unify: unify)    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def apply(v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Vs  = ???
//  def apply(vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with CVs = ???
//  def ==   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Vs  = ???
//  def ==   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Vs  = ???
//  def ==   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with CVs = ???
//  def not  (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Vs  = ???
//  def not  (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with CVs = ???
//  def !=   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Vs  = ???
//  def !=   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Vs  = ???
//  def !=   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with CVs = ???
//  def <    (upper: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def <=   (upper: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def >    (lower: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def >=   (lower: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//}
//
//trait ExprOneT_13[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
//  def apply()                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def apply(unify: unify)    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def apply(v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Vs  = ???
//  def apply(vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with CVs = ???
//  def ==   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Vs  = ???
//  def ==   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Vs  = ???
//  def ==   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with CVs = ???
//  def not  (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Vs  = ???
//  def not  (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with CVs = ???
//  def !=   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Vs  = ???
//  def !=   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Vs  = ???
//  def !=   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with CVs = ???
//  def <    (upper: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def <=   (upper: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def >    (lower: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def >=   (lower: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//}
//
//trait ExprOneT_14[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
//  def apply()                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def apply(unify: unify)    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def apply(v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Vs  = ???
//  def apply(vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with CVs = ???
//  def ==   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Vs  = ???
//  def ==   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Vs  = ???
//  def ==   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with CVs = ???
//  def not  (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Vs  = ???
//  def not  (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with CVs = ???
//  def !=   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Vs  = ???
//  def !=   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Vs  = ???
//  def !=   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with CVs = ???
//  def <    (upper: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def <=   (upper: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def >    (lower: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def >=   (lower: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//}
//
//trait ExprOneT_15[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
//  def apply()                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def apply(unify: unify)    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def apply(v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Vs  = ???
//  def apply(vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with CVs = ???
//  def ==   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Vs  = ???
//  def ==   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Vs  = ???
//  def ==   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with CVs = ???
//  def not  (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Vs  = ???
//  def not  (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with CVs = ???
//  def !=   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Vs  = ???
//  def !=   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Vs  = ???
//  def !=   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with CVs = ???
//  def <    (upper: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def <=   (upper: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def >    (lower: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def >=   (lower: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//}
//
//trait ExprOneT_16[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
//  def apply()                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def apply(unify: unify)    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def apply(v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Vs  = ???
//  def apply(vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with CVs = ???
//  def ==   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Vs  = ???
//  def ==   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Vs  = ???
//  def ==   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with CVs = ???
//  def not  (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Vs  = ???
//  def not  (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with CVs = ???
//  def !=   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Vs  = ???
//  def !=   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Vs  = ???
//  def !=   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with CVs = ???
//  def <    (upper: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def <=   (upper: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def >    (lower: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def >=   (lower: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//}
//
//trait ExprOneT_17[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
//  def apply()                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def apply(unify: unify)    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def apply(v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Vs  = ???
//  def apply(vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with CVs = ???
//  def ==   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Vs  = ???
//  def ==   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Vs  = ???
//  def ==   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with CVs = ???
//  def not  (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Vs  = ???
//  def not  (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with CVs = ???
//  def !=   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Vs  = ???
//  def !=   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Vs  = ???
//  def !=   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with CVs = ???
//  def <    (upper: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def <=   (upper: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def >    (lower: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def >=   (lower: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//}
//
//trait ExprOneT_18[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
//  def apply()                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def apply(unify: unify)    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def apply(v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Vs  = ???
//  def apply(vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with CVs = ???
//  def ==   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Vs  = ???
//  def ==   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Vs  = ???
//  def ==   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with CVs = ???
//  def not  (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Vs  = ???
//  def not  (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with CVs = ???
//  def !=   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Vs  = ???
//  def !=   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Vs  = ???
//  def !=   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with CVs = ???
//  def <    (upper: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def <=   (upper: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def >    (lower: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def >=   (lower: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//}
//
//trait ExprOneT_19[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
//  def apply()                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def apply(unify: unify)    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def apply(v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Vs  = ???
//  def apply(vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with CVs = ???
//  def ==   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Vs  = ???
//  def ==   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Vs  = ???
//  def ==   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with CVs = ???
//  def not  (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Vs  = ???
//  def not  (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with CVs = ???
//  def !=   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Vs  = ???
//  def !=   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Vs  = ???
//  def !=   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with CVs = ???
//  def <    (upper: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def <=   (upper: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def >    (lower: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def >=   (lower: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//}
//
//trait ExprOneT_20[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
//  def apply()                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def apply(unify: unify)    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def apply(v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Vs  = ???
//  def apply(vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with CVs = ???
//  def ==   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Vs  = ???
//  def ==   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Vs  = ???
//  def ==   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with CVs = ???
//  def not  (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Vs  = ???
//  def not  (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with CVs = ???
//  def !=   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Vs  = ???
//  def !=   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Vs  = ???
//  def !=   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with CVs = ???
//  def <    (upper: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def <=   (upper: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def >    (lower: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def >=   (lower: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//}
//
//trait ExprOneT_21[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
//  def apply()                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def apply(unify: unify)    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def apply(v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Vs  = ???
//  def apply(vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with CVs = ???
//  def ==   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Vs  = ???
//  def ==   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Vs  = ???
//  def ==   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with CVs = ???
//  def not  (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Vs  = ???
//  def not  (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with CVs = ???
//  def !=   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Vs  = ???
//  def !=   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Vs  = ???
//  def !=   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with CVs = ???
//  def <    (upper: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def <=   (upper: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def >    (lower: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def >=   (lower: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//}
//
//trait ExprOneT_22[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
//  def apply()                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def apply(unify: unify)    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def apply(v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Vs  = ???
//  def apply(vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with CVs = ???
//  def ==   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Vs  = ???
//  def ==   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Vs  = ???
//  def ==   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with CVs = ???
//  def not  (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Vs  = ???
//  def not  (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with CVs = ???
//  def !=   (v    : t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Vs  = ???
//  def !=   (v    : t, vs: t*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Vs  = ???
//  def !=   (vs   : Seq[t])   : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with CVs = ???
//  def <    (upper: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def <=   (upper: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def >    (lower: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def >=   (lower: t)        : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//}
