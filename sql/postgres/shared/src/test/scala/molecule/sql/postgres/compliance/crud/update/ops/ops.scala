package molecule.sql.postgres.compliance.crud.update.ops

import molecule.coreTests.spi.crud.update.ops._
import molecule.sql.postgres.setup.Test_postgres_async

object Test_OpsOne extends OpsOne with Test_postgres_async
object Test_OpsSet extends OpsSet with Test_postgres_async
object Test_OpsSeq extends OpsSeq with Test_postgres_async
object Test_OpsMap extends OpsMap with Test_postgres_async
