package molecule.sql.mysql.compliance.aggregation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.aggregation.*
import molecule.sql.mysql.setup.Api_mysql_async

class AggrRelationsTest extends Test {
  AggrRelations(this, Api_mysql_async)
}
