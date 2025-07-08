package molecule.db.postgres.setup

import molecule.db
import molecule.db.common.api.{Api_io, Api_io_transact}
import molecule.db.postgres.spi.Spi_postgres_io

object Api_postgres_io
  extends Api_io
    with Api_io_transact
    with Spi_postgres_io
    with DbProviders_postgres


