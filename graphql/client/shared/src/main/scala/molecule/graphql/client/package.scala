package molecule.graphql

import molecule.core.MoleculeImplicits_
import molecule.core.api.{ApiAsync, ApiSync, ApiZio}
import molecule.graphql.client.spi.{SpiAsync_graphql, SpiSync_graphql, SpiZio_graphql}

package object client {
  object async extends MoleculeImplicits_ with ApiAsync with SpiAsync_graphql
  object sync extends MoleculeImplicits_ with ApiSync with SpiSync_graphql
  object Zio extends MoleculeImplicits_ with ApiZio with SpiZio_graphql
}
