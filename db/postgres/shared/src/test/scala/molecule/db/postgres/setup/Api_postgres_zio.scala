package molecule.db.postgres.setup

import molecule.db
import molecule.db.common.api.{Api_zio, Api_zio_transact}
import molecule.db.postgres.spi.Spi_postgres_zio

object Api_postgres_zio
  extends Api_zio
    with Api_zio_transact
    with Spi_postgres_zio
    with DbProviders_postgres_zio


