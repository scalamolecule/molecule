//package molecule.server.h2
//
//import molecule.core.marshalling.MoleculeServerEndpoints
//import molecule.sql.mariadb.marshalling.Rpc_mariadb
//import sttp.tapir.server.netty.NettyFutureServer
//import scala.io.StdIn
//import molecule.core.util.Executor._
//
//
//object MariaDB_Netty extends MoleculeServerEndpoints(Rpc_mariadb) {
//
//  def main(args: Array[String]): Unit = {
//    val server = NettyFutureServer()
//      .port(8080)
//      .addEndpoints(moleculeServerEndpoints)
//      .start()
//
//    server.foreach { _ =>
//      println("✅ Netty server running on http://localhost:8080")
//
//      println("Press ENTER to stop the server.")
//      StdIn.readLine() // Blocks until user presses ENTER
//
//      println("🛑 Shutting down server...")
//      server.flatMap(_.stop())
//    }
//  }
//}