package molecule.sql

import molecule.core.MoleculeImplicits_
import molecule.core.api._
import molecule.sql.mariadb.spi._

package object mariadb {
  object async extends MoleculeImplicits_ with ApiAsync with SpiAsync_mariadb
  object sync extends MoleculeImplicits_ with ApiSync with SpiSync_mariadb
  object zio extends MoleculeImplicits_ with ApiZio with SpiZio_mariadb
}