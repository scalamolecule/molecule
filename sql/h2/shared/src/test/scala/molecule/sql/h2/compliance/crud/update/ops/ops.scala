package molecule.sql.h2.compliance.crud.update.ops

import molecule.coreTests.spi.crud.update.ops._
import molecule.sql.h2.setup.Test_h2_async

object Test_OpsOne extends OpsOne with Test_h2_async
object Test_OpsSet extends OpsSet with Test_h2_async
object Test_OpsSeq extends OpsSeq with Test_h2_async
object Test_OpsMap extends OpsMap with Test_h2_async
object Test_OpsByteArray extends OpsByteArray with Test_h2_async
