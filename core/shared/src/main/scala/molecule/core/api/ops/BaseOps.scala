package molecule.core.api.ops

import molecule.base.util.exceptions.MoleculeException
import molecule.core.util.ModelUtils
import scala.concurrent.{ExecutionContext, Future}

trait BaseOps extends ModelUtils {

  implicit class futEither2fut[T](fut: Future[Either[MoleculeException, T]])(implicit ec: ExecutionContext) {
    def future: Future[T] = {
      fut.map {
        case Right(result) => result
        case Left(exc)     => throw exc
      }
    }
  }

  protected def printStackTrace(exc: Throwable): Unit = {
    println(exc.getStackTrace.mkString("\n"))
  }
}
