package molecule.db.mysql.setup

import molecule.db
import molecule.db.common.api.{Api_sync, Api_sync_transact}
import molecule.db.mysql.spi.Spi_mysql_sync

object Api_mysql_sync
  extends Api_sync
    with Api_sync_transact
    with Spi_mysql_sync
    with DbProviders_mysql


