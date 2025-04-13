package molecule.server

import cats.effect.std.Dispatcher
import cats.effect.{ExitCode, IO, IOApp}
import molecule.core.marshalling.MoleculeServerEndpoints
import molecule.sql.h2.marshalling.Rpc_h2
import org.http4s.blaze.server.BlazeServerBuilder
import sttp.monad.{FutureMonad, MonadError}
import sttp.tapir.server.ServerEndpoint
import sttp.tapir.server.http4s.Http4sServerInterpreter
import scala.concurrent.{ExecutionContext, Future}

object Http4s extends MoleculeServerEndpoints(Rpc_h2) with IOApp {

  override def run(args: List[String]): IO[ExitCode] = {

    // Create a Dispatcher for the Future to IO conversion
    Dispatcher.parallel[IO].use { implicit dispatcher =>
      implicit val ec: ExecutionContext = ExecutionContext.global

      // Convert endpoints to using IO instead of Future
      val ioEndpoints = moleculeServerEndpoints.map(convertEndpoint)

      // Create http4s routes
      val routes = Http4sServerInterpreter[IO]().toRoutes(ioEndpoints)

      // Start server
      BlazeServerBuilder[IO]
        .withExecutionContext(ec)
        .bindHttp(8080, "0.0.0.0")
        .withHttpApp(routes.orNotFound)
        .serve
        .compile
        .drain
        .background // Runs server in background
        .use { _ =>
          for {
            _ <- IO.println("✅ Http4s server running on http://localhost:8080")
            _ <- IO.readLine
            _ <- IO.println("🛑 Shutting down server...")
          } yield ExitCode.Success

        }
    }
  }

  // Some helper functions to use Future endpoints with Http4s

  private val futureMonadError: MonadError[Future] = new FutureMonad()(ExecutionContext.global)

  private def convertEndpoint(endpoint: ServerEndpoint[Any, Future]): ServerEndpoint[Any, IO] = {
    ServerEndpoint[
      endpoint.SECURITY_INPUT,
      endpoint.PRINCIPAL,
      endpoint.INPUT,
      endpoint.ERROR_OUTPUT,
      endpoint.OUTPUT,
      Any,
      IO
    ](
      endpoint.endpoint,
      _ => secInput => IO.fromFuture(IO(endpoint.securityLogic(futureMonadError)(secInput))),
      _ => principal => input => IO.fromFuture(IO(endpoint.logic(futureMonadError)(principal)(input)))
    )
  }
}