package molecule.sql.mariadb.setup

import molecule.sql.mariadb.spi.SpiAsync_mariadb

trait TestAsync_mariadb
  extends TestSuite_mariadb
    with SpiAsync_mariadb // Separate implementations on jvm/js
