//package molecule.adhoc
//
//import cats.effect._
//import molecule.sql.h2.marshalling.Rpc_h2
//import org.http4s.blaze.server.BlazeServerBuilder
//import org.http4s.implicits._
//
//object Http4sServer extends MoleculeRpcServer_Http4s(Rpc_h2) with IOApp {
//
//  override def run(args: List[String]): IO[ExitCode] = {
//    BlazeServerBuilder[IO]
//      //    EmberServerBuilder[IO]
//      .bindHttp(8080, "0.0.0.0")
//      // Bind to all network interfaces
//      .withHttpApp(apiRouter.orNotFound) // Mount routes
//      .resource
//      .use(_ => IO.never) // Keep running indefinitely
//      .as(ExitCode.Success)
//  }
//
////  override def run(args: List[String]): IO[ExitCode] = {
////    val port = sys.env
////      .get("HTTP_PORT")
////      .flatMap(_.toIntOption)
////      .flatMap(Port.fromInt)
////      .getOrElse(port"8080")
////
////    EmberServerBuilder
////      .default[IO]
////      .withHost(Host.fromString("localhost").get) // Bind to all network interfaces
////      .withPort(port) // Bind to all network interfaces
////      .withHttpApp(apiRouter.orNotFound) // Mount routes
////      .build
////      .use(_ => IO.never) // Keep running indefinitely
////      .as(ExitCode.Success)
////  }
//}