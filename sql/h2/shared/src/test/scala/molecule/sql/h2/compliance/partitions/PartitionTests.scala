package molecule.sql.h2.compliance.partitions

import molecule.coreTests.spi.partitions.PartitionTests
import molecule.sql.h2.setup.Test_h2_async

object Test_PartitionTests extends PartitionTests with Test_h2_async
