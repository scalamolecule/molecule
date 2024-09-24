package molecule.sql.mysql.compliance.crud.update.filter

import molecule.coreTests.spi.crud.update.filter._
import molecule.sql.mysql.setup.Test_mysql_async

object Test_FilterOne extends FilterOne with Test_mysql_async
object Test_FilterSet extends FilterSet with Test_mysql_async
object Test_FilterSeq extends FilterSeq with Test_mysql_async
object Test_FilterMap extends FilterMap with Test_mysql_async
