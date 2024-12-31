package molecule.sql.mysql.compliance.relation

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.relation.flat._
import molecule.sql.mysql.setup.Api_mysql_async

class FlatRef extends MUnitSuite {
  FlatRef(this, Api_mysql_async)
}
class FlatRefOpt extends MUnitSuite {
  FlatRefOpt(this, Api_mysql_async)
}
class FlatRefOptNested extends MUnitSuite {
  FlatRefOptNested(this, Api_mysql_async)
}
class FlatRefOptAdjacent extends MUnitSuite {
  FlatRefOptAdjacent(this, Api_mysql_async)
}
