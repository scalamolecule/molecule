package molecule.document.mongodb.compliance.partitions

import molecule.coreTests.spi.partitions.PartitionTests
import molecule.document.mongodb.setup.TestAsync_mongodb

object PartitionTests extends PartitionTests with TestAsync_mongodb
