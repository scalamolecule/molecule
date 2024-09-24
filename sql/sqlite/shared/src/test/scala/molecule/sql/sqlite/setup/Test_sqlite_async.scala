package molecule.sql.sqlite.setup

import molecule.sql.sqlite.spi.Spi_sqlite_async

trait Test_sqlite_async
  extends TestSuite_sqlite
    with Spi_sqlite_async // Separate implementations on jvm/js
