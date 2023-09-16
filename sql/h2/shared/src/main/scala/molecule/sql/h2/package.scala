package molecule.sql

import molecule.core.MoleculeImplicits_
import molecule.core.api._
import molecule.sql.h2.spi._

package object h2 {
  object async extends MoleculeImplicits_ with ApiAsync with SpiAsync_h2
  object sync extends MoleculeImplicits_ with ApiSync with SpiSync_h2
  object zio extends MoleculeImplicits_ with ApiZio with SpiZio_h2
}