package molecule.document.mongodb.setup

import molecule.document.mongodb.spi.SpiSync_mongodb

trait TestSync_mongodb
  extends TestSuite_mongodb
    with SpiSync_mongodb // Separate implementations on jvm/js
