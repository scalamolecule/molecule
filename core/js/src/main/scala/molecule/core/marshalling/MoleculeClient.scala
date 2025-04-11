package molecule.core.marshalling

import java.nio.ByteBuffer
import boopickle.Default._
import molecule.base.error._
import molecule.core.util.Executor._
import sttp.client4.fetch.FetchBackend
import sttp.model.Uri
import sttp.tapir.PublicEndpoint
import sttp.tapir.client.sttp4.SttpClientInterpreter
import scala.concurrent.Future


class MoleculeClient(backendBaseUri: Uri) extends MoleculeRpcRequest("localhost", 8080) {

  def fetch[T](
    endpoint: PublicEndpoint[ByteBuffer, MoleculeError, ByteBuffer, Any],
    args: ByteBuffer,
    unpickle: ByteBuffer => T
  ): Future[Either[MoleculeError, T]] = {
    SttpClientInterpreter()
      .toRequestThrowDecodeFailures(endpoint, Some(backendBaseUri))
      .apply(args)
      .send(FetchBackend())
      .map { response =>
        response.body match {
          case Right(result) => Right(unpickle(result))
          case Left(error)   => Left(error)
        }
      }
      .recover {
        case unexpected => Left(ExecutionError(unexpected.getMessage))
      }
  }
}

