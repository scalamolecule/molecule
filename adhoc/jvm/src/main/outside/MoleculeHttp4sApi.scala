//package molecule.adhoc
//
//import cats.effect._
//import molecule.adhoc.domains.MoleculeTapirApi
//import molecule.core.marshalling.MoleculeRpc
//import org.http4s.HttpRoutes
//import org.http4s.dsl.Http4sDsl
//import org.http4s.server.Router
//import sttp.tapir.server.http4s.Http4sServerInterpreter
//
//class MoleculeHttp4sApi[F[_]: Async](rpc: MoleculeRpc) extends Http4sDsl[F] {
//
//  private val queryRoute: HttpRoutes[F] =
//    Http4sServerInterpreter[F]().toRoutes(
//      MoleculeTapirApi.queryEndpoint.serverLogic { case (proxy, elements, limit) =>
//        rpc.query[Any](proxy, elements, limit).map { result =>
//          Right(result)
//        }
//      }
//    )
//
//  val routes: HttpRoutes[F] = Router("/" -> queryRoute)
//}