package molecule.db.sql.mysql.compliance.aggregation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.aggregation.AggrRelations
import molecule.db.sql
import molecule.db.sql.mysql.setup.Api_mysql_async

class AggrRelationsTest extends Test {
  AggrRelations(this, Api_mysql_async)
}
