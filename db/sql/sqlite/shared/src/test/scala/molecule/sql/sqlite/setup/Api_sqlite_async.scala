package molecule.sql.sqlite.setup

import molecule.core.api.{Api_async, Api_async_transact}
import molecule.sql.sqlite.spi.Spi_sqlite_async

object Api_sqlite_async
  extends Api_async
    with Api_async_transact
    with Spi_sqlite_async
    with DbProviders_sqlite


