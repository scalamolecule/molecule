package molecule.sql.mysql.compliance.filterAttr.one

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filterAttr.one._
import molecule.sql.mysql.setup.Api_mysql_async

class Adjacent extends Test {
  Adjacent(this, Api_mysql_async)
}
class CrossNs extends Test {
  CrossNs(this, Api_mysql_async)
}
class FilterAttr_id extends Test {
  FilterAttr_id(this, Api_mysql_async)
}
class FilterAttrNested extends Test {
  FilterAttrNested(this, Api_mysql_async)
}
class FilterAttrRef extends Test {
  FilterAttrRef(this, Api_mysql_async)
}
class Semantics extends Test {
  Semantics(this, Api_mysql_async)
}
class Sorting extends Test {
  Sorting(this, Api_mysql_async)
}
class Types extends Test {
  Types(this, Api_mysql_async)
}
