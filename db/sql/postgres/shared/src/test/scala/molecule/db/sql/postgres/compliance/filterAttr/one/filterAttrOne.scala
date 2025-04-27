package molecule.db.sql.postgres.compliance.filterAttr.one

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filterAttr.one.*
import molecule.db.sql
import molecule.db.sql.postgres.setup.Api_postgres_async

class AdjacentTest extends Test {
  Adjacent(this, Api_postgres_async)
}
class CrossEntityTest extends Test {
  CrossEntity(this, Api_postgres_async)
}
class FilterAttr_idTest extends Test {
  FilterAttr_id(this, Api_postgres_async)
}
class FilterAttrNestedTest extends Test {
  FilterAttrNested(this, Api_postgres_async)
}
class FilterAttrRefTest extends Test {
  FilterAttrRef(this, Api_postgres_async)
}
class SemanticsTest extends Test {
  Semantics(this, Api_postgres_async)
}
class SortingTest extends Test {
  Sorting(this, Api_postgres_async)
}
class TypesTest extends Test {
  Types(this, Api_postgres_async)
}
