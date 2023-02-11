// GENERATED CODE ********************************
package molecule.db

import molecule.boilerplate.api._
import molecule.core.MoleculeImplicits_
import molecule.db.datomic.action._
import molecule.db.datomic.api.{DatomicApiAsync, DatomicApiSync}
import scala.language.implicitConversions

package object datomic {

  object async extends DatomicMoleculeImplicits with DatomicApiAsync
  object sync extends DatomicMoleculeImplicits with DatomicApiSync
  object typelevel extends DatomicMoleculeImplicits with DatomicApiAsync
  object zio extends DatomicMoleculeImplicits with DatomicApiAsync
  object laminar extends DatomicMoleculeImplicits with DatomicApiAsync

  private[datomic] trait DatomicMoleculeImplicits extends MoleculeImplicits_ {
    implicit final override def m(molecule: Molecule_00): DatomicActions_00 = new DatomicActions_00(molecule)
    implicit final override def m[A](molecule: Molecule_01[A]): DatomicActions_01[A] = new DatomicActions_01(molecule)
    implicit final override def m[A, B](molecule: Molecule_02[A, B]): DatomicActions_02[A, B] = new DatomicActions_02(molecule)
    implicit final override def m[A, B, C](molecule: Molecule_03[A, B, C]): DatomicActions_03[A, B, C] = new DatomicActions_03(molecule)
    implicit final override def m[A, B, C, D](molecule: Molecule_04[A, B, C, D]): DatomicActions_04[A, B, C, D] = new DatomicActions_04(molecule)
    implicit final override def m[A, B, C, D, E](molecule: Molecule_05[A, B, C, D, E]): DatomicActions_05[A, B, C, D, E] = new DatomicActions_05(molecule)
    implicit final override def m[A, B, C, D, E, F](molecule: Molecule_06[A, B, C, D, E, F]): DatomicActions_06[A, B, C, D, E, F] = new DatomicActions_06(molecule)
    implicit final override def m[A, B, C, D, E, F, G](molecule: Molecule_07[A, B, C, D, E, F, G]): DatomicActions_07[A, B, C, D, E, F, G] = new DatomicActions_07(molecule)
    implicit final override def m[A, B, C, D, E, F, G, H](molecule: Molecule_08[A, B, C, D, E, F, G, H]): DatomicActions_08[A, B, C, D, E, F, G, H] = new DatomicActions_08(molecule)
    implicit final override def m[A, B, C, D, E, F, G, H, I](molecule: Molecule_09[A, B, C, D, E, F, G, H, I]): DatomicActions_09[A, B, C, D, E, F, G, H, I] = new DatomicActions_09(molecule)
    implicit final override def m[A, B, C, D, E, F, G, H, I, J](molecule: Molecule_10[A, B, C, D, E, F, G, H, I, J]): DatomicActions_10[A, B, C, D, E, F, G, H, I, J] = new DatomicActions_10(molecule)
    implicit final override def m[A, B, C, D, E, F, G, H, I, J, K](molecule: Molecule_11[A, B, C, D, E, F, G, H, I, J, K]): DatomicActions_11[A, B, C, D, E, F, G, H, I, J, K] = new DatomicActions_11(molecule)
    implicit final override def m[A, B, C, D, E, F, G, H, I, J, K, L](molecule: Molecule_12[A, B, C, D, E, F, G, H, I, J, K, L]): DatomicActions_12[A, B, C, D, E, F, G, H, I, J, K, L] = new DatomicActions_12(molecule)
    implicit final override def m[A, B, C, D, E, F, G, H, I, J, K, L, M](molecule: Molecule_13[A, B, C, D, E, F, G, H, I, J, K, L, M]): DatomicActions_13[A, B, C, D, E, F, G, H, I, J, K, L, M] = new DatomicActions_13(molecule)
    implicit final override def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N](molecule: Molecule_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N]): DatomicActions_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N] = new DatomicActions_14(molecule)
    implicit final override def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](molecule: Molecule_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]): DatomicActions_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = new DatomicActions_15(molecule)
    implicit final override def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](molecule: Molecule_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]): DatomicActions_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = new DatomicActions_16(molecule)
    implicit final override def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](molecule: Molecule_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]): DatomicActions_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = new DatomicActions_17(molecule)
    implicit final override def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](molecule: Molecule_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]): DatomicActions_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = new DatomicActions_18(molecule)
    implicit final override def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](molecule: Molecule_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]): DatomicActions_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = new DatomicActions_19(molecule)
    implicit final override def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](molecule: Molecule_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]): DatomicActions_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = new DatomicActions_20(molecule)
    implicit final override def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](molecule: Molecule_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]): DatomicActions_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = new DatomicActions_21(molecule)
    implicit final override def m[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](molecule: Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]): DatomicActions_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] = new DatomicActions_22(molecule)

    implicit final override def m(composite: Composite_00): DatomicActions_00 = new DatomicActions_00(composite)
    implicit final override def m[T1](composite: Composite_01[T1]): DatomicActions_01[T1] = new DatomicActions_01(composite)
    implicit final override def m[T1, T2](composite: Composite_02[T1, T2]): DatomicActions_02[T1, T2] = new DatomicActions_02(composite)
    implicit final override def m[T1, T2, T3](composite: Composite_03[T1, T2, T3]): DatomicActions_03[T1, T2, T3] = new DatomicActions_03(composite)
    implicit final override def m[T1, T2, T3, T4](composite: Composite_04[T1, T2, T3, T4]): DatomicActions_04[T1, T2, T3, T4] = new DatomicActions_04(composite)
    implicit final override def m[T1, T2, T3, T4, T5](composite: Composite_05[T1, T2, T3, T4, T5]): DatomicActions_05[T1, T2, T3, T4, T5] = new DatomicActions_05(composite)
    implicit final override def m[T1, T2, T3, T4, T5, T6](composite: Composite_06[T1, T2, T3, T4, T5, T6]): DatomicActions_06[T1, T2, T3, T4, T5, T6] = new DatomicActions_06(composite)
    implicit final override def m[T1, T2, T3, T4, T5, T6, T7](composite: Composite_07[T1, T2, T3, T4, T5, T6, T7]): DatomicActions_07[T1, T2, T3, T4, T5, T6, T7] = new DatomicActions_07(composite)
    implicit final override def m[T1, T2, T3, T4, T5, T6, T7, T8](composite: Composite_08[T1, T2, T3, T4, T5, T6, T7, T8]): DatomicActions_08[T1, T2, T3, T4, T5, T6, T7, T8] = new DatomicActions_08(composite)
    implicit final override def m[T1, T2, T3, T4, T5, T6, T7, T8, T9](composite: Composite_09[T1, T2, T3, T4, T5, T6, T7, T8, T9]): DatomicActions_09[T1, T2, T3, T4, T5, T6, T7, T8, T9] = new DatomicActions_09(composite)
    implicit final override def m[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](composite: Composite_10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10]): DatomicActions_10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10] = new DatomicActions_10(composite)
    implicit final override def m[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](composite: Composite_11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11]): DatomicActions_11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11] = new DatomicActions_11(composite)
    implicit final override def m[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](composite: Composite_12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12]): DatomicActions_12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12] = new DatomicActions_12(composite)
    implicit final override def m[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](composite: Composite_13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13]): DatomicActions_13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13] = new DatomicActions_13(composite)
    implicit final override def m[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](composite: Composite_14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14]): DatomicActions_14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14] = new DatomicActions_14(composite)
    implicit final override def m[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](composite: Composite_15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15]): DatomicActions_15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15] = new DatomicActions_15(composite)
    implicit final override def m[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](composite: Composite_16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16]): DatomicActions_16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16] = new DatomicActions_16(composite)
    implicit final override def m[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](composite: Composite_17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17]): DatomicActions_17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17] = new DatomicActions_17(composite)
    implicit final override def m[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](composite: Composite_18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18]): DatomicActions_18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18] = new DatomicActions_18(composite)
    implicit final override def m[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](composite: Composite_19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19]): DatomicActions_19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19] = new DatomicActions_19(composite)
    implicit final override def m[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](composite: Composite_20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20]): DatomicActions_20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20] = new DatomicActions_20(composite)
    implicit final override def m[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](composite: Composite_21[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21]): DatomicActions_21[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21] = new DatomicActions_21(composite)
    implicit final override def m[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](composite: Composite_22[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22]): DatomicActions_22[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22] = new DatomicActions_22(composite)
  }
}