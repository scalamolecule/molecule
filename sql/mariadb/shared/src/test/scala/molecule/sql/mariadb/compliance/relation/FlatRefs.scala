package molecule.sql.mariadb.compliance.relation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.relation.flat._
import molecule.sql.mariadb.setup.Api_mariadb_async

class FlatRef extends Test {
  FlatRef(this, Api_mariadb_async)
}
class FlatRefOpt extends Test {
  FlatRefOpt(this, Api_mariadb_async)
}
class FlatRefOptNested extends Test {
  FlatRefOptNested(this, Api_mariadb_async)
}
class FlatRefOptAdjacent extends Test {
  FlatRefOptAdjacent(this, Api_mariadb_async)
}
