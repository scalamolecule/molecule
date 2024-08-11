package molecule.sql.mysql.compliance.crud.update.filter

import molecule.coreTests.spi.crud.update.filter._
import molecule.sql.mysql.setup.TestAsync_mysql

object Test_FilterOne extends FilterOne with TestAsync_mysql
object Test_FilterSet extends FilterSet with TestAsync_mysql
object Test_FilterSeq extends FilterSeq with TestAsync_mysql
object Test_FilterMap extends FilterMap with TestAsync_mysql
