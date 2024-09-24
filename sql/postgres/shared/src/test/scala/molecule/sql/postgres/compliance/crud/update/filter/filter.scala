package molecule.sql.postgres.compliance.crud.update.filter

import molecule.coreTests.spi.crud.update.filter._
import molecule.sql.postgres.setup.Test_postgres_async

object Test_FilterOne extends FilterOne with Test_postgres_async
object Test_FilterSet extends FilterSet with Test_postgres_async
object Test_FilterSeq extends FilterSeq with Test_postgres_async
object Test_FilterMap extends FilterMap with Test_postgres_async
