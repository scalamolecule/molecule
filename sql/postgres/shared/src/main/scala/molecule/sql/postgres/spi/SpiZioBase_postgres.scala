package molecule.sql.postgres.spi

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.spi.{Conn, SpiZio}
import molecule.sql.h2.spi.SpiZioBase_h2
import molecule.sql.postgres.query.Model2SqlQuery_postgres
import zio.ZIO

trait SpiZioBase_postgres extends SpiZioBase_h2 { spi: SpiZio =>

//  protected def moleculeError[T](result: Task[T]): ZIO[Conn, MoleculeError, T] = {
//    result.mapError {
//      case e: MoleculeError => e
//      case e: Throwable     => ExecutionError(e.toString)
//    }
//  }

  protected override def printInspectQuery(
    label: String,
    elements: List[Element]
  ): ZIO[Conn, MoleculeError, Unit] = ZIO.succeed {
    val query = new Model2SqlQuery_postgres(elements).getSqlQuery(Nil, None, None)
    printInspect(label, Nil, query)
  }
}
