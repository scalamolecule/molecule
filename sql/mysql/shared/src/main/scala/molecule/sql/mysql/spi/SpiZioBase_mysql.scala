package molecule.sql.mysql.spi

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.spi.{Conn, Renderer, SpiZio}
import molecule.sql.mysql.query.Model2SqlQuery_mysql
import zio.ZIO

trait SpiZioBase_mysql extends Renderer { spi: SpiZio =>

  protected def printInspectQuery(
    label: String,
    elements: List[Element]
  ): ZIO[Conn, MoleculeError, Unit] = {
    for {
      conn <- ZIO.service[Conn]
      _ <- ZIO.succeed {
        val query = new Model2SqlQuery_mysql(elements).getSqlQuery(Nil, None, None, None)
        printRaw(label, Nil, query)
      }
    } yield ()
  }
}
