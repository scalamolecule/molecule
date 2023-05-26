package molecule.core.marshalling

// Keep a single http connection for all tests
object RpcRequest {
  val request = new MoleculeRpcRequest("localhost", 8080)
}
