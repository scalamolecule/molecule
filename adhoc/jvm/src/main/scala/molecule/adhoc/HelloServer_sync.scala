//package molecule.adhoc
//
//import boopickle.Default._
//import molecule.adhoc.domains.HelloApi._
//import molecule.base.error.{ExecutionError, MoleculeError}
//import sttp.shared.Identity
//import sttp.tapir.server.ServerEndpoint
//import sttp.tapir.server.netty._
//import scala.concurrent.{ExecutionContext, Future}
//import scala.io.StdIn
//import sttp.tapir.server.netty.sync.NettySyncServer
//import sttp.tapir.server.netty.sync.NettySyncServerBinding
//import sttp.tapir.server.ServerEndpoint
//
//
//// Scala 3 only!
//
//object HelloServer_sync {
//
//  private def toServerEndpoint(endpoint: publicEndpoint): ServerEndpoint[Any, Identity] = {
//    endpoint.serverLogic[Identity] { args =>
//        try {
//          val name     = Unpickle[String].fromBytes(args)
//          val response = HelloLogic.hello(name)
//          Right(Pickle.intoBytes(response))
//        } catch {
//          case e: MoleculeError =>
//            // User errors
//            Left(e)
//          case e: Throwable     =>
//            // Db/pickling errors
//            Left(ExecutionError(e.getMessage))
//        }
//      }
//  }
//
//
//  // Start the server
//  def main(args: Array[String]): Unit = {
////    val server = NettyFutureServer()
//    val server = NettySyncServer()
//      .port(8080)
//      .addEndpoints(moleculeEndpoints.map(toServerEndpoint))
////      .addEndpoints(moleculeEndpoints.map(toServerEndpoint2))
//      .start()
//
//    server.foreach { _ =>
//      println("Server running on http://localhost:8080")
//    }
//
//    println("Press ENTER to stop the server.")
//    StdIn.readLine() // Blocks until user presses ENTER
//
//    println("Shutting down server...")
//    server.flatMap(_.stop())
//  }
//}
