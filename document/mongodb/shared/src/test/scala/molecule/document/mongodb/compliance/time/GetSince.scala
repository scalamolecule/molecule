package molecule.document.mongodb.compliance.time

import molecule.coreTests.spi.time.GetSince
import molecule.document.mongodb.setup.TestAsync_mongodb

object GetSince extends GetSince with TestAsync_mongodb
