package molecule.db.sql.mysql.setup

import molecule.db.common.api.{Api_async, Api_async_transact}
import molecule.db.sql.mysql.spi.Spi_mysql_async

object Api_mysql_async
  extends Api_async
    with Api_async_transact
    with Spi_mysql_async
    with DbProviders_mysql
