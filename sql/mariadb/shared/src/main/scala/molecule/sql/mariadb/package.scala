package molecule.sql

import molecule.core.api._
import molecule.sql.mariadb.spi._

package object mariadb {
  object async extends Api_async with Api_async_transact with Spi_mariadb_async
  object sync extends Api_sync with Api_sync_transact with Spi_mariadb_sync
  object zio extends Api_zio with Api_zio_transact with Spi_mariadb_zio
  object io extends Api_io with Api_io_transact with Spi_mariadb_io
}