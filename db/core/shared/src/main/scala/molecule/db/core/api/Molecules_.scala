// GENERATED CODE ********************************
package molecule.db.core.api

import molecule.db.core.action.*
import molecule.db.core.ast.Element

trait Molecule {
  val elements: List[Element]
}

trait Molecule_00 extends Molecule {
  def delete = Delete(elements)
}

trait MoleculeBase extends Molecule {
  def save = Save(elements)
  def update = Update(elements)
  def upsert = Update(elements, true)
  def delete = Delete(elements)
}

trait Molecule_01[A] extends MoleculeBase {
  def insert = Insert_1[A](elements)
  def query = Query[A](elements)
}

trait Molecule_02[A, B] extends MoleculeBase {
  def insert = Insert_2[A, B](elements)
  def query = Query[(A, B)](elements)
}

trait Molecule_03[A, B, C] extends MoleculeBase {
  def insert = Insert_3[A, B, C](elements)
  def query = Query[(A, B, C)](elements)
}

trait Molecule_04[A, B, C, D] extends MoleculeBase {
  def insert = Insert_4[A, B, C, D](elements)
  def query = Query[(A, B, C, D)](elements)
}

trait Molecule_05[A, B, C, D, E] extends MoleculeBase {
  def insert = Insert_5[A, B, C, D, E](elements)
  def query = Query[(A, B, C, D, E)](elements)
}

trait Molecule_06[A, B, C, D, E, F] extends MoleculeBase {
  def insert = Insert_6[A, B, C, D, E, F](elements)
  def query = Query[(A, B, C, D, E, F)](elements)
}

trait Molecule_07[A, B, C, D, E, F, G] extends MoleculeBase {
  def insert = Insert_7[A, B, C, D, E, F, G](elements)
  def query = Query[(A, B, C, D, E, F, G)](elements)
}

trait Molecule_08[A, B, C, D, E, F, G, H] extends MoleculeBase {
  def insert = Insert_8[A, B, C, D, E, F, G, H](elements)
  def query = Query[(A, B, C, D, E, F, G, H)](elements)
}

trait Molecule_09[A, B, C, D, E, F, G, H, I] extends MoleculeBase {
  def insert = Insert_9[A, B, C, D, E, F, G, H, I](elements)
  def query = Query[(A, B, C, D, E, F, G, H, I)](elements)
}

trait Molecule_10[A, B, C, D, E, F, G, H, I, J] extends MoleculeBase {
  def insert = Insert_10[A, B, C, D, E, F, G, H, I, J](elements)
  def query = Query[(A, B, C, D, E, F, G, H, I, J)](elements)
}

trait Molecule_11[A, B, C, D, E, F, G, H, I, J, K] extends MoleculeBase {
  def insert = Insert_11[A, B, C, D, E, F, G, H, I, J, K](elements)
  def query = Query[(A, B, C, D, E, F, G, H, I, J, K)](elements)
}

trait Molecule_12[A, B, C, D, E, F, G, H, I, J, K, L] extends MoleculeBase {
  def insert = Insert_12[A, B, C, D, E, F, G, H, I, J, K, L](elements)
  def query = Query[(A, B, C, D, E, F, G, H, I, J, K, L)](elements)
}

trait Molecule_13[A, B, C, D, E, F, G, H, I, J, K, L, M] extends MoleculeBase {
  def insert = Insert_13[A, B, C, D, E, F, G, H, I, J, K, L, M](elements)
  def query = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M)](elements)
}

trait Molecule_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N] extends MoleculeBase {
  def insert = Insert_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N](elements)
  def query = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N)](elements)
}

trait Molecule_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] extends MoleculeBase {
  def insert = Insert_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](elements)
  def query = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O)](elements)
}

trait Molecule_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] extends MoleculeBase {
  def insert = Insert_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](elements)
  def query = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P)](elements)
}

trait Molecule_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] extends MoleculeBase {
  def insert = Insert_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](elements)
  def query = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q)](elements)
}

trait Molecule_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] extends MoleculeBase {
  def insert = Insert_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](elements)
  def query = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R)](elements)
}

trait Molecule_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] extends MoleculeBase {
  def insert = Insert_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](elements)
  def query = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S)](elements)
}

trait Molecule_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] extends MoleculeBase {
  def insert = Insert_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](elements)
  def query = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T)](elements)
}

trait Molecule_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] extends MoleculeBase {
  def insert = Insert_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](elements)
  def query = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U)](elements)
}

trait Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] extends MoleculeBase {
  def insert = Insert_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](elements)
  def query = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V)](elements)
}
  
