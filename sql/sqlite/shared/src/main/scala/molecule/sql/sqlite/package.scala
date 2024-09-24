package molecule.sql

import molecule.core.MoleculeImplicits_
import molecule.core.api._
import molecule.sql.sqlite.spi._

package object sqlite {
  object async extends MoleculeImplicits_ with ApiAsync with Spi_sqlite_async
  object sync extends MoleculeImplicits_ with ApiSync with Spi_sqlite_sync
  object zio extends MoleculeImplicits_ with ApiZio with Spi_sqlite_zio
}