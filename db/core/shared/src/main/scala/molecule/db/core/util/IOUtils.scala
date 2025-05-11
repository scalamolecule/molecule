package molecule.db.core.util

import cats.effect.IO
import molecule.db.base.error.*
import molecule.db.core.spi.TxReport
import scala.concurrent.duration.{Duration, DurationInt}
import scala.concurrent.{Await, ExecutionContext, Future}

trait IOUtils extends ModelUtils with MoleculeLogging {

  implicit class futEither2io[T](fut: Future[Either[MoleculeError, T]])(implicit ec: ExecutionContext) {
    def io: IO[T] = {
      IO.fromFuture {
        IO.blocking {
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
    }
  }

  implicit class futTxReport2io(fut: Future[TxReport])(implicit ec: ExecutionContext) {
    def io: IO[TxReport] = {
      IO.fromFuture {
        IO.blocking {
          fut
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
    }
  }

  implicit class futListUnit2io(fut: Future[List[Unit]])(implicit ec: ExecutionContext) {
    def io: IO[List[Unit]] = {
      IO.fromFuture {
        IO.blocking {
          fut
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
    }
  }

  def either[T](fut: Future[T])(implicit ec: ExecutionContext): IO[Either[MoleculeError, T]] = {
    IO.fromFuture {
      IO.blocking {
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
    }
  }

  def io[T](body: => T): IO[T] = {
    IO(body).recover {
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
