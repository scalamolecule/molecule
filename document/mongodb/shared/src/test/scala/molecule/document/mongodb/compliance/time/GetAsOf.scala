package molecule.document.mongodb.compliance.time

import molecule.coreTests.spi.time.GetAsOf
import molecule.document.mongodb.setup.TestAsync_mongodb

object GetAsOf extends GetAsOf with TestAsync_mongodb
