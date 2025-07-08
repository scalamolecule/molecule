package molecule.db.postgres.setup

import molecule.db
import molecule.db.common.api.{Api_sync, Api_sync_transact}
import molecule.db.postgres.spi.Spi_postgres_sync

object Api_postgres_sync
  extends Api_sync
    with Api_sync_transact
    with Spi_postgres_sync
    with DbProviders_postgres


