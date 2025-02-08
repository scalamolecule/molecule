package molecule.sql.postgres.compliance.filterAttr.seq

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filterAttr.seq._
import molecule.sql.postgres.setup.Api_postgres_async

class Adjacent extends Test {
  Adjacent(this, Api_postgres_async)
}
class CrossEntity extends Test {
  CrossEntity(this, Api_postgres_async)
}
class Types extends Test {
  Types(this, Api_postgres_async)
}
