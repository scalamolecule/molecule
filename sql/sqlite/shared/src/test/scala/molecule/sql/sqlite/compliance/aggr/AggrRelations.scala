package molecule.sql.sqlite.compliance.aggr

import molecule.coreTests.spi.aggr.AggrRelations
import molecule.sql.sqlite.setup.TestAsync_sqlite

object AggrRelations extends AggrRelations with TestAsync_sqlite
