package molecule.sql.mariadb.compliance.crud.update.ops

import molecule.coreTests.spi.crud.update.ops._
import molecule.sql.mariadb.setup.Test_mariadb_async

object Test_OpsOne extends OpsOne with Test_mariadb_async
object Test_OpsSet extends OpsSet with Test_mariadb_async
object Test_OpsSeq extends OpsSeq with Test_mariadb_async
object Test_OpsMap extends OpsMap with Test_mariadb_async
object Test_OpsByteArray extends OpsByteArray with Test_mariadb_async
