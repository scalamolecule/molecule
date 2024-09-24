package molecule.sql

import molecule.core.MoleculeImplicits_
import molecule.core.api._
import molecule.sql.mariadb.spi._

package object mariadb {
  object async extends MoleculeImplicits_ with Api_async with Spi_mariadb_async
  object sync extends MoleculeImplicits_ with Api_sync with Spi_mariadb_sync
  object zio extends MoleculeImplicits_ with Api_zio with Spi_mariadb_zio
}