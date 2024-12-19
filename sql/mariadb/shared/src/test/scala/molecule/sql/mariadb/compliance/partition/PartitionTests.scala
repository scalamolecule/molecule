package molecule.sql.mariadb.compliance.partition

import molecule.coreTests.spi.partition.PartitionTests
import molecule.sql.mariadb.setup.Test_mariadb_async

object Test_PartitionTests extends PartitionTests with Test_mariadb_async
