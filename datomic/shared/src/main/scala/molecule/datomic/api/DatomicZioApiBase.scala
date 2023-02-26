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
  ): ZIO[Connection, MoleculeError, Unit] = {
    val d2q = new DatomicModel2Query(elements)
    // Process model to query
    d2q.processQueries(true)
    ZIO.succeed(
      printInspect(label, Nil, d2q.renderQueries(elements)._3)
    )
  }
}
