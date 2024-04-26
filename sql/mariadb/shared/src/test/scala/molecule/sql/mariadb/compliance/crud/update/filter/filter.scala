package molecule.sql.mariadb.compliance.crud.update.filter

import molecule.coreTests.spi.crud.update2.filter._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object FilterOne extends FilterOne with TestAsync_mariadb
object FilterSet extends FilterSet with TestAsync_mariadb
object FilterSeq extends FilterSeq with TestAsync_mariadb
object FilterMap extends FilterMap with TestAsync_mariadb
