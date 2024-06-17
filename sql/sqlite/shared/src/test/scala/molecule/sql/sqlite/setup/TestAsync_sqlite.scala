package molecule.sql.sqlite.setup

import molecule.sql.sqlite.spi.SpiAsync_sqlite

trait TestAsync_sqlite
  extends TestSuite_sqlite
    with SpiAsync_sqlite // Separate implementations on jvm/js
