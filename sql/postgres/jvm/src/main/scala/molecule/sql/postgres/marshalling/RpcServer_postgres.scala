package molecule.sql.postgres.marshalling

import akka.http.scaladsl.Http
import molecule.core.marshalling.MoleculeRpcServer_AkkaHttp
import scala.io.StdIn

/**
 * Server app serving http requests from client
 *
 * Run with
 *
 * sbt sqlPostgresJVM/run
 */
object RpcServer_postgres extends MoleculeRpcServer_AkkaHttp(Rpc_postgres(false)) with App {

  Http()
    .newServerAt("localhost", 8080)
    .bind(moleculeRpcRoute)

  println("Started server at 127.0.0.1:8080, press enter to kill server")
  StdIn.readLine()
  system.terminate()
}
