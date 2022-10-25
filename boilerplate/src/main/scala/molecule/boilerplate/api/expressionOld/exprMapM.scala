//package molecule.boilerplate.api.expression
//
//import molecule.boilerplate.api.aggregates._
//import molecule.boilerplate.api.sortAttrs._
//import molecule.boilerplate.markers.argKindMarkers._
//
//
//trait ExprMapM_1[Attr, t, A, Ns[_, _, _]]
//  extends Aggregates_1[Attr, t, A, Ns]
//    with SortAttrs_1[Attr, t, A, Ns] {
//  def apply  (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A] with Vs  = ???
//  def apply  (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A] with CVs = ???
//  def apply  (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A] with Cs  = ???
//  def apply  (maps : Map[String, t])                                              : Ns[Attr, t, A] with CCs = ???
//  def not    (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A] with Vs  = ???
//  def not    (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A] with CVs = ???
//  def not    (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A] with Cs  = ???
//  def not    (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A] with CCs = ???
//  def ==     (map  : Map[String, t])                                              : Ns[Attr, t, A] with Cs  = ???
//  def ==     (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A] with Cs  = ???
//  def ==     (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A] with CCs = ???
//  def <      (upper: t)                                                           : Ns[Attr, t, A]          = ???
//  def <=     (upper: t)                                                           : Ns[Attr, t, A]          = ???
//  def >      (lower: t)                                                           : Ns[Attr, t, A]          = ???
//  def >=     (lower: t)                                                           : Ns[Attr, t, A]          = ???
//  def assert (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A]          = ???
//  def assert (pairs: Seq[(String, t)])                                            : Ns[Attr, t, A]          = ???
//  def replace(ab   : ((String, t), (String, t)), abs: ((String, t), (String, t))*): Ns[Attr, t, A]          = ???
//  def replace(abs  : Seq[((String, t), (String, t))])                             : Ns[Attr, t, A]          = ???
//  def retract(map  : (String, t), maps: (String, t)*)                             : Ns[Attr, t, A]          = ???
//  def retract(maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A]          = ???
//  def k      (key  : String, keys: String*)                                       : Ns[Attr, t, A] with Vs  = ???
//  def k      (key  : Seq[String])                                                 : Ns[Attr, t, A] with CVs = ???
//}
//
//trait ExprMapM_2[Attr, t, A, B, Ns[_, _, _, _]]
//  extends Aggregates_2[Attr, t, A, B, Ns]
//    with SortAttrs_2[Attr, t, A, B, Ns] {
//  def apply  (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B] with Vs  = ???
//  def apply  (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B] with CVs = ???
//  def apply  (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B] with Cs  = ???
//  def apply  (maps : Map[String, t])                                              : Ns[Attr, t, A, B] with CCs = ???
//  def not    (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B] with Vs  = ???
//  def not    (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B] with CVs = ???
//  def not    (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B] with Cs  = ???
//  def not    (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B] with CCs = ???
//  def ==     (map  : Map[String, t])                                              : Ns[Attr, t, A, B] with Cs  = ???
//  def ==     (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B] with Cs  = ???
//  def ==     (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B] with CCs = ???
//  def <      (upper: t)                                                           : Ns[Attr, t, A, B]          = ???
//  def <=     (upper: t)                                                           : Ns[Attr, t, A, B]          = ???
//  def >      (lower: t)                                                           : Ns[Attr, t, A, B]          = ???
//  def >=     (lower: t)                                                           : Ns[Attr, t, A, B]          = ???
//  def assert (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B]          = ???
//  def assert (pairs: Seq[(String, t)])                                            : Ns[Attr, t, A, B]          = ???
//  def replace(ab   : ((String, t), (String, t)), abs: ((String, t), (String, t))*): Ns[Attr, t, A, B]          = ???
//  def replace(abs  : Seq[((String, t), (String, t))])                             : Ns[Attr, t, A, B]          = ???
//  def retract(map  : (String, t), maps: (String, t)*)                             : Ns[Attr, t, A, B]          = ???
//  def retract(maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B]          = ???
//  def k      (key  : String, keys: String*)                                       : Ns[Attr, t, A, B] with Vs  = ???
//  def k      (key  : Seq[String])                                                 : Ns[Attr, t, A, B] with CVs = ???
//}
//
//trait ExprMapM_3[Attr, t, A, B, C, Ns[_, _, _, _, _]]
//  extends Aggregates_3[Attr, t, A, B, C, Ns]
//    with SortAttrs_3[Attr, t, A, B, C, Ns] {
//  def apply  (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C] with Vs  = ???
//  def apply  (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C] with CVs = ???
//  def apply  (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C] with Cs  = ???
//  def apply  (maps : Map[String, t])                                              : Ns[Attr, t, A, B, C] with CCs = ???
//  def not    (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C] with Vs  = ???
//  def not    (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C] with CVs = ???
//  def not    (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C] with Cs  = ???
//  def not    (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C] with CCs = ???
//  def ==     (map  : Map[String, t])                                              : Ns[Attr, t, A, B, C] with Cs  = ???
//  def ==     (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C] with Cs  = ???
//  def ==     (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C] with CCs = ???
//  def <      (upper: t)                                                           : Ns[Attr, t, A, B, C]          = ???
//  def <=     (upper: t)                                                           : Ns[Attr, t, A, B, C]          = ???
//  def >      (lower: t)                                                           : Ns[Attr, t, A, B, C]          = ???
//  def >=     (lower: t)                                                           : Ns[Attr, t, A, B, C]          = ???
//  def assert (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C]          = ???
//  def assert (pairs: Seq[(String, t)])                                            : Ns[Attr, t, A, B, C]          = ???
//  def replace(ab   : ((String, t), (String, t)), abs: ((String, t), (String, t))*): Ns[Attr, t, A, B, C]          = ???
//  def replace(abs  : Seq[((String, t), (String, t))])                             : Ns[Attr, t, A, B, C]          = ???
//  def retract(map  : (String, t), maps: (String, t)*)                             : Ns[Attr, t, A, B, C]          = ???
//  def retract(maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C]          = ???
//  def k      (key  : String, keys: String*)                                       : Ns[Attr, t, A, B, C] with Vs  = ???
//  def k      (key  : Seq[String])                                                 : Ns[Attr, t, A, B, C] with CVs = ???
//}
//
//trait ExprMapM_4[Attr, t, A, B, C, D, Ns[_, _, _, _, _, _]]
//  extends Aggregates_4[Attr, t, A, B, C, D, Ns]
//    with SortAttrs_4[Attr, t, A, B, C, D, Ns] {
//  def apply  (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D] with Vs  = ???
//  def apply  (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D] with CVs = ???
//  def apply  (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D] with Cs  = ???
//  def apply  (maps : Map[String, t])                                              : Ns[Attr, t, A, B, C, D] with CCs = ???
//  def not    (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D] with Vs  = ???
//  def not    (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D] with CVs = ???
//  def not    (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D] with Cs  = ???
//  def not    (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D] with CCs = ???
//  def ==     (map  : Map[String, t])                                              : Ns[Attr, t, A, B, C, D] with Cs  = ???
//  def ==     (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D] with Cs  = ???
//  def ==     (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D] with CCs = ???
//  def <      (upper: t)                                                           : Ns[Attr, t, A, B, C, D]          = ???
//  def <=     (upper: t)                                                           : Ns[Attr, t, A, B, C, D]          = ???
//  def >      (lower: t)                                                           : Ns[Attr, t, A, B, C, D]          = ???
//  def >=     (lower: t)                                                           : Ns[Attr, t, A, B, C, D]          = ???
//  def assert (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D]          = ???
//  def assert (pairs: Seq[(String, t)])                                            : Ns[Attr, t, A, B, C, D]          = ???
//  def replace(ab   : ((String, t), (String, t)), abs: ((String, t), (String, t))*): Ns[Attr, t, A, B, C, D]          = ???
//  def replace(abs  : Seq[((String, t), (String, t))])                             : Ns[Attr, t, A, B, C, D]          = ???
//  def retract(map  : (String, t), maps: (String, t)*)                             : Ns[Attr, t, A, B, C, D]          = ???
//  def retract(maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D]          = ???
//  def k      (key  : String, keys: String*)                                       : Ns[Attr, t, A, B, C, D] with Vs  = ???
//  def k      (key  : Seq[String])                                                 : Ns[Attr, t, A, B, C, D] with CVs = ???
//}
//
//trait ExprMapM_5[Attr, t, A, B, C, D, E, Ns[_, _, _, _, _, _, _]]
//  extends Aggregates_5[Attr, t, A, B, C, D, E, Ns]
//    with SortAttrs_5[Attr, t, A, B, C, D, E, Ns] {
//  def apply  (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E] with Vs  = ???
//  def apply  (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E] with CVs = ???
//  def apply  (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E] with Cs  = ???
//  def apply  (maps : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E] with CCs = ???
//  def not    (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E] with Vs  = ???
//  def not    (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E] with CVs = ???
//  def not    (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E] with Cs  = ???
//  def not    (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E] with CCs = ???
//  def ==     (map  : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E] with Cs  = ???
//  def ==     (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E] with Cs  = ???
//  def ==     (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E] with CCs = ???
//  def <      (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E]          = ???
//  def <=     (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E]          = ???
//  def >      (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E]          = ???
//  def >=     (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E]          = ???
//  def assert (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E]          = ???
//  def assert (pairs: Seq[(String, t)])                                            : Ns[Attr, t, A, B, C, D, E]          = ???
//  def replace(ab   : ((String, t), (String, t)), abs: ((String, t), (String, t))*): Ns[Attr, t, A, B, C, D, E]          = ???
//  def replace(abs  : Seq[((String, t), (String, t))])                             : Ns[Attr, t, A, B, C, D, E]          = ???
//  def retract(map  : (String, t), maps: (String, t)*)                             : Ns[Attr, t, A, B, C, D, E]          = ???
//  def retract(maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E]          = ???
//  def k      (key  : String, keys: String*)                                       : Ns[Attr, t, A, B, C, D, E] with Vs  = ???
//  def k      (key  : Seq[String])                                                 : Ns[Attr, t, A, B, C, D, E] with CVs = ???
//}
//
//trait ExprMapM_6[Attr, t, A, B, C, D, E, F, Ns[_, _, _, _, _, _, _, _]]
//  extends Aggregates_6[Attr, t, A, B, C, D, E, F, Ns]
//    with SortAttrs_6[Attr, t, A, B, C, D, E, F, Ns] {
//  def apply  (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F] with Vs  = ???
//  def apply  (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F] with CVs = ???
//  def apply  (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F] with Cs  = ???
//  def apply  (maps : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F] with CCs = ???
//  def not    (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F] with Vs  = ???
//  def not    (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F] with CVs = ???
//  def not    (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F] with Cs  = ???
//  def not    (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F] with CCs = ???
//  def ==     (map  : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F] with Cs  = ???
//  def ==     (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F] with Cs  = ???
//  def ==     (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F] with CCs = ???
//  def <      (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def <=     (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def >      (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def >=     (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def assert (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def assert (pairs: Seq[(String, t)])                                            : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def replace(ab   : ((String, t), (String, t)), abs: ((String, t), (String, t))*): Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def replace(abs  : Seq[((String, t), (String, t))])                             : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def retract(map  : (String, t), maps: (String, t)*)                             : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def retract(maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F]          = ???
//  def k      (key  : String, keys: String*)                                       : Ns[Attr, t, A, B, C, D, E, F] with Vs  = ???
//  def k      (key  : Seq[String])                                                 : Ns[Attr, t, A, B, C, D, E, F] with CVs = ???
//}
//
//trait ExprMapM_7[Attr, t, A, B, C, D, E, F, G, Ns[_, _, _, _, _, _, _, _, _]]
//  extends Aggregates_7[Attr, t, A, B, C, D, E, F, G, Ns]
//    with SortAttrs_7[Attr, t, A, B, C, D, E, F, G, Ns] {
//  def apply  (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G] with Vs  = ???
//  def apply  (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F, G] with CVs = ???
//  def apply  (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G] with Cs  = ???
//  def apply  (maps : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F, G] with CCs = ???
//  def not    (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G] with Vs  = ???
//  def not    (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F, G] with CVs = ???
//  def not    (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G] with Cs  = ???
//  def not    (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G] with CCs = ???
//  def ==     (map  : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F, G] with Cs  = ???
//  def ==     (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G] with Cs  = ???
//  def ==     (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G] with CCs = ???
//  def <      (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def <=     (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def >      (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def >=     (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def assert (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def assert (pairs: Seq[(String, t)])                                            : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def replace(ab   : ((String, t), (String, t)), abs: ((String, t), (String, t))*): Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def replace(abs  : Seq[((String, t), (String, t))])                             : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def retract(map  : (String, t), maps: (String, t)*)                             : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def retract(maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G]          = ???
//  def k      (key  : String, keys: String*)                                       : Ns[Attr, t, A, B, C, D, E, F, G] with Vs  = ???
//  def k      (key  : Seq[String])                                                 : Ns[Attr, t, A, B, C, D, E, F, G] with CVs = ???
//}
//
//trait ExprMapM_8[Attr, t, A, B, C, D, E, F, G, H, Ns[_, _, _, _, _, _, _, _, _, _]]
//  extends Aggregates_8[Attr, t, A, B, C, D, E, F, G, H, Ns]
//    with SortAttrs_8[Attr, t, A, B, C, D, E, F, G, H, Ns] {
//  def apply  (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H] with Vs  = ???
//  def apply  (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F, G, H] with CVs = ???
//  def apply  (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H] with Cs  = ???
//  def apply  (maps : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F, G, H] with CCs = ???
//  def not    (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H] with Vs  = ???
//  def not    (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F, G, H] with CVs = ???
//  def not    (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H] with Cs  = ???
//  def not    (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H] with CCs = ???
//  def ==     (map  : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F, G, H] with Cs  = ???
//  def ==     (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H] with Cs  = ???
//  def ==     (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H] with CCs = ???
//  def <      (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def <=     (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def >      (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def >=     (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def assert (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def assert (pairs: Seq[(String, t)])                                            : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def replace(ab   : ((String, t), (String, t)), abs: ((String, t), (String, t))*): Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def replace(abs  : Seq[((String, t), (String, t))])                             : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def retract(map  : (String, t), maps: (String, t)*)                             : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def retract(maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H]          = ???
//  def k      (key  : String, keys: String*)                                       : Ns[Attr, t, A, B, C, D, E, F, G, H] with Vs  = ???
//  def k      (key  : Seq[String])                                                 : Ns[Attr, t, A, B, C, D, E, F, G, H] with CVs = ???
//}
//
//trait ExprMapM_9[Attr, t, A, B, C, D, E, F, G, H, I, Ns[_, _, _, _, _, _, _, _, _, _, _]]
//  extends Aggregates_9[Attr, t, A, B, C, D, E, F, G, H, I, Ns]
//    with SortAttrs_9[Attr, t, A, B, C, D, E, F, G, H, I, Ns] {
//  def apply  (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Vs  = ???
//  def apply  (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with CVs = ???
//  def apply  (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Cs  = ???
//  def apply  (maps : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with CCs = ???
//  def not    (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Vs  = ???
//  def not    (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with CVs = ???
//  def not    (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Cs  = ???
//  def not    (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with CCs = ???
//  def ==     (map  : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Cs  = ???
//  def ==     (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Cs  = ???
//  def ==     (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with CCs = ???
//  def <      (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def <=     (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def >      (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def >=     (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def assert (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def assert (pairs: Seq[(String, t)])                                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def replace(ab   : ((String, t), (String, t)), abs: ((String, t), (String, t))*): Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def replace(abs  : Seq[((String, t), (String, t))])                             : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def retract(map  : (String, t), maps: (String, t)*)                             : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def retract(maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I]          = ???
//  def k      (key  : String, keys: String*)                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with Vs  = ???
//  def k      (key  : Seq[String])                                                 : Ns[Attr, t, A, B, C, D, E, F, G, H, I] with CVs = ???
//}
//
//trait ExprMapM_10[Attr, t, A, B, C, D, E, F, G, H, I, J, Ns[_, _, _, _, _, _, _, _, _, _, _, _]]
//  extends Aggregates_10[Attr, t, A, B, C, D, E, F, G, H, I, J, Ns]
//    with SortAttrs_10[Attr, t, A, B, C, D, E, F, G, H, I, J, Ns] {
//  def apply  (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Vs  = ???
//  def apply  (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with CVs = ???
//  def apply  (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Cs  = ???
//  def apply  (maps : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with CCs = ???
//  def not    (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Vs  = ???
//  def not    (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with CVs = ???
//  def not    (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Cs  = ???
//  def not    (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with CCs = ???
//  def ==     (map  : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Cs  = ???
//  def ==     (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Cs  = ???
//  def ==     (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with CCs = ???
//  def <      (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def <=     (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def >      (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def >=     (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def assert (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def assert (pairs: Seq[(String, t)])                                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def replace(ab   : ((String, t), (String, t)), abs: ((String, t), (String, t))*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def replace(abs  : Seq[((String, t), (String, t))])                             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def retract(map  : (String, t), maps: (String, t)*)                             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def retract(maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J]          = ???
//  def k      (key  : String, keys: String*)                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with Vs  = ???
//  def k      (key  : Seq[String])                                                 : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J] with CVs = ???
//}
//
//trait ExprMapM_11[Attr, t, A, B, C, D, E, F, G, H, I, J, K, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends Aggregates_11[Attr, t, A, B, C, D, E, F, G, H, I, J, K, Ns]
//    with SortAttrs_11[Attr, t, A, B, C, D, E, F, G, H, I, J, K, Ns] {
//  def apply  (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Vs  = ???
//  def apply  (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with CVs = ???
//  def apply  (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Cs  = ???
//  def apply  (maps : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with CCs = ???
//  def not    (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Vs  = ???
//  def not    (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with CVs = ???
//  def not    (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Cs  = ???
//  def not    (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with CCs = ???
//  def ==     (map  : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Cs  = ???
//  def ==     (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Cs  = ???
//  def ==     (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with CCs = ???
//  def <      (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def <=     (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def >      (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def >=     (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def assert (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def assert (pairs: Seq[(String, t)])                                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def replace(ab   : ((String, t), (String, t)), abs: ((String, t), (String, t))*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def replace(abs  : Seq[((String, t), (String, t))])                             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def retract(map  : (String, t), maps: (String, t)*)                             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def retract(maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K]          = ???
//  def k      (key  : String, keys: String*)                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with Vs  = ???
//  def k      (key  : Seq[String])                                                 : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K] with CVs = ???
//}
//
//trait ExprMapM_12[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends Aggregates_12[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, Ns]
//    with SortAttrs_12[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, Ns] {
//  def apply  (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Vs  = ???
//  def apply  (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with CVs = ???
//  def apply  (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Cs  = ???
//  def apply  (maps : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with CCs = ???
//  def not    (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Vs  = ???
//  def not    (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with CVs = ???
//  def not    (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Cs  = ???
//  def not    (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with CCs = ???
//  def ==     (map  : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Cs  = ???
//  def ==     (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Cs  = ???
//  def ==     (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with CCs = ???
//  def <      (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def <=     (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def >      (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def >=     (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def assert (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def assert (pairs: Seq[(String, t)])                                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def replace(ab   : ((String, t), (String, t)), abs: ((String, t), (String, t))*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def replace(abs  : Seq[((String, t), (String, t))])                             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def retract(map  : (String, t), maps: (String, t)*)                             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def retract(maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L]          = ???
//  def k      (key  : String, keys: String*)                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with Vs  = ???
//  def k      (key  : Seq[String])                                                 : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L] with CVs = ???
//}
//
//trait ExprMapM_13[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends Aggregates_13[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, Ns]
//    with SortAttrs_13[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, Ns] {
//  def apply  (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Vs  = ???
//  def apply  (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with CVs = ???
//  def apply  (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Cs  = ???
//  def apply  (maps : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with CCs = ???
//  def not    (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Vs  = ???
//  def not    (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with CVs = ???
//  def not    (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Cs  = ???
//  def not    (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with CCs = ???
//  def ==     (map  : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Cs  = ???
//  def ==     (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Cs  = ???
//  def ==     (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with CCs = ???
//  def <      (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def <=     (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def >      (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def >=     (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def assert (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def assert (pairs: Seq[(String, t)])                                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def replace(ab   : ((String, t), (String, t)), abs: ((String, t), (String, t))*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def replace(abs  : Seq[((String, t), (String, t))])                             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def retract(map  : (String, t), maps: (String, t)*)                             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def retract(maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M]          = ???
//  def k      (key  : String, keys: String*)                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with Vs  = ???
//  def k      (key  : Seq[String])                                                 : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M] with CVs = ???
//}
//
//trait ExprMapM_14[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends Aggregates_14[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, Ns]
//    with SortAttrs_14[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, Ns] {
//  def apply  (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Vs  = ???
//  def apply  (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with CVs = ???
//  def apply  (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Cs  = ???
//  def apply  (maps : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with CCs = ???
//  def not    (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Vs  = ???
//  def not    (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with CVs = ???
//  def not    (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Cs  = ???
//  def not    (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with CCs = ???
//  def ==     (map  : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Cs  = ???
//  def ==     (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Cs  = ???
//  def ==     (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with CCs = ???
//  def <      (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def <=     (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def >      (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def >=     (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def assert (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def assert (pairs: Seq[(String, t)])                                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def replace(ab   : ((String, t), (String, t)), abs: ((String, t), (String, t))*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def replace(abs  : Seq[((String, t), (String, t))])                             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def retract(map  : (String, t), maps: (String, t)*)                             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def retract(maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N]          = ???
//  def k      (key  : String, keys: String*)                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with Vs  = ???
//  def k      (key  : Seq[String])                                                 : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] with CVs = ???
//}
//
//trait ExprMapM_15[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends Aggregates_15[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Ns]
//    with SortAttrs_15[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Ns] {
//  def apply  (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Vs  = ???
//  def apply  (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with CVs = ???
//  def apply  (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Cs  = ???
//  def apply  (maps : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with CCs = ???
//  def not    (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Vs  = ???
//  def not    (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with CVs = ???
//  def not    (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Cs  = ???
//  def not    (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with CCs = ???
//  def ==     (map  : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Cs  = ???
//  def ==     (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Cs  = ???
//  def ==     (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with CCs = ???
//  def <      (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def <=     (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def >      (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def >=     (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def assert (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def assert (pairs: Seq[(String, t)])                                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def replace(ab   : ((String, t), (String, t)), abs: ((String, t), (String, t))*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def replace(abs  : Seq[((String, t), (String, t))])                             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def retract(map  : (String, t), maps: (String, t)*)                             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def retract(maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]          = ???
//  def k      (key  : String, keys: String*)                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with Vs  = ???
//  def k      (key  : Seq[String])                                                 : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with CVs = ???
//}
//
//trait ExprMapM_16[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends Aggregates_16[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Ns]
//    with SortAttrs_16[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Ns] {
//  def apply  (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Vs  = ???
//  def apply  (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with CVs = ???
//  def apply  (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Cs  = ???
//  def apply  (maps : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with CCs = ???
//  def not    (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Vs  = ???
//  def not    (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with CVs = ???
//  def not    (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Cs  = ???
//  def not    (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with CCs = ???
//  def ==     (map  : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Cs  = ???
//  def ==     (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Cs  = ???
//  def ==     (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with CCs = ???
//  def <      (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def <=     (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def >      (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def >=     (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def assert (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def assert (pairs: Seq[(String, t)])                                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def replace(ab   : ((String, t), (String, t)), abs: ((String, t), (String, t))*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def replace(abs  : Seq[((String, t), (String, t))])                             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def retract(map  : (String, t), maps: (String, t)*)                             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def retract(maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]          = ???
//  def k      (key  : String, keys: String*)                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with Vs  = ???
//  def k      (key  : Seq[String])                                                 : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with CVs = ???
//}
//
//trait ExprMapM_17[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends Aggregates_17[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Ns]
//    with SortAttrs_17[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Ns] {
//  def apply  (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Vs  = ???
//  def apply  (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with CVs = ???
//  def apply  (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Cs  = ???
//  def apply  (maps : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with CCs = ???
//  def not    (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Vs  = ???
//  def not    (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with CVs = ???
//  def not    (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Cs  = ???
//  def not    (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with CCs = ???
//  def ==     (map  : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Cs  = ???
//  def ==     (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Cs  = ???
//  def ==     (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with CCs = ???
//  def <      (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def <=     (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def >      (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def >=     (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def assert (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def assert (pairs: Seq[(String, t)])                                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def replace(ab   : ((String, t), (String, t)), abs: ((String, t), (String, t))*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def replace(abs  : Seq[((String, t), (String, t))])                             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def retract(map  : (String, t), maps: (String, t)*)                             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def retract(maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]          = ???
//  def k      (key  : String, keys: String*)                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with Vs  = ???
//  def k      (key  : Seq[String])                                                 : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with CVs = ???
//}
//
//trait ExprMapM_18[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends Aggregates_18[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Ns]
//    with SortAttrs_18[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Ns] {
//  def apply  (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Vs  = ???
//  def apply  (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with CVs = ???
//  def apply  (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Cs  = ???
//  def apply  (maps : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with CCs = ???
//  def not    (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Vs  = ???
//  def not    (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with CVs = ???
//  def not    (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Cs  = ???
//  def not    (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with CCs = ???
//  def ==     (map  : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Cs  = ???
//  def ==     (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Cs  = ???
//  def ==     (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with CCs = ???
//  def <      (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def <=     (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def >      (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def >=     (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def assert (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def assert (pairs: Seq[(String, t)])                                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def replace(ab   : ((String, t), (String, t)), abs: ((String, t), (String, t))*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def replace(abs  : Seq[((String, t), (String, t))])                             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def retract(map  : (String, t), maps: (String, t)*)                             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def retract(maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]          = ???
//  def k      (key  : String, keys: String*)                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with Vs  = ???
//  def k      (key  : Seq[String])                                                 : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with CVs = ???
//}
//
//trait ExprMapM_19[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends Aggregates_19[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Ns]
//    with SortAttrs_19[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Ns] {
//  def apply  (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Vs  = ???
//  def apply  (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with CVs = ???
//  def apply  (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Cs  = ???
//  def apply  (maps : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with CCs = ???
//  def not    (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Vs  = ???
//  def not    (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with CVs = ???
//  def not    (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Cs  = ???
//  def not    (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with CCs = ???
//  def ==     (map  : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Cs  = ???
//  def ==     (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Cs  = ???
//  def ==     (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with CCs = ???
//  def <      (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def <=     (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def >      (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def >=     (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def assert (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def assert (pairs: Seq[(String, t)])                                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def replace(ab   : ((String, t), (String, t)), abs: ((String, t), (String, t))*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def replace(abs  : Seq[((String, t), (String, t))])                             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def retract(map  : (String, t), maps: (String, t)*)                             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def retract(maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]          = ???
//  def k      (key  : String, keys: String*)                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with Vs  = ???
//  def k      (key  : Seq[String])                                                 : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with CVs = ???
//}
//
//trait ExprMapM_20[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends Aggregates_20[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Ns]
//    with SortAttrs_20[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Ns] {
//  def apply  (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Vs  = ???
//  def apply  (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with CVs = ???
//  def apply  (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Cs  = ???
//  def apply  (maps : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with CCs = ???
//  def not    (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Vs  = ???
//  def not    (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with CVs = ???
//  def not    (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Cs  = ???
//  def not    (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with CCs = ???
//  def ==     (map  : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Cs  = ???
//  def ==     (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Cs  = ???
//  def ==     (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with CCs = ???
//  def <      (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def <=     (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def >      (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def >=     (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def assert (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def assert (pairs: Seq[(String, t)])                                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def replace(ab   : ((String, t), (String, t)), abs: ((String, t), (String, t))*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def replace(abs  : Seq[((String, t), (String, t))])                             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def retract(map  : (String, t), maps: (String, t)*)                             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def retract(maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]          = ???
//  def k      (key  : String, keys: String*)                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with Vs  = ???
//  def k      (key  : Seq[String])                                                 : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with CVs = ???
//}
//
//trait ExprMapM_21[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends Aggregates_21[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Ns]
//    with SortAttrs_21[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Ns] {
//  def apply  (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Vs  = ???
//  def apply  (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with CVs = ???
//  def apply  (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Cs  = ???
//  def apply  (maps : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with CCs = ???
//  def not    (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Vs  = ???
//  def not    (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with CVs = ???
//  def not    (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Cs  = ???
//  def not    (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with CCs = ???
//  def ==     (map  : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Cs  = ???
//  def ==     (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Cs  = ???
//  def ==     (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with CCs = ???
//  def <      (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def <=     (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def >      (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def >=     (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def assert (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def assert (pairs: Seq[(String, t)])                                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def replace(ab   : ((String, t), (String, t)), abs: ((String, t), (String, t))*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def replace(abs  : Seq[((String, t), (String, t))])                             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def retract(map  : (String, t), maps: (String, t)*)                             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def retract(maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]          = ???
//  def k      (key  : String, keys: String*)                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with Vs  = ???
//  def k      (key  : Seq[String])                                                 : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with CVs = ???
//}
//
//trait ExprMapM_22[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
//  extends Aggregates_22[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, Ns]
//    with SortAttrs_22[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, Ns] {
//  def apply  (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Vs  = ???
//  def apply  (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with CVs = ???
//  def apply  (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Cs  = ???
//  def apply  (maps : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with CCs = ???
//  def not    (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Vs  = ???
//  def not    (pairs: Iterable[(String, t)])                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with CVs = ???
//  def not    (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Cs  = ???
//  def not    (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with CCs = ???
//  def ==     (map  : Map[String, t])                                              : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Cs  = ???
//  def ==     (map  : Map[String, t], maps: Map[String, t]*)                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Cs  = ???
//  def ==     (maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with CCs = ???
//  def <      (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def <=     (upper: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def >      (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def >=     (lower: t)                                                           : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def assert (pair : (String, t), pairs: (String, t)*)                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def assert (pairs: Seq[(String, t)])                                            : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def replace(ab   : ((String, t), (String, t)), abs: ((String, t), (String, t))*): Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def replace(abs  : Seq[((String, t), (String, t))])                             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def retract(map  : (String, t), maps: (String, t)*)                             : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def retract(maps : Seq[Map[String, t]])                                         : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]          = ???
//  def k      (key  : String, keys: String*)                                       : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with Vs  = ???
//  def k      (key  : Seq[String])                                                 : Ns[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] with CVs = ???
//}
