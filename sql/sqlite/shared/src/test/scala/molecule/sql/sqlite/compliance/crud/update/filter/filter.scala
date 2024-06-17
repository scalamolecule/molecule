package molecule.sql.sqlite.compliance.crud.update.filter

import molecule.coreTests.spi.crud.update.filter._
import molecule.sql.sqlite.setup.TestAsync_sqlite

object FilterOne extends FilterOne with TestAsync_sqlite
object FilterSet extends FilterSet with TestAsync_sqlite
object FilterSeq extends FilterSeq with TestAsync_sqlite
object FilterMap extends FilterMap with TestAsync_sqlite
