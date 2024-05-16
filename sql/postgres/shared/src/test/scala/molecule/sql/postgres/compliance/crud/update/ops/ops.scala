package molecule.sql.postgres.compliance.crud.update.ops

import molecule.coreTests.spi.crud.update.ops._
import molecule.sql.postgres.setup.TestAsync_postgres

object OpsOne extends OpsOne with TestAsync_postgres
object OpsSet extends OpsSet with TestAsync_postgres
object OpsSeq extends OpsSeq with TestAsync_postgres
object OpsMap extends OpsMap with TestAsync_postgres
