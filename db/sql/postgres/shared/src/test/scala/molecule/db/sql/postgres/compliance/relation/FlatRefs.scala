package molecule.db.sql.postgres.compliance.relation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.relation.flat.{FlatOptEntity, FlatOptRef, FlatOptRefAdjacent, FlatOptRefNested, FlatRef}
import molecule.db.sql
import molecule.db.sql.postgres.setup.Api_postgres_async

class FlatRefTest extends Test {
  FlatRef(this, Api_postgres_async)
}
class FlatOptEntityTest extends Test {
  FlatOptEntity(this, Api_postgres_async)
}
class FlatOptRefTest extends Test {
  FlatOptRef(this, Api_postgres_async)
}
class FlatOptRefNestedTest extends Test {
  FlatOptRefNested(this, Api_postgres_async)
}
class FlatOptRefAdjacentTest extends Test {
  FlatOptRefAdjacent(this, Api_postgres_async)
}
