package molecule.core.api

import molecule.core.api.Insert.Insert_2

object MoleculeApi {

  trait MoleculeApi_2[A, B] {
    def insert: Insert_2[A, B]
    def save: Insert_2[A, B] = ???
    def update: Insert_2[A, B] = ???
    def retract: Insert_2[A, B] = ???
    def delete: Insert_2[A, B] = ???
    def query: Query[(A, B)]
  }
}
