package molecule.db.mysql.setup

import molecule.db
import molecule.db.common.api.{Api_async, Api_async_transact}
import molecule.db.mysql.spi.Spi_mysql_async

object Api_mysql_async
  extends Api_async
    with Api_async_transact
    with Spi_mysql_async
    with DbProviders_mysql
