package molecule.sql.mysql.compliance.partition

import molecule.coreTests.spi.partition.PartitionTests
import molecule.sql.mysql.setup.Test_mysql_async

object Test_PartitionTests extends PartitionTests with Test_mysql_async
