package molecule.sql.postgres.compliance.crud.update.ops

import molecule.coreTests.spi.crud.update.ops._
import molecule.sql.postgres.setup.TestAsync_postgres

object Test_OpsOne extends OpsOne with TestAsync_postgres
object Test_OpsSet extends OpsSet with TestAsync_postgres
object Test_OpsSeq extends OpsSeq with TestAsync_postgres
object Test_OpsMap extends OpsMap with TestAsync_postgres
