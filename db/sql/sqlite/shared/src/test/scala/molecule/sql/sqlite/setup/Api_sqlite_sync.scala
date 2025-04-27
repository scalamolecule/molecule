package molecule.sql.sqlite.setup

import molecule.core.api.{Api_sync, Api_sync_transact}
import molecule.sql.sqlite.spi.Spi_sqlite_sync

object Api_sqlite_sync
  extends Api_sync
    with Api_sync_transact
    with Spi_sqlite_sync
    with DbProviders_sqlite


