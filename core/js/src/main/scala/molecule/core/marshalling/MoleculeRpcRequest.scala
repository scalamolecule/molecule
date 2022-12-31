package molecule.core.marshalling

import java.nio.ByteBuffer
import boopickle.Default._
import molecule.core.util.Executor._
import org.scalajs.dom
import org.scalajs.dom.XMLHttpRequest
import sloth._
import scala.concurrent.{Future, Promise}
import scala.scalajs.js.typedarray.TypedArrayBufferOps._
import scala.scalajs.js.typedarray._

case class MoleculeRpcRequest(interface: String, port: Int)
  extends RequestTransport[ByteBuffer, Future] with BooPicklers {

  case class PostException(xhr: dom.XMLHttpRequest) extends Exception {
    def isTimeout: Boolean = xhr.status == 0 && xhr.readyState == 4
  }

  // Sloth RequestTransport implementation

  override def apply(slothReq: Request[ByteBuffer]): Future[ByteBuffer] = {
    val url         = s"http://$interface:$port/ajax/" + slothReq.path.mkString("/")
    val byteBuffer  = Pickle.intoBytes(slothReq.payload)
    val requestData = byteBuffer.typedArray().subarray(byteBuffer.position, byteBuffer.limit)
    val req         = new dom.XMLHttpRequest()
    val promise     = Promise[dom.XMLHttpRequest]()

    req.onreadystatechange = { (e: dom.Event) =>
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
    if (requestData == null) {
      req.send()
    } else {
      req.send(requestData)
    }

    promise.future.recover {
      case PostException(xhr) =>
        val advice = "\nNew api methods might not be visible as js-code upon a refresh " +
          "since the sloth macro needs to generate them first. " +
          "\nCached js-code can also become out of date. " +
          "\nSo you can try to stop the server from the terminal (ctrl-c twice), " +
          "run `sbt`, `clean` and then `run`. " +
          "\nThis should allow new signatures to propagate to js-code."
        val msg    = xhr.status match {
          case 0 => s"Ajax call failed: server not responding. $advice"
          case n => s"Ajax call failed: XMLHttpRequest.status = $n. $advice"
        }
        println(msg)
        xhr
    }.flatMap { req =>
      val raw = req.response.asInstanceOf[ArrayBuffer]
      Future(TypedArrayBuffer.wrap(raw))
    }
  }
}
