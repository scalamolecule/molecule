package molecule.db.sql.postgres.setup

import molecule.db.core.api.{Api_sync, Api_sync_transact}
import molecule.db.sql.postgres.spi.Spi_postgres_sync

object Api_postgres_sync
  extends Api_sync
    with Api_sync_transact
    with Spi_postgres_sync
    with DbProviders_postgres


