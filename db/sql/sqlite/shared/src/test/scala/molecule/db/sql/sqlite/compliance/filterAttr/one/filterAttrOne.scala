package molecule.db.sql.sqlite.compliance.filterAttr.one

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.filterAttr.one.*
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class AdjacentTest extends Test {
  Adjacent(this, Api_sqlite_async)
}
class CrossEntityTest extends Test {
  CrossEntity(this, Api_sqlite_async)
}
class FilterAttr_idTest extends Test {
  FilterAttr_id(this, Api_sqlite_async)
}
class FilterAttrNestedTest extends Test {
  FilterAttrNested(this, Api_sqlite_async)
}
class FilterAttrRefTest extends Test {
  FilterAttrRef(this, Api_sqlite_async)
}
class SemanticsTest extends Test {
  Semantics(this, Api_sqlite_async)
}
class SortingTest extends Test {
  Sorting(this, Api_sqlite_async)
}
class TypesTest extends Test {
  Types(this, Api_sqlite_async)
}
