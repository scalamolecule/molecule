package molecule.graphql

import molecule.db.core.MoleculeImplicits_
import molecule.db.core.api.{ApiAsync, ApiSync, ApiZio}
import molecule.rpc.grpc.spi.{SpiAsync_grpc, SpiSync_grpc, SpiZio_grpc}

package object client {
  object async extends MoleculeImplicits_ with ApiAsync with SpiAsync_grpc
  object sync extends MoleculeImplicits_ with ApiSync with SpiSync_grpc
  object Zio extends MoleculeImplicits_ with ApiZio with SpiZio_grpc
}
