package molecule.sql.postgres.spi

import molecule.base.error.ModelError
import molecule.core.spi.Spi_sync


trait Spi_postgres_sync extends Spi_sync {

  throw ModelError(
    "Molecule has no synchronous api on the JS platform since RPCs are asynchronous."
  )
}
