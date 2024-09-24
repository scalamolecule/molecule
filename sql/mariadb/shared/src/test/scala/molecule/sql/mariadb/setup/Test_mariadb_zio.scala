package molecule.sql.mariadb.setup

import molecule.sql.mariadb.spi.Spi_mariadb_zio

trait Test_mariadb_zio
  extends TestSuite_mariadb_zio
    with Spi_mariadb_zio // Separate implementations on jvm/js
