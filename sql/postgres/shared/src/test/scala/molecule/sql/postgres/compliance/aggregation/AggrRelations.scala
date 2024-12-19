package molecule.sql.postgres.compliance.aggregation

import molecule.coreTests.spi.aggregation.AggrRelations
import molecule.sql.postgres.setup.Test_postgres_async

object Test_AggrRelations extends AggrRelations with Test_postgres_async
