package molecule.document.mongodb.setup

import molecule.document.mongodb.spi.SpiAsync_mongodb

trait TestAsync_mongodb2
  extends TestSuite_mongodb2
    with SpiAsync_mongodb // Separate implementations on jvm/js
