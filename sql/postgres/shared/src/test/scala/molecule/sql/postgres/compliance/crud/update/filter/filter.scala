package molecule.sql.postgres.compliance.crud.update.filter

import molecule.coreTests.spi.crud.update2.filter._
import molecule.sql.postgres.setup.TestAsync_postgres

object FilterOne extends FilterOne with TestAsync_postgres
object FilterSet extends FilterSet with TestAsync_postgres
object FilterSeq extends FilterSeq with TestAsync_postgres
object FilterMap extends FilterMap with TestAsync_postgres
