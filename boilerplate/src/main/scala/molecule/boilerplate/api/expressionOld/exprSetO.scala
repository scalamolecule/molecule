//package molecule.boilerplate.api.expression
//
//import molecule.boilerplate.api.sortAttrs._
//import molecule.boilerplate.markers.argKindMarkers._
//
//
//trait ExprSetO_1[Attr, t, A, Ns[_, _, _]]
//  extends SortAttrs_1[Attr, t, A, Ns] {
//  def apply(v    : Option[t])          : Ns[Attr, t, A] with Vs  = ???
//  def apply(vs   : Option[Seq[t]])     : Ns[Attr, t, A] with CVs = ???
//  def apply(set  : Option[Set[t]])     : Ns[Attr, t, A] with Cs  = ???
//  def apply(sets : Option[Seq[Set[t]]]): Ns[Attr, t, A] with CCs = ???
//  def ==   (set  : Option[Set[t]])     : Ns[Attr, t, A] with Cs  = ???
//  def ==   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A] with CCs = ???
//  def not  (v    : Option[t])          : Ns[Attr, t, A] with Vs  = ???
//  def not  (vs   : Option[Seq[t]])     : Ns[Attr, t, A] with CVs = ???
//  def not  (set  : Option[Set[t]])     : Ns[Attr, t, A] with Cs  = ???
//  def not  (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A] with CCs = ???
//  def !=   (set  : Option[Set[t]])     : Ns[Attr, t, A] with Cs  = ???
//  def !=   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A] with CCs = ???
//  def <    (upper: Option[t])          : Ns[Attr, t, A]          = ???
//  def <=   (upper: Option[t])          : Ns[Attr, t, A]          = ???
//  def >    (lower: Option[t])          : Ns[Attr, t, A]          = ???
//  def >=   (lower: Option[t])          : Ns[Attr, t, A]          = ???
//}
//
//trait ExprSetO_2[Attr, t, A, B, Ns[_, _, _, _]]
//  extends SortAttrs_2[Attr, t, A, B, Ns] {
//  def apply(v    : Option[t])          : Ns[Attr, t, A, B] with Vs  = ???
//  def apply(vs   : Option[Seq[t]])     : Ns[Attr, t, A, B] with CVs = ???
//  def apply(set  : Option[Set[t]])     : Ns[Attr, t, A, B] with Cs  = ???
//  def apply(sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B] with CCs = ???
//  def ==   (set  : Option[Set[t]])     : Ns[Attr, t, A, B] with Cs  = ???
//  def ==   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B] with CCs = ???
//  def not  (v    : Option[t])          : Ns[Attr, t, A, B] with Vs  = ???
//  def not  (vs   : Option[Seq[t]])     : Ns[Attr, t, A, B] with CVs = ???
//  def not  (set  : Option[Set[t]])     : Ns[Attr, t, A, B] with Cs  = ???
//  def not  (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B] with CCs = ???
//  def !=   (set  : Option[Set[t]])     : Ns[Attr, t, A, B] with Cs  = ???
//  def !=   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B] with CCs = ???
//  def <    (upper: Option[t])          : Ns[Attr, t, A, B]          = ???
//  def <=   (upper: Option[t])          : Ns[Attr, t, A, B]          = ???
//  def >    (lower: Option[t])          : Ns[Attr, t, A, B]          = ???
//  def >=   (lower: Option[t])          : Ns[Attr, t, A, B]          = ???
//}
//
//trait ExprSetO_3[Attr, t, A, B, C, Ns[_, _, _, _, _]]
//  extends SortAttrs_3[Attr, t, A, B, C, Ns] {
//  def apply(v    : Option[t])          : Ns[Attr, t, A, B, C] with Vs  = ???
//  def apply(vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C] with CVs = ???
//  def apply(set  : Option[Set[t]])     : Ns[Attr, t, A, B, C] with Cs  = ???
//  def apply(sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C] with CCs = ???
//  def ==   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C] with Cs  = ???
//  def ==   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C] with CCs = ???
//  def not  (v    : Option[t])          : Ns[Attr, t, A, B, C] with Vs  = ???
//  def not  (vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C] with CVs = ???
//  def not  (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C] with Cs  = ???
//  def not  (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C] with CCs = ???
//  def !=   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C] with Cs  = ???
//  def !=   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C] with CCs = ???
//  def <    (upper: Option[t])          : Ns[Attr, t, A, B, C]          = ???
//  def <=   (upper: Option[t])          : Ns[Attr, t, A, B, C]          = ???
//  def >    (lower: Option[t])          : Ns[Attr, t, A, B, C]          = ???
//  def >=   (lower: Option[t])          : Ns[Attr, t, A, B, C]          = ???
//}
//
//trait ExprSetO_4[Attr, t, A, B, C, D, Ns[_, _, _, _, _, _]]
//  extends SortAttrs_4[Attr, t, A, B, C, D, Ns] {
//  def apply(v    : Option[t])          : Ns[Attr, t, A, B, C, D] with Vs  = ???
//  def apply(vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D] with CVs = ???
//  def apply(set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D] with Cs  = ???
//  def apply(sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D] with CCs = ???
//  def ==   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D] with Cs  = ???
//  def ==   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D] with CCs = ???
//  def not  (v    : Option[t])          : Ns[Attr, t, A, B, C, D] with Vs  = ???
//  def not  (vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D] with CVs = ???
//  def not  (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D] with Cs  = ???
//  def not  (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D] with CCs = ???
//  def !=   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D] with Cs  = ???
//  def !=   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D] with CCs = ???
//  def <    (upper: Option[t])          : Ns[Attr, t, A, B, C, D]          = ???
//  def <=   (upper: Option[t])          : Ns[Attr, t, A, B, C, D]          = ???
//  def >    (lower: Option[t])          : Ns[Attr, t, A, B, C, D]          = ???
//  def >=   (lower: Option[t])          : Ns[Attr, t, A, B, C, D]          = ???
//}
//
//trait ExprSetO_5[Attr, t, A, B, C, D, E, Ns[_, _, _, _, _, _, _]]
//  extends SortAttrs_5[Attr, t, A, B, C, D, E, Ns] {
//  def apply(v    : Option[t])          : Ns[Attr, t, A, B, C, D, E] with Vs  = ???
//  def apply(vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E] with CVs = ???
//  def apply(set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E] with Cs  = ???
//  def apply(sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E] with CCs = ???
//  def ==   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E] with Cs  = ???
//  def ==   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E] with CCs = ???
//  def not  (v    : Option[t])          : Ns[Attr, t, A, B, C, D, E] with Vs  = ???
//  def not  (vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E] with CVs = ???
//  def not  (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E] with Cs  = ???
//  def not  (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E] with CCs = ???
//  def !=   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E] with Cs  = ???
//  def !=   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E] with CCs = ???
//  def <    (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E]          = ???
//  def <=   (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E]          = ???
//  def >    (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E]          = ???
//  def >=   (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E]          = ???
//}
//
//trait ExprSetO_6[Attr, t, A, B, C, D, E, F, Ns[_, _, _, _, _, _, _, _]]
//  extends SortAttrs_6[Attr, t, A, B, C, D, E, F, Ns] {
//  def apply(v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F] with Vs  = ???
//  def apply(vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F] with CVs = ???
//  def apply(set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F] with Cs  = ???
//  def apply(sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F] with CCs = ???
//  def ==   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F] with Cs  = ???
//  def ==   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F] with CCs = ???
//  def not  (v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F] with Vs  = ???
//  def not  (vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F] with CVs = ???
//  def not  (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F] with Cs  = ???
//  def not  (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F] with CCs = ???
//  def !=   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F] with Cs  = ???
//  def !=   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F] with CCs = ???
//  def <    (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def <=   (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def >    (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def >=   (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F]          = ???
//}
//
//trait ExprSetO_7[Attr, t, A, B, C, D, E, F, G, Ns[_, _, _, _, _, _, _, _, _]]
//  extends SortAttrs_7[Attr, t, A, B, C, D, E, F, G, Ns] {
//  def apply(v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G] with Vs  = ???
//  def apply(vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F, G] with CVs = ???
//  def apply(set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G] with Cs  = ???
//  def apply(sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G] with CCs = ???
//  def ==   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G] with Cs  = ???
//  def ==   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G] with CCs = ???
//  def not  (v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G] with Vs  = ???
//  def not  (vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F, G] with CVs = ???
//  def not  (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G] with Cs  = ???
//  def not  (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G] with CCs = ???
//  def !=   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G] with Cs  = ???
//  def !=   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G] with CCs = ???
//  def <    (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def <=   (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def >    (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def >=   (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//}
//
//trait ExprSetO_8[Attr, t, A, B, C, D, E, F, G, H, Ns[_, _, _, _, _, _, _, _, _, _]]
//  extends SortAttrs_8[Attr, t, A, B, C, D, E, F, G, H, Ns] {
//  def apply(v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H] with Vs  = ???
//  def apply(vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H] with CVs = ???
//  def apply(set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H] with Cs  = ???
//  def apply(sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H] with CCs = ???
//  def ==   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H] with Cs  = ???
//  def ==   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H] with CCs = ???
//  def not  (v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H] with Vs  = ???
//  def not  (vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H] with CVs = ???
//  def not  (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H] with Cs  = ???
//  def not  (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H] with CCs = ???
//  def !=   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H] with Cs  = ???
//  def !=   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H] with CCs = ???
//  def <    (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def <=   (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def >    (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def >=   (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//}
//
//trait ExprSetO_9[Attr, t, A, B, C, D, E, F, G, H, I, Ns[_, _, _, _, _, _, _, _, _, _, _]]
//  extends SortAttrs_9[Attr, t, A, B, C, D, E, F, G, H, I, Ns] {
//  def apply(v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Vs  = ???
//  def apply(vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with CVs = ???
//  def apply(set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Cs  = ???
//  def apply(sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I] with CCs = ???
//  def ==   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Cs  = ???
//  def ==   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I] with CCs = ???
//  def not  (v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Vs  = ???
//  def not  (vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with CVs = ???
//  def not  (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Cs  = ???
//  def not  (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I] with CCs = ???
//  def !=   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Cs  = ???
//  def !=   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I] with CCs = ???
//  def <    (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def <=   (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def >    (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def >=   (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//}
//
//trait ExprSetO_10[Attr, t, A, B, C, D, E, F, G, H, I, J, Ns[_, _, _, _, _, _, _, _, _, _, _, _]]
//  extends SortAttrs_10[Attr, t, A, B, C, D, E, F, G, H, I, J, Ns] {
//  def apply(v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Vs  = ???
//  def apply(vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with CVs = ???
//  def apply(set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Cs  = ???
//  def apply(sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with CCs = ???
//  def ==   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Cs  = ???
//  def ==   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with CCs = ???
//  def not  (v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Vs  = ???
//  def not  (vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with CVs = ???
//  def not  (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Cs  = ???
//  def not  (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with CCs = ???
//  def !=   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Cs  = ???
//  def !=   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with CCs = ???
//  def <    (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def <=   (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def >    (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def >=   (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//}
//
//trait ExprSetO_11[Attr, t, A, B, C, D, E, F, G, H, I, J, K, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends SortAttrs_11[Attr, t, A, B, C, D, E, F, G, H, I, J, K, Ns] {
//  def apply(v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Vs  = ???
//  def apply(vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with CVs = ???
//  def apply(set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Cs  = ???
//  def apply(sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with CCs = ???
//  def ==   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Cs  = ???
//  def ==   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with CCs = ???
//  def not  (v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Vs  = ???
//  def not  (vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with CVs = ???
//  def not  (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Cs  = ???
//  def not  (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with CCs = ???
//  def !=   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Cs  = ???
//  def !=   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with CCs = ???
//  def <    (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def <=   (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def >    (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def >=   (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//}
//
//trait ExprSetO_12[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends SortAttrs_12[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, Ns] {
//  def apply(v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Vs  = ???
//  def apply(vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with CVs = ???
//  def apply(set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Cs  = ???
//  def apply(sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with CCs = ???
//  def ==   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Cs  = ???
//  def ==   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with CCs = ???
//  def not  (v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Vs  = ???
//  def not  (vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with CVs = ???
//  def not  (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Cs  = ???
//  def not  (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with CCs = ???
//  def !=   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Cs  = ???
//  def !=   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with CCs = ???
//  def <    (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def <=   (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def >    (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def >=   (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//}
//
//trait ExprSetO_13[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends SortAttrs_13[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, Ns] {
//  def apply(v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Vs  = ???
//  def apply(vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with CVs = ???
//  def apply(set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Cs  = ???
//  def apply(sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with CCs = ???
//  def ==   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Cs  = ???
//  def ==   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with CCs = ???
//  def not  (v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Vs  = ???
//  def not  (vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with CVs = ???
//  def not  (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Cs  = ???
//  def not  (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with CCs = ???
//  def !=   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Cs  = ???
//  def !=   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with CCs = ???
//  def <    (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def <=   (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def >    (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def >=   (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//}
//
//trait ExprSetO_14[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends SortAttrs_14[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, Ns] {
//  def apply(v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Vs  = ???
//  def apply(vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with CVs = ???
//  def apply(set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Cs  = ???
//  def apply(sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with CCs = ???
//  def ==   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Cs  = ???
//  def ==   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with CCs = ???
//  def not  (v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Vs  = ???
//  def not  (vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with CVs = ???
//  def not  (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Cs  = ???
//  def not  (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with CCs = ???
//  def !=   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Cs  = ???
//  def !=   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with CCs = ???
//  def <    (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def <=   (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def >    (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def >=   (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//}
//
//trait ExprSetO_15[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends SortAttrs_15[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Ns] {
//  def apply(v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Vs  = ???
//  def apply(vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with CVs = ???
//  def apply(set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Cs  = ???
//  def apply(sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with CCs = ???
//  def ==   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Cs  = ???
//  def ==   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with CCs = ???
//  def not  (v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Vs  = ???
//  def not  (vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with CVs = ???
//  def not  (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Cs  = ???
//  def not  (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with CCs = ???
//  def !=   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Cs  = ???
//  def !=   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with CCs = ???
//  def <    (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def <=   (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def >    (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def >=   (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//}
//
//trait ExprSetO_16[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends SortAttrs_16[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Ns] {
//  def apply(v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Vs  = ???
//  def apply(vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with CVs = ???
//  def apply(set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Cs  = ???
//  def apply(sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with CCs = ???
//  def ==   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Cs  = ???
//  def ==   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with CCs = ???
//  def not  (v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Vs  = ???
//  def not  (vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with CVs = ???
//  def not  (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Cs  = ???
//  def not  (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with CCs = ???
//  def !=   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Cs  = ???
//  def !=   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with CCs = ???
//  def <    (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def <=   (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def >    (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def >=   (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//}
//
//trait ExprSetO_17[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends SortAttrs_17[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Ns] {
//  def apply(v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Vs  = ???
//  def apply(vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with CVs = ???
//  def apply(set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Cs  = ???
//  def apply(sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with CCs = ???
//  def ==   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Cs  = ???
//  def ==   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with CCs = ???
//  def not  (v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Vs  = ???
//  def not  (vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with CVs = ???
//  def not  (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Cs  = ???
//  def not  (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with CCs = ???
//  def !=   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Cs  = ???
//  def !=   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with CCs = ???
//  def <    (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def <=   (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def >    (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def >=   (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//}
//
//trait ExprSetO_18[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends SortAttrs_18[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Ns] {
//  def apply(v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Vs  = ???
//  def apply(vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with CVs = ???
//  def apply(set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Cs  = ???
//  def apply(sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with CCs = ???
//  def ==   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Cs  = ???
//  def ==   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with CCs = ???
//  def not  (v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Vs  = ???
//  def not  (vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with CVs = ???
//  def not  (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Cs  = ???
//  def not  (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with CCs = ???
//  def !=   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Cs  = ???
//  def !=   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with CCs = ???
//  def <    (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def <=   (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def >    (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def >=   (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//}
//
//trait ExprSetO_19[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends SortAttrs_19[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Ns] {
//  def apply(v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Vs  = ???
//  def apply(vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with CVs = ???
//  def apply(set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Cs  = ???
//  def apply(sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with CCs = ???
//  def ==   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Cs  = ???
//  def ==   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with CCs = ???
//  def not  (v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Vs  = ???
//  def not  (vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with CVs = ???
//  def not  (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Cs  = ???
//  def not  (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with CCs = ???
//  def !=   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Cs  = ???
//  def !=   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with CCs = ???
//  def <    (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def <=   (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def >    (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def >=   (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//}
//
//trait ExprSetO_20[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends SortAttrs_20[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Ns] {
//  def apply(v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Vs  = ???
//  def apply(vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with CVs = ???
//  def apply(set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Cs  = ???
//  def apply(sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with CCs = ???
//  def ==   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Cs  = ???
//  def ==   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with CCs = ???
//  def not  (v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Vs  = ???
//  def not  (vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with CVs = ???
//  def not  (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Cs  = ???
//  def not  (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with CCs = ???
//  def !=   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Cs  = ???
//  def !=   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with CCs = ???
//  def <    (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def <=   (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def >    (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def >=   (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//}
//
//trait ExprSetO_21[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends SortAttrs_21[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Ns] {
//  def apply(v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Vs  = ???
//  def apply(vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with CVs = ???
//  def apply(set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Cs  = ???
//  def apply(sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with CCs = ???
//  def ==   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Cs  = ???
//  def ==   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with CCs = ???
//  def not  (v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Vs  = ???
//  def not  (vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with CVs = ???
//  def not  (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Cs  = ???
//  def not  (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with CCs = ???
//  def !=   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Cs  = ???
//  def !=   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with CCs = ???
//  def <    (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def <=   (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def >    (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def >=   (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//}
//
//trait ExprSetO_22[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends SortAttrs_22[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, Ns] {
//  def apply(v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Vs  = ???
//  def apply(vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with CVs = ???
//  def apply(set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Cs  = ???
//  def apply(sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with CCs = ???
//  def ==   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Cs  = ???
//  def ==   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with CCs = ???
//  def not  (v    : Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Vs  = ???
//  def not  (vs   : Option[Seq[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with CVs = ???
//  def not  (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Cs  = ???
//  def not  (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with CCs = ???
//  def !=   (set  : Option[Set[t]])     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Cs  = ???
//  def !=   (sets : Option[Seq[Set[t]]]): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with CCs = ???
//  def <    (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def <=   (upper: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def >    (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def >=   (lower: Option[t])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//}
