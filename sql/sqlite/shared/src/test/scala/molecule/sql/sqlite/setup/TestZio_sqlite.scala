package molecule.sql.sqlite.setup

import molecule.sql.sqlite.spi.SpiZio_sqlite

trait TestZio_sqlite
  extends ZioSpec_sqlite
    with SpiZio_sqlite // Separate implementations on jvm/js
