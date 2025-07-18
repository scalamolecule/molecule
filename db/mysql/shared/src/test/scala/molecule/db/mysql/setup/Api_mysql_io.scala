package molecule.db.mysql.setup

import molecule.db
import molecule.db.common.api.{Api_io, Api_io_transact}
import molecule.db.mysql.spi.Spi_mysql_io

object Api_mysql_io
  extends Api_io
    with Api_io_transact
    with Spi_mysql_io
    with DbProviders_mysql


