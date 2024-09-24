package molecule.sql.h2.setup

import molecule.sql.h2.spi.Spi_h2_sync

trait Test_h2_sync
  extends TestSuite_h2
    with Spi_h2_sync // Separate implementations on jvm/js
