package molecule.sql.mysql.compliance.partitions

import molecule.coreTests.spi.partitions.PartitionTests
import molecule.sql.mysql.setup.TestAsync_mysql

object Test_PartitionTests extends PartitionTests with TestAsync_mysql
