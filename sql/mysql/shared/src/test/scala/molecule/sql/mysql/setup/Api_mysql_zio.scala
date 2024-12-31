package molecule.sql.mysql.setup

import molecule.core.api.Api_zio
import molecule.sql.mysql.spi.Spi_mysql_zio

object Api_mysql_zio
  extends Api_zio
    with Spi_mysql_zio
    with DbProviders_mysql


