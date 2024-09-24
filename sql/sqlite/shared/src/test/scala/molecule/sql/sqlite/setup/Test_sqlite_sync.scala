package molecule.sql.sqlite.setup

import molecule.sql.sqlite.spi.Spi_sqlite_sync

trait Test_sqlite_sync
  extends TestSuite_sqlite
    with Spi_sqlite_sync // Separate implementations on jvm/js
