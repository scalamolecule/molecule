package molecule.sql.mysql.setup

import molecule.sql.mysql.spi.SpiAsync_mysql

trait TestAsync_mysql
  extends TestSuite_mysql
    with SpiAsync_mysql // Separate implementations on jvm/js
