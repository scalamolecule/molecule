package molecule.datalog

import molecule.core.MoleculeImplicits_
import molecule.core.api.{ApiAsync, ApiSync, ApiZio}
import molecule.datalog.datomic.spi._

package object datomic {
  object async extends MoleculeImplicits_ with ApiAsync with SpiAsync_datomic
  object sync extends MoleculeImplicits_ with ApiSync with SpiSync_datomic
  object zio extends MoleculeImplicits_ with ApiZio with SpiZio_datomic
}