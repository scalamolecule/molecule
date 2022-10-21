//package molecule.boilerplate.api.expression
//
//import molecule.boilerplate.api.aggregates._
//import molecule.boilerplate.api.sortAttrs._
//import molecule.boilerplate.markers.argKindMarkers._
//
//
//trait ExprSetM_1[Attr, t, A, Ns[_, _, _]]
//  extends Aggregates_1[Attr, t, A, Ns]
//    with SortAttrs_1[Attr, t, A, Ns] {
//  def apply  (v    : t, vs: t*)             : Ns[Attr, t, A] with Vs  = ???
//  def apply  (vs   : Seq[t])                : Ns[Attr, t, A] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])           : Ns[Attr, t, A] with CCs = ???
//  def ==     (set  : Set[t])                : Ns[Attr, t, A] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])           : Ns[Attr, t, A] with CCs = ???
//  def not    (v    : t, vs: t*)             : Ns[Attr, t, A] with Vs  = ???
//  def not    (vs   : Seq[t])                : Ns[Attr, t, A] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A] with Cs  = ???
//  def not    (sets : Seq[Set[t]])           : Ns[Attr, t, A] with CCs = ???
//  def !=     (set  : Set[t])                : Ns[Attr, t, A] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])           : Ns[Attr, t, A] with CCs = ???
//  def <      (upper: t)                     : Ns[Attr, t, A]          = ???
//  def <=     (upper: t)                     : Ns[Attr, t, A]          = ???
//  def >      (lower: t)                     : Ns[Attr, t, A]          = ???
//  def >=     (lower: t)                     : Ns[Attr, t, A]          = ???
//  def assert (v    : t, vs: t*)             : Ns[Attr, t, A]          = ???
//  def assert (vs   : Seq[t])                : Ns[Attr, t, A]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*)  : Ns[Attr, t, A]          = ???
//  def replace(abs  : Seq[(t, t)])           : Ns[Attr, t, A]          = ???
//  def retract(v    : t, vs: t*)             : Ns[Attr, t, A]          = ???
//  def retract(vs   : Seq[t])                : Ns[Attr, t, A]          = ???
//}
//
//trait ExprSetM_2[Attr, t, A, B, Ns[_, _, _, _]]
//  extends Aggregates_2[Attr, t, A, B, Ns]
//    with SortAttrs_2[Attr, t, A, B, Ns] {
//  def apply  (v    : t, vs: t*)             : Ns[Attr, t, A, B] with Vs  = ???
//  def apply  (vs   : Seq[t])                : Ns[Attr, t, A, B] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])           : Ns[Attr, t, A, B] with CCs = ???
//  def ==     (set  : Set[t])                : Ns[Attr, t, A, B] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B] with CCs = ???
//  def not    (v    : t, vs: t*)             : Ns[Attr, t, A, B] with Vs  = ???
//  def not    (vs   : Seq[t])                : Ns[Attr, t, A, B] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B] with Cs  = ???
//  def not    (sets : Seq[Set[t]])           : Ns[Attr, t, A, B] with CCs = ???
//  def !=     (set  : Set[t])                : Ns[Attr, t, A, B] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B] with CCs = ???
//  def <      (upper: t)                     : Ns[Attr, t, A, B]          = ???
//  def <=     (upper: t)                     : Ns[Attr, t, A, B]          = ???
//  def >      (lower: t)                     : Ns[Attr, t, A, B]          = ???
//  def >=     (lower: t)                     : Ns[Attr, t, A, B]          = ???
//  def assert (v    : t, vs: t*)             : Ns[Attr, t, A, B]          = ???
//  def assert (vs   : Seq[t])                : Ns[Attr, t, A, B]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*)  : Ns[Attr, t, A, B]          = ???
//  def replace(abs  : Seq[(t, t)])           : Ns[Attr, t, A, B]          = ???
//  def retract(v    : t, vs: t*)             : Ns[Attr, t, A, B]          = ???
//  def retract(vs   : Seq[t])                : Ns[Attr, t, A, B]          = ???
//}
//
//trait ExprSetM_3[Attr, t, A, B, C, Ns[_, _, _, _, _]]
//  extends Aggregates_3[Attr, t, A, B, C, Ns]
//    with SortAttrs_3[Attr, t, A, B, C, Ns] {
//  def apply  (v    : t, vs: t*)             : Ns[Attr, t, A, B, C] with Vs  = ???
//  def apply  (vs   : Seq[t])                : Ns[Attr, t, A, B, C] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C] with CCs = ???
//  def ==     (set  : Set[t])                : Ns[Attr, t, A, B, C] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C] with CCs = ???
//  def not    (v    : t, vs: t*)             : Ns[Attr, t, A, B, C] with Vs  = ???
//  def not    (vs   : Seq[t])                : Ns[Attr, t, A, B, C] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C] with Cs  = ???
//  def not    (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C] with CCs = ???
//  def !=     (set  : Set[t])                : Ns[Attr, t, A, B, C] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C] with CCs = ???
//  def <      (upper: t)                     : Ns[Attr, t, A, B, C]          = ???
//  def <=     (upper: t)                     : Ns[Attr, t, A, B, C]          = ???
//  def >      (lower: t)                     : Ns[Attr, t, A, B, C]          = ???
//  def >=     (lower: t)                     : Ns[Attr, t, A, B, C]          = ???
//  def assert (v    : t, vs: t*)             : Ns[Attr, t, A, B, C]          = ???
//  def assert (vs   : Seq[t])                : Ns[Attr, t, A, B, C]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*)  : Ns[Attr, t, A, B, C]          = ???
//  def replace(abs  : Seq[(t, t)])           : Ns[Attr, t, A, B, C]          = ???
//  def retract(v    : t, vs: t*)             : Ns[Attr, t, A, B, C]          = ???
//  def retract(vs   : Seq[t])                : Ns[Attr, t, A, B, C]          = ???
//}
//
//trait ExprSetM_4[Attr, t, A, B, C, D, Ns[_, _, _, _, _, _]]
//  extends Aggregates_4[Attr, t, A, B, C, D, Ns]
//    with SortAttrs_4[Attr, t, A, B, C, D, Ns] {
//  def apply  (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D] with Vs  = ???
//  def apply  (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D] with CCs = ???
//  def ==     (set  : Set[t])                : Ns[Attr, t, A, B, C, D] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D] with CCs = ???
//  def not    (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D] with Vs  = ???
//  def not    (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D] with Cs  = ???
//  def not    (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D] with CCs = ???
//  def !=     (set  : Set[t])                : Ns[Attr, t, A, B, C, D] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D] with CCs = ???
//  def <      (upper: t)                     : Ns[Attr, t, A, B, C, D]          = ???
//  def <=     (upper: t)                     : Ns[Attr, t, A, B, C, D]          = ???
//  def >      (lower: t)                     : Ns[Attr, t, A, B, C, D]          = ???
//  def >=     (lower: t)                     : Ns[Attr, t, A, B, C, D]          = ???
//  def assert (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D]          = ???
//  def assert (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*)  : Ns[Attr, t, A, B, C, D]          = ???
//  def replace(abs  : Seq[(t, t)])           : Ns[Attr, t, A, B, C, D]          = ???
//  def retract(v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D]          = ???
//  def retract(vs   : Seq[t])                : Ns[Attr, t, A, B, C, D]          = ???
//}
//
//trait ExprSetM_5[Attr, t, A, B, C, D, E, Ns[_, _, _, _, _, _, _]]
//  extends Aggregates_5[Attr, t, A, B, C, D, E, Ns]
//    with SortAttrs_5[Attr, t, A, B, C, D, E, Ns] {
//  def apply  (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E] with Vs  = ???
//  def apply  (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E] with CCs = ???
//  def ==     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E] with CCs = ???
//  def not    (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E] with Vs  = ???
//  def not    (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E] with Cs  = ???
//  def not    (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E] with CCs = ???
//  def !=     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E] with CCs = ???
//  def <      (upper: t)                     : Ns[Attr, t, A, B, C, D, E]          = ???
//  def <=     (upper: t)                     : Ns[Attr, t, A, B, C, D, E]          = ???
//  def >      (lower: t)                     : Ns[Attr, t, A, B, C, D, E]          = ???
//  def >=     (lower: t)                     : Ns[Attr, t, A, B, C, D, E]          = ???
//  def assert (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E]          = ???
//  def assert (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*)  : Ns[Attr, t, A, B, C, D, E]          = ???
//  def replace(abs  : Seq[(t, t)])           : Ns[Attr, t, A, B, C, D, E]          = ???
//  def retract(v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E]          = ???
//  def retract(vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E]          = ???
//}
//
//trait ExprSetM_6[Attr, t, A, B, C, D, E, F, Ns[_, _, _, _, _, _, _, _]]
//  extends Aggregates_6[Attr, t, A, B, C, D, E, F, Ns]
//    with SortAttrs_6[Attr, t, A, B, C, D, E, F, Ns] {
//  def apply  (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F] with Vs  = ???
//  def apply  (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F] with CCs = ???
//  def ==     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F] with CCs = ???
//  def not    (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F] with Vs  = ???
//  def not    (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F] with Cs  = ???
//  def not    (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F] with CCs = ???
//  def !=     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F] with CCs = ???
//  def <      (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def <=     (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def >      (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def >=     (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def assert (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def assert (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*)  : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def replace(abs  : Seq[(t, t)])           : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def retract(v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def retract(vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F]          = ???
//}
//
//trait ExprSetM_7[Attr, t, A, B, C, D, E, F, G, Ns[_, _, _, _, _, _, _, _, _]]
//  extends Aggregates_7[Attr, t, A, B, C, D, E, F, G, Ns]
//    with SortAttrs_7[Attr, t, A, B, C, D, E, F, G, Ns] {
//  def apply  (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G] with Vs  = ???
//  def apply  (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G] with CCs = ???
//  def ==     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F, G] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G] with CCs = ???
//  def not    (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G] with Vs  = ???
//  def not    (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G] with Cs  = ???
//  def not    (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G] with CCs = ???
//  def !=     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F, G] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G] with CCs = ???
//  def <      (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def <=     (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def >      (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def >=     (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def assert (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def assert (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*)  : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def replace(abs  : Seq[(t, t)])           : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def retract(v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def retract(vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//}
//
//trait ExprSetM_8[Attr, t, A, B, C, D, E, F, G, H, Ns[_, _, _, _, _, _, _, _, _, _]]
//  extends Aggregates_8[Attr, t, A, B, C, D, E, F, G, H, Ns]
//    with SortAttrs_8[Attr, t, A, B, C, D, E, F, G, H, Ns] {
//  def apply  (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H] with Vs  = ???
//  def apply  (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H] with CCs = ???
//  def ==     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H] with CCs = ???
//  def not    (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H] with Vs  = ???
//  def not    (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H] with Cs  = ???
//  def not    (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H] with CCs = ???
//  def !=     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H] with CCs = ???
//  def <      (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def <=     (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def >      (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def >=     (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def assert (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def assert (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*)  : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def replace(abs  : Seq[(t, t)])           : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def retract(v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def retract(vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//}
//
//trait ExprSetM_9[Attr, t, A, B, C, D, E, F, G, H, I, Ns[_, _, _, _, _, _, _, _, _, _, _]]
//  extends Aggregates_9[Attr, t, A, B, C, D, E, F, G, H, I, Ns]
//    with SortAttrs_9[Attr, t, A, B, C, D, E, F, G, H, I, Ns] {
//  def apply  (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Vs  = ???
//  def apply  (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with CCs = ???
//  def ==     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with CCs = ???
//  def not    (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Vs  = ???
//  def not    (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Cs  = ???
//  def not    (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with CCs = ???
//  def !=     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with CCs = ???
//  def <      (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def <=     (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def >      (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def >=     (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def assert (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def assert (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*)  : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def replace(abs  : Seq[(t, t)])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def retract(v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def retract(vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//}
//
//trait ExprSetM_10[Attr, t, A, B, C, D, E, F, G, H, I, J, Ns[_, _, _, _, _, _, _, _, _, _, _, _]]
//  extends Aggregates_10[Attr, t, A, B, C, D, E, F, G, H, I, J, Ns]
//    with SortAttrs_10[Attr, t, A, B, C, D, E, F, G, H, I, J, Ns] {
//  def apply  (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Vs  = ???
//  def apply  (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with CCs = ???
//  def ==     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with CCs = ???
//  def not    (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Vs  = ???
//  def not    (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Cs  = ???
//  def not    (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with CCs = ???
//  def !=     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with CCs = ???
//  def <      (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def <=     (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def >      (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def >=     (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def assert (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def assert (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*)  : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def replace(abs  : Seq[(t, t)])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def retract(v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def retract(vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//}
//
//trait ExprSetM_11[Attr, t, A, B, C, D, E, F, G, H, I, J, K, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends Aggregates_11[Attr, t, A, B, C, D, E, F, G, H, I, J, K, Ns]
//    with SortAttrs_11[Attr, t, A, B, C, D, E, F, G, H, I, J, K, Ns] {
//  def apply  (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Vs  = ???
//  def apply  (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with CCs = ???
//  def ==     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with CCs = ???
//  def not    (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Vs  = ???
//  def not    (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Cs  = ???
//  def not    (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with CCs = ???
//  def !=     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with CCs = ???
//  def <      (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def <=     (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def >      (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def >=     (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def assert (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def assert (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*)  : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def replace(abs  : Seq[(t, t)])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def retract(v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def retract(vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//}
//
//trait ExprSetM_12[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends Aggregates_12[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, Ns]
//    with SortAttrs_12[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, Ns] {
//  def apply  (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Vs  = ???
//  def apply  (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with CCs = ???
//  def ==     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with CCs = ???
//  def not    (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Vs  = ???
//  def not    (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Cs  = ???
//  def not    (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with CCs = ???
//  def !=     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with CCs = ???
//  def <      (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def <=     (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def >      (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def >=     (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def assert (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def assert (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*)  : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def replace(abs  : Seq[(t, t)])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def retract(v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def retract(vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//}
//
//trait ExprSetM_13[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends Aggregates_13[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, Ns]
//    with SortAttrs_13[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, Ns] {
//  def apply  (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Vs  = ???
//  def apply  (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with CCs = ???
//  def ==     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with CCs = ???
//  def not    (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Vs  = ???
//  def not    (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Cs  = ???
//  def not    (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with CCs = ???
//  def !=     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with CCs = ???
//  def <      (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def <=     (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def >      (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def >=     (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def assert (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def assert (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*)  : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def replace(abs  : Seq[(t, t)])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def retract(v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def retract(vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//}
//
//trait ExprSetM_14[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends Aggregates_14[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, Ns]
//    with SortAttrs_14[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, Ns] {
//  def apply  (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Vs  = ???
//  def apply  (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with CCs = ???
//  def ==     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with CCs = ???
//  def not    (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Vs  = ???
//  def not    (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Cs  = ???
//  def not    (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with CCs = ???
//  def !=     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with CCs = ???
//  def <      (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def <=     (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def >      (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def >=     (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def assert (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def assert (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*)  : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def replace(abs  : Seq[(t, t)])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def retract(v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def retract(vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//}
//
//trait ExprSetM_15[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends Aggregates_15[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Ns]
//    with SortAttrs_15[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Ns] {
//  def apply  (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Vs  = ???
//  def apply  (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with CCs = ???
//  def ==     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with CCs = ???
//  def not    (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Vs  = ???
//  def not    (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Cs  = ???
//  def not    (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with CCs = ???
//  def !=     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with CCs = ???
//  def <      (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def <=     (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def >      (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def >=     (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def assert (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def assert (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*)  : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def replace(abs  : Seq[(t, t)])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def retract(v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def retract(vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//}
//
//trait ExprSetM_16[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends Aggregates_16[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Ns]
//    with SortAttrs_16[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Ns] {
//  def apply  (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Vs  = ???
//  def apply  (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with CCs = ???
//  def ==     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with CCs = ???
//  def not    (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Vs  = ???
//  def not    (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Cs  = ???
//  def not    (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with CCs = ???
//  def !=     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with CCs = ???
//  def <      (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def <=     (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def >      (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def >=     (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def assert (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def assert (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*)  : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def replace(abs  : Seq[(t, t)])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def retract(v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def retract(vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//}
//
//trait ExprSetM_17[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends Aggregates_17[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Ns]
//    with SortAttrs_17[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Ns] {
//  def apply  (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Vs  = ???
//  def apply  (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with CCs = ???
//  def ==     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with CCs = ???
//  def not    (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Vs  = ???
//  def not    (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Cs  = ???
//  def not    (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with CCs = ???
//  def !=     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with CCs = ???
//  def <      (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def <=     (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def >      (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def >=     (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def assert (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def assert (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*)  : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def replace(abs  : Seq[(t, t)])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def retract(v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def retract(vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//}
//
//trait ExprSetM_18[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends Aggregates_18[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Ns]
//    with SortAttrs_18[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Ns] {
//  def apply  (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Vs  = ???
//  def apply  (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with CCs = ???
//  def ==     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with CCs = ???
//  def not    (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Vs  = ???
//  def not    (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Cs  = ???
//  def not    (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with CCs = ???
//  def !=     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with CCs = ???
//  def <      (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def <=     (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def >      (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def >=     (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def assert (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def assert (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*)  : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def replace(abs  : Seq[(t, t)])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def retract(v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def retract(vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//}
//
//trait ExprSetM_19[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends Aggregates_19[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Ns]
//    with SortAttrs_19[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Ns] {
//  def apply  (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Vs  = ???
//  def apply  (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with CCs = ???
//  def ==     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with CCs = ???
//  def not    (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Vs  = ???
//  def not    (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Cs  = ???
//  def not    (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with CCs = ???
//  def !=     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with CCs = ???
//  def <      (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def <=     (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def >      (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def >=     (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def assert (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def assert (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*)  : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def replace(abs  : Seq[(t, t)])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def retract(v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def retract(vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//}
//
//trait ExprSetM_20[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends Aggregates_20[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Ns]
//    with SortAttrs_20[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Ns] {
//  def apply  (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Vs  = ???
//  def apply  (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with CCs = ???
//  def ==     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with CCs = ???
//  def not    (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Vs  = ???
//  def not    (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Cs  = ???
//  def not    (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with CCs = ???
//  def !=     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with CCs = ???
//  def <      (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def <=     (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def >      (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def >=     (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def assert (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def assert (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*)  : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def replace(abs  : Seq[(t, t)])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def retract(v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def retract(vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//}
//
//trait ExprSetM_21[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends Aggregates_21[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Ns]
//    with SortAttrs_21[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Ns] {
//  def apply  (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Vs  = ???
//  def apply  (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with CCs = ???
//  def ==     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with CCs = ???
//  def not    (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Vs  = ???
//  def not    (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Cs  = ???
//  def not    (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with CCs = ???
//  def !=     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with CCs = ???
//  def <      (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def <=     (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def >      (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def >=     (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def assert (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def assert (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*)  : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def replace(abs  : Seq[(t, t)])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def retract(v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def retract(vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//}
//
//trait ExprSetM_22[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends Aggregates_22[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, Ns]
//    with SortAttrs_22[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, Ns] {
//  def apply  (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Vs  = ???
//  def apply  (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with CCs = ???
//  def ==     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with CCs = ???
//  def not    (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Vs  = ???
//  def not    (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Cs  = ???
//  def not    (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with CCs = ???
//  def !=     (set  : Set[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with CCs = ???
//  def <      (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def <=     (upper: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def >      (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def >=     (lower: t)                     : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def assert (v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def assert (vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*)  : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def replace(abs  : Seq[(t, t)])           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def retract(v    : t, vs: t*)             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def retract(vs   : Seq[t])                : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//}
