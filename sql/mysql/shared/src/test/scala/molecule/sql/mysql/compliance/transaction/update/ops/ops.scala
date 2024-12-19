package molecule.sql.mysql.compliance.transaction.update.ops

import molecule.coreTests.spi.transaction.update.ops._
import molecule.sql.mysql.setup.Test_mysql_async

object Test_OpsOne extends OpsOne with Test_mysql_async
object Test_OpsSet extends OpsSet with Test_mysql_async
object Test_OpsSeq extends OpsSeq with Test_mysql_async
object Test_OpsMap extends OpsMap with Test_mysql_async
