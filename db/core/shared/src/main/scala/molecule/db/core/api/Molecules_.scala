// GENERATED CODE ********************************
package molecule.db.core.api

import molecule.db.core.action.*
import molecule.db.core.ast.DataModel

trait Molecule {
  val dataModel: DataModel
}

trait Molecule_00 extends Molecule {
  def delete = Delete(dataModel)
}

trait MoleculeBase extends Molecule {
  def save = Save(dataModel)
  def update = Update(dataModel)
  def upsert = Update(dataModel, true)
  def delete = Delete(dataModel)
}

  trait Molecule_01[A] extends MoleculeBase {
    def insert = Insert_1[A](dataModel)
    def query  = Query[A](dataModel)
  }
  
  trait Molecule_02[A, B] extends MoleculeBase {
    def insert = Insert_2[A, B](dataModel)
    def query  = Query[(A, B)](dataModel)
  }
  
  trait Molecule_03[A, B, C] extends MoleculeBase {
    def insert = Insert_3[A, B, C](dataModel)
    def query  = Query[(A, B, C)](dataModel)
  }
  
  trait Molecule_04[A, B, C, D] extends MoleculeBase {
    def insert = Insert_4[A, B, C, D](dataModel)
    def query  = Query[(A, B, C, D)](dataModel)
  }
  
  trait Molecule_05[A, B, C, D, E] extends MoleculeBase {
    def insert = Insert_5[A, B, C, D, E](dataModel)
    def query  = Query[(A, B, C, D, E)](dataModel)
  }
  
  trait Molecule_06[A, B, C, D, E, F] extends MoleculeBase {
    def insert = Insert_6[A, B, C, D, E, F](dataModel)
    def query  = Query[(A, B, C, D, E, F)](dataModel)
  }
  
  trait Molecule_07[A, B, C, D, E, F, G] extends MoleculeBase {
    def insert = Insert_7[A, B, C, D, E, F, G](dataModel)
    def query  = Query[(A, B, C, D, E, F, G)](dataModel)
  }
  
  trait Molecule_08[A, B, C, D, E, F, G, H] extends MoleculeBase {
    def insert = Insert_8[A, B, C, D, E, F, G, H](dataModel)
    def query  = Query[(A, B, C, D, E, F, G, H)](dataModel)
  }
  
  trait Molecule_09[A, B, C, D, E, F, G, H, I] extends MoleculeBase {
    def insert = Insert_9[A, B, C, D, E, F, G, H, I](dataModel)
    def query  = Query[(A, B, C, D, E, F, G, H, I)](dataModel)
  }
  
  trait Molecule_10[A, B, C, D, E, F, G, H, I, J] extends MoleculeBase {
    def insert = Insert_10[A, B, C, D, E, F, G, H, I, J](dataModel)
    def query  = Query[(A, B, C, D, E, F, G, H, I, J)](dataModel)
  }
  
  trait Molecule_11[A, B, C, D, E, F, G, H, I, J, K] extends MoleculeBase {
    def insert = Insert_11[A, B, C, D, E, F, G, H, I, J, K](dataModel)
    def query  = Query[(A, B, C, D, E, F, G, H, I, J, K)](dataModel)
  }
  
  trait Molecule_12[A, B, C, D, E, F, G, H, I, J, K, L] extends MoleculeBase {
    def insert = Insert_12[A, B, C, D, E, F, G, H, I, J, K, L](dataModel)
    def query  = Query[(A, B, C, D, E, F, G, H, I, J, K, L)](dataModel)
  }
  
  trait Molecule_13[A, B, C, D, E, F, G, H, I, J, K, L, M] extends MoleculeBase {
    def insert = Insert_13[A, B, C, D, E, F, G, H, I, J, K, L, M](dataModel)
    def query  = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M)](dataModel)
  }
  
  trait Molecule_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N] extends MoleculeBase {
    def insert = Insert_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N](dataModel)
    def query  = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N)](dataModel)
  }
  
  trait Molecule_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] extends MoleculeBase {
    def insert = Insert_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](dataModel)
    def query  = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O)](dataModel)
  }
  
  trait Molecule_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] extends MoleculeBase {
    def insert = Insert_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](dataModel)
    def query  = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P)](dataModel)
  }
  
  trait Molecule_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] extends MoleculeBase {
    def insert = Insert_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](dataModel)
    def query  = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q)](dataModel)
  }
  
  trait Molecule_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] extends MoleculeBase {
    def insert = Insert_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](dataModel)
    def query  = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R)](dataModel)
  }
  
  trait Molecule_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] extends MoleculeBase {
    def insert = Insert_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](dataModel)
    def query  = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S)](dataModel)
  }
  
  trait Molecule_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] extends MoleculeBase {
    def insert = Insert_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](dataModel)
    def query  = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T)](dataModel)
  }
  
  trait Molecule_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] extends MoleculeBase {
    def insert = Insert_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](dataModel)
    def query  = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U)](dataModel)
  }
  
  trait Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V] extends MoleculeBase {
    def insert = Insert_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](dataModel)
    def query  = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V)](dataModel)
  }
  
