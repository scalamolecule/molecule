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

  implicit class either2fut[T](fut: Either[MoleculeError, T])(implicit ec: ExecutionContext) {
    def future: Future[T] = {
      fut match {
        case Right(result) => Future(result)
        case Left(exc)     => Future.failed(exc)
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

//  def guard[T](fut: Future[T])(implicit ec: ExecutionContext): Future[T] = {
//    fut.recover {
//      case e: MoleculeError => throw e
//      case e: Throwable     => throw MoleculeError(e.toString, e)
//    }
//  }

  def tryFuture[T](toFuture: => Future[T])(implicit ec: ExecutionContext): Future[T] = try {
    toFuture
  } catch {
    case e: MoleculeError => Future.failed(e)
    case e: Throwable     => Future.failed(MoleculeError(e.toString, e))
  }

//  def guard2[T](fut: Future[Either[MoleculeError, T]])
//               (implicit ec: ExecutionContext): Future[Either[MoleculeError, T]] = {
//    // Catch callees that haven't guarded against thrown exceptions
//    fut.recover {
//      case e: Throwable =>
//        logger.error(
//          "Unguarded exception thrown: " + e +
//            e.getStackTrace.toList.mkString("\n", "\n", "")
//        )
//        throw e
//    }
//  }

  //  implicit class fut2futEither[T](fut: Future[T])(implicit ec: ExecutionContext) {
  //    def either: Future[Either[MoleculeError, T]] = {
  //      fut
  //        .map(txR => Right(txR))
  //        .recover {
  //          case e: MoleculeError => Left(e)
  //          case e: Throwable     => Left(MoleculeError(e.getMessage, e))
  //        }
  //    }
  //  }
}
