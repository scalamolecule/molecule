package molecule.sql.h2.compliance.crud.update.ops

import molecule.coreTests.spi.crud.update.ops._
import molecule.sql.h2.setup.TestAsync_h2

object Test_OpsOne extends OpsOne with TestAsync_h2
object Test_OpsSet extends OpsSet with TestAsync_h2
object Test_OpsSeq extends OpsSeq with TestAsync_h2
object Test_OpsMap extends OpsMap with TestAsync_h2
