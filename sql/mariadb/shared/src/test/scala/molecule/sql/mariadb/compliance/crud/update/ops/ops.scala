package molecule.sql.mariadb.compliance.crud.update.ops

import molecule.coreTests.spi.crud.update2.ops._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object OpsOne extends OpsOne with TestAsync_mariadb
object OpsSet extends OpsSet with TestAsync_mariadb
object OpsSeq extends OpsSeq with TestAsync_mariadb
object OpsMap extends OpsMap with TestAsync_mariadb
