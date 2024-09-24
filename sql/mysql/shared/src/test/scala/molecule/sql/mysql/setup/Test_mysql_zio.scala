package molecule.sql.mysql.setup

import molecule.sql.mysql.spi.Spi_mysql_zio

trait Test_mysql_zio
  extends TestSuite_mysql_zio
    with Spi_mysql_zio // Separate implementations on jvm/js
