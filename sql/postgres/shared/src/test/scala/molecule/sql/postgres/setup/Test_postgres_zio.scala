package molecule.sql.postgres.setup

import molecule.sql.postgres.spi.Spi_postgres_zio

trait Test_postgres_zio
  extends TestSuite_postgres_zio
    with Spi_postgres_zio // Separate implementations on jvm/js
