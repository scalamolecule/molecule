package molecule.db.datalog.datomic.compliance.filterAttr.one

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.filterAttr.one.*
import molecule.db.datalog.datomic.setup.Api_datomic_async

class AdjacentTest extends Test {
  Adjacent(this, Api_datomic_async)
}
class CrossEntityTest extends Test {
  CrossEntity(this, Api_datomic_async)
}
class FilterAttr_idTest extends Test {
  FilterAttr_id(this, Api_datomic_async)
}
class FilterAttrNestedTest extends Test {
  FilterAttrNested(this, Api_datomic_async)
}
class FilterAttrRefTest extends Test {
  FilterAttrRef(this, Api_datomic_async)
}
class SemanticsTest extends Test {
  Semantics(this, Api_datomic_async)
}
class SortingTest extends Test {
  Sorting(this, Api_datomic_async)
}
class TypesTest extends Test {
  Types(this, Api_datomic_async)
}

