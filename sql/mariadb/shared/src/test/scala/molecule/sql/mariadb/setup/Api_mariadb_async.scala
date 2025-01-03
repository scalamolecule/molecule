package molecule.sql.mariadb.setup

import molecule.core.api.{Api_async, Api_async_transact}
import molecule.sql.mariadb.spi.Spi_mariadb_async

object Api_mariadb_async
  extends Api_async
    with Api_async_transact
    with Spi_mariadb_async
    with DbProviders_mariadb

