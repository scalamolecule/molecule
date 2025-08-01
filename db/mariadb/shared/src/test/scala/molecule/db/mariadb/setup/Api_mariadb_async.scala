package molecule.db.mariadb.setup

import molecule.db
import molecule.db.common.api.{Api_async, Api_async_transact}
import molecule.db.mariadb.spi.Spi_mariadb_async

object Api_mariadb_async
  extends Api_async
    with Api_async_transact
    with Spi_mariadb_async
    with DbProviders_mariadb


