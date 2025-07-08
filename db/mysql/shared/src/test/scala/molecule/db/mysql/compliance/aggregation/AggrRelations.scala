package molecule.db.mysql.compliance.aggregation

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.aggregation.AggrRelations
import molecule.db.mysql.setup.Api_mysql_async

class AggrRelationsTest extends MUnit {
  AggrRelations(this, Api_mysql_async)
}
