// GENERATED CODE ********************************
package molecule.db

import molecule.boilerplate.api._
import molecule.core.MoleculeImplicits_
import molecule.db.datomic.api._
import scala.language.implicitConversions

package object datomic
  extends MoleculeImplicits_
    with Keywords {

  implicit final override def m[A](molecule: Molecule_01[A]): DatomicMoleculeApi_01[A] = new DatomicMoleculeApi_01(molecule)
  implicit final override def m[A, B](molecule: Molecule_02[A, B]): DatomicMoleculeApi_02[A, B] = new DatomicMoleculeApi_02(molecule)
  implicit final override def m[A, B, C](molecule: Molecule_03[A, B, C]): DatomicMoleculeApi_03[A, B, C] = new DatomicMoleculeApi_03(molecule)
  implicit final override def m[A, B, C, D](molecule: Molecule_04[A, B, C, D]): DatomicMoleculeApi_04[A, B, C, D] = new DatomicMoleculeApi_04(molecule)
  implicit final override def m[A, B, C, D, E](molecule: Molecule_05[A, B, C, D, E]): DatomicMoleculeApi_05[A, B, C, D, E] = new DatomicMoleculeApi_05(molecule)
  implicit final override def m[A, B, C, D, E, F](molecule: Molecule_06[A, B, C, D, E, F]): DatomicMoleculeApi_06[A, B, C, D, E, F] = new DatomicMoleculeApi_06(molecule)
  implicit final override def m[A, B, C, D, E, F, G](molecule: Molecule_07[A, B, C, D, E, F, G]): DatomicMoleculeApi_07[A, B, C, D, E, F, G] = new DatomicMoleculeApi_07(molecule)
  implicit final override def m[A, B, C, D, E, F, G, H](molecule: Molecule_08[A, B, C, D, E, F, G, H]): DatomicMoleculeApi_08[A, B, C, D, E, F, G, H] = new DatomicMoleculeApi_08(molecule)
  implicit final override def m[A, B, C, D, E, F, G, H, I](molecule: Molecule_09[A, B, C, D, E, F, G, H, I]): DatomicMoleculeApi_09[A, B, C, D, E, F, G, H, I] = new DatomicMoleculeApi_09(molecule)
  implicit final override def m[A, B, C, D, E, F, G, H, I, J](molecule: Molecule_10[A, B, C, D, E, F, G, H, I, J]): DatomicMoleculeApi_10[A, B, C, D, E, F, G, H, I, J] = new DatomicMoleculeApi_10(molecule)
  implicit final override def m[A, B, C, D, E, F, G, H, I, J, K](molecule: Molecule_11[A, B, C, D, E, F, G, H, I, J, K]): DatomicMoleculeApi_11[A, B, C, D, E, F, G, H, I, J, K] = new DatomicMoleculeApi_11(molecule)
  implicit final override def m[A, B, C, D, E, F, G, H, I, J, K, L](molecule: Molecule_12[A, B, C, D, E, F, G, H, I, J, K, L]): DatomicMoleculeApi_12[A, B, C, D, E, F, G, H, I, J, K, L] = new DatomicMoleculeApi_12(molecule)
  implicit final override def m[A, B, C, D, E, F, G, H, I, J, K, L, M](molecule: Molecule_13[A, B, C, D, E, F, G, H, I, J, K, L, M]): DatomicMoleculeApi_13[A, B, C, D, E, F, G, H, I, J, K, L, M] = new DatomicMoleculeApi_13(molecule)
  implicit final override def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N](molecule: Molecule_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N]): DatomicMoleculeApi_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N] = new DatomicMoleculeApi_14(molecule)
  implicit final override def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](molecule: Molecule_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]): DatomicMoleculeApi_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = new DatomicMoleculeApi_15(molecule)
  implicit final override def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](molecule: Molecule_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]): DatomicMoleculeApi_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = new DatomicMoleculeApi_16(molecule)
  implicit final override def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](molecule: Molecule_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]): DatomicMoleculeApi_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = new DatomicMoleculeApi_17(molecule)
  implicit final override def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](molecule: Molecule_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]): DatomicMoleculeApi_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = new DatomicMoleculeApi_18(molecule)
  implicit final override def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](molecule: Molecule_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]): DatomicMoleculeApi_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = new DatomicMoleculeApi_19(molecule)
  implicit final override def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](molecule: Molecule_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]): DatomicMoleculeApi_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = new DatomicMoleculeApi_20(molecule)
  implicit final override def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](molecule: Molecule_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]): DatomicMoleculeApi_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = new DatomicMoleculeApi_21(molecule)
  implicit final override def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](molecule: Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]): DatomicMoleculeApi_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] = new DatomicMoleculeApi_22(molecule)
}