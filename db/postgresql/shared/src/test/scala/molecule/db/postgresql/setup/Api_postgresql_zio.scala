package molecule.db.postgresql.setup

import molecule.db
import molecule.db.common.api.{Api_zio, Api_zio_transact}
import molecule.db.postgresql.spi.Spi_postgresql_zio

object Api_postgresql_zio
  extends Api_zio
    with Api_zio_transact
    with Spi_postgresql_zio
    with DbProviders_postgresql_zio


