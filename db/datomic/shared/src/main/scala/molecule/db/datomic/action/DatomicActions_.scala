// GENERATED CODE ********************************
package molecule.db.datomic.action

import molecule.boilerplate.api._
import molecule.core.action._


class DatomicActions_00(molecule: Molecule_00) extends Actions_00 {
  override def delete: DatomicDelete = DatomicDelete(molecule.elements)
}

class DatomicActions_01[A](molecule: Molecule_01[A]) extends Actions_01[A] {
  override def save  : DatomicSave        = DatomicSave(molecule.elements)
  override def insert: DatomicInsert_1[A] = DatomicInsert_1[A](molecule.elements)
  override def query : DatomicQuery[A]    = DatomicQuery[A](molecule.elements)
  override def update: DatomicUpdate      = DatomicUpdate(molecule.elements)
  override def upsert: DatomicUpdate      = DatomicUpdate(molecule.elements, true)
  override def delete: DatomicDelete      = DatomicDelete(molecule.elements)
}

class DatomicActions_02[A, B](molecule: Molecule_02[A, B]) extends Actions_02[A, B] {
  override def save  : DatomicSave           = DatomicSave(molecule.elements)
  override def insert: DatomicInsert_2[A, B] = DatomicInsert_2[A, B](molecule.elements)
  override def query : DatomicQuery[(A, B)]  = DatomicQuery[(A, B)](molecule.elements)
  override def update: DatomicUpdate         = DatomicUpdate(molecule.elements)
  override def upsert: DatomicUpdate         = DatomicUpdate(molecule.elements, true)
  override def delete: DatomicDelete         = DatomicDelete(molecule.elements)
}

class DatomicActions_03[A, B, C](molecule: Molecule_03[A, B, C]) extends Actions_03[A, B, C] {
  override def save  : DatomicSave              = DatomicSave(molecule.elements)
  override def insert: DatomicInsert_3[A, B, C] = DatomicInsert_3[A, B, C](molecule.elements)
  override def query : DatomicQuery[(A, B, C)]  = DatomicQuery[(A, B, C)](molecule.elements)
  override def update: DatomicUpdate            = DatomicUpdate(molecule.elements)
  override def upsert: DatomicUpdate            = DatomicUpdate(molecule.elements, true)
  override def delete: DatomicDelete            = DatomicDelete(molecule.elements)
}

class DatomicActions_04[A, B, C, D](molecule: Molecule_04[A, B, C, D]) extends Actions_04[A, B, C, D] {
  override def save  : DatomicSave                 = DatomicSave(molecule.elements)
  override def insert: DatomicInsert_4[A, B, C, D] = DatomicInsert_4[A, B, C, D](molecule.elements)
  override def query : DatomicQuery[(A, B, C, D)]  = DatomicQuery[(A, B, C, D)](molecule.elements)
  override def update: DatomicUpdate               = DatomicUpdate(molecule.elements)
  override def upsert: DatomicUpdate               = DatomicUpdate(molecule.elements, true)
  override def delete: DatomicDelete               = DatomicDelete(molecule.elements)
}

class DatomicActions_05[A, B, C, D, E](molecule: Molecule_05[A, B, C, D, E]) extends Actions_05[A, B, C, D, E] {
  override def save  : DatomicSave                    = DatomicSave(molecule.elements)
  override def insert: DatomicInsert_5[A, B, C, D, E] = DatomicInsert_5[A, B, C, D, E](molecule.elements)
  override def query : DatomicQuery[(A, B, C, D, E)]  = DatomicQuery[(A, B, C, D, E)](molecule.elements)
  override def update: DatomicUpdate                  = DatomicUpdate(molecule.elements)
  override def upsert: DatomicUpdate                  = DatomicUpdate(molecule.elements, true)
  override def delete: DatomicDelete                  = DatomicDelete(molecule.elements)
}

class DatomicActions_06[A, B, C, D, E, F](molecule: Molecule_06[A, B, C, D, E, F]) extends Actions_06[A, B, C, D, E, F] {
  override def save  : DatomicSave                       = DatomicSave(molecule.elements)
  override def insert: DatomicInsert_6[A, B, C, D, E, F] = DatomicInsert_6[A, B, C, D, E, F](molecule.elements)
  override def query : DatomicQuery[(A, B, C, D, E, F)]  = DatomicQuery[(A, B, C, D, E, F)](molecule.elements)
  override def update: DatomicUpdate                     = DatomicUpdate(molecule.elements)
  override def upsert: DatomicUpdate                     = DatomicUpdate(molecule.elements, true)
  override def delete: DatomicDelete                     = DatomicDelete(molecule.elements)
}

class DatomicActions_07[A, B, C, D, E, F, G](molecule: Molecule_07[A, B, C, D, E, F, G]) extends Actions_07[A, B, C, D, E, F, G] {
  override def save  : DatomicSave                          = DatomicSave(molecule.elements)
  override def insert: DatomicInsert_7[A, B, C, D, E, F, G] = DatomicInsert_7[A, B, C, D, E, F, G](molecule.elements)
  override def query : DatomicQuery[(A, B, C, D, E, F, G)]  = DatomicQuery[(A, B, C, D, E, F, G)](molecule.elements)
  override def update: DatomicUpdate                        = DatomicUpdate(molecule.elements)
  override def upsert: DatomicUpdate                        = DatomicUpdate(molecule.elements, true)
  override def delete: DatomicDelete                        = DatomicDelete(molecule.elements)
}

class DatomicActions_08[A, B, C, D, E, F, G, H](molecule: Molecule_08[A, B, C, D, E, F, G, H]) extends Actions_08[A, B, C, D, E, F, G, H] {
  override def save  : DatomicSave                             = DatomicSave(molecule.elements)
  override def insert: DatomicInsert_8[A, B, C, D, E, F, G, H] = DatomicInsert_8[A, B, C, D, E, F, G, H](molecule.elements)
  override def query : DatomicQuery[(A, B, C, D, E, F, G, H)]  = DatomicQuery[(A, B, C, D, E, F, G, H)](molecule.elements)
  override def update: DatomicUpdate                           = DatomicUpdate(molecule.elements)
  override def upsert: DatomicUpdate                           = DatomicUpdate(molecule.elements, true)
  override def delete: DatomicDelete                           = DatomicDelete(molecule.elements)
}

class DatomicActions_09[A, B, C, D, E, F, G, H, I](molecule: Molecule_09[A, B, C, D, E, F, G, H, I]) extends Actions_09[A, B, C, D, E, F, G, H, I] {
  override def save  : DatomicSave                                = DatomicSave(molecule.elements)
  override def insert: DatomicInsert_9[A, B, C, D, E, F, G, H, I] = DatomicInsert_9[A, B, C, D, E, F, G, H, I](molecule.elements)
  override def query : DatomicQuery[(A, B, C, D, E, F, G, H, I)]  = DatomicQuery[(A, B, C, D, E, F, G, H, I)](molecule.elements)
  override def update: DatomicUpdate                              = DatomicUpdate(molecule.elements)
  override def upsert: DatomicUpdate                              = DatomicUpdate(molecule.elements, true)
  override def delete: DatomicDelete                              = DatomicDelete(molecule.elements)
}

class DatomicActions_10[A, B, C, D, E, F, G, H, I, J](molecule: Molecule_10[A, B, C, D, E, F, G, H, I, J]) extends Actions_10[A, B, C, D, E, F, G, H, I, J] {
  override def save  : DatomicSave                                    = DatomicSave(molecule.elements)
  override def insert: DatomicInsert_10[A, B, C, D, E, F, G, H, I, J] = DatomicInsert_10[A, B, C, D, E, F, G, H, I, J](molecule.elements)
  override def query : DatomicQuery[(A, B, C, D, E, F, G, H, I, J)]   = DatomicQuery[(A, B, C, D, E, F, G, H, I, J)](molecule.elements)
  override def update: DatomicUpdate                                  = DatomicUpdate(molecule.elements)
  override def upsert: DatomicUpdate                                  = DatomicUpdate(molecule.elements, true)
  override def delete: DatomicDelete                                  = DatomicDelete(molecule.elements)
}

class DatomicActions_11[A, B, C, D, E, F, G, H, I, J, K](molecule: Molecule_11[A, B, C, D, E, F, G, H, I, J, K]) extends Actions_11[A, B, C, D, E, F, G, H, I, J, K] {
  override def save  : DatomicSave                                       = DatomicSave(molecule.elements)
  override def insert: DatomicInsert_11[A, B, C, D, E, F, G, H, I, J, K] = DatomicInsert_11[A, B, C, D, E, F, G, H, I, J, K](molecule.elements)
  override def query : DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K)]   = DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K)](molecule.elements)
  override def update: DatomicUpdate                                     = DatomicUpdate(molecule.elements)
  override def upsert: DatomicUpdate                                     = DatomicUpdate(molecule.elements, true)
  override def delete: DatomicDelete                                     = DatomicDelete(molecule.elements)
}

class DatomicActions_12[A, B, C, D, E, F, G, H, I, J, K, L](molecule: Molecule_12[A, B, C, D, E, F, G, H, I, J, K, L]) extends Actions_12[A, B, C, D, E, F, G, H, I, J, K, L] {
  override def save  : DatomicSave                                          = DatomicSave(molecule.elements)
  override def insert: DatomicInsert_12[A, B, C, D, E, F, G, H, I, J, K, L] = DatomicInsert_12[A, B, C, D, E, F, G, H, I, J, K, L](molecule.elements)
  override def query : DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L)]   = DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L)](molecule.elements)
  override def update: DatomicUpdate                                        = DatomicUpdate(molecule.elements)
  override def upsert: DatomicUpdate                                        = DatomicUpdate(molecule.elements, true)
  override def delete: DatomicDelete                                        = DatomicDelete(molecule.elements)
}

class DatomicActions_13[A, B, C, D, E, F, G, H, I, J, K, L, M](molecule: Molecule_13[A, B, C, D, E, F, G, H, I, J, K, L, M]) extends Actions_13[A, B, C, D, E, F, G, H, I, J, K, L, M] {
  override def save  : DatomicSave                                             = DatomicSave(molecule.elements)
  override def insert: DatomicInsert_13[A, B, C, D, E, F, G, H, I, J, K, L, M] = DatomicInsert_13[A, B, C, D, E, F, G, H, I, J, K, L, M](molecule.elements)
  override def query : DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M)]   = DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M)](molecule.elements)
  override def update: DatomicUpdate                                           = DatomicUpdate(molecule.elements)
  override def upsert: DatomicUpdate                                           = DatomicUpdate(molecule.elements, true)
  override def delete: DatomicDelete                                           = DatomicDelete(molecule.elements)
}

class DatomicActions_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N](molecule: Molecule_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N]) extends Actions_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N] {
  override def save  : DatomicSave                                                = DatomicSave(molecule.elements)
  override def insert: DatomicInsert_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N] = DatomicInsert_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N](molecule.elements)
  override def query : DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N)]   = DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N)](molecule.elements)
  override def update: DatomicUpdate                                              = DatomicUpdate(molecule.elements)
  override def upsert: DatomicUpdate                                              = DatomicUpdate(molecule.elements, true)
  override def delete: DatomicDelete                                              = DatomicDelete(molecule.elements)
}

class DatomicActions_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](molecule: Molecule_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]) extends Actions_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] {
  override def save  : DatomicSave                                                   = DatomicSave(molecule.elements)
  override def insert: DatomicInsert_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = DatomicInsert_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](molecule.elements)
  override def query : DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O)]   = DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O)](molecule.elements)
  override def update: DatomicUpdate                                                 = DatomicUpdate(molecule.elements)
  override def upsert: DatomicUpdate                                                 = DatomicUpdate(molecule.elements, true)
  override def delete: DatomicDelete                                                 = DatomicDelete(molecule.elements)
}

class DatomicActions_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](molecule: Molecule_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]) extends Actions_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] {
  override def save  : DatomicSave                                                      = DatomicSave(molecule.elements)
  override def insert: DatomicInsert_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = DatomicInsert_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](molecule.elements)
  override def query : DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P)]   = DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P)](molecule.elements)
  override def update: DatomicUpdate                                                    = DatomicUpdate(molecule.elements)
  override def upsert: DatomicUpdate                                                    = DatomicUpdate(molecule.elements, true)
  override def delete: DatomicDelete                                                    = DatomicDelete(molecule.elements)
}

class DatomicActions_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](molecule: Molecule_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]) extends Actions_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] {
  override def save  : DatomicSave                                                         = DatomicSave(molecule.elements)
  override def insert: DatomicInsert_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = DatomicInsert_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](molecule.elements)
  override def query : DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q)]   = DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q)](molecule.elements)
  override def update: DatomicUpdate                                                       = DatomicUpdate(molecule.elements)
  override def upsert: DatomicUpdate                                                       = DatomicUpdate(molecule.elements, true)
  override def delete: DatomicDelete                                                       = DatomicDelete(molecule.elements)
}

class DatomicActions_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](molecule: Molecule_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]) extends Actions_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] {
  override def save  : DatomicSave                                                            = DatomicSave(molecule.elements)
  override def insert: DatomicInsert_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = DatomicInsert_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](molecule.elements)
  override def query : DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R)]   = DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R)](molecule.elements)
  override def update: DatomicUpdate                                                          = DatomicUpdate(molecule.elements)
  override def upsert: DatomicUpdate                                                          = DatomicUpdate(molecule.elements, true)
  override def delete: DatomicDelete                                                          = DatomicDelete(molecule.elements)
}

class DatomicActions_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](molecule: Molecule_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]) extends Actions_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] {
  override def save  : DatomicSave                                                               = DatomicSave(molecule.elements)
  override def insert: DatomicInsert_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = DatomicInsert_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](molecule.elements)
  override def query : DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S)]   = DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S)](molecule.elements)
  override def update: DatomicUpdate                                                             = DatomicUpdate(molecule.elements)
  override def upsert: DatomicUpdate                                                             = DatomicUpdate(molecule.elements, true)
  override def delete: DatomicDelete                                                             = DatomicDelete(molecule.elements)
}

class DatomicActions_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](molecule: Molecule_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]) extends Actions_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] {
  override def save  : DatomicSave                                                                  = DatomicSave(molecule.elements)
  override def insert: DatomicInsert_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = DatomicInsert_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](molecule.elements)
  override def query : DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T)]   = DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T)](molecule.elements)
  override def update: DatomicUpdate                                                                = DatomicUpdate(molecule.elements)
  override def upsert: DatomicUpdate                                                                = DatomicUpdate(molecule.elements, true)
  override def delete: DatomicDelete                                                                = DatomicDelete(molecule.elements)
}

class DatomicActions_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](molecule: Molecule_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]) extends Actions_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] {
  override def save  : DatomicSave                                                                     = DatomicSave(molecule.elements)
  override def insert: DatomicInsert_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = DatomicInsert_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](molecule.elements)
  override def query : DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U)]   = DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U)](molecule.elements)
  override def update: DatomicUpdate                                                                   = DatomicUpdate(molecule.elements)
  override def upsert: DatomicUpdate                                                                   = DatomicUpdate(molecule.elements, true)
  override def delete: DatomicDelete                                                                   = DatomicDelete(molecule.elements)
}

class DatomicActions_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](molecule: Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]) extends Actions_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] {
  override def save  : DatomicSave                                                                        = DatomicSave(molecule.elements)
  override def insert: DatomicInsert_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] = DatomicInsert_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](molecule.elements)
  override def query : DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V)]   = DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V)](molecule.elements)
  override def update: DatomicUpdate                                                                      = DatomicUpdate(molecule.elements)
  override def upsert: DatomicUpdate                                                                      = DatomicUpdate(molecule.elements, true)
  override def delete: DatomicDelete                                                                      = DatomicDelete(molecule.elements)
}
