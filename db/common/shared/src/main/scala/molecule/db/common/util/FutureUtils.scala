package molecule.db.common.util

import molecule.base.error.{ExecutionError, MoleculeError}
import molecule.core.util.MoleculeLogging
import scala.concurrent.duration.{Duration, DurationInt}
import scala.concurrent.{Await, ExecutionContext, Future}

trait FutureUtils extends ModelUtils with MoleculeLogging {

  implicit class futEither2fut[T](fut: Future[Either[MoleculeError, T]])(implicit ec: ExecutionContext) {
    def future: Future[T] = {
      fut
        .map {
          case Right(result)       => result
          case Left(moleculeError) => throw moleculeError
        }
        .recover {
          case e: MoleculeError =>
            logger.debug(e)
            throw e
          case e: Throwable     =>
            logger.error(e.toString + "\n" + e.getStackTrace.toList.mkString("\n"))
            // Re-throw to preserve original stacktrace
            throw e
        }
    }
  }

  def either[T](fut: Future[T])(implicit ec: ExecutionContext): Future[Either[MoleculeError, T]] = {
    fut
      .map(txR => Right(txR))
      .recover {
        case e: MoleculeError =>
          logger.debug(e)
          Left(e)
        case e: Throwable     =>
          // Unexpected error that should be treated like a bug to be fixed
          logger.error(e.toString + "\n" + e.getStackTrace.toList.mkString("\n"))
          Left(ExecutionError(e.getMessage))
      }
  }

  def future[T](body: => T)(implicit ec: ExecutionContext): Future[T] = {
    Future(body).recover {
      case e: MoleculeError =>
        logger.debug(e)
        throw e
      case e: Throwable     =>
        logger.debug(e.toString + "\n" + e.getStackTrace.toList.mkString("\n"))
        throw e
    }
  }

  def await[T](body: => Future[T], atMost: Duration = 10.seconds): T = try {
    Await.result(body, atMost)
  } catch {
    case e: Throwable => throw e
  }
}
