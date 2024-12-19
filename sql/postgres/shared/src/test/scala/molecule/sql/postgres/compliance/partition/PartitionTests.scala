package molecule.sql.postgres.compliance.partition

import molecule.coreTests.spi.partition.PartitionTests
import molecule.sql.postgres.setup.Test_postgres_async

object Test_PartitionTests extends PartitionTests with Test_postgres_async
