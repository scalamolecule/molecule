package molecule.sql.postgres.compliance.filterAttr.one

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filterAttr.one._
import molecule.sql.postgres.setup.Api_postgres_async

class Adjacent extends Test {
  Adjacent(this, Api_postgres_async)
}
class CrossEntity extends Test {
  CrossEntity(this, Api_postgres_async)
}
class FilterAttr_id extends Test {
  FilterAttr_id(this, Api_postgres_async)
}
class FilterAttrNested extends Test {
  FilterAttrNested(this, Api_postgres_async)
}
class FilterAttrRef extends Test {
  FilterAttrRef(this, Api_postgres_async)
}
class Semantics extends Test {
  Semantics(this, Api_postgres_async)
}
class Sorting extends Test {
  Sorting(this, Api_postgres_async)
}
class Types extends Test {
  Types(this, Api_postgres_async)
}
