package molecule.sql.mariadb.spi

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.spi.{Conn, Renderer, SpiZio}
import molecule.sql.mariadb.query.Model2SqlQuery_mariadb
import zio.ZIO

trait SpiZioBase_mariadb extends Renderer { spi: SpiZio =>

  protected def printInspectQuery(
    label: String,
    elements: List[Element]
  ): ZIO[Conn, MoleculeError, Unit] = {
    for {
      conn <- ZIO.service[Conn]
      _ <- ZIO.succeed {
        val query = new Model2SqlQuery_mariadb(elements).getSqlQuery(Nil, None, None)
        printRaw(label, Nil, query)
      }
    } yield ()
  }
}
