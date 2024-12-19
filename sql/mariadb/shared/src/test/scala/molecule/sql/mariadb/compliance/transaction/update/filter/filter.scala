package molecule.sql.mariadb.compliance.transaction.update.filter

import molecule.coreTests.spi.transaction.update.filter._
import molecule.sql.mariadb.setup.Test_mariadb_async

object Test_FilterOne extends FilterOne with Test_mariadb_async
object Test_FilterSet extends FilterSet with Test_mariadb_async
object Test_FilterSeq extends FilterSeq with Test_mariadb_async
object Test_FilterMap extends FilterMap with Test_mariadb_async
