package molecule.db.sql

import molecule.core.api.*
import molecule.db.sql.postgres.spi.{Spi_postgres_async, Spi_postgres_io, Spi_postgres_sync, Spi_postgres_zio}
import postgres.spi.*

package object postgres {
  object async extends Api_async with Api_async_transact with Spi_postgres_async
  object sync extends Api_sync with Api_sync_transact with Spi_postgres_sync
  object Zio extends Api_zio with Api_zio_transact with Spi_postgres_zio
  object io extends Api_io with Api_io_transact with Spi_postgres_io
}