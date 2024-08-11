package molecule.sql.sqlite.compliance.crud.update.filter

import molecule.coreTests.spi.crud.update.filter._
import molecule.sql.sqlite.setup.TestAsync_sqlite

object Test_FilterOne extends FilterOne with TestAsync_sqlite
object Test_FilterSet extends FilterSet with TestAsync_sqlite
object Test_FilterSeq extends FilterSeq with TestAsync_sqlite
object Test_FilterMap extends FilterMap with TestAsync_sqlite
