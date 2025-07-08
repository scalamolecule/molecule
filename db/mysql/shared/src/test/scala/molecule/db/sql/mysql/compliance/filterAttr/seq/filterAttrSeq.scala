package molecule.db.sql.mysql.compliance.filterAttr.seq

import molecule.core.setup.MUnit
import molecule.db.compliance.test.filterAttr.seq.{Adjacent, CrossEntity, Types}
import molecule.db.sql.mysql.setup.Api_mysql_async

class AdjacentTest extends MUnit {
  Adjacent(this, Api_mysql_async)
}
class CrossEntityTest extends MUnit {
  CrossEntity(this, Api_mysql_async)
}
class TypesTest extends MUnit {
  Types(this, Api_mysql_async)
}
