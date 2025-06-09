package molecule.rest.spi

import molecule.base.error.ModelError
import molecule.db.core.spi.SpiSync


trait SpiSync_rest extends SpiSync {

  throw ModelError(
    "Molecule has no synchronous api on the JS platform since RPCs are asynchronous."
  )
}
