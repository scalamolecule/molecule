package molecule.sql.postgres.compliance.partitions

import molecule.coreTests.spi.partitions.PartitionTests
import molecule.sql.postgres.setup.TestAsync_postgres

object Test_PartitionTests extends PartitionTests with TestAsync_postgres
