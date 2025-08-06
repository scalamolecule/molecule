package molecule.db.postgresql.spi

import molecule.core.error.ModelError
import molecule.db.common.spi.Spi_sync


trait Spi_postgresql_sync extends Spi_sync {

  throw ModelError(
    "Molecule has no synchronous api on the JS platform since RPCs are asynchronous."
  )
}
