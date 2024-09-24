package molecule.sql.h2.setup

import molecule.sql.h2.spi.Spi_h2_zio

trait Test_h2_zio
  extends TestSuite_h2_zio
    with Spi_h2_zio // Separate implementations on jvm/js
