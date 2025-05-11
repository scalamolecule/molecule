package molecule.db.sql.postgres.setup

import molecule.db.core.api.{Api_io, Api_io_transact}
import molecule.db.sql
import molecule.db.sql.postgres.spi.Spi_postgres_io

object Api_postgres_io
  extends Api_io
    with Api_io_transact
    with Spi_postgres_io
    with DbProviders_postgres


