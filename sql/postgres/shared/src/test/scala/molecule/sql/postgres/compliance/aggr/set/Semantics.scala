package molecule.sql.postgres.compliance.aggr.set

import molecule.coreTests.spi.aggr.set.Semantics
import molecule.sql.postgres.setup.TestAsync_postgres

object Semantics extends Semantics with TestAsync_postgres
