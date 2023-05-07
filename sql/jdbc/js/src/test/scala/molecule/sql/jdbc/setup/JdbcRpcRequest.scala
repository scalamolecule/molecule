package molecule.sql.jdbc.setup

import molecule.core.marshalling.MoleculeRpcRequest

// Keep a single http connection for all tests
object JdbcRpcRequest {
  val moleculeRpcRequest = new MoleculeRpcRequest("localhost", 8080)
}
