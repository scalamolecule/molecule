package molecule.db.sql.mysql.setup

import molecule.db.core.api.{Api_io, Api_io_transact}
import molecule.db.sql.mysql.spi.Spi_mysql_io

object Api_mysql_io
  extends Api_io
    with Api_io_transact
    with Spi_mysql_io
    with DbProviders_mysql


