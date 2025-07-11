package molecule.db.postgresql.setup

import molecule.db
import molecule.db.common.api.{Api_io, Api_io_transact}
import molecule.db.postgresql.spi.Spi_postgresql_io

object Api_postgresql_io
  extends Api_io
    with Api_io_transact
    with Spi_postgresql_io
    with DbProviders_postgresql


