package molecule.db.postgresql.compliance.filterAttr.one

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.filterAttr.one.*
import molecule.db.postgresql.setup.Api_postgresql_async

class AdjacentTest extends MUnit {
  Adjacent(this, Api_postgresql_async)
}
class CrossEntityTest extends MUnit {
  CrossEntity(this, Api_postgresql_async)
}
class FilterAttr_idTest extends MUnit {
  FilterAttr_id(this, Api_postgresql_async)
}
class FilterAttrNestedTest extends MUnit {
  FilterAttrNested(this, Api_postgresql_async)
}
class FilterAttrRefTest extends MUnit {
  FilterAttrRef(this, Api_postgresql_async)
}
class SemanticsTest extends MUnit {
  Semantics(this, Api_postgresql_async)
}
class SortingTest extends MUnit {
  Sorting(this, Api_postgresql_async)
}
class TypesTest extends MUnit {
  Types(this, Api_postgresql_async)
}
