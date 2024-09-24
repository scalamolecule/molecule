package molecule.sql.mysql.setup

import molecule.sql.mysql.spi.Spi_mysql_sync

trait Test_mysql_sync
  extends TestSuite_mysql
    with Spi_mysql_sync // Separate implementations on jvm/js
