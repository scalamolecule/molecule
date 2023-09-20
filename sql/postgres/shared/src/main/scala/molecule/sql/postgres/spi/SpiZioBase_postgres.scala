package molecule.sql.postgres.spi

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.spi.{Conn, PrintInspect, SpiZio}
import molecule.sql.postgres.query.Model2SqlQuery_postgres
import zio.ZIO

trait SpiZioBase_postgres extends PrintInspect { spi: SpiZio =>

  protected def printInspectQuery(
    label: String,
    elements: List[Element]
  ): ZIO[Conn, MoleculeError, Unit] = ZIO.succeed {
    val query = new Model2SqlQuery_postgres(elements).getSqlQuery(Nil, None, None)
    printInspect(label, Nil, query)
  }
}
