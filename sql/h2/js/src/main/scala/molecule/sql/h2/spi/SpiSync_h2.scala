package molecule.sql.h2.spi

import molecule.base.error.ModelError
import molecule.core.spi.SpiSync


trait SpiSync_h2 extends SpiSync {

  throw ModelError(
    "Molecule has no synchronous api on the JS platform since RPCs are asynchronous."
  )
}
