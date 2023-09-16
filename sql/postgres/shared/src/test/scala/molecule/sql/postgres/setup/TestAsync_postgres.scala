package molecule.sql.postgres.setup

import molecule.sql.postgres.spi.SpiAsync_postgres

trait TestAsync_postgres
  extends TestSuite_postgres
    with SpiAsync_postgres // Separate implementations on jvm/js
