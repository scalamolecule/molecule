package molecule.document.mongodb.setup

import molecule.document.mongodb.spi.SpiZio_mongodb

trait TestZio_mongodb
  extends ZioSpec_mongodb
    with SpiZio_mongodb // Separate implementations on jvm/js
