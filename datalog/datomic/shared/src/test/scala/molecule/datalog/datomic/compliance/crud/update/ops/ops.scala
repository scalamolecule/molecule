package molecule.datalog.datomic.compliance.crud.update.ops

import molecule.coreTests.spi.crud.update2.ops._
import molecule.datalog.datomic.setup.TestAsync_datomic

object OpsOne extends OpsOne with TestAsync_datomic
object OpsSet extends OpsSet with TestAsync_datomic
object OpsSeq extends OpsSeq with TestAsync_datomic
object OpsMap extends OpsMap with TestAsync_datomic
