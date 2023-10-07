package molecule.sql.mariadb.setup

import molecule.sql.mariadb.spi.SpiSync_mariadb

trait TestSync_mariadb
  extends TestSuite_mariadb
    with SpiSync_mariadb // Separate implementations on jvm/js
