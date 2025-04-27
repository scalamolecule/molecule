package molecule.sql.mariadb.setup

import molecule.core.api.{Api_io, Api_io_transact}
import molecule.sql.mariadb.spi.Spi_mariadb_io

object Api_mariadb_io
  extends Api_io
    with Api_io_transact
    with Spi_mariadb_io
    with DbProviders_mariadb


