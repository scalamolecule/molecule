package molecule.sql.sqlite.compliance.transaction.update.ops

import molecule.coreTests.spi.transaction.update.ops._
import molecule.sql.sqlite.setup.Test_sqlite_async

object Test_OpsOne extends OpsOne with Test_sqlite_async
object Test_OpsSet extends OpsSet with Test_sqlite_async
object Test_OpsSeq extends OpsSeq with Test_sqlite_async
object Test_OpsMap extends OpsMap with Test_sqlite_async