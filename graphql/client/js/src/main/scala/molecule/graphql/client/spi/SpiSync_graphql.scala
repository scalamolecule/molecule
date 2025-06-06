package molecule.graphql.client.spi

import molecule.db.base.error.ModelError
import molecule.db.core.spi.Spi_sync


trait SpiSync_graphql extends Spi_sync {

  throw ModelError(
    "Molecule has no synchronous api on the JS platform since RPCs are asynchronous."
  )
}
