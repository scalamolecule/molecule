package molecule.sql.postgres.setup

import molecule.core.api.Api_sync
import molecule.sql.postgres.spi.Spi_postgres_sync

object Api_postgres_sync
  extends Api_sync
    with Spi_postgres_sync
    with DbProviders_postgres


