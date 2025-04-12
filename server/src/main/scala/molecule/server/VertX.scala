package molecule.server

import boopickle.Default._
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import molecule.core.marshalling.MoleculeServerEndpoints
import molecule.core.util.Executor._
import molecule.sql.h2.marshalling.Rpc_h2
import sttp.tapir.server.vertx.VertxFutureServerInterpreter
import scala.compat.java8.FutureConverters._
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.io.StdIn  // Required for CompletionStage conversion

object VertX extends MoleculeServerEndpoints(Rpc_h2) {

  def main(args: Array[String]): Unit = {

    // 1. Create Vertx instance and router
    val vertx = Vertx.vertx()
    val router = Router.router(vertx)

    // 2. Convert endpoints to Vert.x routes
    moleculeServerEndpoints.foreach { endpoint =>
      VertxFutureServerInterpreter().route(endpoint)(router)
    }

    // 3. Start server and convert to Scala Future
    val serverFuture = vertx.createHttpServer()
      .requestHandler(router)
      .listen(8080)
      .toCompletionStage
      .toScala

    // 4. Block until server starts
    Await.result(serverFuture, 10.seconds)
    println("✅ VertX server running on http://localhost:8080")


    // 5. Shutdown hook
    println("Press ENTER to stop the server.")
    StdIn.readLine()

    println("🛑 Shutting down server...")
    val shutdownFuture = serverFuture.flatMap { httpServer =>
      httpServer.close().toCompletionStage.toScala
    }
    Await.result(shutdownFuture, 10.seconds)
    vertx.close()
  }
}