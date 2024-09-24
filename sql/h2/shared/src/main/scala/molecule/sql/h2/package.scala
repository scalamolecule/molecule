package molecule.sql

import molecule.core.MoleculeImplicits_
import molecule.core.api._
import molecule.sql.h2.spi._

package object h2 {
  object async extends MoleculeImplicits_ with ApiAsync with Spi_h2_async
  object sync extends MoleculeImplicits_ with ApiSync with Spi_h2_sync
  object zio extends MoleculeImplicits_ with ApiZio with Spi_h2_zio
}