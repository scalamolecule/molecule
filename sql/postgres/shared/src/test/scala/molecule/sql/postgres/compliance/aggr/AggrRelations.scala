package molecule.sql.postgres.compliance.aggr

import molecule.coreTests.spi.aggr.AggrRelations
import molecule.sql.postgres.setup.TestAsync_postgres

object AggrRelations extends AggrRelations with TestAsync_postgres
