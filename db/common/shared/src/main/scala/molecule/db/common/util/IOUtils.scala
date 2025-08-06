package molecule.db.common.util

import scala.annotation.targetName
import scala.concurrent.duration.{Duration, DurationInt}
import scala.concurrent.{Await, ExecutionContext, Future}
import cats.effect.IO
import molecule.core.error.{ExecutionError, MoleculeError}
import molecule.core.util.MoleculeLogging
import molecule.db.common.spi.TxReport

trait IOUtils extends ModelUtils with MoleculeLogging {

  extension [T](fut: Future[Either[MoleculeError, T]])(using ec: ExecutionContext) {
    @targetName("eitherToIO")
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

  extension (fut: Future[TxReport])(using ec: ExecutionContext) {
    @targetName("txReportToIO")
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

  extension (fut: Future[Unit])(using ec: ExecutionContext) {
    @targetName("unitToIO")
    def io: IO[Unit] = {
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

  def either[T](fut: Future[T])(using ec: ExecutionContext): IO[Either[MoleculeError, T]] = {
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
