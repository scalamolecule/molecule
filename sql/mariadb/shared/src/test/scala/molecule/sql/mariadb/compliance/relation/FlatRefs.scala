package molecule.sql.mariadb.compliance.relation

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.relation.flat._
import molecule.sql.mariadb.setup.Api_mariadb_async

class FlatRef extends MUnitSuite {
  FlatRef(this, Api_mariadb_async)
}
class FlatRefOpt extends MUnitSuite {
  FlatRefOpt(this, Api_mariadb_async)
}
class FlatRefOptNested extends MUnitSuite {
  FlatRefOptNested(this, Api_mariadb_async)
}
class FlatRefOptAdjacent extends MUnitSuite {
  FlatRefOptAdjacent(this, Api_mariadb_async)
}
