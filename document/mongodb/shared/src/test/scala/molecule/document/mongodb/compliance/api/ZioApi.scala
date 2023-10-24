package molecule.document.mongodb.compliance.api

import molecule.coreTests.spi.api._
import molecule.document.mongodb.setup.TestZio_mongodb

object ZioApi extends ZioApi with TestZio_mongodb
