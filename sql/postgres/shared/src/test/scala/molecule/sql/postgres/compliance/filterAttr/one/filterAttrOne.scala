package molecule.sql.postgres.compliance.filterAttr.one

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.filterAttr.one._
import molecule.sql.postgres.setup.Api_postgres_async

class Adjacent extends MUnitSuite {
  Adjacent(this, Api_postgres_async)
}
class CrossNs extends MUnitSuite {
  CrossNs(this, Api_postgres_async)
}
class FilterAttr_id extends MUnitSuite {
  FilterAttr_id(this, Api_postgres_async)
}
class FilterAttrNested extends MUnitSuite {
  FilterAttrNested(this, Api_postgres_async)
}
class FilterAttrRef extends MUnitSuite {
  FilterAttrRef(this, Api_postgres_async)
}
class Semantics extends MUnitSuite {
  Semantics(this, Api_postgres_async)
}
class Sorting extends MUnitSuite {
  Sorting(this, Api_postgres_async)
}
class Types extends MUnitSuite {
  Types(this, Api_postgres_async)
}
