package molecule.sql.mariadb.compliance.crud.update.filter

import molecule.coreTests.spi.crud.update.filter._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object Test_FilterOne extends FilterOne with TestAsync_mariadb
object Test_FilterSet extends FilterSet with TestAsync_mariadb
object Test_FilterSeq extends FilterSeq with TestAsync_mariadb
object Test_FilterMap extends FilterMap with TestAsync_mariadb
