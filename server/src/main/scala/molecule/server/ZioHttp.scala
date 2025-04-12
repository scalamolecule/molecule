package molecule.server

import boopickle.Default._
import molecule.core.marshalling.MoleculeServerEndpoints
import molecule.sql.h2.marshalling.Rpc_h2
import sttp.monad.FutureMonad
import sttp.tapir.server.ServerEndpoint
import sttp.tapir.server.ziohttp.ZioHttpInterpreter
import zio._
import zio.http._
import scala.concurrent.{ExecutionContext, Future}

object ZioHttp extends MoleculeServerEndpoints(Rpc_h2) with ZIOAppDefault {

  // Convert all endpoints
  private val zioEndpoints: List[ServerEndpoint[Any, Task]] =
    moleculeServerEndpoints.map(convertEndpoint[Any])

  // Create ZIO HTTP app
  private val app = ZioHttpInterpreter().toHttp(zioEndpoints)

  // ZIO application entry point
  override def run: ZIO[Environment with ZIOAppArgs with Scope, Any, Any] = {
    for {
      fiber <- Server.serve(app)
        .provide(Server.defaultWithPort(8080))
        .fork // Run server in background
      _ <- Console.printLine("✅ ZioHttp server running on http://localhost:8080")
      _ <- Console.printLine("Press ENTER to stop the server...")
      _ <- Console.readLine
      _ <- Console.printLine("🛑 Shutting down server...")
      _ <- fiber.interrupt // Gracefully shutdown the server
    } yield ExitCode.success
  }


  // Convert Future server endpoints to Task server endpoints

  private val ec               = ExecutionContext.global
  private val futureMonadError = new FutureMonad()(ec)

  private def convertEndpoint[R](endpoint: ServerEndpoint[Any, Future]): ServerEndpoint[R, Task] = {
    ServerEndpoint[
      endpoint.SECURITY_INPUT,
      endpoint.PRINCIPAL,
      endpoint.INPUT,
      endpoint.ERROR_OUTPUT,
      endpoint.OUTPUT,
      R,
      Task
    ](
      endpoint.endpoint,
      _ => secInput => ZIO.fromFuture(_ => endpoint.securityLogic(futureMonadError)(secInput)),
      _ => principal => input => ZIO.fromFuture(_ => endpoint.logic(futureMonadError)(principal)(input))
    )
  }
}