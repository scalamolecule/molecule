package molecule.sql.mysql.spi

import molecule.base.error.ModelError
import molecule.core.spi.SpiSync


trait Spi_mysql_sync extends SpiSync {

  throw ModelError(
    "Molecule has no synchronous api on the JS platform since RPCs are asynchronous."
  )
}
