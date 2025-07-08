package molecule.db.mariadb.setup

import molecule.db
import molecule.db.common.api.{Api_zio, Api_zio_transact}
import molecule.db.mariadb.spi.Spi_mariadb_zio

object Api_mariadb_zio
  extends Api_zio
    with Api_zio_transact
    with Spi_mariadb_zio
    with DbProviders_mariadb_zio


