package molecule.sql.sqlite.setup

import molecule.core.api.{Api_io, Api_io_transact}
import molecule.sql.sqlite.spi.Spi_sqlite_io

object Api_sqlite_io
  extends Api_io
    with Api_io_transact
    with Spi_sqlite_io
    with DbProviders_sqlite


