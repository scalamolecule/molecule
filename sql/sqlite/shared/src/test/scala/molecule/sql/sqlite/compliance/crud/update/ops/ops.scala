package molecule.sql.sqlite.compliance.crud.update.ops

import molecule.coreTests.spi.crud.update.ops._
import molecule.sql.sqlite.setup.TestAsync_sqlite

object Test_OpsOne extends OpsOne with TestAsync_sqlite
object Test_OpsSet extends OpsSet with TestAsync_sqlite
object Test_OpsSeq extends OpsSeq with TestAsync_sqlite
object Test_OpsMap extends OpsMap with TestAsync_sqlite
