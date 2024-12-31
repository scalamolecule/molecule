package molecule.sql.postgres.setup

import molecule.core.api.Api_io
import molecule.sql.postgres.spi.Spi_postgres_io

object Api_postgres_io
  extends Api_io
    with Spi_postgres_io
    with DbProviders_postgres


