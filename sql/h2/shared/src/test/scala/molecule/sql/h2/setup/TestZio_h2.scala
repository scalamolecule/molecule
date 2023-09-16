package molecule.sql.h2.setup

import molecule.sql.h2.spi.SpiZio_h2

trait TestZio_h2
  extends ZioSpec_h2
    with SpiZio_h2 // Separate implementations on jvm/js
