package molecule.sql.postgres.compliance.crud.update.filter

import molecule.coreTests.spi.crud.update.filter._
import molecule.sql.postgres.setup.TestAsync_postgres

object Test_FilterOne extends FilterOne with TestAsync_postgres
object Test_FilterSet extends FilterSet with TestAsync_postgres
object Test_FilterSeq extends FilterSeq with TestAsync_postgres
object Test_FilterMap extends FilterMap with TestAsync_postgres
