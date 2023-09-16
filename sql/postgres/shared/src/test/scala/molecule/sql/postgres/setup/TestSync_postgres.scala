package molecule.sql.postgres.setup

import molecule.sql.postgres.spi.SpiSync_postgres

trait TestSync_postgres
  extends TestSuite_postgres
    with SpiSync_postgres // Separate implementations on jvm/js
