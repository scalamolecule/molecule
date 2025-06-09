// GENERATED CODE ********************************
package molecule.graphql.client.api

import molecule.base.error.ModelError
import molecule.core.ast.DataModel
import molecule.graphql.client.action.{Mutate, Query}

trait Molecule {
  val dataModel: DataModel

  protected def noBinding: Unit = {
    if (dataModel.binds != 0)
      throw ModelError(s"Mutations don't support input arguments.")
  }
}

trait Molecule_00 extends Molecule

trait MoleculeBase extends Molecule {
  def mutate: Mutate = {
    noBinding
    Mutate(dataModel)
  }
}

trait Molecule_01[A] extends MoleculeBase {
  def query: Query[A] =
    Query[A](dataModel)
}

trait Molecule_02[A, B] extends MoleculeBase {
  def query: Query[(A, B)] =
    Query[(A, B)](dataModel)
}

trait Molecule_03[A, B, C] extends MoleculeBase {
  def query: Query[(A, B, C)] =
    Query[(A, B, C)](dataModel)
}

trait Molecule_04[A, B, C, D] extends MoleculeBase {
  def query: Query[(A, B, C, D)] =
    Query[(A, B, C, D)](dataModel)
}

trait Molecule_05[A, B, C, D, E] extends MoleculeBase {
  def query: Query[(A, B, C, D, E)] =
    Query[(A, B, C, D, E)](dataModel)
}

trait Molecule_06[A, B, C, D, E, F] extends MoleculeBase {
  def query: Query[(A, B, C, D, E, F)] =
    Query[(A, B, C, D, E, F)](dataModel)
}

trait Molecule_07[A, B, C, D, E, F, G] extends MoleculeBase {
  def query: Query[(A, B, C, D, E, F, G)] =
    Query[(A, B, C, D, E, F, G)](dataModel)
}

trait Molecule_08[A, B, C, D, E, F, G, H] extends MoleculeBase {
  def query: Query[(A, B, C, D, E, F, G, H)] =
    Query[(A, B, C, D, E, F, G, H)](dataModel)
}

trait Molecule_09[A, B, C, D, E, F, G, H, I] extends MoleculeBase {
  def query: Query[(A, B, C, D, E, F, G, H, I)] =
    Query[(A, B, C, D, E, F, G, H, I)](dataModel)
}

trait Molecule_10[A, B, C, D, E, F, G, H, I, J] extends MoleculeBase {
  def query: Query[(A, B, C, D, E, F, G, H, I, J)] =
    Query[(A, B, C, D, E, F, G, H, I, J)](dataModel)
}

trait Molecule_11[A, B, C, D, E, F, G, H, I, J, K] extends MoleculeBase {
  def query: Query[(A, B, C, D, E, F, G, H, I, J, K)] =
    Query[(A, B, C, D, E, F, G, H, I, J, K)](dataModel)
}

trait Molecule_12[A, B, C, D, E, F, G, H, I, J, K, L] extends MoleculeBase {
  def query: Query[(A, B, C, D, E, F, G, H, I, J, K, L)] =
    Query[(A, B, C, D, E, F, G, H, I, J, K, L)](dataModel)
}

trait Molecule_13[A, B, C, D, E, F, G, H, I, J, K, L, M] extends MoleculeBase {
  def query: Query[(A, B, C, D, E, F, G, H, I, J, K, L, M)] =
    Query[(A, B, C, D, E, F, G, H, I, J, K, L, M)](dataModel)
}

trait Molecule_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N] extends MoleculeBase {
  def query: Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N)] =
    Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N)](dataModel)
}

trait Molecule_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] extends MoleculeBase {
  def query: Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O)] =
    Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O)](dataModel)
}

trait Molecule_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] extends MoleculeBase {
  def query: Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P)] =
    Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P)](dataModel)
}

trait Molecule_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] extends MoleculeBase {
  def query: Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q)] =
    Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q)](dataModel)
}

trait Molecule_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] extends MoleculeBase {
  def query: Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R)] =
    Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R)](dataModel)
}

trait Molecule_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] extends MoleculeBase {
  def query: Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S)] =
    Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S)](dataModel)
}

trait Molecule_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] extends MoleculeBase {
  def query: Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T)] =
    Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T)](dataModel)
}

trait Molecule_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] extends MoleculeBase {
  def query: Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U)] =
    Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U)](dataModel)
}

trait Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] extends MoleculeBase {
  def query: Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V)] =
    Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V)](dataModel)
}

