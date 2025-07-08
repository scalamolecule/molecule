package molecule.db.mysql.compliance.filterAttr.set

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.filterAttr.set.{Adjacent, CrossEntity, Types}
import molecule.db.mysql.setup.Api_mysql_async

class AdjacentTest extends MUnit {
  Adjacent(this, Api_mysql_async)
}
class CrossEntityTest extends MUnit {
  CrossEntity(this, Api_mysql_async)
}
class TypesTest extends MUnit {
  Types(this, Api_mysql_async)
}
