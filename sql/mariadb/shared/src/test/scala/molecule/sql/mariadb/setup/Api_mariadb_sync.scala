package molecule.sql.mariadb.setup

import molecule.core.api.{Api_sync, Api_sync_transact}
import molecule.sql.mariadb.spi.Spi_mariadb_sync

object Api_mariadb_sync
  extends Api_sync
    with Api_sync_transact
    with Spi_mariadb_sync
    with DbProviders_mariadb


