package molecule.sql.mariadb.compliance.partitions

import molecule.coreTests.spi.partitions.PartitionTests
import molecule.sql.mariadb.setup.TestAsync_mariadb

object PartitionTests extends PartitionTests with TestAsync_mariadb
