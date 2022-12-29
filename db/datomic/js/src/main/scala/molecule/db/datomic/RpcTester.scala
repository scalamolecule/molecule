//package molecule.db.datomic
//
//import java.nio.ByteBuffer
//import molecule.core.marshalling.{MoleculeRpc, MoleculeRpcRequest}
//import sloth._
//import scala.concurrent.Future
//import molecule.core.util.Executor._
//import boopickle.Default._
//import chameleon._
//
//object RpcTester {
//
//
//  val rpc2 = Client.apply[ByteBuffer, Future](
//    MoleculeRpcRequest("localhost", 8080)
//  ).wire[MoleculeRpc]
//}
