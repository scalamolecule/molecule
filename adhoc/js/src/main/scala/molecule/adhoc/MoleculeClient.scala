package molecule.adhoc


import java.nio.ByteBuffer
import boopickle.Default._
import molecule.base.error._
import sttp.client4.fetch.FetchBackend
import sttp.model.Uri
import sttp.tapir.PublicEndpoint
import sttp.tapir.client.sttp4.SttpClientInterpreter
import scala.concurrent.Future
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

class MoleculeClient(backendBaseUri: Uri) {

  def fetch[T: Pickler](
    endpoint: PublicEndpoint[ByteBuffer, MoleculeError, ByteBuffer, Any],
    args: ByteBuffer,
  ): Future[Either[MoleculeError, T]] = {
    SttpClientInterpreter()
      .toRequestThrowDecodeFailures(endpoint, Some(backendBaseUri))
      .apply(args)
      .send(FetchBackend())
      .map { response =>
        response.body match {
          case Right(resultByteBuffer) => Right(Unpickle[T].fromBytes(resultByteBuffer))
          case Left(error)             => Left(error)
        }
      }
      .recover {
        case unexpected => Left(ExecutionError(unexpected.getMessage))
      }
  }
}

