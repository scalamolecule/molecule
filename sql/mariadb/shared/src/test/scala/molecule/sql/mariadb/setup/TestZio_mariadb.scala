package molecule.sql.mariadb.setup

import molecule.sql.mariadb.spi.SpiZio_mariadb

trait TestZio_mariadb
  extends ZioSpec_mariadb
    with SpiZio_mariadb // Separate implementations on jvm/js
