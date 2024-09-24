package molecule.sql.mariadb.compliance.partitions

import molecule.coreTests.spi.partitions.PartitionTests
import molecule.sql.mariadb.setup.Test_mariadb_async

object Test_PartitionTests extends PartitionTests with Test_mariadb_async
