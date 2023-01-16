// GENERATED CODE ********************************
package molecule.db.datomic.api

import molecule.boilerplate.api._
import molecule.core.api._
import molecule.db.datomic.api.action._


class DatomicMoleculeApi_00(molecule: Molecule_00) extends MoleculeApi_00 {
  override def delete: DatomicDeleteApi = new DatomicDeleteApi(molecule.elements)
}

class DatomicMoleculeApi_01[A](molecule: Molecule_01[A]) extends MoleculeApi_01[A] {
  override def save  : DatomicSaveApi     = new DatomicSaveApi(molecule.elements)
  override def insert: DatomicInsert_1[A] = new DatomicInsert_1[A](molecule.elements)
  override def query : DatomicQueryApi[A] = new DatomicQueryApi[A](molecule.elements)
  override def update: DatomicUpdateApi   = new DatomicUpdateApi(molecule.elements)
  override def upsert: DatomicUpdateApi   = new DatomicUpdateApi(molecule.elements, true)
  override def delete: DatomicDeleteApi   = new DatomicDeleteApi(molecule.elements)
}

class DatomicMoleculeApi_02[A, B](molecule: Molecule_02[A, B]) extends MoleculeApi_02[A, B] {
  override def save  : DatomicSaveApi          = new DatomicSaveApi(molecule.elements)
  override def insert: DatomicInsert_2[A, B]   = new DatomicInsert_2[A, B](molecule.elements)
  override def query : DatomicQueryApi[(A, B)] = new DatomicQueryApi[(A, B)](molecule.elements)
  override def update: DatomicUpdateApi        = new DatomicUpdateApi(molecule.elements)
  override def upsert: DatomicUpdateApi        = new DatomicUpdateApi(molecule.elements, true)
  override def delete: DatomicDeleteApi        = new DatomicDeleteApi(molecule.elements)
}

class DatomicMoleculeApi_03[A, B, C](molecule: Molecule_03[A, B, C]) extends MoleculeApi_03[A, B, C] {
  override def save  : DatomicSaveApi             = new DatomicSaveApi(molecule.elements)
  override def insert: DatomicInsert_3[A, B, C]   = new DatomicInsert_3[A, B, C](molecule.elements)
  override def query : DatomicQueryApi[(A, B, C)] = new DatomicQueryApi[(A, B, C)](molecule.elements)
  override def update: DatomicUpdateApi           = new DatomicUpdateApi(molecule.elements)
  override def upsert: DatomicUpdateApi           = new DatomicUpdateApi(molecule.elements, true)
  override def delete: DatomicDeleteApi           = new DatomicDeleteApi(molecule.elements)
}

class DatomicMoleculeApi_04[A, B, C, D](molecule: Molecule_04[A, B, C, D]) extends MoleculeApi_04[A, B, C, D] {
  override def save  : DatomicSaveApi                = new DatomicSaveApi(molecule.elements)
  override def insert: DatomicInsert_4[A, B, C, D]   = new DatomicInsert_4[A, B, C, D](molecule.elements)
  override def query : DatomicQueryApi[(A, B, C, D)] = new DatomicQueryApi[(A, B, C, D)](molecule.elements)
  override def update: DatomicUpdateApi              = new DatomicUpdateApi(molecule.elements)
  override def upsert: DatomicUpdateApi              = new DatomicUpdateApi(molecule.elements, true)
  override def delete: DatomicDeleteApi              = new DatomicDeleteApi(molecule.elements)
}

class DatomicMoleculeApi_05[A, B, C, D, E](molecule: Molecule_05[A, B, C, D, E]) extends MoleculeApi_05[A, B, C, D, E] {
  override def save  : DatomicSaveApi                   = new DatomicSaveApi(molecule.elements)
  override def insert: DatomicInsert_5[A, B, C, D, E]   = new DatomicInsert_5[A, B, C, D, E](molecule.elements)
  override def query : DatomicQueryApi[(A, B, C, D, E)] = new DatomicQueryApi[(A, B, C, D, E)](molecule.elements)
  override def update: DatomicUpdateApi                 = new DatomicUpdateApi(molecule.elements)
  override def upsert: DatomicUpdateApi                 = new DatomicUpdateApi(molecule.elements, true)
  override def delete: DatomicDeleteApi                 = new DatomicDeleteApi(molecule.elements)
}

class DatomicMoleculeApi_06[A, B, C, D, E, F](molecule: Molecule_06[A, B, C, D, E, F]) extends MoleculeApi_06[A, B, C, D, E, F] {
  override def save  : DatomicSaveApi                      = new DatomicSaveApi(molecule.elements)
  override def insert: DatomicInsert_6[A, B, C, D, E, F]   = new DatomicInsert_6[A, B, C, D, E, F](molecule.elements)
  override def query : DatomicQueryApi[(A, B, C, D, E, F)] = new DatomicQueryApi[(A, B, C, D, E, F)](molecule.elements)
  override def update: DatomicUpdateApi                    = new DatomicUpdateApi(molecule.elements)
  override def upsert: DatomicUpdateApi                    = new DatomicUpdateApi(molecule.elements, true)
  override def delete: DatomicDeleteApi                    = new DatomicDeleteApi(molecule.elements)
}

class DatomicMoleculeApi_07[A, B, C, D, E, F, G](molecule: Molecule_07[A, B, C, D, E, F, G]) extends MoleculeApi_07[A, B, C, D, E, F, G] {
  override def save  : DatomicSaveApi                         = new DatomicSaveApi(molecule.elements)
  override def insert: DatomicInsert_7[A, B, C, D, E, F, G]   = new DatomicInsert_7[A, B, C, D, E, F, G](molecule.elements)
  override def query : DatomicQueryApi[(A, B, C, D, E, F, G)] = new DatomicQueryApi[(A, B, C, D, E, F, G)](molecule.elements)
  override def update: DatomicUpdateApi                       = new DatomicUpdateApi(molecule.elements)
  override def upsert: DatomicUpdateApi                       = new DatomicUpdateApi(molecule.elements, true)
  override def delete: DatomicDeleteApi                       = new DatomicDeleteApi(molecule.elements)
}

class DatomicMoleculeApi_08[A, B, C, D, E, F, G, H](molecule: Molecule_08[A, B, C, D, E, F, G, H]) extends MoleculeApi_08[A, B, C, D, E, F, G, H] {
  override def save  : DatomicSaveApi                            = new DatomicSaveApi(molecule.elements)
  override def insert: DatomicInsert_8[A, B, C, D, E, F, G, H]   = new DatomicInsert_8[A, B, C, D, E, F, G, H](molecule.elements)
  override def query : DatomicQueryApi[(A, B, C, D, E, F, G, H)] = new DatomicQueryApi[(A, B, C, D, E, F, G, H)](molecule.elements)
  override def update: DatomicUpdateApi                          = new DatomicUpdateApi(molecule.elements)
  override def upsert: DatomicUpdateApi                          = new DatomicUpdateApi(molecule.elements, true)
  override def delete: DatomicDeleteApi                          = new DatomicDeleteApi(molecule.elements)
}

class DatomicMoleculeApi_09[A, B, C, D, E, F, G, H, I](molecule: Molecule_09[A, B, C, D, E, F, G, H, I]) extends MoleculeApi_09[A, B, C, D, E, F, G, H, I] {
  override def save  : DatomicSaveApi                               = new DatomicSaveApi(molecule.elements)
  override def insert: DatomicInsert_9[A, B, C, D, E, F, G, H, I]   = new DatomicInsert_9[A, B, C, D, E, F, G, H, I](molecule.elements)
  override def query : DatomicQueryApi[(A, B, C, D, E, F, G, H, I)] = new DatomicQueryApi[(A, B, C, D, E, F, G, H, I)](molecule.elements)
  override def update: DatomicUpdateApi                             = new DatomicUpdateApi(molecule.elements)
  override def upsert: DatomicUpdateApi                             = new DatomicUpdateApi(molecule.elements, true)
  override def delete: DatomicDeleteApi                             = new DatomicDeleteApi(molecule.elements)
}

class DatomicMoleculeApi_10[A, B, C, D, E, F, G, H, I, J](molecule: Molecule_10[A, B, C, D, E, F, G, H, I, J]) extends MoleculeApi_10[A, B, C, D, E, F, G, H, I, J] {
  override def save  : DatomicSaveApi                                  = new DatomicSaveApi(molecule.elements)
  override def insert: DatomicInsert_10[A, B, C, D, E, F, G, H, I, J]  = new DatomicInsert_10[A, B, C, D, E, F, G, H, I, J](molecule.elements)
  override def query : DatomicQueryApi[(A, B, C, D, E, F, G, H, I, J)] = new DatomicQueryApi[(A, B, C, D, E, F, G, H, I, J)](molecule.elements)
  override def update: DatomicUpdateApi                                = new DatomicUpdateApi(molecule.elements)
  override def upsert: DatomicUpdateApi                                = new DatomicUpdateApi(molecule.elements, true)
  override def delete: DatomicDeleteApi                                = new DatomicDeleteApi(molecule.elements)
}

class DatomicMoleculeApi_11[A, B, C, D, E, F, G, H, I, J, K](molecule: Molecule_11[A, B, C, D, E, F, G, H, I, J, K]) extends MoleculeApi_11[A, B, C, D, E, F, G, H, I, J, K] {
  override def save  : DatomicSaveApi                                     = new DatomicSaveApi(molecule.elements)
  override def insert: DatomicInsert_11[A, B, C, D, E, F, G, H, I, J, K]  = new DatomicInsert_11[A, B, C, D, E, F, G, H, I, J, K](molecule.elements)
  override def query : DatomicQueryApi[(A, B, C, D, E, F, G, H, I, J, K)] = new DatomicQueryApi[(A, B, C, D, E, F, G, H, I, J, K)](molecule.elements)
  override def update: DatomicUpdateApi                                   = new DatomicUpdateApi(molecule.elements)
  override def upsert: DatomicUpdateApi                                   = new DatomicUpdateApi(molecule.elements, true)
  override def delete: DatomicDeleteApi                                   = new DatomicDeleteApi(molecule.elements)
}

class DatomicMoleculeApi_12[A, B, C, D, E, F, G, H, I, J, K, L](molecule: Molecule_12[A, B, C, D, E, F, G, H, I, J, K, L]) extends MoleculeApi_12[A, B, C, D, E, F, G, H, I, J, K, L] {
  override def save  : DatomicSaveApi                                        = new DatomicSaveApi(molecule.elements)
  override def insert: DatomicInsert_12[A, B, C, D, E, F, G, H, I, J, K, L]  = new DatomicInsert_12[A, B, C, D, E, F, G, H, I, J, K, L](molecule.elements)
  override def query : DatomicQueryApi[(A, B, C, D, E, F, G, H, I, J, K, L)] = new DatomicQueryApi[(A, B, C, D, E, F, G, H, I, J, K, L)](molecule.elements)
  override def update: DatomicUpdateApi                                      = new DatomicUpdateApi(molecule.elements)
  override def upsert: DatomicUpdateApi                                      = new DatomicUpdateApi(molecule.elements, true)
  override def delete: DatomicDeleteApi                                      = new DatomicDeleteApi(molecule.elements)
}

class DatomicMoleculeApi_13[A, B, C, D, E, F, G, H, I, J, K, L, M](molecule: Molecule_13[A, B, C, D, E, F, G, H, I, J, K, L, M]) extends MoleculeApi_13[A, B, C, D, E, F, G, H, I, J, K, L, M] {
  override def save  : DatomicSaveApi                                           = new DatomicSaveApi(molecule.elements)
  override def insert: DatomicInsert_13[A, B, C, D, E, F, G, H, I, J, K, L, M]  = new DatomicInsert_13[A, B, C, D, E, F, G, H, I, J, K, L, M](molecule.elements)
  override def query : DatomicQueryApi[(A, B, C, D, E, F, G, H, I, J, K, L, M)] = new DatomicQueryApi[(A, B, C, D, E, F, G, H, I, J, K, L, M)](molecule.elements)
  override def update: DatomicUpdateApi                                         = new DatomicUpdateApi(molecule.elements)
  override def upsert: DatomicUpdateApi                                         = new DatomicUpdateApi(molecule.elements, true)
  override def delete: DatomicDeleteApi                                         = new DatomicDeleteApi(molecule.elements)
}

class DatomicMoleculeApi_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N](molecule: Molecule_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N]) extends MoleculeApi_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N] {
  override def save  : DatomicSaveApi                                              = new DatomicSaveApi(molecule.elements)
  override def insert: DatomicInsert_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N]  = new DatomicInsert_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N](molecule.elements)
  override def query : DatomicQueryApi[(A, B, C, D, E, F, G, H, I, J, K, L, M, N)] = new DatomicQueryApi[(A, B, C, D, E, F, G, H, I, J, K, L, M, N)](molecule.elements)
  override def update: DatomicUpdateApi                                            = new DatomicUpdateApi(molecule.elements)
  override def upsert: DatomicUpdateApi                                            = new DatomicUpdateApi(molecule.elements, true)
  override def delete: DatomicDeleteApi                                            = new DatomicDeleteApi(molecule.elements)
}

class DatomicMoleculeApi_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](molecule: Molecule_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]) extends MoleculeApi_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] {
  override def save  : DatomicSaveApi                                                 = new DatomicSaveApi(molecule.elements)
  override def insert: DatomicInsert_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]  = new DatomicInsert_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](molecule.elements)
  override def query : DatomicQueryApi[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O)] = new DatomicQueryApi[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O)](molecule.elements)
  override def update: DatomicUpdateApi                                               = new DatomicUpdateApi(molecule.elements)
  override def upsert: DatomicUpdateApi                                               = new DatomicUpdateApi(molecule.elements, true)
  override def delete: DatomicDeleteApi                                               = new DatomicDeleteApi(molecule.elements)
}

class DatomicMoleculeApi_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](molecule: Molecule_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]) extends MoleculeApi_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] {
  override def save  : DatomicSaveApi                                                    = new DatomicSaveApi(molecule.elements)
  override def insert: DatomicInsert_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]  = new DatomicInsert_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](molecule.elements)
  override def query : DatomicQueryApi[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P)] = new DatomicQueryApi[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P)](molecule.elements)
  override def update: DatomicUpdateApi                                                  = new DatomicUpdateApi(molecule.elements)
  override def upsert: DatomicUpdateApi                                                  = new DatomicUpdateApi(molecule.elements, true)
  override def delete: DatomicDeleteApi                                                  = new DatomicDeleteApi(molecule.elements)
}

class DatomicMoleculeApi_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](molecule: Molecule_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]) extends MoleculeApi_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] {
  override def save  : DatomicSaveApi                                                       = new DatomicSaveApi(molecule.elements)
  override def insert: DatomicInsert_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]  = new DatomicInsert_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](molecule.elements)
  override def query : DatomicQueryApi[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q)] = new DatomicQueryApi[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q)](molecule.elements)
  override def update: DatomicUpdateApi                                                     = new DatomicUpdateApi(molecule.elements)
  override def upsert: DatomicUpdateApi                                                     = new DatomicUpdateApi(molecule.elements, true)
  override def delete: DatomicDeleteApi                                                     = new DatomicDeleteApi(molecule.elements)
}

class DatomicMoleculeApi_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](molecule: Molecule_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]) extends MoleculeApi_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] {
  override def save  : DatomicSaveApi                                                          = new DatomicSaveApi(molecule.elements)
  override def insert: DatomicInsert_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]  = new DatomicInsert_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](molecule.elements)
  override def query : DatomicQueryApi[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R)] = new DatomicQueryApi[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R)](molecule.elements)
  override def update: DatomicUpdateApi                                                        = new DatomicUpdateApi(molecule.elements)
  override def upsert: DatomicUpdateApi                                                        = new DatomicUpdateApi(molecule.elements, true)
  override def delete: DatomicDeleteApi                                                        = new DatomicDeleteApi(molecule.elements)
}

class DatomicMoleculeApi_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](molecule: Molecule_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]) extends MoleculeApi_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] {
  override def save  : DatomicSaveApi                                                             = new DatomicSaveApi(molecule.elements)
  override def insert: DatomicInsert_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]  = new DatomicInsert_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](molecule.elements)
  override def query : DatomicQueryApi[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S)] = new DatomicQueryApi[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S)](molecule.elements)
  override def update: DatomicUpdateApi                                                           = new DatomicUpdateApi(molecule.elements)
  override def upsert: DatomicUpdateApi                                                           = new DatomicUpdateApi(molecule.elements, true)
  override def delete: DatomicDeleteApi                                                           = new DatomicDeleteApi(molecule.elements)
}

class DatomicMoleculeApi_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](molecule: Molecule_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]) extends MoleculeApi_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] {
  override def save  : DatomicSaveApi                                                                = new DatomicSaveApi(molecule.elements)
  override def insert: DatomicInsert_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]  = new DatomicInsert_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](molecule.elements)
  override def query : DatomicQueryApi[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T)] = new DatomicQueryApi[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T)](molecule.elements)
  override def update: DatomicUpdateApi                                                              = new DatomicUpdateApi(molecule.elements)
  override def upsert: DatomicUpdateApi                                                              = new DatomicUpdateApi(molecule.elements, true)
  override def delete: DatomicDeleteApi                                                              = new DatomicDeleteApi(molecule.elements)
}

class DatomicMoleculeApi_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](molecule: Molecule_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]) extends MoleculeApi_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] {
  override def save  : DatomicSaveApi                                                                   = new DatomicSaveApi(molecule.elements)
  override def insert: DatomicInsert_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]  = new DatomicInsert_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](molecule.elements)
  override def query : DatomicQueryApi[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U)] = new DatomicQueryApi[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U)](molecule.elements)
  override def update: DatomicUpdateApi                                                                 = new DatomicUpdateApi(molecule.elements)
  override def upsert: DatomicUpdateApi                                                                 = new DatomicUpdateApi(molecule.elements, true)
  override def delete: DatomicDeleteApi                                                                 = new DatomicDeleteApi(molecule.elements)
}

class DatomicMoleculeApi_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](molecule: Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]) extends MoleculeApi_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] {
  override def save  : DatomicSaveApi                                                                      = new DatomicSaveApi(molecule.elements)
  override def insert: DatomicInsert_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]  = new DatomicInsert_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](molecule.elements)
  override def query : DatomicQueryApi[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V)] = new DatomicQueryApi[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V)](molecule.elements)
  override def update: DatomicUpdateApi                                                                    = new DatomicUpdateApi(molecule.elements)
  override def upsert: DatomicUpdateApi                                                                    = new DatomicUpdateApi(molecule.elements, true)
  override def delete: DatomicDeleteApi                                                                    = new DatomicDeleteApi(molecule.elements)
}
