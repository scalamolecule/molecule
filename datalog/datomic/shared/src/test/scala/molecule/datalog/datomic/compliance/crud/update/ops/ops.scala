package molecule.datalog.datomic.compliance.crud.update.ops

import molecule.coreTests.spi.crud.update.ops._
import molecule.datalog.datomic.setup.TestAsync_datomic

object Test_OpsOne extends OpsOne with TestAsync_datomic
object Test_OpsSet extends OpsSet with TestAsync_datomic
object Test_OpsSeq extends OpsSeq with TestAsync_datomic
object Test_OpsMap extends OpsMap with TestAsync_datomic
