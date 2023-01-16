package molecule.db.datomic.marshalling

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.util.ByteString
import boopickle.Default._
import boopickle.PicklerHelper
import ch.megard.akka.http.cors.scaladsl.CorsDirectives._
import molecule.base.util.exceptions.MoleculeError
import molecule.boilerplate.ast.Model._
import molecule.core.api.TxReport
import molecule.core.api.action.ApiOps
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling.ConnProxy
import molecule.core.marshalling.serialize.PickleTpls
import molecule.core.util.SerializationUtils
import scala.concurrent.duration.DurationInt
import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.language.implicitConversions
import scala.util.{Failure, Success}


/** Akka Http RPC server responding to molecule ajax requests
 *
 * Start this server in terminal with
 *
 * sbt datomicJVM/run
 * */
object DatomicRpcServer
  extends PicklerHelper
    with ApiOps
    with SerializationUtils
    with App {

  implicit val system          : ActorSystem[Nothing]     = ActorSystem(Behaviors.empty, "MoleculeAjaxSystem")
  implicit val executionContext: ExecutionContextExecutor = system.executionContext
  val MoleculeRpc = "MoleculeRpc"

  Http()
    .newServerAt("localhost", 8080)
    .bind(route)
    .map(_.addToCoordinatedShutdown(hardTerminationDeadline = 120.seconds))
    .onComplete {
      case Success(b) => println(s"Akka http server is running ${b.localAddress} ")
      case Failure(e) => println(s"there was an error starting the server $e")
    }


  lazy val route: Route = cors() {
    path(MoleculeRpc / "query")(toRoute(handleQuery)) ~
      path(MoleculeRpc / "save")(toRoute(handleSave)) ~
      path(MoleculeRpc / "insert")(toRoute(handleInsert)) ~
      path(MoleculeRpc / "update")(toRoute(handleUpdate)) ~
      path(MoleculeRpc / "delete")(toRoute(handleDelete))
  }


  def toRoute(handler: ByteString => Future[Array[Byte]]): Route = {
    post {
      extractRequest { req =>
        req.entity match {
          case HttpEntity.Strict(_, argsSerialized) => complete(handler(argsSerialized))
          case HttpEntity.Default(_, _, chunks)     => complete(
            chunks.reduce(_ ++ _).runFoldAsync(Array.empty[Byte]) {
              case (_, argsSerialized) => handler(argsSerialized)
            }
          )

          case other => complete("Unexpected HttpEntity in DatomicRpcServer: " + other)
        }
      }
    }
  }

  type Dummy = Int
  val msg = "Pickling on server unexpectedly failed: "

  def left(err: MoleculeError) = {
    Future(
      Pickle.intoBytes[Either[MoleculeError, Dummy]](Left(err)).toArray
    )
  }

  def tryUnpickle(body: => Future[Array[Byte]]): Future[Array[Byte]] = try {
    body
  } catch {
    case e: MoleculeError =>
      logger.trace(e)
      left(e.copy(message = msg + e.message))
    case e: Throwable     =>
      logger.error(e.toString)
      logger.error(e.getStackTrace.mkString("\n"))
      left(MoleculeError(msg + e.toString, e))
  }

  def handleQuery(argsSerialized: ByteString): Future[Array[Byte]] = tryUnpickle {
    val (proxy, elements) = Unpickle.apply[(ConnProxy, List[Element])]
      .fromBytes(argsSerialized.asByteBuffer)
    // Data is typed when un-serialized on the client side
    DatomicRpcJVM.query[Any](proxy, elements).map(either =>
      PickleTpls(elements, either, false).pickle
    )
  }

  def handleSave(argsSerialized: ByteString): Future[Array[Byte]] = tryUnpickle {
    val (proxy, elements) = Unpickle.apply[(ConnProxy, List[Element])]
      .fromBytes(argsSerialized.asByteBuffer)
    DatomicRpcJVM.save(proxy, elements).map(either =>
      Pickle.intoBytes[Either[MoleculeError, TxReport]](either).toArray
    )
  }

  def handleInsert(argsSerialized: ByteString): Future[Array[Byte]] = tryUnpickle {
    val (proxy, tplElements, tplsSerialized, txElements) =
      Unpickle.apply[(ConnProxy, List[Element], Array[Byte], List[Element])]
        .fromBytes(argsSerialized.asByteBuffer)
    DatomicRpcJVM.insert(proxy, tplElements, tplsSerialized, txElements)
      .map(either =>
        Pickle.intoBytes[Either[MoleculeError, TxReport]](either).toArray
      )
  }

  def handleUpdate(argsSerialized: ByteString): Future[Array[Byte]] = tryUnpickle {
    val (proxy, elements, isUpsert, isMultiple) =
      Unpickle.apply[(ConnProxy, List[Element], Boolean, Boolean)]
        .fromBytes(argsSerialized.asByteBuffer)
    DatomicRpcJVM.update(proxy, elements, isUpsert, isMultiple)
      .map(either => Pickle.intoBytes[Either[MoleculeError, TxReport]](either).toArray)
  }

  def handleDelete(argsSerialized: ByteString): Future[Array[Byte]] = tryUnpickle {
    val (proxy, elements, isMultiple) =
      Unpickle.apply[(ConnProxy, List[Element], Boolean)]
        .fromBytes(argsSerialized.asByteBuffer)
    DatomicRpcJVM.delete(proxy, elements, isMultiple)
      .map(either => Pickle.intoBytes[Either[MoleculeError, TxReport]](either).toArray)
  }

  // This creates problems with `sbt -client` --> project datomicJS not loading..
  // (don't know if this variation is better, need to consult some Akka Http expert)
  //  val bindingFuture = Http().newServerAt(interface, port).bind(route)
  //  println(s"Server online at http://localhost:8088/\nPress RETURN to stop...")
  //  scala.io.StdIn.readLine()
  //  bindingFuture
  //    .flatMap(_.unbind())
  //    .onComplete(_ => system.terminate())
}
