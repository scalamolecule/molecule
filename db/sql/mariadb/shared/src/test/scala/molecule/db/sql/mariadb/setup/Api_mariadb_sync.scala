package molecule.db.sql.mariadb.setup

import molecule.db.core.api.{Api_sync, Api_sync_transact}
import molecule.db.sql
import molecule.db.sql.mariadb.spi.Spi_mariadb_sync

object Api_mariadb_sync
  extends Api_sync
    with Api_sync_transact
    with Spi_mariadb_sync
    with DbProviders_mariadb


