package molecule.sql.postgres.setup

import molecule.core.api.Api_async
import molecule.sql.postgres.spi.Spi_postgres_async

object Api_postgres_async
  extends Api_async
    with Spi_postgres_async
    with DbProviders_postgres


