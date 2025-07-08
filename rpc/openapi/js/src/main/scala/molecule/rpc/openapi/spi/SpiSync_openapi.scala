package molecule.rpc.openapi.spi

import molecule.base.error.ModelError
import molecule.db.common.spi.SpiSync


trait SpiSync_openapi extends SpiSync {

  throw ModelError(
    "Molecule has no synchronous api on the JS platform since RPCs are asynchronous."
  )
}
