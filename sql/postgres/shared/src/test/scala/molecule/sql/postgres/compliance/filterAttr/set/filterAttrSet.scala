package molecule.sql.postgres.compliance.filterAttr.set

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filterAttr.set._
import molecule.sql.postgres.setup.Api_postgres_async

class Adjacent extends Test {
  Adjacent(this, Api_postgres_async)
}
class CrossNs extends Test {
  CrossNs(this, Api_postgres_async)
}
class Types extends Test {
  Types(this, Api_postgres_async)
}
