package molecule.sql

import molecule.core.api.*
import molecule.sql.mysql.spi.*

package object mysql {
  object async extends Api_async with Api_async_transact with Spi_mysql_async
  object sync extends Api_sync with Api_sync_transact with Spi_mysql_sync
  object Zio extends Api_zio with Api_zio_transact with Spi_mysql_zio
  object io extends Api_io with Api_io_transact with Spi_mysql_io
}