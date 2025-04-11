//package molecule.sql.h2.marshalling
//
//import akka.http.scaladsl.Http
//import molecule.core.marshalling.MoleculeRpcServer_AkkaHttp
//import scala.io.StdIn
//
///**
// * Server app serving http requests from client
// *
// * Run with
// *
// * sbt sqlH2JVM/run
// */
//object RpcServer_h2 extends MoleculeRpcServer_AkkaHttp(Rpc_h2) with App {
//
//  Http()
//    .newServerAt("localhost", 8080)
//    .bind(moleculeRpcRoute)
//
//  println("Started server at 127.0.0.1:8080, press enter to kill server")
//  StdIn.readLine()
//  system.terminate()
//}