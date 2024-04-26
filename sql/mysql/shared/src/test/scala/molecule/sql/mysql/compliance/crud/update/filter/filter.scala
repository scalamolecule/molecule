package molecule.sql.mysql.compliance.crud.update.filter

import molecule.coreTests.spi.crud.update2.filter._
import molecule.sql.mysql.setup.TestAsync_mysql

object FilterOne extends FilterOne with TestAsync_mysql
object FilterSet extends FilterSet with TestAsync_mysql
object FilterSeq extends FilterSeq with TestAsync_mysql
object FilterMap extends FilterMap with TestAsync_mysql
