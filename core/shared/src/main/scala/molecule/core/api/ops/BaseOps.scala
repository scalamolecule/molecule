package molecule.core.api.ops

import molecule.base.util.exceptions.MoleculeException
import scala.concurrent.{ExecutionContext, Future}

trait BaseOps {
  implicit class futEither2fut[T](fut: Future[Either[MoleculeException, T]])(implicit ec: ExecutionContext) {
    def toFuture: Future[T] = {
      fut.flatMap {
        case Left(exc)       => Future.failed(exc)
        case Right(txReport) => Future(txReport)
      }
    }
  }
}
