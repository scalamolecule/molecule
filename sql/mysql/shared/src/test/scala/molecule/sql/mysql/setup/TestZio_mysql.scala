package molecule.sql.mysql.setup

import molecule.sql.mysql.spi.SpiZio_mysql

trait TestZio_mysql
  extends ZioSpec_mysql
    with SpiZio_mysql // Separate implementations on jvm/js
