package molecule.adhoc


import java.nio.ByteBuffer
import boopickle.Default._
import molecule.base.error._
import sttp.client4._
import sttp.client4.fetch.FetchBackend
import sttp.tapir.client.sttp4.SttpClientInterpreter
import sttp.tapir.{DecodeResult, PublicEndpoint}
import scala.concurrent.Future
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue


case class MoleculeClient(host: String, port: Int) {

  def callServer[T: Pickler](
    endpoint: PublicEndpoint[ByteBuffer, MoleculeError, ByteBuffer, Any],
    args: ByteBuffer
  ): Future[Unit] = {
    SttpClientInterpreter()
      .toRequestThrowDecodeFailures(endpoint, Some(uri"http://$host:$port"))
      .apply(args)
      .send(FetchBackend())
      .map { response =>
        response.body match {
          case Right(resultByteBuffer) =>
            val result = Unpickle[T].fromBytes(resultByteBuffer)
            println(s"✅ Received result: $result")

          case Left(moleculeError) => moleculeError match {
            case ModelError(msg)            => println(s"⚠️  Model error: " + msg)
            case ValidationErrors(errorMap) => println(s"⚠️  Validation errors: " + errorMap)
            case e: InsertErrors            => println(s"⚠️  Insert errors:\n" + e)
            case ExecutionError(msg)        => println(s"⚠️  Execution error: " + msg)
          }
        }
      }
      .recover {
        case x => println("❌ Recovered error: " + x)
      }
  }

  // No need for DecodeResult with closed RPC api I guess...
  def callServer2(
    endpoint: PublicEndpoint[ByteBuffer, MoleculeError, ByteBuffer, Any],
    args: ByteBuffer
  ): Future[Unit] = {
    SttpClientInterpreter()
      .toRequest(endpoint, Some(uri"http://$host:$port"))
      .apply(args)
      .send(FetchBackend())
      .map { response =>
        response.body match {
          case DecodeResult.Value(Right(responseBytes)) =>
            val decoded = Unpickle[String].fromBytes(responseBytes)
            println(s"✅ Received response: $decoded")

          case DecodeResult.Multiple(vs) =>
            println(s"✅ Multiple values: $vs")

          case DecodeResult.Value(Left(_)) =>
            println("⚠️ Server returned an error (unit output)")

          case DecodeResult.InvalidValue(errors) =>
            println(s"❌ Invalid value: $errors")

          case DecodeResult.Missing =>
            println(s"❌ Missing decoder")

          case DecodeResult.Mismatch(_, error) =>
            println(s"❌ Decode mismatch: $error")

          case DecodeResult.Error(msg, error) =>
            println(s"❌ Decode error: $msg - ${error.getMessage}")
        }
      }
  }
}

