package molecule.sql.postgres.setup

import molecule.sql.postgres.spi.Spi_postgres_async

trait Test_postgres_async
  extends TestSuite_postgres
    with Spi_postgres_async // Separate implementations on jvm/js
