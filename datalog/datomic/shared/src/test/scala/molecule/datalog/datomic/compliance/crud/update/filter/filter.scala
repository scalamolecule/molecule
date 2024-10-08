package molecule.datalog.datomic.compliance.crud.update.filter

import molecule.coreTests.spi.crud.update.filter._
import molecule.datalog.datomic.setup.Test_datomic_async

object Test_FilterOne extends FilterOne with Test_datomic_async
object Test_FilterSet extends FilterSet with Test_datomic_async
object Test_FilterSeq extends FilterSeq with Test_datomic_async
object Test_FilterMap extends FilterMap with Test_datomic_async
