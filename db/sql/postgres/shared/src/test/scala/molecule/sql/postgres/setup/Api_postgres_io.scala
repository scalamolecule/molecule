package molecule.sql.postgres.setup

import molecule.core.api.{Api_io, Api_io_transact}
import molecule.sql.postgres.spi.Spi_postgres_io

object Api_postgres_io
  extends Api_io
    with Api_io_transact
    with Spi_postgres_io
    with DbProviders_postgres


