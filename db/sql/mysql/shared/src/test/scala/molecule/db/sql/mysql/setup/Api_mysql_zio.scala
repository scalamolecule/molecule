package molecule.db.sql.mysql.setup

import molecule.core.api.{Api_zio, Api_zio_transact}
import molecule.db.sql
import molecule.db.sql.mysql.spi.Spi_mysql_zio

object Api_mysql_zio
  extends Api_zio
    with Api_zio_transact
    with Spi_mysql_zio
    with DbProviders_mysql_zio


