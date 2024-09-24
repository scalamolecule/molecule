package molecule.sql.mysql.setup

import molecule.sql.mysql.spi.Spi_mysql_async

trait Test_mysql_async
  extends TestSuite_mysql
    with Spi_mysql_async // Separate implementations on jvm/js
