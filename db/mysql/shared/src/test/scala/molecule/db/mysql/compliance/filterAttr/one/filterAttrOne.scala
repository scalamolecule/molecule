package molecule.db.mysql.compliance.filterAttr.one

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.filterAttr.one.*
import molecule.db.mysql.setup.Api_mysql_async

class AdjacentTest extends MUnit {
  Adjacent(this, Api_mysql_async)
}
class CrossEntityTest extends MUnit {
  CrossEntity(this, Api_mysql_async)
}
class FilterAttr_idTest extends MUnit {
  FilterAttr_id(this, Api_mysql_async)
}
class FilterAttrNestedTest extends MUnit {
  FilterAttrNested(this, Api_mysql_async)
}
class FilterAttrRefTest extends MUnit {
  FilterAttrRef(this, Api_mysql_async)
}
class SemanticsTest extends MUnit {
  Semantics(this, Api_mysql_async)
}
class SortingTest extends MUnit {
  Sorting(this, Api_mysql_async)
}
class TypesTest extends MUnit {
  Types(this, Api_mysql_async)
}
