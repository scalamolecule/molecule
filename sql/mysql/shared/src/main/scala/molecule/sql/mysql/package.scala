package molecule.sql

import molecule.core.MoleculeImplicits_
import molecule.core.api._
import molecule.sql.mysql.spi._

package object mysql {
  object async extends MoleculeImplicits_ with ApiAsync with SpiAsync_mysql
  object sync extends MoleculeImplicits_ with ApiSync with SpiSync_mysql
  object zio extends MoleculeImplicits_ with ApiZio with SpiZio_mysql
}