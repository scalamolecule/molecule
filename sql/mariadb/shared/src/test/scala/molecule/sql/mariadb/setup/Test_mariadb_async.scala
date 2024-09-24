package molecule.sql.mariadb.setup

import molecule.sql.mariadb.spi.Spi_mariadb_async

trait Test_mariadb_async
  extends TestSuite_mariadb
    with Spi_mariadb_async // Separate implementations on jvm/js
