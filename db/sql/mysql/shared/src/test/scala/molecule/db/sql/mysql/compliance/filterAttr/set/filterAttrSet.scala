package molecule.db.sql.mysql.compliance.filterAttr.set

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.filterAttr.set.{Adjacent, CrossEntity, Types}
import molecule.db.sql.mysql.setup.Api_mysql_async

class AdjacentTest extends Test {
  Adjacent(this, Api_mysql_async)
}
class CrossEntityTest extends Test {
  CrossEntity(this, Api_mysql_async)
}
class TypesTest extends Test {
  Types(this, Api_mysql_async)
}
