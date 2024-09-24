package molecule.sql.postgres.setup

import molecule.sql.postgres.spi.Spi_postgres_io

trait Test_postgres_io
  extends TestSuite_postgres_io
    with Spi_postgres_io // Separate implementations on jvm/js
