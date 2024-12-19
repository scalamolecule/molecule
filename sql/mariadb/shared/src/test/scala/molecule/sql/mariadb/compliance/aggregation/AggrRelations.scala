package molecule.sql.mariadb.compliance.aggregation

import molecule.coreTests.spi.aggregation.AggrRelations
import molecule.sql.mariadb.setup.Test_mariadb_async

object Test_AggrRelations extends AggrRelations with Test_mariadb_async
