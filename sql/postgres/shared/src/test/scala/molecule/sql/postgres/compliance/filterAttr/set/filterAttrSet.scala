package molecule.sql.postgres.compliance.filterAttr.set

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.filterAttr.set._
import molecule.sql.postgres.setup.Api_postgres_async

class Adjacent extends MUnitSuite {
  Adjacent(this, Api_postgres_async)
}
class CrossNs extends MUnitSuite {
  CrossNs(this, Api_postgres_async)
}
class Types extends MUnitSuite {
  Types(this, Api_postgres_async)
}
