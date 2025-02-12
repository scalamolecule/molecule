package molecule.sql.mariadb.compliance.relation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.relation.flat._
import molecule.sql.mariadb.setup.Api_mariadb_async

class FlatRef extends Test {
  FlatRef(this, Api_mariadb_async)
}
class FlatOptEntity extends Test {
  FlatOptEntity(this, Api_mariadb_async)
}
class FlatOptRef extends Test {
  FlatOptRef(this, Api_mariadb_async)
}
class FlatOptRefNested extends Test {
  FlatOptRefNested(this, Api_mariadb_async)
}
class FlatOptRefAdjacent extends Test {
  FlatOptRefAdjacent(this, Api_mariadb_async)
}
