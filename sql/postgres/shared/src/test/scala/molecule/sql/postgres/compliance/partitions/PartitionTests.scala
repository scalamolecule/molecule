package molecule.sql.postgres.compliance.partitions

import molecule.coreTests.spi.partitions.PartitionTests
import molecule.sql.postgres.setup.Test_postgres_async

object Test_PartitionTests extends PartitionTests with Test_postgres_async
