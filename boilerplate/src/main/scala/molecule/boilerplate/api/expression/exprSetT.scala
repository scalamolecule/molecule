//package molecule.boilerplate.api.expression
//
//import molecule.boilerplate.markers.argKindMarkers._
//
//
//trait ExprSetT_0[Attr, t, Ns[_, _]] {
//  def apply  ()                            : Ns[Attr, t]          = ???
//  def apply  (v    : t, vs: t*)            : Ns[Attr, t] with Vs  = ???
//  def apply  (vs   : Seq[t])               : Ns[Attr, t] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*): Ns[Attr, t] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])          : Ns[Attr, t] with CCs = ???
//  def ==     (set  : Set[t])               : Ns[Attr, t] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*): Ns[Attr, t] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])          : Ns[Attr, t] with CCs = ???
//  def not    (v    : t, vs: t*)            : Ns[Attr, t] with Vs  = ???
//  def not    (vs   : Seq[t])               : Ns[Attr, t] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*): Ns[Attr, t] with Cs  = ???
//  def not    (sets : Seq[Set[t]])          : Ns[Attr, t] with CCs = ???
//  def !=     (set  : Set[t])               : Ns[Attr, t] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*): Ns[Attr, t] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])          : Ns[Attr, t] with CCs = ???
//  def <      (upper: t)                    : Ns[Attr, t]          = ???
//  def <=     (upper: t)                    : Ns[Attr, t]          = ???
//  def >      (lower: t)                    : Ns[Attr, t]          = ???
//  def >=     (lower: t)                    : Ns[Attr, t]          = ???
//  def assert (v    : t, vs: t*)            : Ns[Attr, t]          = ???
//  def assert (vs   : Seq[t])               : Ns[Attr, t]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*) : Ns[Attr, t]          = ???
//  def replace(abs  : Seq[(t, t)])          : Ns[Attr, t]          = ???
//  def retract(v    : t, vs: t*)            : Ns[Attr, t]          = ???
//  def retract(vs   : Seq[t])               : Ns[Attr, t]          = ???
//}
//
//trait ExprSetT_1[Attr, t, A, Ns[_, _, _]] {
//  def apply  ()                            : Ns[Attr, t, A]          = ???
//  def apply  (v    : t, vs: t*)            : Ns[Attr, t, A] with Vs  = ???
//  def apply  (vs   : Seq[t])               : Ns[Attr, t, A] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])          : Ns[Attr, t, A] with CCs = ???
//  def ==     (set  : Set[t])               : Ns[Attr, t, A] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])          : Ns[Attr, t, A] with CCs = ???
//  def not    (v    : t, vs: t*)            : Ns[Attr, t, A] with Vs  = ???
//  def not    (vs   : Seq[t])               : Ns[Attr, t, A] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A] with Cs  = ???
//  def not    (sets : Seq[Set[t]])          : Ns[Attr, t, A] with CCs = ???
//  def !=     (set  : Set[t])               : Ns[Attr, t, A] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])          : Ns[Attr, t, A] with CCs = ???
//  def <      (upper: t)                    : Ns[Attr, t, A]          = ???
//  def <=     (upper: t)                    : Ns[Attr, t, A]          = ???
//  def >      (lower: t)                    : Ns[Attr, t, A]          = ???
//  def >=     (lower: t)                    : Ns[Attr, t, A]          = ???
//  def assert (v    : t, vs: t*)            : Ns[Attr, t, A]          = ???
//  def assert (vs   : Seq[t])               : Ns[Attr, t, A]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*) : Ns[Attr, t, A]          = ???
//  def replace(abs  : Seq[(t, t)])          : Ns[Attr, t, A]          = ???
//  def retract(v    : t, vs: t*)            : Ns[Attr, t, A]          = ???
//  def retract(vs   : Seq[t])               : Ns[Attr, t, A]          = ???
//}
//
//trait ExprSetT_2[Attr, t, A, B, Ns[_, _, _, _]] {
//  def apply  ()                            : Ns[Attr, t, A, B]          = ???
//  def apply  (v    : t, vs: t*)            : Ns[Attr, t, A, B] with Vs  = ???
//  def apply  (vs   : Seq[t])               : Ns[Attr, t, A, B] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])          : Ns[Attr, t, A, B] with CCs = ???
//  def ==     (set  : Set[t])               : Ns[Attr, t, A, B] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B] with CCs = ???
//  def not    (v    : t, vs: t*)            : Ns[Attr, t, A, B] with Vs  = ???
//  def not    (vs   : Seq[t])               : Ns[Attr, t, A, B] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B] with Cs  = ???
//  def not    (sets : Seq[Set[t]])          : Ns[Attr, t, A, B] with CCs = ???
//  def !=     (set  : Set[t])               : Ns[Attr, t, A, B] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B] with CCs = ???
//  def <      (upper: t)                    : Ns[Attr, t, A, B]          = ???
//  def <=     (upper: t)                    : Ns[Attr, t, A, B]          = ???
//  def >      (lower: t)                    : Ns[Attr, t, A, B]          = ???
//  def >=     (lower: t)                    : Ns[Attr, t, A, B]          = ???
//  def assert (v    : t, vs: t*)            : Ns[Attr, t, A, B]          = ???
//  def assert (vs   : Seq[t])               : Ns[Attr, t, A, B]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*) : Ns[Attr, t, A, B]          = ???
//  def replace(abs  : Seq[(t, t)])          : Ns[Attr, t, A, B]          = ???
//  def retract(v    : t, vs: t*)            : Ns[Attr, t, A, B]          = ???
//  def retract(vs   : Seq[t])               : Ns[Attr, t, A, B]          = ???
//}
//
//trait ExprSetT_3[Attr, t, A, B, C, Ns[_, _, _, _, _]] {
//  def apply  ()                            : Ns[Attr, t, A, B, C]          = ???
//  def apply  (v    : t, vs: t*)            : Ns[Attr, t, A, B, C] with Vs  = ???
//  def apply  (vs   : Seq[t])               : Ns[Attr, t, A, B, C] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C] with CCs = ???
//  def ==     (set  : Set[t])               : Ns[Attr, t, A, B, C] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C] with CCs = ???
//  def not    (v    : t, vs: t*)            : Ns[Attr, t, A, B, C] with Vs  = ???
//  def not    (vs   : Seq[t])               : Ns[Attr, t, A, B, C] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C] with Cs  = ???
//  def not    (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C] with CCs = ???
//  def !=     (set  : Set[t])               : Ns[Attr, t, A, B, C] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C] with CCs = ???
//  def <      (upper: t)                    : Ns[Attr, t, A, B, C]          = ???
//  def <=     (upper: t)                    : Ns[Attr, t, A, B, C]          = ???
//  def >      (lower: t)                    : Ns[Attr, t, A, B, C]          = ???
//  def >=     (lower: t)                    : Ns[Attr, t, A, B, C]          = ???
//  def assert (v    : t, vs: t*)            : Ns[Attr, t, A, B, C]          = ???
//  def assert (vs   : Seq[t])               : Ns[Attr, t, A, B, C]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*) : Ns[Attr, t, A, B, C]          = ???
//  def replace(abs  : Seq[(t, t)])          : Ns[Attr, t, A, B, C]          = ???
//  def retract(v    : t, vs: t*)            : Ns[Attr, t, A, B, C]          = ???
//  def retract(vs   : Seq[t])               : Ns[Attr, t, A, B, C]          = ???
//}
//
//trait ExprSetT_4[Attr, t, A, B, C, D, Ns[_, _, _, _, _, _]] {
//  def apply  ()                            : Ns[Attr, t, A, B, C, D]          = ???
//  def apply  (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D] with Vs  = ???
//  def apply  (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D] with CCs = ???
//  def ==     (set  : Set[t])               : Ns[Attr, t, A, B, C, D] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D] with CCs = ???
//  def not    (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D] with Vs  = ???
//  def not    (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D] with Cs  = ???
//  def not    (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D] with CCs = ???
//  def !=     (set  : Set[t])               : Ns[Attr, t, A, B, C, D] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D] with CCs = ???
//  def <      (upper: t)                    : Ns[Attr, t, A, B, C, D]          = ???
//  def <=     (upper: t)                    : Ns[Attr, t, A, B, C, D]          = ???
//  def >      (lower: t)                    : Ns[Attr, t, A, B, C, D]          = ???
//  def >=     (lower: t)                    : Ns[Attr, t, A, B, C, D]          = ???
//  def assert (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D]          = ???
//  def assert (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*) : Ns[Attr, t, A, B, C, D]          = ???
//  def replace(abs  : Seq[(t, t)])          : Ns[Attr, t, A, B, C, D]          = ???
//  def retract(v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D]          = ???
//  def retract(vs   : Seq[t])               : Ns[Attr, t, A, B, C, D]          = ???
//}
//
//trait ExprSetT_5[Attr, t, A, B, C, D, E, Ns[_, _, _, _, _, _, _]] {
//  def apply  ()                            : Ns[Attr, t, A, B, C, D, E]          = ???
//  def apply  (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E] with Vs  = ???
//  def apply  (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E] with CCs = ???
//  def ==     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E] with CCs = ???
//  def not    (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E] with Vs  = ???
//  def not    (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E] with Cs  = ???
//  def not    (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E] with CCs = ???
//  def !=     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E] with CCs = ???
//  def <      (upper: t)                    : Ns[Attr, t, A, B, C, D, E]          = ???
//  def <=     (upper: t)                    : Ns[Attr, t, A, B, C, D, E]          = ???
//  def >      (lower: t)                    : Ns[Attr, t, A, B, C, D, E]          = ???
//  def >=     (lower: t)                    : Ns[Attr, t, A, B, C, D, E]          = ???
//  def assert (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E]          = ???
//  def assert (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*) : Ns[Attr, t, A, B, C, D, E]          = ???
//  def replace(abs  : Seq[(t, t)])          : Ns[Attr, t, A, B, C, D, E]          = ???
//  def retract(v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E]          = ???
//  def retract(vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E]          = ???
//}
//
//trait ExprSetT_6[Attr, t, A, B, C, D, E, F, Ns[_, _, _, _, _, _, _, _]] {
//  def apply  ()                            : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def apply  (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F] with Vs  = ???
//  def apply  (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F] with CCs = ???
//  def ==     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F] with CCs = ???
//  def not    (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F] with Vs  = ???
//  def not    (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F] with Cs  = ???
//  def not    (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F] with CCs = ???
//  def !=     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F] with CCs = ???
//  def <      (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def <=     (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def >      (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def >=     (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def assert (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def assert (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*) : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def replace(abs  : Seq[(t, t)])          : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def retract(v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def retract(vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F]          = ???
//}
//
//trait ExprSetT_7[Attr, t, A, B, C, D, E, F, G, Ns[_, _, _, _, _, _, _, _, _]] {
//  def apply  ()                            : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def apply  (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G] with Vs  = ???
//  def apply  (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G] with CCs = ???
//  def ==     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F, G] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G] with CCs = ???
//  def not    (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G] with Vs  = ???
//  def not    (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G] with Cs  = ???
//  def not    (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G] with CCs = ???
//  def !=     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F, G] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G] with CCs = ???
//  def <      (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def <=     (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def >      (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def >=     (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def assert (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def assert (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*) : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def replace(abs  : Seq[(t, t)])          : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def retract(v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def retract(vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//}
//
//trait ExprSetT_8[Attr, t, A, B, C, D, E, F, G, H, Ns[_, _, _, _, _, _, _, _, _, _]] {
//  def apply  ()                            : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def apply  (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H] with Vs  = ???
//  def apply  (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H] with CCs = ???
//  def ==     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H] with CCs = ???
//  def not    (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H] with Vs  = ???
//  def not    (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H] with Cs  = ???
//  def not    (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H] with CCs = ???
//  def !=     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H] with CCs = ???
//  def <      (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def <=     (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def >      (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def >=     (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def assert (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def assert (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*) : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def replace(abs  : Seq[(t, t)])          : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def retract(v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def retract(vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//}
//
//trait ExprSetT_9[Attr, t, A, B, C, D, E, F, G, H, I, Ns[_, _, _, _, _, _, _, _, _, _, _]] {
//  def apply  ()                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def apply  (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Vs  = ???
//  def apply  (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with CCs = ???
//  def ==     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with CCs = ???
//  def not    (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Vs  = ???
//  def not    (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Cs  = ???
//  def not    (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with CCs = ???
//  def !=     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with CCs = ???
//  def <      (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def <=     (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def >      (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def >=     (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def assert (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def assert (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def replace(abs  : Seq[(t, t)])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def retract(v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def retract(vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//}
//
//trait ExprSetT_10[Attr, t, A, B, C, D, E, F, G, H, I, J, Ns[_, _, _, _, _, _, _, _, _, _, _, _]] {
//  def apply  ()                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def apply  (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Vs  = ???
//  def apply  (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with CCs = ???
//  def ==     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with CCs = ???
//  def not    (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Vs  = ???
//  def not    (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Cs  = ???
//  def not    (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with CCs = ???
//  def !=     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with CCs = ???
//  def <      (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def <=     (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def >      (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def >=     (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def assert (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def assert (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def replace(abs  : Seq[(t, t)])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def retract(v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def retract(vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//}
//
//trait ExprSetT_11[Attr, t, A, B, C, D, E, F, G, H, I, J, K, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _]] {
//  def apply  ()                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def apply  (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Vs  = ???
//  def apply  (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with CCs = ???
//  def ==     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with CCs = ???
//  def not    (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Vs  = ???
//  def not    (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Cs  = ???
//  def not    (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with CCs = ???
//  def !=     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with CCs = ???
//  def <      (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def <=     (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def >      (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def >=     (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def assert (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def assert (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def replace(abs  : Seq[(t, t)])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def retract(v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def retract(vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//}
//
//trait ExprSetT_12[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
//  def apply  ()                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def apply  (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Vs  = ???
//  def apply  (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with CCs = ???
//  def ==     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with CCs = ???
//  def not    (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Vs  = ???
//  def not    (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Cs  = ???
//  def not    (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with CCs = ???
//  def !=     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with CCs = ???
//  def <      (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def <=     (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def >      (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def >=     (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def assert (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def assert (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def replace(abs  : Seq[(t, t)])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def retract(v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def retract(vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//}
//
//trait ExprSetT_13[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
//  def apply  ()                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def apply  (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Vs  = ???
//  def apply  (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with CCs = ???
//  def ==     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with CCs = ???
//  def not    (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Vs  = ???
//  def not    (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Cs  = ???
//  def not    (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with CCs = ???
//  def !=     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with CCs = ???
//  def <      (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def <=     (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def >      (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def >=     (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def assert (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def assert (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def replace(abs  : Seq[(t, t)])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def retract(v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def retract(vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//}
//
//trait ExprSetT_14[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
//  def apply  ()                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def apply  (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Vs  = ???
//  def apply  (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with CCs = ???
//  def ==     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with CCs = ???
//  def not    (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Vs  = ???
//  def not    (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Cs  = ???
//  def not    (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with CCs = ???
//  def !=     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with CCs = ???
//  def <      (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def <=     (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def >      (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def >=     (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def assert (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def assert (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def replace(abs  : Seq[(t, t)])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def retract(v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def retract(vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//}
//
//trait ExprSetT_15[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
//  def apply  ()                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def apply  (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Vs  = ???
//  def apply  (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with CCs = ???
//  def ==     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with CCs = ???
//  def not    (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Vs  = ???
//  def not    (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Cs  = ???
//  def not    (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with CCs = ???
//  def !=     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with CCs = ???
//  def <      (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def <=     (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def >      (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def >=     (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def assert (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def assert (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def replace(abs  : Seq[(t, t)])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def retract(v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def retract(vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//}
//
//trait ExprSetT_16[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
//  def apply  ()                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def apply  (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Vs  = ???
//  def apply  (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with CCs = ???
//  def ==     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with CCs = ???
//  def not    (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Vs  = ???
//  def not    (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Cs  = ???
//  def not    (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with CCs = ???
//  def !=     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with CCs = ???
//  def <      (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def <=     (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def >      (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def >=     (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def assert (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def assert (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def replace(abs  : Seq[(t, t)])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def retract(v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def retract(vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//}
//
//trait ExprSetT_17[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
//  def apply  ()                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def apply  (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Vs  = ???
//  def apply  (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with CCs = ???
//  def ==     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with CCs = ???
//  def not    (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Vs  = ???
//  def not    (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Cs  = ???
//  def not    (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with CCs = ???
//  def !=     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with CCs = ???
//  def <      (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def <=     (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def >      (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def >=     (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def assert (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def assert (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def replace(abs  : Seq[(t, t)])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def retract(v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def retract(vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//}
//
//trait ExprSetT_18[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
//  def apply  ()                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def apply  (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Vs  = ???
//  def apply  (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with CCs = ???
//  def ==     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with CCs = ???
//  def not    (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Vs  = ???
//  def not    (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Cs  = ???
//  def not    (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with CCs = ???
//  def !=     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with CCs = ???
//  def <      (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def <=     (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def >      (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def >=     (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def assert (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def assert (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def replace(abs  : Seq[(t, t)])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def retract(v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def retract(vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//}
//
//trait ExprSetT_19[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
//  def apply  ()                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def apply  (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Vs  = ???
//  def apply  (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with CCs = ???
//  def ==     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with CCs = ???
//  def not    (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Vs  = ???
//  def not    (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Cs  = ???
//  def not    (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with CCs = ???
//  def !=     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with CCs = ???
//  def <      (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def <=     (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def >      (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def >=     (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def assert (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def assert (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def replace(abs  : Seq[(t, t)])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def retract(v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def retract(vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//}
//
//trait ExprSetT_20[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
//  def apply  ()                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def apply  (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Vs  = ???
//  def apply  (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with CCs = ???
//  def ==     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with CCs = ???
//  def not    (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Vs  = ???
//  def not    (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Cs  = ???
//  def not    (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with CCs = ???
//  def !=     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with CCs = ???
//  def <      (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def <=     (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def >      (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def >=     (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def assert (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def assert (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def replace(abs  : Seq[(t, t)])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def retract(v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def retract(vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//}
//
//trait ExprSetT_21[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
//  def apply  ()                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def apply  (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Vs  = ???
//  def apply  (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with CCs = ???
//  def ==     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with CCs = ???
//  def not    (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Vs  = ???
//  def not    (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Cs  = ???
//  def not    (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with CCs = ???
//  def !=     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with CCs = ???
//  def <      (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def <=     (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def >      (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def >=     (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def assert (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def assert (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def replace(abs  : Seq[(t, t)])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def retract(v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def retract(vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//}
//
//trait ExprSetT_22[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
//  def apply  ()                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def apply  (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Vs  = ???
//  def apply  (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with CVs = ???
//  def apply  (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Cs  = ???
//  def apply  (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with CCs = ???
//  def ==     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Cs  = ???
//  def ==     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Cs  = ???
//  def ==     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with CCs = ???
//  def not    (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Vs  = ???
//  def not    (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with CVs = ???
//  def not    (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Cs  = ???
//  def not    (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with CCs = ???
//  def !=     (set  : Set[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Cs  = ???
//  def !=     (set  : Set[t], sets: Set[t]*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Cs  = ???
//  def !=     (sets : Seq[Set[t]])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with CCs = ???
//  def <      (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def <=     (upper: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def >      (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def >=     (lower: t)                    : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def assert (v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def assert (vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def replace(ab   : (t, t), abs: (t, t)*) : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def replace(abs  : Seq[(t, t)])          : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def retract(v    : t, vs: t*)            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def retract(vs   : Seq[t])               : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//}
