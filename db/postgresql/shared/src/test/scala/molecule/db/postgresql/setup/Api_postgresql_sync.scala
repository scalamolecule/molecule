package molecule.db.postgresql.setup

import molecule.db
import molecule.db.common.api.{Api_sync, Api_sync_transact}
import molecule.db.postgresql.spi.Spi_postgresql_sync

object Api_postgresql_sync
  extends Api_sync
    with Api_sync_transact
    with Spi_postgresql_sync
    with DbProviders_postgresql


