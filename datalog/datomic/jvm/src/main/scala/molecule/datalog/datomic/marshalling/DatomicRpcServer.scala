package molecule.datalog.datomic.marshalling

import akka.http.scaladsl.Http
import molecule.core.marshalling.MoleculeRpcServer_AkkaHttp
import scala.io.StdIn

/**
 * Server app serving http requests/ws from client
 *
 * Run with
 *
 * sbt datomicJVM/run
 */
object DatomicRpcServer extends MoleculeRpcServer_AkkaHttp(DatomicRpcJVM) with App {

  Http()
    .newServerAt("localhost", 8080)
    .bind(moleculeRpcRoute)
  //    .map(_.addToCoordinatedShutdown(hardTerminationDeadline = 120.seconds))

  println("Started server at 127.0.0.1:8080, press enter to kill server")
  StdIn.readLine()
  system.terminate()
}
