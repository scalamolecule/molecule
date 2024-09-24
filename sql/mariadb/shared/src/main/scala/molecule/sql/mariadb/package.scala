package molecule.sql

import molecule.core.MoleculeImplicits_
import molecule.core.api._
import molecule.sql.mariadb.spi._

package object mariadb {
  object async extends MoleculeImplicits_ with ApiAsync with Spi_mariadb_async
  object sync extends MoleculeImplicits_ with ApiSync with Spi_mariadb_sync
  object zio extends MoleculeImplicits_ with ApiZio with Spi_mariadb_zio
}