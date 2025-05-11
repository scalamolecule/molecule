package molecule.graphql.client.spi

import molecule.db.base.error.ModelError
import molecule.db.core.spi.SpiSync


trait SpiSync_graphql extends SpiSync {

  throw ModelError(
    "Molecule has no synchronous api on the JS platform since RPCs are asynchronous."
  )
}
