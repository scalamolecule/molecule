package molecule.sql.sqlite.setup

import molecule.core.api.Api_async
import molecule.sql.sqlite.spi.Spi_sqlite_async

object Api_sqlite_async
  extends Api_async
    with Spi_sqlite_async
    with DbProviders_sqlite


