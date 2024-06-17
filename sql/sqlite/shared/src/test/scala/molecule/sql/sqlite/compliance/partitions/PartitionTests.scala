package molecule.sql.sqlite.compliance.partitions

import molecule.coreTests.spi.partitions.PartitionTests
import molecule.sql.sqlite.setup.TestAsync_sqlite

object PartitionTests extends PartitionTests with TestAsync_sqlite
