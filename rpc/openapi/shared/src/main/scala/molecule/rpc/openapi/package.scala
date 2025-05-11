package molecule.graphql

import molecule.db.core.MoleculeImplicits_
import molecule.db.core.api.{ApiAsync, ApiSync, ApiZio}
import molecule.rpc.openapi.spi.{SpiAsync_openapi, SpiSync_openapi, SpiZio_openapi}

package object client {
  object async extends MoleculeImplicits_ with ApiAsync with SpiAsync_openapi
  object sync extends MoleculeImplicits_ with ApiSync with SpiSync_openapi
  object Zio extends MoleculeImplicits_ with ApiZio with SpiZio_openapi
}
