package molecule.db.sql.postgres.compliance.filterAttr.seq

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.filterAttr.seq.{Adjacent, CrossEntity, Types}
import molecule.db.sql.postgres.setup.Api_postgres_async

class AdjacentTest extends Test {
  Adjacent(this, Api_postgres_async)
}
class CrossEntityTest extends Test {
  CrossEntity(this, Api_postgres_async)
}
class TypesTest extends Test {
  Types(this, Api_postgres_async)
}
