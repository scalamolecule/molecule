package molecule.sql.postgres.setup

import molecule.core.api.{Api_zio, Api_zio_transact}
import molecule.sql.postgres.spi.Spi_postgres_zio

object Api_postgres_zio
  extends Api_zio
    with Api_zio_transact
    with Spi_postgres_zio
    with DbProviders_postgres_zio


