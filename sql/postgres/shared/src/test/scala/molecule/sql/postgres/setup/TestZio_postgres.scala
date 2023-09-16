package molecule.sql.postgres.setup

import molecule.sql.postgres.spi.SpiZio_postgres

trait TestZio_postgres
  extends ZioSpec_postgres
    with SpiZio_postgres // Separate implementations on jvm/js
