package molecule.db.sql

import molecule.db.sql.h2.spi.{Spi_h2_async, Spi_h2_io, Spi_h2_sync, Spi_h2_zio}
import h2.spi.*
import molecule.db.core.api.{Api_async, Api_async_transact, Api_io, Api_io_transact, Api_sync, Api_sync_transact, Api_zio, Api_zio_transact}

package object h2 {
  object async extends Api_async with Api_async_transact with Spi_h2_async
  object sync extends Api_sync with Api_sync_transact with Spi_h2_sync

  // With capital Z to avoid collision with zio namespace from ZIO
  object Zio extends Api_zio with Api_zio_transact with Spi_h2_zio
  object io extends Api_io with Api_io_transact with Spi_h2_io
}