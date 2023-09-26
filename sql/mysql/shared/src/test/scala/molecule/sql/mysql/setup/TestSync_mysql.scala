package molecule.sql.mysql.setup

import molecule.sql.mysql.spi.SpiSync_mysql

trait TestSync_mysql
  extends TestSuite_mysql
    with SpiSync_mysql // Separate implementations on jvm/js
