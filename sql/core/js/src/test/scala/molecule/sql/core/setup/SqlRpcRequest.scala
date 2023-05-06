package molecule.sql.core.setup

import molecule.core.marshalling.MoleculeRpcRequest

// Keep a single http connection for all tests
object SqlRpcRequest {
  val moleculeRpcRequest = new MoleculeRpcRequest("localhost", 8080)
}
