package molecule.core.api.action

import molecule.base.util.exceptions.MoleculeError
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.util.ModelUtils
import scala.concurrent.{ExecutionContext, Future}

trait ApiOps extends ModelUtils with MoleculeLogging {

  implicit class futEither2fut[T](fut: Future[Either[MoleculeError, T]])(implicit ec: ExecutionContext) {
    def future: Future[T] = {
      fut
        .map {
          case Right(result)       => result
          case Left(moleculeError) => throw moleculeError
        }
        .recover {
          case e: Throwable => throw e
        }
    }
  }

  def either[T](fut: Future[T])(implicit ec: ExecutionContext): Future[Either[MoleculeError, T]] = {
    fut
      .map(txR => Right(txR))
      .recover {
        // Always MoleculeError
        case e: MoleculeError =>
          logger.trace(e.toString)
          Left(e)
        case e: Throwable     =>
          // Unexpected error that should be treated like a bug to be fixed
          logger.error(e.toString + "\n" + e.getStackTrace.toList.mkString("\n"))
          Left(MoleculeError(e.getMessage, e))
      }
  }

  def future[T](body: => T)(implicit ec: ExecutionContext): Future[T] = {
    Future(body).recover {
      case e: MoleculeError => throw e
      case e: Throwable     => throw MoleculeError(e.toString, e)
    }
  }

  def tryFuture[T](toFuture: => Future[T])(implicit ec: ExecutionContext): Future[T] = try {
    toFuture
  } catch {
    case e: MoleculeError => Future.failed(e)
    case e: Throwable     => Future.failed(MoleculeError(e.toString, e))
  }
}
