package molecule.db.sql

import molecule.db.sql.mariadb.spi.{Spi_mariadb_async, Spi_mariadb_io, Spi_mariadb_sync, Spi_mariadb_zio}
import mariadb.spi.*
import molecule.db.core.api.{Api_async, Api_async_transact, Api_io, Api_io_transact, Api_sync, Api_sync_transact, Api_zio, Api_zio_transact}

package object mariadb {
  object async extends Api_async with Api_async_transact with Spi_mariadb_async
  object sync extends Api_sync with Api_sync_transact with Spi_mariadb_sync
  object Zio extends Api_zio with Api_zio_transact with Spi_mariadb_zio
  object io extends Api_io with Api_io_transact with Spi_mariadb_io
}