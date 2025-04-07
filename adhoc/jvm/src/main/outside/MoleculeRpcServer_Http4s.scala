//package molecule.adhoc
//
//import java.nio.ByteBuffer
//import boopickle.Default._
//import cats.effect.IO
//import molecule.adhoc.domains.MoleculeTapirApi
//import molecule.base.error.MoleculeError
//import molecule.core.ast.DataModel.Element
//import molecule.core.marshalling.Boopicklers._
//import molecule.core.marshalling.serialize.PickleTpls
//import molecule.core.marshalling.{ConnProxy, MoleculeRpc, RpcHandlers}
//import molecule.core.util.Executor._
//import org.http4s.HttpRoutes
//import org.http4s.server.Router
//import sttp.tapir.server._
//import sttp.tapir.server.http4s._
//import scala.concurrent.Future
//import scala.language.implicitConversions
//import scala.util._
//
//abstract class MoleculeRpcServer_Http4s(rpc: MoleculeRpc)
//  extends RpcHandlers(rpc) {
//
//  type ErrorInfo = String
//  def logic(s: String): Future[Int] = ???
//
//  def handleErrors[T](f: Future[T]): Future[Either[ErrorInfo, T]] =
//    f.transform {
//      case Success(v) => Success(Right(v))
//      case Failure(e) =>
//        println(s"Exception when running endpoint logic: $e")
//        Success(Left(e.getMessage))
//    }
//
//  // Define the Tapir endpoint logic
//  private def queryHandler: ServerEndpoint[Any, IO] = {
//    //    MoleculeTapirApi.queryEndpoint
//    //      .serverLogic[IO] { argsSerialized =>
//    //        val (proxy, elements, limit) =
//    //          Unpickle[(ConnProxy, List[Element], Option[Int])]
//    //            .fromBytes(ByteBuffer.wrap(argsSerialized))
//    ////            .fromBytes(argsSerialized)
//    //
//    //        IO.fromFuture(
//    //          IO {
//    //            rpc.query[Any](proxy, elements, limit).map { result =>
//    ////              PickleTpls(elements, false).pickleEither2(result)
//    //              PickleTpls(elements, false).pickleEither(result)
//    //            }
//    //          }
//    //        )
//    //      }
//
//    ???
//  }
//
//  private def queryHandler2: ServerEndpoint[Any, IO] = {
//    //    MoleculeTapirApi
//    //      .queryEndpoint2
//    //      .serverLogic[IO] { argsSerialized =>
//    //        val (proxy, elements, limit) =
//    //          Unpickle[(ConnProxy, List[Element], Option[Int])]
//    ////            .fromBytes(ByteBuffer.wrap(argsSerialized))
//    //            .fromBytes(argsSerialized)
//    //
//    //        IO.fromFuture(
//    //          IO {
//    ////            val y: Future[PickleTpls] =
//    //////              .map { result =>
//    ////////              PickleTpls(elements, false).pickleEither2(result)
//    ////////              val x: Array[Byte] = PickleTpls(elements, false) //.pickleEither(result)
//    //////              val x: PickleTpls = PickleTpls(elements, false) //.pickleEither(result)
//    //////
//    //////              x
//    //////            }
//    ////            y
//    //              rpc.query[Any](proxy, elements, limit)
//    //          }
//    //        )
//    ////          .flatMap{z =>
//    ////          IO.pure(Right(s"Hello, $name!"))
//    ////        }
//    //      }
//
//    ???
//  }
//
//  // Convert Tapir endpoints into Http4s routes
//  def routes: HttpRoutes[IO] =
//    Http4sServerInterpreter[IO]().toRoutes(List(queryHandler))
//
//  // Combine routes into a Router for easier handling
//  def apiRouter: HttpRoutes[IO] =
//    Router("/molecule" -> routes)
//}
