package molecule.sql.postgres.setup

import molecule.sql.postgres.spi.Spi_postgres_sync

trait Test_postgres_sync
  extends TestSuite_postgres
    with Spi_postgres_sync // Separate implementations on jvm/js
