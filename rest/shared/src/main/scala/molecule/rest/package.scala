package molecule.graphql

import molecule.core.MoleculeImplicits_
import molecule.core.api.{ApiAsync, ApiSync, ApiZio}
import molecule.rest.spi.{SpiAsync_rest, SpiSync_rest, SpiZio_rest}

package object client {
  object async extends MoleculeImplicits_ with ApiAsync with SpiAsync_rest
  object sync extends MoleculeImplicits_ with ApiSync with SpiSync_rest
  object Zio extends MoleculeImplicits_ with ApiZio with SpiZio_rest
}
