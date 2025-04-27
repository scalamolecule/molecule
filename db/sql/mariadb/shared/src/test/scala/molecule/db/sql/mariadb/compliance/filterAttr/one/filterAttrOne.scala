package molecule.db.sql.mariadb.compliance.filterAttr.one

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filterAttr.one.*
import molecule.db.sql
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class AdjacentTest extends Test {
  Adjacent(this, Api_mariadb_async)
}
class CrossEntityTest extends Test {
  CrossEntity(this, Api_mariadb_async)
}
class FilterAttr_idTest extends Test {
  FilterAttr_id(this, Api_mariadb_async)
}
class FilterAttrNestedTest extends Test {
  FilterAttrNested(this, Api_mariadb_async)
}
class FilterAttrRefTest extends Test {
  FilterAttrRef(this, Api_mariadb_async)
}
class SemanticsTest extends Test {
  Semantics(this, Api_mariadb_async)
}
class SortingTest extends Test {
  Sorting(this, Api_mariadb_async)
}
class TypesTest extends Test {
  Types(this, Api_mariadb_async)
}
