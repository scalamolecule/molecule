package molecule.sql

import molecule.core.api._
import molecule.sql.h2.spi._

package object h2 {
  object async extends Api_async with Api_async_transact with Spi_h2_async
  object sync extends Api_sync with Api_sync_transact with Spi_h2_sync
  object zio extends Api_zio with Api_zio_transact with Spi_h2_zio
  object io extends Api_io with Api_io_transact with Spi_h2_io
}