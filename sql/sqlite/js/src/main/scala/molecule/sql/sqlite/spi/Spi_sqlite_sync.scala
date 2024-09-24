package molecule.sql.sqlite.spi

import molecule.base.error.ModelError
import molecule.core.spi.SpiSync


trait Spi_sqlite_sync extends SpiSync {

  throw ModelError(
    "Molecule has no synchronous api on the JS platform since RPCs are asynchronous."
  )
}
