package molecule.db.sql.mysql.spi

import molecule.base.error.ModelError
import molecule.db.core.spi.Spi_sync


trait Spi_mysql_sync extends Spi_sync {

  throw ModelError(
    "Molecule has no synchronous api on the JS platform since RPCs are asynchronous."
  )
}
