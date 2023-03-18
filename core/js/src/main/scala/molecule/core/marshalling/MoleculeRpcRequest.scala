package molecule.core.marshalling

import java.nio.ByteBuffer
import java.util.UUID
import boopickle.Default._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.util.Executor._
import org.scalajs.dom
import org.scalajs.dom.{MessageEvent, WebSocket, XMLHttpRequest}
import scala.concurrent.{Future, Promise}
import scala.scalajs.js.typedarray._

class MoleculeRpcRequest(interface: String, port: Int) extends MoleculeLogging {

  case class PostException(xhr: dom.XMLHttpRequest) extends Exception {
    def isTimeout: Boolean = xhr.status == 0 && xhr.readyState == 4
  }

  def xmlHttpRequest(action: String, argsSerialized: Int8Array): Future[ByteBuffer] = {
    val url     = s"http://$interface:$port/molecule/$action"
    val req     = new dom.XMLHttpRequest()
    val promise = Promise[dom.XMLHttpRequest]()
    req.onreadystatechange = { (_: dom.Event) =>
      if (req.readyState == XMLHttpRequest.DONE) {
        if ((req.status >= 200 && req.status < 300) || req.status == 304) {
          promise.success(req)
        } else {
          promise.failure(PostException(req))
        }
      }
    }
    req.open("POST", url)
    req.responseType = "arraybuffer"
    req.timeout = 0
    req.withCredentials = false
    req.setRequestHeader("Content-Type", "application/octet-stream")
    if (argsSerialized == null) req.send() else req.send(argsSerialized)
    promise.future
      .recover {
        case PostException(xhr) =>
          val msg = xhr.status match {
            case 0 => s"Ajax call failed: server not responding"
            case n => s"Ajax call failed: XMLHttpRequest.status = $n"
          }
          logger.error(msg)
          xhr
      }
      .flatMap { req =>
        val raw = req.response.asInstanceOf[ArrayBuffer]
        Future(TypedArrayBuffer.wrap(raw))
      }
  }


  def websocketSubscription(
    argsSerialized: Int8Array,
    callbackDeserialize: ByteBuffer => Unit
  ): Unit = {
    // One websocket connection per subscription
    val uuid   = UUID.randomUUID()
    val uri    = s"ws://$interface:$port/molecule/ws/$uuid"
    val socket = new WebSocket(uri)
    socket.binaryType = "arraybuffer"
    socket.onerror = {
      case e: dom.Event =>
        logger.error(s"WebSocket error: $e!")
        //        keys(e).toList.foreach(k => println(s"$k -> ${apply(k)}"))
        socket.close(1000, e.toString)
    }
    socket.onclose = {
      case _: dom.CloseEvent => logger.warn("WebSocket onclose")
    }
    socket.onopen = {
      case _: dom.Event =>
        logger.trace(s"WebSocket onopen")
        //        println("WebSocket onopen...")
        socket.send(argsSerialized.buffer)
    }

    socket.onmessage = {
      case e: MessageEvent =>
        logger.trace(s"WebSocket onmessage")
        //        println("WebSocket onmessage...")
        val resultSerialized = TypedArrayBuffer.wrap(e.data.asInstanceOf[ArrayBuffer])
        callbackDeserialize(resultSerialized)
    }
  }
}
