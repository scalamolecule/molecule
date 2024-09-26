package molecule.sql.mysql.compliance.crud.update.ops

import molecule.coreTests.spi.crud.update.ops._
import molecule.sql.mysql.setup.Test_mysql_async

object Test_OpsOne extends OpsOne with Test_mysql_async
object Test_OpsSet extends OpsSet with Test_mysql_async
object Test_OpsSeq extends OpsSeq with Test_mysql_async
object Test_OpsMap extends OpsMap with Test_mysql_async
object Test_OpsByteArray extends OpsByteArray with Test_mysql_async
