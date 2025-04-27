package molecule.sql.postgres.setup

import molecule.core.api.{Api_sync, Api_sync_transact}
import molecule.sql.postgres.spi.Spi_postgres_sync

object Api_postgres_sync
  extends Api_sync
    with Api_sync_transact
    with Spi_postgres_sync
    with DbProviders_postgres


