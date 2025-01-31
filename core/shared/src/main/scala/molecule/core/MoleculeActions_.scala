// GENERATED CODE ********************************
package molecule.core

import molecule.core.api._
import molecule.core.action._
import scala.language.implicitConversions

trait MoleculeActions_ extends Keywords {
  implicit final class _actions0(molecule: Molecule_00){
    def delete = Delete(molecule.elements)
  }
  
  implicit final class _actions1[A](molecule: Molecule_01[A]) {
    def save   = Save(molecule.elements)
    def insert = Insert_1[A](molecule.elements)
    def query  = Query[A](molecule.elements)
    def update = Update(molecule.elements)
    def upsert = Update(molecule.elements, true)
    def delete = Delete(molecule.elements)
  }
  
  implicit final class _actions2[A, B](molecule: Molecule_02[A, B]) {
    def save   = Save(molecule.elements)
    def insert = Insert_2[A, B](molecule.elements)
    def query  = Query[(A, B)](molecule.elements)
    def update = Update(molecule.elements)
    def upsert = Update(molecule.elements, true)
    def delete = Delete(molecule.elements)
  }
  
  implicit final class _actions3[A, B, C](molecule: Molecule_03[A, B, C]) {
    def save   = Save(molecule.elements)
    def insert = Insert_3[A, B, C](molecule.elements)
    def query  = Query[(A, B, C)](molecule.elements)
    def update = Update(molecule.elements)
    def upsert = Update(molecule.elements, true)
    def delete = Delete(molecule.elements)
  }
  
  implicit final class _actions4[A, B, C, D](molecule: Molecule_04[A, B, C, D]) {
    def save   = Save(molecule.elements)
    def insert = Insert_4[A, B, C, D](molecule.elements)
    def query  = Query[(A, B, C, D)](molecule.elements)
    def update = Update(molecule.elements)
    def upsert = Update(molecule.elements, true)
    def delete = Delete(molecule.elements)
  }
  
  implicit final class _actions5[A, B, C, D, E](molecule: Molecule_05[A, B, C, D, E]) {
    def save   = Save(molecule.elements)
    def insert = Insert_5[A, B, C, D, E](molecule.elements)
    def query  = Query[(A, B, C, D, E)](molecule.elements)
    def update = Update(molecule.elements)
    def upsert = Update(molecule.elements, true)
    def delete = Delete(molecule.elements)
  }
  
  implicit final class _actions6[A, B, C, D, E, F](molecule: Molecule_06[A, B, C, D, E, F]) {
    def save   = Save(molecule.elements)
    def insert = Insert_6[A, B, C, D, E, F](molecule.elements)
    def query  = Query[(A, B, C, D, E, F)](molecule.elements)
    def update = Update(molecule.elements)
    def upsert = Update(molecule.elements, true)
    def delete = Delete(molecule.elements)
  }
  
  implicit final class _actions7[A, B, C, D, E, F, G](molecule: Molecule_07[A, B, C, D, E, F, G]) {
    def save   = Save(molecule.elements)
    def insert = Insert_7[A, B, C, D, E, F, G](molecule.elements)
    def query  = Query[(A, B, C, D, E, F, G)](molecule.elements)
    def update = Update(molecule.elements)
    def upsert = Update(molecule.elements, true)
    def delete = Delete(molecule.elements)
  }
  
  implicit final class _actions8[A, B, C, D, E, F, G, H](molecule: Molecule_08[A, B, C, D, E, F, G, H]) {
    def save   = Save(molecule.elements)
    def insert = Insert_8[A, B, C, D, E, F, G, H](molecule.elements)
    def query  = Query[(A, B, C, D, E, F, G, H)](molecule.elements)
    def update = Update(molecule.elements)
    def upsert = Update(molecule.elements, true)
    def delete = Delete(molecule.elements)
  }
  
  implicit final class _actions9[A, B, C, D, E, F, G, H, I](molecule: Molecule_09[A, B, C, D, E, F, G, H, I]) {
    def save   = Save(molecule.elements)
    def insert = Insert_9[A, B, C, D, E, F, G, H, I](molecule.elements)
    def query  = Query[(A, B, C, D, E, F, G, H, I)](molecule.elements)
    def update = Update(molecule.elements)
    def upsert = Update(molecule.elements, true)
    def delete = Delete(molecule.elements)
  }
  
  implicit final class _actions10[A, B, C, D, E, F, G, H, I, J](molecule: Molecule_10[A, B, C, D, E, F, G, H, I, J]) {
    def save   = Save(molecule.elements)
    def insert = Insert_10[A, B, C, D, E, F, G, H, I, J](molecule.elements)
    def query  = Query[(A, B, C, D, E, F, G, H, I, J)](molecule.elements)
    def update = Update(molecule.elements)
    def upsert = Update(molecule.elements, true)
    def delete = Delete(molecule.elements)
  }
  
  implicit final class _actions11[A, B, C, D, E, F, G, H, I, J, K](molecule: Molecule_11[A, B, C, D, E, F, G, H, I, J, K]) {
    def save   = Save(molecule.elements)
    def insert = Insert_11[A, B, C, D, E, F, G, H, I, J, K](molecule.elements)
    def query  = Query[(A, B, C, D, E, F, G, H, I, J, K)](molecule.elements)
    def update = Update(molecule.elements)
    def upsert = Update(molecule.elements, true)
    def delete = Delete(molecule.elements)
  }
  
  implicit final class _actions12[A, B, C, D, E, F, G, H, I, J, K, L](molecule: Molecule_12[A, B, C, D, E, F, G, H, I, J, K, L]) {
    def save   = Save(molecule.elements)
    def insert = Insert_12[A, B, C, D, E, F, G, H, I, J, K, L](molecule.elements)
    def query  = Query[(A, B, C, D, E, F, G, H, I, J, K, L)](molecule.elements)
    def update = Update(molecule.elements)
    def upsert = Update(molecule.elements, true)
    def delete = Delete(molecule.elements)
  }
  
  implicit final class _actions13[A, B, C, D, E, F, G, H, I, J, K, L, M](molecule: Molecule_13[A, B, C, D, E, F, G, H, I, J, K, L, M]) {
    def save   = Save(molecule.elements)
    def insert = Insert_13[A, B, C, D, E, F, G, H, I, J, K, L, M](molecule.elements)
    def query  = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M)](molecule.elements)
    def update = Update(molecule.elements)
    def upsert = Update(molecule.elements, true)
    def delete = Delete(molecule.elements)
  }
  
  implicit final class _actions14[A, B, C, D, E, F, G, H, I, J, K, L, M, N](molecule: Molecule_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N]) {
    def save   = Save(molecule.elements)
    def insert = Insert_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N](molecule.elements)
    def query  = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N)](molecule.elements)
    def update = Update(molecule.elements)
    def upsert = Update(molecule.elements, true)
    def delete = Delete(molecule.elements)
  }
  
  implicit final class _actions15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](molecule: Molecule_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]) {
    def save   = Save(molecule.elements)
    def insert = Insert_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O](molecule.elements)
    def query  = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O)](molecule.elements)
    def update = Update(molecule.elements)
    def upsert = Update(molecule.elements, true)
    def delete = Delete(molecule.elements)
  }
  
  implicit final class _actions16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](molecule: Molecule_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]) {
    def save   = Save(molecule.elements)
    def insert = Insert_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P](molecule.elements)
    def query  = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P)](molecule.elements)
    def update = Update(molecule.elements)
    def upsert = Update(molecule.elements, true)
    def delete = Delete(molecule.elements)
  }
  
  implicit final class _actions17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](molecule: Molecule_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]) {
    def save   = Save(molecule.elements)
    def insert = Insert_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q](molecule.elements)
    def query  = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q)](molecule.elements)
    def update = Update(molecule.elements)
    def upsert = Update(molecule.elements, true)
    def delete = Delete(molecule.elements)
  }
  
  implicit final class _actions18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](molecule: Molecule_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]) {
    def save   = Save(molecule.elements)
    def insert = Insert_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](molecule.elements)
    def query  = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R)](molecule.elements)
    def update = Update(molecule.elements)
    def upsert = Update(molecule.elements, true)
    def delete = Delete(molecule.elements)
  }
  
  implicit final class _actions19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](molecule: Molecule_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]) {
    def save   = Save(molecule.elements)
    def insert = Insert_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S](molecule.elements)
    def query  = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S)](molecule.elements)
    def update = Update(molecule.elements)
    def upsert = Update(molecule.elements, true)
    def delete = Delete(molecule.elements)
  }
  
  implicit final class _actions20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](molecule: Molecule_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]) {
    def save   = Save(molecule.elements)
    def insert = Insert_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T](molecule.elements)
    def query  = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T)](molecule.elements)
    def update = Update(molecule.elements)
    def upsert = Update(molecule.elements, true)
    def delete = Delete(molecule.elements)
  }
  
  implicit final class _actions21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](molecule: Molecule_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]) {
    def save   = Save(molecule.elements)
    def insert = Insert_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U](molecule.elements)
    def query  = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U)](molecule.elements)
    def update = Update(molecule.elements)
    def upsert = Update(molecule.elements, true)
    def delete = Delete(molecule.elements)
  }
  
  implicit final class _actions22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](molecule: Molecule_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]) {
    def save   = Save(molecule.elements)
    def insert = Insert_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V](molecule.elements)
    def query  = Query[(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V)](molecule.elements)
    def update = Update(molecule.elements)
    def upsert = Update(molecule.elements, true)
    def delete = Delete(molecule.elements)
  }
  
}