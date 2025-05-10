package molecule.server.play

import boopickle.Default.*
import molecule.core.marshalling.MoleculeRpc
import org.apache.pekko.actor.{ActorSystem, Terminated}
import org.apache.pekko.http.scaladsl.model.ws.Message
import org.apache.pekko.http.scaladsl.server.Directives.handleWebSocketMessages
import org.apache.pekko.http.scaladsl.server.Route
import org.apache.pekko.stream.Materializer
import org.apache.pekko.stream.scaladsl.Flow
import play.api.Mode
import play.api.http.{DefaultFileMimeTypes, FileMimeTypesConfiguration}
import play.api.mvc.{ControllerComponents, DefaultActionBuilder, DefaultControllerComponents, Handler, PlayBodyParsers, WebSocket}
import play.api.routing.Router
import play.core.server.*
import sttp.capabilities
import sttp.tapir.*
import sttp.tapir.server.ServerEndpoint
import sttp.tapir.server.ServerEndpoint.Full
import sttp.tapir.server.play.PlayServerInterpreter
import scala.concurrent.{ExecutionContext, Future}
import scala.io.StdIn
import sttp.capabilities
import play.api.routing.*
import play.api.routing.Router.Routes
import play.api.routing.sird.*
import play.api.routing.sird.*
import sttp.capabilities.WebSockets
import sttp.capabilities.pekko.PekkoStreams


case class Play(rpc: MoleculeRpc) extends PlayServerEndpoints(rpc) {

  implicit val actorSystem: ActorSystem      = ActorSystem("tapir-play-server")
  implicit val ec         : ExecutionContext = actorSystem.dispatcher
  implicit val mat        : Materializer     = Materializer(actorSystem)

  val moleculeServerEndpoint_subscribe: ServerEndpoint[PekkoStreams with WebSockets, Future] =
    endpoint
      .in("molecule" / "subscribe")
      .out(
        webSocketBody[Array[Byte], CodecFormat.OctetStream, Array[Byte], CodecFormat.OctetStream](PekkoStreams)
      )
      .serverLogicSuccess(_ => Future.successful(moleculeWebsocketHandler_ByteFlow))

  def run(db: String): Future[Terminated] = {
    val config = ServerConfig(port = Some(8080), mode = Mode.Dev)
    val routes = PlayServerInterpreter().toRoutes(moleculeServerEndpoints_Future :+ moleculeServerEndpoint_subscribe)
    val server = PekkoHttpServer.fromRouterWithComponents(config)(_ => routes)

    println(s"\n✅ Play server running on http://localhost:8080 for $db")
    println("   Press ENTER to stop the server.")
    StdIn.readLine()
    println("🛑 Shutting down server...")
    server.stop()
    actorSystem.terminate()
  }
}