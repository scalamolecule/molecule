package molecule.sql.sqlite.compliance.partitions

import molecule.coreTests.spi.partitions.PartitionTests
import molecule.sql.sqlite.setup.Test_sqlite_async

object Test_PartitionTests extends PartitionTests with Test_sqlite_async
