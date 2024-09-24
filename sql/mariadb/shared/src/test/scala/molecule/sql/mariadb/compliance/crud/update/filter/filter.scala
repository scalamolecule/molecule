package molecule.sql.mariadb.compliance.crud.update.filter

import molecule.coreTests.spi.crud.update.filter._
import molecule.sql.mariadb.setup.Test_mariadb_async

object Test_FilterOne extends FilterOne with Test_mariadb_async
object Test_FilterSet extends FilterSet with Test_mariadb_async
object Test_FilterSeq extends FilterSeq with Test_mariadb_async
object Test_FilterMap extends FilterMap with Test_mariadb_async
