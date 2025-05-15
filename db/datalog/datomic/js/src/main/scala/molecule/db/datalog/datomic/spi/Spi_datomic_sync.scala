package molecule.db.datalog.datomic.spi

import molecule.db.base.error.ModelError
import molecule.db.core.spi.Spi_sync


trait Spi_datomic_sync extends Spi_sync {

  throw ModelError(
    "Molecule has no synchronous api on the JS platform since RPCs are asynchronous."
  )
}
