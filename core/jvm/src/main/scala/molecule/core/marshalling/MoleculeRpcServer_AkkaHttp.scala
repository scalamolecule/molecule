package molecule.core.marshalling

import java.nio.ByteBuffer
import akka.actor.ActorSystem
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import boopickle.Default._
import ch.megard.akka.http.cors.scaladsl.CorsDirectives._
import molecule.core.marshalling.Boopicklers._
import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.language.implicitConversions

abstract class MoleculeRpcServer_AkkaHttp(rpc: MoleculeRpc)
  extends RpcHandlers(rpc) {

  implicit val system          : ActorSystem              = ActorSystem()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  private def toRoute(handler: ByteBuffer => Future[Array[Byte]]): Route = {
    post {
      extractRequest { req =>
        req.entity match {
          case HttpEntity.Strict(_, argsSerialized) => complete(
            handler(argsSerialized.asByteBuffer)
          )
          case HttpEntity.Default(_, _, chunks)     => complete(
            chunks.reduce(_ ++ _).runFoldAsync(Array.empty[Byte]) {
              case (_, argsSerialized) => handler(argsSerialized.asByteBuffer)
            }
          )

          case other => complete("Unexpected HttpEntity in MoleculeRpcServer: " + other)
        }
      }
    }
  }

  private val prefix = "molecule"
  val moleculeRpcRoute = cors() {
    path(prefix / "query")(toRoute(handleQuery)) ~
      path(prefix / "queryOffset")(toRoute(handleQueryOffset)) ~
      path(prefix / "queryCursor")(toRoute(handleQueryCursor)) ~
      path(prefix / "save")(toRoute(handleSave)) ~
      path(prefix / "insert")(toRoute(handleInsert)) ~
      path(prefix / "update")(toRoute(handleUpdate)) ~
      path(prefix / "delete")(toRoute(handleDelete))
  }
}
