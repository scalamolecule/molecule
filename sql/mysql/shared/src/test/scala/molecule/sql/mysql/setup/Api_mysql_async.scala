package molecule.sql.mysql.setup

import molecule.core.api.{Api_async, Api_async_transact}
import molecule.sql.mysql.spi.Spi_mysql_async

object Api_mysql_async
  extends Api_async
    with Api_async_transact
    with Spi_mysql_async
    with DbProviders_mysql
