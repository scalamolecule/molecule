package molecule.boilerplate.markers

import argKindMarkers._
import cardinalityMarkers._
import molecule.boilerplate.ast.moleculeModel.{Atom, Element}


/** Namespace markers to identify various namespace types during dsl2model extraction. */
object namespaceMarkers {

  /** Initial empty property trait for object type buildup */
  trait Init

  /** First namespace of molecule */
  trait FirstNS

  /** Namespace has entity ids applied */
  trait NsEids


  trait Ref[Ns, RefNs]
  trait OneRef[Ns, RefNs] extends Ref[Ns, RefNs] with Card1
  trait ManyRef[Ns, RefNs] extends Ref[Ns, RefNs] with Card2

  trait SelfJoin


  trait Molecule[Tpl] {
    private[molecule] val elements: Seq[Element]
  }
  trait Molecule_00 extends Molecule[Nothing]
  trait Molecule_01[A] extends Molecule[A]
  trait Molecule_02[A, B] extends Molecule[(A, B)]
  trait Molecule_03[A, B, C] extends Molecule[(A, B, C)]
  trait Molecule_04[A, B, C, D] extends Molecule[(A, B, C, D)]
  trait Molecule_05[A, B, C, D, E] extends Molecule[(A, B, C, D, E)]
  trait Molecule_06[A, B, C, D, E, F] extends Molecule[(A, B, C, D, E, F)]
  trait Molecule_07[A, B, C, D, E, F, G] extends Molecule[(A, B, C, D, E, F, G)]
  trait Molecule_08[A, B, C, D, E, F, G, H] extends Molecule[(A, B, C, D, E, F, G, H)]
  trait Molecule_09[A, B, C, D, E, F, G, H, I] extends Molecule[(A, B, C, D, E, F, G, H, I)]
  trait Molecule_10[A, B, C, D, E, F, G, H, I, J] extends Molecule[(A, B, C, D, E, F, G, H, I, J)]
  trait Molecule_11[A, B, C, D, E, F, G, H, I, J, K] extends Molecule[(A, B, C, D, E, F, G, H, I, J, K)]
  trait Molecule_12[A, B, C, D, E, F, G, H, I, J, K, L] extends Molecule[(A, B, C, D, E, F, G, H, I, J, K, L)]
  trait Molecule_13[A, B, C, D, E, F, G, H, I, J, K, L, M] extends Molecule[(A, B, C, D, E, F, G, H, I, J, K, L, M)]
  trait Molecule_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N] extends Molecule[(A, B, C, D, E, F, G, H, I, J, K, L, M, N)]
  trait Molecule_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] extends Molecule[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O)]
  trait Molecule_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] extends Molecule[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P)]
  trait Molecule_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] extends Molecule[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q)]
  trait Molecule_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] extends Molecule[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R)]
  trait Molecule_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] extends Molecule[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S)]
  trait Molecule_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] extends Molecule[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T)]
  trait Molecule_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] extends Molecule[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U)]
  trait Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] extends Molecule[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V)]
}
