package molecule.datomic.api

import molecule.base.error.ExecutionError
import molecule.boilerplate.ast.Model._
import molecule.core.api.{Connection, PrintInspect}
import molecule.datomic.query.DatomicModel2Query
import zio.{Task, ZIO}

trait DatomicZioApiBase extends PrintInspect {

  protected def moleculeError[T](result: Task[T]): ZIO[Connection, ExecutionError, T] = {
    result.mapError {
      case e: ExecutionError => e
      case e: Throwable      => ExecutionError(e.toString, e)
    }
  }

  protected def printInspectQuery(
    label: String,
    elements: List[Element]
  ): ZIO[Connection, ExecutionError, Unit] = ZIO.succeed {
    val queries = new DatomicModel2Query(elements).getQueries(true)._3
    printInspect(label, Nil, queries)
  }
}
