package molecule.sql

import molecule.core.MoleculeImplicits_
import molecule.core.api._
import molecule.sql.postgres.spi._

package object postgres {
  object async extends MoleculeImplicits_ with ApiAsync with SpiAsync_postgres
  object sync extends MoleculeImplicits_ with ApiSync with SpiSync_postgres
  object zio extends MoleculeImplicits_ with ApiZio with SpiZio_postgres
}