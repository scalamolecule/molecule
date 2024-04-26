package molecule.sql.h2.compliance.crud.update.ops

import molecule.coreTests.spi.crud.update2.ops._
import molecule.sql.h2.setup.TestAsync_h2

object OpsOne extends OpsOne with TestAsync_h2
object OpsSet extends OpsSet with TestAsync_h2
object OpsSeq extends OpsSeq with TestAsync_h2
object OpsMap extends OpsMap with TestAsync_h2
