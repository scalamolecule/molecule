package molecule.sql.postgres.setup

import molecule.core.api.Api_zio
import molecule.sql.postgres.spi.Spi_postgres_zio

object Api_postgres_zio
  extends Api_zio
    with Spi_postgres_zio
    with DbProviders_postgres


