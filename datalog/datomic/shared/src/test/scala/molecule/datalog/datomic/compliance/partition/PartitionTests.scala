package molecule.datalog.datomic.compliance.partition

import molecule.coreTests.spi.partition.PartitionTests
import molecule.datalog.datomic.setup.Test_datomic_async

object Test_PartitionTests extends PartitionTests with Test_datomic_async
