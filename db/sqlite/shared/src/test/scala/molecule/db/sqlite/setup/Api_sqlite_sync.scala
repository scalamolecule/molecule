package molecule.db.sqlite.setup

import molecule.db
import molecule.db.common.api.{Api_sync, Api_sync_transact}
import molecule.db.sqlite.spi.Spi_sqlite_sync

object Api_sqlite_sync
  extends Api_sync
    with Api_sync_transact
    with Spi_sqlite_sync
    with DbProviders_sqlite


