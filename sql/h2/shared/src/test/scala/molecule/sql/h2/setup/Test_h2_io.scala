package molecule.sql.h2.setup

import molecule.sql.h2.spi.Spi_h2_io

trait Test_h2_io
  extends TestSuite_h2_io
    with Spi_h2_io // Separate implementations on jvm/js
