package molecule.db.sql.postgres.compliance.relation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.relation.flat.*
import molecule.db.sql.postgres.setup.Api_postgres_async

class FlatEntityTest extends MUnit {
  FlatEntity(this, Api_postgres_async)
}
class FlatRefTest extends MUnit {
  FlatRef(this, Api_postgres_async)
}
class FlatOptEntityTest extends MUnit {
  FlatOptEntity(this, Api_postgres_async)
}
class FlatOptRefTest extends MUnit {
  FlatOptRef(this, Api_postgres_async)
}
class FlatOptRefNestedTest extends MUnit {
  FlatOptRefNested(this, Api_postgres_async)
}
class FlatOptRefAdjacentTest extends MUnit {
  FlatOptRefAdjacent(this, Api_postgres_async)
}
