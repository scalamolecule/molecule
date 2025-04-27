package molecule.sql.postgres.setup

import molecule.core.api.{Api_async, Api_async_transact}
import molecule.sql.postgres.spi.Spi_postgres_async

object Api_postgres_async
  extends Api_async
    with Api_async_transact
    with Spi_postgres_async
    with DbProviders_postgres


