//// GENERATED CODE ********************************
//package molecule.core.action
//
//import molecule.boilerplate.api._
//
//class ActionsComposite_00(composite: Composite_00) {
//  final def delete = Delete(composite.elements)
//}
//
//class ActionsComposite_01[A](composite: Composite_01[A]) {
//  final def save   = Save(composite.elements)
//  final def insert = Insert_1[A](composite.elements)
//  final def query  = Query[A](composite.elements)
//  final def update = Update(composite.elements)
//  final def upsert = Update(composite.elements, true)
//  final def delete = Delete(composite.elements)
//}
//
//class ActionsComposite_02[A, B](composite: Composite_02[A, B]) {
//  final def save   = Save(composite.elements)
//  final def insert = Insert_2[A, B](composite.elements)
//  final def query  = Query[(A, B)](composite.elements)
//  final def update = Update(composite.elements)
//  final def upsert = Update(composite.elements, true)
//  final def delete = Delete(composite.elements)
//}
//
//class ActionsComposite_03[A, B, C](composite: Composite_03[A, B, C]) {
//  final def save   = Save(composite.elements)
//  final def insert = Insert_3[A, B, C](composite.elements)
//  final def query  = Query[(A, B, C)](composite.elements)
//  final def update = Update(composite.elements)
//  final def upsert = Update(composite.elements, true)
//  final def delete = Delete(composite.elements)
//}
//
//class ActionsComposite_04[A, B, C, D](composite: Composite_04[A, B, C, D]) {
//  final def save   = Save(composite.elements)
//  final def insert = Insert_4[A, B, C, D](composite.elements)
//  final def query  = Query[(A, B, C, D)](composite.elements)
//  final def update = Update(composite.elements)
//  final def upsert = Update(composite.elements, true)
//  final def delete = Delete(composite.elements)
//}
//
//class ActionsComposite_05[A, B, C, D, E](composite: Composite_05[A, B, C, D, E]) {
//  final def save   = Save(composite.elements)
//  final def insert = Insert_5[A, B, C, D, E](composite.elements)
//  final def query  = Query[(A, B, C, D, E)](composite.elements)
//  final def update = Update(composite.elements)
//  final def upsert = Update(composite.elements, true)
//  final def delete = Delete(composite.elements)
//}
//
//class ActionsComposite_06[A, B, C, D, E, F](composite: Composite_06[A, B, C, D, E, F]) {
//  final def save   = Save(composite.elements)
//  final def insert = Insert_6[A, B, C, D, E, F](composite.elements)
//  final def query  = Query[(A, B, C, D, E, F)](composite.elements)
//  final def update = Update(composite.elements)
//  final def upsert = Update(composite.elements, true)
//  final def delete = Delete(composite.elements)
//}
//
//class ActionsComposite_07[A, B, C, D, E, F, G](composite: Composite_07[A, B, C, D, E, F, G]) {
//  final def save   = Save(composite.elements)
//  final def insert = Insert_7[A, B, C, D, E, F, G](composite.elements)
//  final def query  = Query[(A, B, C, D, E, F, G)](composite.elements)
//  final def update = Update(composite.elements)
//  final def upsert = Update(composite.elements, true)
//  final def delete = Delete(composite.elements)
//}
//
//class ActionsComposite_08[A, B, C, D, E, F, G, H](composite: Composite_08[A, B, C, D, E, F, G, H]) {
//  final def save   = Save(composite.elements)
//  final def insert = Insert_8[A, B, C, D, E, F, G, H](composite.elements)
//  final def query  = Query[(A, B, C, D, E, F, G, H)](composite.elements)
//  final def update = Update(composite.elements)
//  final def upsert = Update(composite.elements, true)
//  final def delete = Delete(composite.elements)
//}
//
//class ActionsComposite_09[A, B, C, D, E, F, G, H, I](composite: Composite_09[A, B, C, D, E, F, G, H, I]) {
//  final def save   = Save(composite.elements)
//  final def insert = Insert_9[A, B, C, D, E, F, G, H, I](composite.elements)
//  final def query  = Query[(A, B, C, D, E, F, G, H, I)](composite.elements)
//  final def update = Update(composite.elements)
//  final def upsert = Update(composite.elements, true)
//  final def delete = Delete(composite.elements)
//}
//
//class ActionsComposite_10[A, B, C, D, E, F, G, H, I, J](composite: Composite_10[A, B, C, D, E, F, G, H, I, J]) {
//  final def save   = Save(composite.elements)
//  final def insert = Insert_10[A, B, C, D, E, F, G, H, I, J](composite.elements)
//  final def query  = Query[(A, B, C, D, E, F, G, H, I, J)](composite.elements)
//  final def update = Update(composite.elements)
//  final def upsert = Update(composite.elements, true)
//  final def delete = Delete(composite.elements)
//}
//
//class ActionsComposite_11[A, B, C, D, E, F, G, H, I, J, K](composite: Composite_11[A, B, C, D, E, F, G, H, I, J, K]) {
//  final def save   = Save(composite.elements)
//  final def insert = Insert_11[A, B, C, D, E, F, G, H, I, J, K](composite.elements)
//  final def query  = Query[(A, B, C, D, E, F, G, H, I, J, K)](composite.elements)
//  final def update = Update(composite.elements)
//  final def upsert = Update(composite.elements, true)
//  final def delete = Delete(composite.elements)
//}
//
//class ActionsComposite_12[A, B, C, D, E, F, G, H, I, J, K, L](composite: Composite_12[A, B, C, D, E, F, G, H, I, J, K, L]) {
//  final def save   = Save(composite.elements)
//  final def insert = Insert_12[A, B, C, D, E, F, G, H, I, J, K, L](composite.elements)
//  final def query  = Query[(A, B, C, D, E, F, G, H, I, J, K, L)](composite.elements)
//  final def update = Update(composite.elements)
//  final def upsert = Update(composite.elements, true)
//  final def delete = Delete(composite.elements)
//}
//
//class ActionsComposite_13[A, B, C, D, E, F, G, H, I, J, K, L, M](composite: Composite_13[A, B, C, D, E, F, G, H, I, J, K, L, M]) {
//  final def save   = Save(composite.elements)
//  final def insert = Insert_13[A, B, C, D, E, F, G, H, I, J, K, L, M](composite.elements)
//  final def query  = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M)](composite.elements)
//  final def update = Update(composite.elements)
//  final def upsert = Update(composite.elements, true)
//  final def delete = Delete(composite.elements)
//}
//
//class ActionsComposite_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N](composite: Composite_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N]) {
//  final def save   = Save(composite.elements)
//  final def insert = Insert_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N](composite.elements)
//  final def query  = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N)](composite.elements)
//  final def update = Update(composite.elements)
//  final def upsert = Update(composite.elements, true)
//  final def delete = Delete(composite.elements)
//}
//
//class ActionsComposite_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](composite: Composite_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]) {
//  final def save   = Save(composite.elements)
//  final def insert = Insert_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](composite.elements)
//  final def query  = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O)](composite.elements)
//  final def update = Update(composite.elements)
//  final def upsert = Update(composite.elements, true)
//  final def delete = Delete(composite.elements)
//}
//
//class ActionsComposite_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](composite: Composite_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]) {
//  final def save   = Save(composite.elements)
//  final def insert = Insert_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](composite.elements)
//  final def query  = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P)](composite.elements)
//  final def update = Update(composite.elements)
//  final def upsert = Update(composite.elements, true)
//  final def delete = Delete(composite.elements)
//}
//
//class ActionsComposite_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](composite: Composite_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]) {
//  final def save   = Save(composite.elements)
//  final def insert = Insert_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](composite.elements)
//  final def query  = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q)](composite.elements)
//  final def update = Update(composite.elements)
//  final def upsert = Update(composite.elements, true)
//  final def delete = Delete(composite.elements)
//}
//
//class ActionsComposite_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](composite: Composite_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]) {
//  final def save   = Save(composite.elements)
//  final def insert = Insert_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](composite.elements)
//  final def query  = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R)](composite.elements)
//  final def update = Update(composite.elements)
//  final def upsert = Update(composite.elements, true)
//  final def delete = Delete(composite.elements)
//}
//
//class ActionsComposite_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](composite: Composite_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]) {
//  final def save   = Save(composite.elements)
//  final def insert = Insert_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](composite.elements)
//  final def query  = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S)](composite.elements)
//  final def update = Update(composite.elements)
//  final def upsert = Update(composite.elements, true)
//  final def delete = Delete(composite.elements)
//}
//
//class ActionsComposite_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](composite: Composite_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]) {
//  final def save   = Save(composite.elements)
//  final def insert = Insert_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](composite.elements)
//  final def query  = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T)](composite.elements)
//  final def update = Update(composite.elements)
//  final def upsert = Update(composite.elements, true)
//  final def delete = Delete(composite.elements)
//}
//
//class ActionsComposite_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](composite: Composite_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]) {
//  final def save   = Save(composite.elements)
//  final def insert = Insert_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](composite.elements)
//  final def query  = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U)](composite.elements)
//  final def update = Update(composite.elements)
//  final def upsert = Update(composite.elements, true)
//  final def delete = Delete(composite.elements)
//}
//
//class ActionsComposite_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](composite: Composite_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]) {
//  final def save   = Save(composite.elements)
//  final def insert = Insert_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](composite.elements)
//  final def query  = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V)](composite.elements)
//  final def update = Update(composite.elements)
//  final def upsert = Update(composite.elements, true)
//  final def delete = Delete(composite.elements)
//}
