package molecule.db.sql.postgres.compliance.filterAttr.one

import molecule.core.setup.MUnit
import molecule.db.compliance.test.filterAttr.one.*
import molecule.db.sql.postgres.setup.Api_postgres_async

class AdjacentTest extends MUnit {
  Adjacent(this, Api_postgres_async)
}
class CrossEntityTest extends MUnit {
  CrossEntity(this, Api_postgres_async)
}
class FilterAttr_idTest extends MUnit {
  FilterAttr_id(this, Api_postgres_async)
}
class FilterAttrNestedTest extends MUnit {
  FilterAttrNested(this, Api_postgres_async)
}
class FilterAttrRefTest extends MUnit {
  FilterAttrRef(this, Api_postgres_async)
}
class SemanticsTest extends MUnit {
  Semantics(this, Api_postgres_async)
}
class SortingTest extends MUnit {
  Sorting(this, Api_postgres_async)
}
class TypesTest extends MUnit {
  Types(this, Api_postgres_async)
}
