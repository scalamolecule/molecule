package molecule.document.mongodb.setup

import molecule.document.mongodb.spi.SpiAsync_mongodb

trait TestAsync_mongodb
  extends TestSuite_mongodb
    with SpiAsync_mongodb // Separate implementations on jvm/js
