package molecule.sql.mysql.compliance.partitions

import molecule.coreTests.spi.partitions.PartitionTests
import molecule.sql.mysql.setup.Test_mysql_async

object Test_PartitionTests extends PartitionTests with Test_mysql_async
