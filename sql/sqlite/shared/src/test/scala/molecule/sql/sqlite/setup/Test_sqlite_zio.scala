package molecule.sql.sqlite.setup

import molecule.sql.sqlite.spi.Spi_sqlite_zio

trait Test_sqlite_zio
  extends TestSuite_sqlite_zio
    with Spi_sqlite_zio // Separate implementations on jvm/js
