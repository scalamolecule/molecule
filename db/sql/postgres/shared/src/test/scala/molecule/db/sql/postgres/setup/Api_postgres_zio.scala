package molecule.db.sql.postgres.setup

import molecule.db.core.api.{Api_zio, Api_zio_transact}
import molecule.db.sql.postgres.spi.Spi_postgres_zio

object Api_postgres_zio
  extends Api_zio
    with Api_zio_transact
    with Spi_postgres_zio
    with DbProviders_postgres_zio


