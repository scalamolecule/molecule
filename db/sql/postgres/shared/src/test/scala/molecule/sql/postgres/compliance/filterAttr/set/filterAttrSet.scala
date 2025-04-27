package molecule.sql.postgres.compliance.filterAttr.set

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filterAttr.set.*
import molecule.sql.postgres.setup.Api_postgres_async

class AdjacentTest extends Test {
  Adjacent(this, Api_postgres_async)
}
class CrossEntityTest extends Test {
  CrossEntity(this, Api_postgres_async)
}
class TypesTest extends Test {
  Types(this, Api_postgres_async)
}
