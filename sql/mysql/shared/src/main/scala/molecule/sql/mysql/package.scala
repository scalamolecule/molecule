package molecule.sql

import molecule.core.MoleculeImplicits_
import molecule.core.api._
import molecule.sql.mysql.spi._

package object mysql {
  object async extends MoleculeImplicits_ with Api_async with Spi_mysql_async
  object sync extends MoleculeImplicits_ with Api_sync with Spi_mysql_sync
  object zio extends MoleculeImplicits_ with Api_zio with Spi_mysql_zio
}