package molecule.sql.h2.compliance.filterAttr.one

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filterAttr.one._
import molecule.sql.h2.setup.Api_h2_async

class Adjacent extends Test {
  Adjacent(this, Api_h2_async)
}
class CrossEntity extends Test {
  CrossEntity(this, Api_h2_async)
}
class FilterAttr_id extends Test {
  FilterAttr_id(this, Api_h2_async)
}
class FilterAttrNested extends Test {
  FilterAttrNested(this, Api_h2_async)
}
class FilterAttrRef extends Test {
  FilterAttrRef(this, Api_h2_async)
}
class Semantics extends Test {
  Semantics(this, Api_h2_async)
}
class Sorting extends Test {
  Sorting(this, Api_h2_async)
}
class Types extends Test {
  Types(this, Api_h2_async)
}
