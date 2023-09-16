package molecule.sql.h2.setup

import molecule.sql.h2.spi.SpiSync_h2

trait TestSync_h2
  extends TestSuite_h2
    with SpiSync_h2 // Separate implementations on jvm/js
