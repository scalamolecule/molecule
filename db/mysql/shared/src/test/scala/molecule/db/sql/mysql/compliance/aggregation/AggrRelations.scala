package molecule.db.sql.mysql.compliance.aggregation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.aggregation.AggrRelations
import molecule.db.sql.mysql.setup.Api_mysql_async

class AggrRelationsTest extends MUnit {
  AggrRelations(this, Api_mysql_async)
}
