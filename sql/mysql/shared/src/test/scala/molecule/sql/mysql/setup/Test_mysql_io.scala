package molecule.sql.mysql.setup

import molecule.sql.mysql.spi.Spi_mysql_io

trait Test_mysql_io
  extends TestSuite_mysql_io
    with Spi_mysql_io // Separate implementations on jvm/js
