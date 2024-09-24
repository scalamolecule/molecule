package molecule.sql

import molecule.core.MoleculeImplicits_
import molecule.core.api._
import molecule.sql.mysql.spi._

package object mysql {
  object async extends MoleculeImplicits_ with ApiAsync with Spi_mysql_async
  object sync extends MoleculeImplicits_ with ApiSync with Spi_mysql_sync
  object zio extends MoleculeImplicits_ with ApiZio with Spi_mysql_zio
}