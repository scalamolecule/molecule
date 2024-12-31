package molecule.sql.sqlite.setup

import molecule.core.api.Api_io
import molecule.sql.sqlite.spi.Spi_sqlite_io

object Api_sqlite_io
  extends Api_io
    with Spi_sqlite_io
    with DbProviders_sqlite


