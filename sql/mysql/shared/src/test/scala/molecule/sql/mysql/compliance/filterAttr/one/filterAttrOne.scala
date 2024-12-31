package molecule.sql.mysql.compliance.filterAttr.one

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.filterAttr.one._
import molecule.sql.mysql.setup.Api_mysql_async

class Adjacent extends MUnitSuite {
  Adjacent(this, Api_mysql_async)
}
class CrossNs extends MUnitSuite {
  CrossNs(this, Api_mysql_async)
}
class FilterAttr_id extends MUnitSuite {
  FilterAttr_id(this, Api_mysql_async)
}
class FilterAttrNested extends MUnitSuite {
  FilterAttrNested(this, Api_mysql_async)
}
class FilterAttrRef extends MUnitSuite {
  FilterAttrRef(this, Api_mysql_async)
}
class Semantics extends MUnitSuite {
  Semantics(this, Api_mysql_async)
}
class Sorting extends MUnitSuite {
  Sorting(this, Api_mysql_async)
}
class Types extends MUnitSuite {
  Types(this, Api_mysql_async)
}
