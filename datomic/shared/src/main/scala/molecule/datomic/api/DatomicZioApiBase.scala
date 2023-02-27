package molecule.datomic.api

import molecule.base.util.exceptions.MoleculeError
import molecule.boilerplate.ast.Model._
import molecule.core.api.{Connection, PrintInspect}
import molecule.datomic.query.DatomicModel2Query
import zio.{Task, ZIO}

trait DatomicZioApiBase extends PrintInspect {

  protected def moleculeError[T](result: Task[T]): ZIO[Connection, MoleculeError, T] = {
    result.mapError {
      case e: MoleculeError => e
      case e: Throwable     => MoleculeError(e.toString, e)
    }
  }

  protected def printInspectQuery(
    label: String,
    elements: List[Element]
  ): ZIO[Connection, MoleculeError, Unit] = ZIO.succeed {
    val queries = new DatomicModel2Query(elements).getQueries(true)._3
    printInspect(label, Nil, queries)
  }
}
