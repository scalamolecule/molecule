package molecule.datalog.datomic.compliance.sort

import molecule.coreTests.spi.sort._
import molecule.datalog.datomic.setup.Test_datomic_async

object Test_SortAggr extends SortAggr with Test_datomic_async
object Test_SortBasics extends SortBasics with Test_datomic_async
object Test_SortDynamic extends SortDynamic with Test_datomic_async
object Test_SortNested extends SortNested with Test_datomic_async
