package molecule.db.sql.mysql.compliance.filterAttr.one

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.filterAttr.one.*
import molecule.db.sql.mysql.setup.Api_mysql_async

class AdjacentTest extends Test {
  Adjacent(this, Api_mysql_async)
}
class CrossEntityTest extends Test {
  CrossEntity(this, Api_mysql_async)
}
class FilterAttr_idTest extends Test {
  FilterAttr_id(this, Api_mysql_async)
}
class FilterAttrNestedTest extends Test {
  FilterAttrNested(this, Api_mysql_async)
}
class FilterAttrRefTest extends Test {
  FilterAttrRef(this, Api_mysql_async)
}
class SemanticsTest extends Test {
  Semantics(this, Api_mysql_async)
}
class SortingTest extends Test {
  Sorting(this, Api_mysql_async)
}
class TypesTest extends Test {
  Types(this, Api_mysql_async)
}
