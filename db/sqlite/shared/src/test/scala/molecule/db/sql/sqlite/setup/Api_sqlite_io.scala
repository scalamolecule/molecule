package molecule.db.sql.sqlite.setup

import molecule.db.common.api.{Api_io, Api_io_transact}
import molecule.db.sql.sqlite.spi.Spi_sqlite_io

object Api_sqlite_io
  extends Api_io
    with Api_io_transact
    with Spi_sqlite_io
    with DbProviders_sqlite


