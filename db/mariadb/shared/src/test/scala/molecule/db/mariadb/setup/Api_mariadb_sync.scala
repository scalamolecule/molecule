package molecule.db.mariadb.setup

import molecule.db
import molecule.db.common.api.{Api_sync, Api_sync_transact}
import molecule.db.mariadb.spi.Spi_mariadb_sync

object Api_mariadb_sync
  extends Api_sync
    with Api_sync_transact
    with Spi_mariadb_sync
    with DbProviders_mariadb


