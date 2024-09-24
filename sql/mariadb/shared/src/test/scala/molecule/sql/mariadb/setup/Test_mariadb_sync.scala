package molecule.sql.mariadb.setup

import molecule.sql.mariadb.spi.Spi_mariadb_sync

trait Test_mariadb_sync
  extends TestSuite_mariadb
    with Spi_mariadb_sync // Separate implementations on jvm/js
