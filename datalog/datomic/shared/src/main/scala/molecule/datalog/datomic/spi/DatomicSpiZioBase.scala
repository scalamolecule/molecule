package molecule.datalog.datomic.spi

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.spi.{Conn, PrintInspect}
import molecule.datalog.core.query.Model2DatomicQuery
import zio.{Task, ZIO}

trait DatomicSpiZioBase extends PrintInspect {

  protected def moleculeError[T](result: Task[T]): ZIO[Conn, MoleculeError, T] = {
    result.mapError {
      case e: MoleculeError => e
      case e: Throwable     => ExecutionError(e.toString)
    }
  }

  protected def printInspectQuery(
    label: String,
    elements: List[Element]
  ): ZIO[Conn, MoleculeError, Unit] = ZIO.succeed {
    val queries = new Model2DatomicQuery(elements).getDatomicQueries(true)._3
    printInspect(label, Nil, queries)
  }
}
