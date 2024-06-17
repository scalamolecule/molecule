package molecule.sql

import molecule.core.MoleculeImplicits_
import molecule.core.api._
import molecule.sql.sqlite.spi._

package object sqlite {
  object async extends MoleculeImplicits_ with ApiAsync with SpiAsync_sqlite
  object sync extends MoleculeImplicits_ with ApiSync with SpiSync_sqlite
  object zio extends MoleculeImplicits_ with ApiZio with SpiZio_sqlite
}