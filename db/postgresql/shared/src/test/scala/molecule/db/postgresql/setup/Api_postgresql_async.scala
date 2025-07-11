package molecule.db.postgresql.setup

import molecule.db
import molecule.db.common.api.{Api_async, Api_async_transact}
import molecule.db.postgresql.spi.Spi_postgresql_async

object Api_postgresql_async
  extends Api_async
    with Api_async_transact
    with Spi_postgresql_async
    with DbProviders_postgresql


