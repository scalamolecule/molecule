package molecule.sql.sqlite.setup

import molecule.sql.sqlite.spi.SpiSync_sqlite

trait TestSync_sqlite
  extends TestSuite_sqlite
    with SpiSync_sqlite // Separate implementations on jvm/js
