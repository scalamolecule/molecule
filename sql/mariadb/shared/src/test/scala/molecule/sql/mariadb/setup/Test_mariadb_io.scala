package molecule.sql.mariadb.setup

import molecule.sql.mariadb.spi.Spi_mariadb_io

trait Test_mariadb_io
  extends TestSuite_mariadb_io
    with Spi_mariadb_io // Separate implementations on jvm/js
