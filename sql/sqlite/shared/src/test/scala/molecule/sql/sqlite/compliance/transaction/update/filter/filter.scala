package molecule.sql.sqlite.compliance.transaction.update.filter

import molecule.coreTests.spi.transaction.update.filter._
import molecule.sql.sqlite.setup.Test_sqlite_async

object Test_FilterOne extends FilterOne with Test_sqlite_async
object Test_FilterSet extends FilterSet with Test_sqlite_async
object Test_FilterSeq extends FilterSeq with Test_sqlite_async
object Test_FilterMap extends FilterMap with Test_sqlite_async