package molecule.db.sql

import molecule.db.core.api.*
import molecule.db.sql.h2.spi.{Spi_h2_async, Spi_h2_io, Spi_h2_sync, Spi_h2_zio}

package object h2 {
  object async extends Api_async with Api_async_transact with Spi_h2_async
  object sync extends Api_sync with Api_sync_transact with Spi_h2_sync

  // With capital Z to avoid collision with zio namespace from ZIO
  object Zio extends Api_zio with Api_zio_transact with Spi_h2_zio
  object io extends Api_io with Api_io_transact with Spi_h2_io
}