package molecule.sql.mariadb.setup

import molecule.core.api.Api_async
import molecule.sql.mariadb.spi.Spi_mariadb_async

object Api_mariadb_async
  extends Api_async
    with Spi_mariadb_async
    with DbProviders_mariadb


