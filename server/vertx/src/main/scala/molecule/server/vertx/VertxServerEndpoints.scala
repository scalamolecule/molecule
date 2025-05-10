package molecule.server.vertx

import java.nio.ByteBuffer
import boopickle.Default.*
import io.vertx.core.buffer.Buffer
import io.vertx.core.http.ServerWebSocket
import io.vertx.ext.web.Router
import molecule.base.error.ModelError
import molecule.core.ast.DataModel.Element
import molecule.core.marshalling.Boopicklers.*
import molecule.core.marshalling.serialize.PickleTpls
import molecule.core.marshalling.{ConnProxy, MoleculeRpc}
import molecule.server.core.ServerEndpoints_async
import scala.util.Try

abstract class VertxServerEndpoints(rpc: MoleculeRpc) extends ServerEndpoints_async(rpc) {


  def addWebSocketRoute(router: Router): Unit = {
    router.route("/molecule/subscribe").handler { ctx =>
      if (!ctx.request().isWebSocket) {
        ctx.response().setStatusCode(400).end("Expected WebSocket connection")
        return
      }

      val wsOpt: Option[ServerWebSocket] = Try(ctx.request().upgrade()).toOption

      wsOpt.foreach { ws =>
        ws.binaryMessageHandler { (buffer: Buffer) =>
          val inBytes = buffer.getBytes
          try {
            val (proxy, elements, limit) =
              Unpickle[(ConnProxy, List[Element], Option[Int])].fromBytes(ByteBuffer.wrap(inBytes))

            val callback: List[Any] => Unit = { result =>
              val outBytes = PickleTpls(elements, false).pickleEither2ByteArray(Right(result))
              ws.writeBinaryMessage(Buffer.buffer(outBytes))
            }

            rpc.subscribe[Any](proxy, elements, limit, callback)

          } catch {
            case ex: Throwable =>
              val errorBytes = Pickle
                .intoBytes[Either[ModelError, Int]](Left(ModelError(s"Deserialization error: ${ex.getMessage}")))
                .array()
              ws.writeBinaryMessage(Buffer.buffer(errorBytes))
          }
        }

        ws.closeHandler { _ =>
          println("WebSocket connection closed.")
        }
      }
    }
  }
}