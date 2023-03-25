package molecule.core.util

import molecule.base.error.{ExecutionError, InsertValidationErrors, MoleculeError, ValidationErrors}
import molecule.boilerplate.util.MoleculeLogging
import scala.concurrent.{ExecutionContext, Future}

trait FutureUtils extends ModelUtils with MoleculeLogging {

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
        case e: ValidationErrors       =>
          logger.trace(e.toString)
          Left(e)
        case e: InsertValidationErrors =>
          logger.trace(e.toString)
          Left(e)
        case e: ExecutionError         =>
          logger.trace(e.toString)
          Left(e)
        case e: Throwable              =>
          // Unexpected error that should be treated like a bug to be fixed
          logger.error(e.toString + "\n" + e.getStackTrace.toList.mkString("\n"))
          Left(ExecutionError(e.getMessage, e))
      }
  }

  def future[T](body: => T)(implicit ec: ExecutionContext): Future[T] = {
    Future(body).recover {
      case e: ValidationErrors       => throw e
      case e: InsertValidationErrors => throw e
      case e: ExecutionError         => throw e
      case e: Throwable              => throw ExecutionError(e.toString, e)
    }
  }

  def tryFuture[T](toFuture: => Future[T])(implicit ec: ExecutionContext): Future[T] = try {
    toFuture
  } catch {
    case e: ValidationErrors       => Future.failed(e)
    case e: InsertValidationErrors => Future.failed(e)
    case e: ExecutionError         => Future.failed(e)
    case e: Throwable              => Future.failed(ExecutionError(e.toString, e))
  }
}
