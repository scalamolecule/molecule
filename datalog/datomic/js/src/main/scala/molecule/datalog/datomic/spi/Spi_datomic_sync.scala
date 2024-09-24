package molecule.datalog.datomic.spi

import molecule.base.error.ModelError
import molecule.core.spi.SpiSync


trait Spi_datomic_sync extends SpiSync {

  throw ModelError(
    "Molecule has no synchronous api on the JS platform since RPCs are asynchronous."
  )
}
