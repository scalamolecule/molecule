package molecule.sql.core.api

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.api.{Connection, PrintInspect}
import molecule.sql.core.query.SqlModel2Query
import zio.{Task, ZIO}

trait SqlZioApiBase extends PrintInspect {

  protected def moleculeError[T](result: Task[T]): ZIO[Connection, MoleculeError, T] = {
    result.mapError {
      case e: MoleculeError => e
      case e: Throwable     => ExecutionError(e.toString)
    }
  }

  protected def printInspectQuery(
    label: String,
    elements: List[Element]
  ): ZIO[Connection, MoleculeError, Unit] = ZIO.succeed {
    val queries = new SqlModel2Query(elements).getQueries(true)._3
    printInspect(label, Nil, queries)
  }
}
