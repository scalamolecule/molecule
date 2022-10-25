// GENERATED CODE ********************************
package molecule.db.datomic.api

import molecule.boilerplate.markers.NamespaceMarkers._
import molecule.core.api.MoleculeApi._
import molecule.db.datomic.api.DatomicInsert._

object DatomicMoleculeApi {

  class DatomicMoleculeApi_01[A](molecule: Molecule_01[A]) extends MoleculeApi_01[A] {
    override def insert : DatomicInsert_1[A] = new DatomicInsert_1[A](molecule)
    override def save   : DatomicInsert_1[A] = new DatomicInsert_1[A](molecule)
    override def update : DatomicInsert_1[A] = new DatomicInsert_1[A](molecule)
    override def retract: DatomicInsert_1[A] = new DatomicInsert_1[A](molecule)
    override def delete : DatomicInsert_1[A] = new DatomicInsert_1[A](molecule)
    override def query  : DatomicQuery[(A)]  = new DatomicQuery[(A)](molecule.elements)
  }

  class DatomicMoleculeApi_02[A, B](molecule: Molecule_02[A, B]) extends MoleculeApi_02[A, B] {
    override def insert : DatomicInsert_2[A, B] = new DatomicInsert_2[A, B](molecule)
    override def save   : DatomicInsert_2[A, B] = new DatomicInsert_2[A, B](molecule)
    override def update : DatomicInsert_2[A, B] = new DatomicInsert_2[A, B](molecule)
    override def retract: DatomicInsert_2[A, B] = new DatomicInsert_2[A, B](molecule)
    override def delete : DatomicInsert_2[A, B] = new DatomicInsert_2[A, B](molecule)
    override def query  : DatomicQuery[(A, B)]  = new DatomicQuery[(A, B)](molecule.elements)
  }

  class DatomicMoleculeApi_03[A, B, C](molecule: Molecule_03[A, B, C]) extends MoleculeApi_03[A, B, C] {
    override def insert : DatomicInsert_3[A, B, C] = new DatomicInsert_3[A, B, C](molecule)
    override def save   : DatomicInsert_3[A, B, C] = new DatomicInsert_3[A, B, C](molecule)
    override def update : DatomicInsert_3[A, B, C] = new DatomicInsert_3[A, B, C](molecule)
    override def retract: DatomicInsert_3[A, B, C] = new DatomicInsert_3[A, B, C](molecule)
    override def delete : DatomicInsert_3[A, B, C] = new DatomicInsert_3[A, B, C](molecule)
    override def query  : DatomicQuery[(A, B, C)]  = new DatomicQuery[(A, B, C)](molecule.elements)
  }

  class DatomicMoleculeApi_04[A, B, C, D](molecule: Molecule_04[A, B, C, D]) extends MoleculeApi_04[A, B, C, D] {
    override def insert : DatomicInsert_4[A, B, C, D] = new DatomicInsert_4[A, B, C, D](molecule)
    override def save   : DatomicInsert_4[A, B, C, D] = new DatomicInsert_4[A, B, C, D](molecule)
    override def update : DatomicInsert_4[A, B, C, D] = new DatomicInsert_4[A, B, C, D](molecule)
    override def retract: DatomicInsert_4[A, B, C, D] = new DatomicInsert_4[A, B, C, D](molecule)
    override def delete : DatomicInsert_4[A, B, C, D] = new DatomicInsert_4[A, B, C, D](molecule)
    override def query  : DatomicQuery[(A, B, C, D)]  = new DatomicQuery[(A, B, C, D)](molecule.elements)
  }

  class DatomicMoleculeApi_05[A, B, C, D, E](molecule: Molecule_05[A, B, C, D, E]) extends MoleculeApi_05[A, B, C, D, E] {
    override def insert : DatomicInsert_5[A, B, C, D, E] = new DatomicInsert_5[A, B, C, D, E](molecule)
    override def save   : DatomicInsert_5[A, B, C, D, E] = new DatomicInsert_5[A, B, C, D, E](molecule)
    override def update : DatomicInsert_5[A, B, C, D, E] = new DatomicInsert_5[A, B, C, D, E](molecule)
    override def retract: DatomicInsert_5[A, B, C, D, E] = new DatomicInsert_5[A, B, C, D, E](molecule)
    override def delete : DatomicInsert_5[A, B, C, D, E] = new DatomicInsert_5[A, B, C, D, E](molecule)
    override def query  : DatomicQuery[(A, B, C, D, E)]  = new DatomicQuery[(A, B, C, D, E)](molecule.elements)
  }

  class DatomicMoleculeApi_06[A, B, C, D, E, F](molecule: Molecule_06[A, B, C, D, E, F]) extends MoleculeApi_06[A, B, C, D, E, F] {
    override def insert : DatomicInsert_6[A, B, C, D, E, F] = new DatomicInsert_6[A, B, C, D, E, F](molecule)
    override def save   : DatomicInsert_6[A, B, C, D, E, F] = new DatomicInsert_6[A, B, C, D, E, F](molecule)
    override def update : DatomicInsert_6[A, B, C, D, E, F] = new DatomicInsert_6[A, B, C, D, E, F](molecule)
    override def retract: DatomicInsert_6[A, B, C, D, E, F] = new DatomicInsert_6[A, B, C, D, E, F](molecule)
    override def delete : DatomicInsert_6[A, B, C, D, E, F] = new DatomicInsert_6[A, B, C, D, E, F](molecule)
    override def query  : DatomicQuery[(A, B, C, D, E, F)]  = new DatomicQuery[(A, B, C, D, E, F)](molecule.elements)
  }

  class DatomicMoleculeApi_07[A, B, C, D, E, F, G](molecule: Molecule_07[A, B, C, D, E, F, G]) extends MoleculeApi_07[A, B, C, D, E, F, G] {
    override def insert : DatomicInsert_7[A, B, C, D, E, F, G] = new DatomicInsert_7[A, B, C, D, E, F, G](molecule)
    override def save   : DatomicInsert_7[A, B, C, D, E, F, G] = new DatomicInsert_7[A, B, C, D, E, F, G](molecule)
    override def update : DatomicInsert_7[A, B, C, D, E, F, G] = new DatomicInsert_7[A, B, C, D, E, F, G](molecule)
    override def retract: DatomicInsert_7[A, B, C, D, E, F, G] = new DatomicInsert_7[A, B, C, D, E, F, G](molecule)
    override def delete : DatomicInsert_7[A, B, C, D, E, F, G] = new DatomicInsert_7[A, B, C, D, E, F, G](molecule)
    override def query  : DatomicQuery[(A, B, C, D, E, F, G)]  = new DatomicQuery[(A, B, C, D, E, F, G)](molecule.elements)
  }

  class DatomicMoleculeApi_08[A, B, C, D, E, F, G, H](molecule: Molecule_08[A, B, C, D, E, F, G, H]) extends MoleculeApi_08[A, B, C, D, E, F, G, H] {
    override def insert : DatomicInsert_8[A, B, C, D, E, F, G, H] = new DatomicInsert_8[A, B, C, D, E, F, G, H](molecule)
    override def save   : DatomicInsert_8[A, B, C, D, E, F, G, H] = new DatomicInsert_8[A, B, C, D, E, F, G, H](molecule)
    override def update : DatomicInsert_8[A, B, C, D, E, F, G, H] = new DatomicInsert_8[A, B, C, D, E, F, G, H](molecule)
    override def retract: DatomicInsert_8[A, B, C, D, E, F, G, H] = new DatomicInsert_8[A, B, C, D, E, F, G, H](molecule)
    override def delete : DatomicInsert_8[A, B, C, D, E, F, G, H] = new DatomicInsert_8[A, B, C, D, E, F, G, H](molecule)
    override def query  : DatomicQuery[(A, B, C, D, E, F, G, H)]  = new DatomicQuery[(A, B, C, D, E, F, G, H)](molecule.elements)
  }

  class DatomicMoleculeApi_09[A, B, C, D, E, F, G, H, I](molecule: Molecule_09[A, B, C, D, E, F, G, H, I]) extends MoleculeApi_09[A, B, C, D, E, F, G, H, I] {
    override def insert : DatomicInsert_9[A, B, C, D, E, F, G, H, I] = new DatomicInsert_9[A, B, C, D, E, F, G, H, I](molecule)
    override def save   : DatomicInsert_9[A, B, C, D, E, F, G, H, I] = new DatomicInsert_9[A, B, C, D, E, F, G, H, I](molecule)
    override def update : DatomicInsert_9[A, B, C, D, E, F, G, H, I] = new DatomicInsert_9[A, B, C, D, E, F, G, H, I](molecule)
    override def retract: DatomicInsert_9[A, B, C, D, E, F, G, H, I] = new DatomicInsert_9[A, B, C, D, E, F, G, H, I](molecule)
    override def delete : DatomicInsert_9[A, B, C, D, E, F, G, H, I] = new DatomicInsert_9[A, B, C, D, E, F, G, H, I](molecule)
    override def query  : DatomicQuery[(A, B, C, D, E, F, G, H, I)]  = new DatomicQuery[(A, B, C, D, E, F, G, H, I)](molecule.elements)
  }

  class DatomicMoleculeApi_10[A, B, C, D, E, F, G, H, I, J](molecule: Molecule_10[A, B, C, D, E, F, G, H, I, J]) extends MoleculeApi_10[A, B, C, D, E, F, G, H, I, J] {
    override def insert : DatomicInsert_10[A, B, C, D, E, F, G, H, I, J] = new DatomicInsert_10[A, B, C, D, E, F, G, H, I, J](molecule)
    override def save   : DatomicInsert_10[A, B, C, D, E, F, G, H, I, J] = new DatomicInsert_10[A, B, C, D, E, F, G, H, I, J](molecule)
    override def update : DatomicInsert_10[A, B, C, D, E, F, G, H, I, J] = new DatomicInsert_10[A, B, C, D, E, F, G, H, I, J](molecule)
    override def retract: DatomicInsert_10[A, B, C, D, E, F, G, H, I, J] = new DatomicInsert_10[A, B, C, D, E, F, G, H, I, J](molecule)
    override def delete : DatomicInsert_10[A, B, C, D, E, F, G, H, I, J] = new DatomicInsert_10[A, B, C, D, E, F, G, H, I, J](molecule)
    override def query  : DatomicQuery[(A, B, C, D, E, F, G, H, I, J)]   = new DatomicQuery[(A, B, C, D, E, F, G, H, I, J)](molecule.elements)
  }

  class DatomicMoleculeApi_11[A, B, C, D, E, F, G, H, I, J, K](molecule: Molecule_11[A, B, C, D, E, F, G, H, I, J, K]) extends MoleculeApi_11[A, B, C, D, E, F, G, H, I, J, K] {
    override def insert : DatomicInsert_11[A, B, C, D, E, F, G, H, I, J, K] = new DatomicInsert_11[A, B, C, D, E, F, G, H, I, J, K](molecule)
    override def save   : DatomicInsert_11[A, B, C, D, E, F, G, H, I, J, K] = new DatomicInsert_11[A, B, C, D, E, F, G, H, I, J, K](molecule)
    override def update : DatomicInsert_11[A, B, C, D, E, F, G, H, I, J, K] = new DatomicInsert_11[A, B, C, D, E, F, G, H, I, J, K](molecule)
    override def retract: DatomicInsert_11[A, B, C, D, E, F, G, H, I, J, K] = new DatomicInsert_11[A, B, C, D, E, F, G, H, I, J, K](molecule)
    override def delete : DatomicInsert_11[A, B, C, D, E, F, G, H, I, J, K] = new DatomicInsert_11[A, B, C, D, E, F, G, H, I, J, K](molecule)
    override def query  : DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K)]   = new DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K)](molecule.elements)
  }

  class DatomicMoleculeApi_12[A, B, C, D, E, F, G, H, I, J, K, L](molecule: Molecule_12[A, B, C, D, E, F, G, H, I, J, K, L]) extends MoleculeApi_12[A, B, C, D, E, F, G, H, I, J, K, L] {
    override def insert : DatomicInsert_12[A, B, C, D, E, F, G, H, I, J, K, L] = new DatomicInsert_12[A, B, C, D, E, F, G, H, I, J, K, L](molecule)
    override def save   : DatomicInsert_12[A, B, C, D, E, F, G, H, I, J, K, L] = new DatomicInsert_12[A, B, C, D, E, F, G, H, I, J, K, L](molecule)
    override def update : DatomicInsert_12[A, B, C, D, E, F, G, H, I, J, K, L] = new DatomicInsert_12[A, B, C, D, E, F, G, H, I, J, K, L](molecule)
    override def retract: DatomicInsert_12[A, B, C, D, E, F, G, H, I, J, K, L] = new DatomicInsert_12[A, B, C, D, E, F, G, H, I, J, K, L](molecule)
    override def delete : DatomicInsert_12[A, B, C, D, E, F, G, H, I, J, K, L] = new DatomicInsert_12[A, B, C, D, E, F, G, H, I, J, K, L](molecule)
    override def query  : DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L)]   = new DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L)](molecule.elements)
  }

  class DatomicMoleculeApi_13[A, B, C, D, E, F, G, H, I, J, K, L, M](molecule: Molecule_13[A, B, C, D, E, F, G, H, I, J, K, L, M]) extends MoleculeApi_13[A, B, C, D, E, F, G, H, I, J, K, L, M] {
    override def insert : DatomicInsert_13[A, B, C, D, E, F, G, H, I, J, K, L, M] = new DatomicInsert_13[A, B, C, D, E, F, G, H, I, J, K, L, M](molecule)
    override def save   : DatomicInsert_13[A, B, C, D, E, F, G, H, I, J, K, L, M] = new DatomicInsert_13[A, B, C, D, E, F, G, H, I, J, K, L, M](molecule)
    override def update : DatomicInsert_13[A, B, C, D, E, F, G, H, I, J, K, L, M] = new DatomicInsert_13[A, B, C, D, E, F, G, H, I, J, K, L, M](molecule)
    override def retract: DatomicInsert_13[A, B, C, D, E, F, G, H, I, J, K, L, M] = new DatomicInsert_13[A, B, C, D, E, F, G, H, I, J, K, L, M](molecule)
    override def delete : DatomicInsert_13[A, B, C, D, E, F, G, H, I, J, K, L, M] = new DatomicInsert_13[A, B, C, D, E, F, G, H, I, J, K, L, M](molecule)
    override def query  : DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M)]   = new DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M)](molecule.elements)
  }

  class DatomicMoleculeApi_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N](molecule: Molecule_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N]) extends MoleculeApi_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N] {
    override def insert : DatomicInsert_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N] = new DatomicInsert_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N](molecule)
    override def save   : DatomicInsert_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N] = new DatomicInsert_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N](molecule)
    override def update : DatomicInsert_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N] = new DatomicInsert_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N](molecule)
    override def retract: DatomicInsert_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N] = new DatomicInsert_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N](molecule)
    override def delete : DatomicInsert_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N] = new DatomicInsert_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N](molecule)
    override def query  : DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N)]   = new DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N)](molecule.elements)
  }

  class DatomicMoleculeApi_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](molecule: Molecule_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]) extends MoleculeApi_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] {
    override def insert : DatomicInsert_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = new DatomicInsert_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](molecule)
    override def save   : DatomicInsert_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = new DatomicInsert_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](molecule)
    override def update : DatomicInsert_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = new DatomicInsert_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](molecule)
    override def retract: DatomicInsert_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = new DatomicInsert_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](molecule)
    override def delete : DatomicInsert_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = new DatomicInsert_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](molecule)
    override def query  : DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O)]   = new DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O)](molecule.elements)
  }

  class DatomicMoleculeApi_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](molecule: Molecule_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]) extends MoleculeApi_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] {
    override def insert : DatomicInsert_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = new DatomicInsert_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](molecule)
    override def save   : DatomicInsert_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = new DatomicInsert_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](molecule)
    override def update : DatomicInsert_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = new DatomicInsert_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](molecule)
    override def retract: DatomicInsert_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = new DatomicInsert_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](molecule)
    override def delete : DatomicInsert_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = new DatomicInsert_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](molecule)
    override def query  : DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P)]   = new DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P)](molecule.elements)
  }

  class DatomicMoleculeApi_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](molecule: Molecule_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]) extends MoleculeApi_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] {
    override def insert : DatomicInsert_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = new DatomicInsert_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](molecule)
    override def save   : DatomicInsert_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = new DatomicInsert_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](molecule)
    override def update : DatomicInsert_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = new DatomicInsert_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](molecule)
    override def retract: DatomicInsert_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = new DatomicInsert_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](molecule)
    override def delete : DatomicInsert_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = new DatomicInsert_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](molecule)
    override def query  : DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q)]   = new DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q)](molecule.elements)
  }

  class DatomicMoleculeApi_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](molecule: Molecule_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]) extends MoleculeApi_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] {
    override def insert : DatomicInsert_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = new DatomicInsert_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](molecule)
    override def save   : DatomicInsert_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = new DatomicInsert_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](molecule)
    override def update : DatomicInsert_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = new DatomicInsert_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](molecule)
    override def retract: DatomicInsert_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = new DatomicInsert_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](molecule)
    override def delete : DatomicInsert_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = new DatomicInsert_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](molecule)
    override def query  : DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R)]   = new DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R)](molecule.elements)
  }

  class DatomicMoleculeApi_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](molecule: Molecule_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]) extends MoleculeApi_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] {
    override def insert : DatomicInsert_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = new DatomicInsert_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](molecule)
    override def save   : DatomicInsert_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = new DatomicInsert_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](molecule)
    override def update : DatomicInsert_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = new DatomicInsert_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](molecule)
    override def retract: DatomicInsert_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = new DatomicInsert_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](molecule)
    override def delete : DatomicInsert_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = new DatomicInsert_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](molecule)
    override def query  : DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S)]   = new DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S)](molecule.elements)
  }

  class DatomicMoleculeApi_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](molecule: Molecule_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]) extends MoleculeApi_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] {
    override def insert : DatomicInsert_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = new DatomicInsert_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](molecule)
    override def save   : DatomicInsert_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = new DatomicInsert_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](molecule)
    override def update : DatomicInsert_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = new DatomicInsert_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](molecule)
    override def retract: DatomicInsert_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = new DatomicInsert_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](molecule)
    override def delete : DatomicInsert_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = new DatomicInsert_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](molecule)
    override def query  : DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T)]   = new DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T)](molecule.elements)
  }

  class DatomicMoleculeApi_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](molecule: Molecule_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]) extends MoleculeApi_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] {
    override def insert : DatomicInsert_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = new DatomicInsert_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](molecule)
    override def save   : DatomicInsert_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = new DatomicInsert_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](molecule)
    override def update : DatomicInsert_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = new DatomicInsert_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](molecule)
    override def retract: DatomicInsert_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = new DatomicInsert_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](molecule)
    override def delete : DatomicInsert_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = new DatomicInsert_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](molecule)
    override def query  : DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U)]   = new DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U)](molecule.elements)
  }

  class DatomicMoleculeApi_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](molecule: Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]) extends MoleculeApi_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] {
    override def insert : DatomicInsert_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] = new DatomicInsert_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](molecule)
    override def save   : DatomicInsert_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] = new DatomicInsert_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](molecule)
    override def update : DatomicInsert_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] = new DatomicInsert_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](molecule)
    override def retract: DatomicInsert_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] = new DatomicInsert_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](molecule)
    override def delete : DatomicInsert_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] = new DatomicInsert_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](molecule)
    override def query  : DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V)]   = new DatomicQuery[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V)](molecule.elements)
  }
}