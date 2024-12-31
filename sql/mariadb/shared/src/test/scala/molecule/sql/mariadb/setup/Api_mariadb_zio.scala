package molecule.sql.mariadb.setup

import molecule.core.api.Api_zio
import molecule.sql.mariadb.spi.Spi_mariadb_zio

object Api_mariadb_zio
  extends Api_zio
    with Spi_mariadb_zio
    with DbProviders_mariadb


