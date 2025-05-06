package molecule.server

import boopickle.Default.*
import io.vertx.core
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import molecule.core.marshalling.{MoleculeRpc, MoleculeServerEndpoints}
import molecule.core.util.Executor.*
import sttp.tapir.server.vertx.VertxFutureServerInterpreter
import scala.compat.java8.FutureConverters.*
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.*
import scala.io.StdIn

case class VertX(rpc: MoleculeRpc) extends MoleculeServerEndpoints(rpc) {

  def run(db: String): core.Future[Void] = {

    // 1. Create Vertx instance and router
    val vertx = Vertx.vertx()
    val router = Router.router(vertx)

    // 2. Convert endpoints to Vert.x routes
    moleculeServerEndpoints_Future.foreach { endpoint =>
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
    println(s"\n✅ VertX server running on http://localhost:8080 for $db")

    // 5. Shutdown hook
    println("   Press ENTER to stop the server.")
    StdIn.readLine()

    println("🛑 Shutting down server...")
    val shutdownFuture = serverFuture.flatMap { httpServer =>
      httpServer.close().toCompletionStage.toScala
    }
    Await.result(shutdownFuture, 10.seconds)
    vertx.close()
  }
}