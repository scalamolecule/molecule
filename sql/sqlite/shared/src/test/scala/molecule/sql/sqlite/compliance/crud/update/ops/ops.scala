package molecule.sql.sqlite.compliance.crud.update.ops

import molecule.coreTests.spi.crud.update.ops._
import molecule.sql.sqlite.setup.TestAsync_sqlite

object OpsOne extends OpsOne with TestAsync_sqlite
object OpsSet extends OpsSet with TestAsync_sqlite
object OpsSeq extends OpsSeq with TestAsync_sqlite
object OpsMap extends OpsMap with TestAsync_sqlite
