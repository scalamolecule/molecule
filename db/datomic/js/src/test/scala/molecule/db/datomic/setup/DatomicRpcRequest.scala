package molecule.db.datomic.setup

import molecule.core.marshalling.MoleculeRpcRequest

// Keep a single http connection for all tests
object DatomicRpcRequest {
  val moleculeRpcRequest = MoleculeRpcRequest("localhost", 8080)
}
