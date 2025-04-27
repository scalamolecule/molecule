package molecule.sql.mysql.setup

import molecule.core.api.{Api_sync, Api_sync_transact}
import molecule.sql.mysql.spi.Spi_mysql_sync

object Api_mysql_sync
  extends Api_sync
    with Api_sync_transact
    with Spi_mysql_sync
    with DbProviders_mysql


