package molecule.sql

import molecule.core.MoleculeImplicits_
import molecule.core.api._
import molecule.sql.sqlite.spi._

package object sqlite {
  object async extends MoleculeImplicits_ with Api_async with Spi_sqlite_async
  object sync extends MoleculeImplicits_ with Api_sync with Spi_sqlite_sync
  object zio extends MoleculeImplicits_ with Api_zio with Spi_sqlite_zio
  object io extends MoleculeImplicits_ with Api_io with Spi_sqlite_io
}