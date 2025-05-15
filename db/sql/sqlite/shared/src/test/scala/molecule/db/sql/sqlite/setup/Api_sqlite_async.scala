package molecule.db.sql.sqlite.setup

import molecule.db.core.api.{Api_async, Api_async_transact}
import molecule.db.sql.sqlite.spi.Spi_sqlite_async

object Api_sqlite_async
  extends Api_async
    with Api_async_transact
    with Spi_sqlite_async
    with DbProviders_sqlite


