package molecule.sql.sqlite.setup

import molecule.sql.sqlite.spi.Spi_sqlite_io

trait Test_sqlite_io
  extends TestSuite_sqlite_io
    with Spi_sqlite_io // Separate implementations on jvm/js
