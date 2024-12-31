package molecule.sql.mysql.setup

import molecule.core.api.Api_sync
import molecule.sql.mysql.spi.Spi_mysql_sync

object Api_mysql_sync
  extends Api_sync
    with Spi_mysql_sync
    with DbProviders_mysql


