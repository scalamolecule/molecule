package molecule.graphql

import molecule.db.core.api.Api_async
import molecule.graphql.client.spi.SpiAsync_graphql

package object client {
  object async extends Api_async with SpiAsync_graphql
//  object sync extends MoleculeImplicits_ with ApiSync with SpiSync_graphql
//  object Zio extends MoleculeImplicits_ with ApiZio with SpiZio_graphql
}
