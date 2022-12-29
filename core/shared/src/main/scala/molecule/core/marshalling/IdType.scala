package molecule.core.marshalling

import cats.Id

object IdType {

  type Id[A] = A
  object Id {
    def apply[A](a: A): Id[A] = a
  }
}
