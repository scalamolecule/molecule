package molecule.sql.postgres.compliance.aggr

import molecule.coreTests.spi.aggr.AggrRelations
import molecule.sql.postgres.setup.Test_postgres_async

object Test_AggrRelations extends AggrRelations with Test_postgres_async
