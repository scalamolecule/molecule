package molecule.datalog.datomic.compliance.crud.update.ops

import molecule.coreTests.spi.crud.update.ops._
import molecule.datalog.datomic.setup.Test_datomic_async

object Test_OpsOne extends OpsOne with Test_datomic_async
object Test_OpsSet extends OpsSet with Test_datomic_async
object Test_OpsSeq extends OpsSeq with Test_datomic_async
object Test_OpsMap extends OpsMap with Test_datomic_async
