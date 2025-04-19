package molecule.sql.h2.compliance.filterAttr.one

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filterAttr.one.*
import molecule.sql.h2.setup.Api_h2_async

class AdjacentTest extends Test {
  Adjacent(this, Api_h2_async)
}
class CrossEntityTest extends Test {
  CrossEntity(this, Api_h2_async)
}
class FilterAttr_idTest extends Test {
  FilterAttr_id(this, Api_h2_async)
}
class FilterAttrNestedTest extends Test {
  FilterAttrNested(this, Api_h2_async)
}
class FilterAttrRefTest extends Test {
  FilterAttrRef(this, Api_h2_async)
}
class SemanticsTest extends Test {
  Semantics(this, Api_h2_async)
}
class SortingTest extends Test {
  Sorting(this, Api_h2_async)
}
class TypesTest extends Test {
  Types(this, Api_h2_async)
}
