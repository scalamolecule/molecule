package molecule.sql.h2.setup

import molecule.sql.h2.spi.Spi_h2_async

trait Test_h2_async
  extends TestSuite_h2
    with Spi_h2_async // Separate implementations on jvm/js
