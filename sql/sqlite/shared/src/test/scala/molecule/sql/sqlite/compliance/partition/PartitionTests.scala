package molecule.sql.sqlite.compliance.partition

import molecule.coreTests.spi.partition.PartitionTests
import molecule.sql.sqlite.setup.Test_sqlite_async

object Test_PartitionTests extends PartitionTests with Test_sqlite_async
