package molecule.sql.h2.setup

import molecule.sql.h2.spi.SpiAsync_h2

trait TestAsync_h2
  extends TestSuite_h2
    with SpiAsync_h2 // Separate implementations on jvm/js
