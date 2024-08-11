package molecule.sql.mysql.compliance.crud.update.ops

import molecule.coreTests.spi.crud.update.ops._
import molecule.sql.mysql.setup.TestAsync_mysql

object Test_OpsOne extends OpsOne with TestAsync_mysql
object Test_OpsSet extends OpsSet with TestAsync_mysql
object Test_OpsSeq extends OpsSeq with TestAsync_mysql
object Test_OpsMap extends OpsMap with TestAsync_mysql
