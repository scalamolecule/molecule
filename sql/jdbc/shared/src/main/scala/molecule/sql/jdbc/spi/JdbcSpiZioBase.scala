package molecule.sql.jdbc.spi

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.spi.{Conn, PrintInspect, SpiZio}
import molecule.sql.core.query.SqlModel2Query
import zio.{Task, ZIO}

trait JdbcSpiZioBase extends PrintInspect { self: SpiZio =>

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
    val queries = new SqlModel2Query(elements).getSqlQuery(Nil) //._3
    printInspect(label, Nil, queries)
  }
}
