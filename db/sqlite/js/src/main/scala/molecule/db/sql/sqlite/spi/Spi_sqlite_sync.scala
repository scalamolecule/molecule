package molecule.db.sql.sqlite.spi

import molecule.base.error.ModelError
import molecule.db.common.spi.Spi_sync


trait Spi_sqlite_sync extends Spi_sync {

  throw ModelError(
    "Molecule has no synchronous api on the JS platform since RPCs are asynchronous."
  )
}
