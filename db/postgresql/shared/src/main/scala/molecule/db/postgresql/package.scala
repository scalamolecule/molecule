package molecule.db

import molecule.db.common.api.*
import molecule.db.postgresql.spi.*

package object postgresql {
  object async extends Api_async with Api_async_transact with Spi_postgresql_async
  object sync extends Api_sync with Api_sync_transact with Spi_postgresql_sync
  object Zio extends Api_zio with Api_zio_transact with Spi_postgresql_zio
  object io extends Api_io with Api_io_transact with Spi_postgresql_io
}