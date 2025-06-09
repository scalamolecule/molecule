package molecule.db.sql.mariadb.compliance.filterAttr.one

import molecule.core.setup.MUnit
import molecule.db.compliance.test.filterAttr.one.*
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class AdjacentTest extends MUnit {
  Adjacent(this, Api_mariadb_async)
}
class CrossEntityTest extends MUnit {
  CrossEntity(this, Api_mariadb_async)
}
class FilterAttr_idTest extends MUnit {
  FilterAttr_id(this, Api_mariadb_async)
}
class FilterAttrNestedTest extends MUnit {
  FilterAttrNested(this, Api_mariadb_async)
}
class FilterAttrRefTest extends MUnit {
  FilterAttrRef(this, Api_mariadb_async)
}
class SemanticsTest extends MUnit {
  Semantics(this, Api_mariadb_async)
}
class SortingTest extends MUnit {
  Sorting(this, Api_mariadb_async)
}
class TypesTest extends MUnit {
  Types(this, Api_mariadb_async)
}
