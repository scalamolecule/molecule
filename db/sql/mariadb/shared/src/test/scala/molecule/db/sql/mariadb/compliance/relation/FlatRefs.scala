package molecule.db.sql.mariadb.compliance.relation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.relation.flat.*
import molecule.db.sql
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class FlatRefTest extends Test {
  FlatRef(this, Api_mariadb_async)
}
class FlatOptEntityTest extends Test {
  FlatOptEntity(this, Api_mariadb_async)
}
class FlatOptRefTest extends Test {
  FlatOptRef(this, Api_mariadb_async)
}
class FlatOptRefNestedTest extends Test {
  FlatOptRefNested(this, Api_mariadb_async)
}
class FlatOptRefAdjacentTest extends Test {
  FlatOptRefAdjacent(this, Api_mariadb_async)
}
