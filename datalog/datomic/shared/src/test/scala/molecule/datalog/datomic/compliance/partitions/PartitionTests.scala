package molecule.datalog.datomic.compliance.partitions

import molecule.coreTests.spi.partitions.PartitionTests
import molecule.datalog.datomic.setup.Test_datomic_async

object Test_PartitionTests extends PartitionTests with Test_datomic_async
