package molecule.sql.mysql.compliance.crud.update.ops

import molecule.coreTests.spi.crud.update.ops._
import molecule.sql.mysql.setup.TestAsync_mysql

object OpsOne extends OpsOne with TestAsync_mysql
object OpsSet extends OpsSet with TestAsync_mysql
object OpsSeq extends OpsSeq with TestAsync_mysql
object OpsMap extends OpsMap with TestAsync_mysql
