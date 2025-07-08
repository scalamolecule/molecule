package molecule.db.postgres.compliance.filterAttr.seq

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.filterAttr.seq.{Adjacent, CrossEntity, Types}
import molecule.db.postgres.setup.Api_postgres_async

class AdjacentTest extends MUnit {
  Adjacent(this, Api_postgres_async)
}
class CrossEntityTest extends MUnit {
  CrossEntity(this, Api_postgres_async)
}
class TypesTest extends MUnit {
  Types(this, Api_postgres_async)
}
