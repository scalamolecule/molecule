package molecule.sql.mariadb.compliance.crud.update.ops

import molecule.coreTests.spi.crud.update.ops._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object Test_OpsOne extends OpsOne with TestAsync_mariadb
object Test_OpsSet extends OpsSet with TestAsync_mariadb
object Test_OpsSeq extends OpsSeq with TestAsync_mariadb
object Test_OpsMap extends OpsMap with TestAsync_mariadb
