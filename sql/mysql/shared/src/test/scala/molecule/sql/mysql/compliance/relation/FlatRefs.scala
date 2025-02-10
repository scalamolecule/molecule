package molecule.sql.mysql.compliance.relation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.relation.flat._
import molecule.sql.mysql.setup.Api_mysql_async

class FlatRef extends Test {
  FlatRef(this, Api_mysql_async)
}
class FlatRefOpt extends Test {
  FlatOptRef(this, Api_mysql_async)
}
class FlatRefOptNested extends Test {
  FlatRefOptNested(this, Api_mysql_async)
}
class FlatRefOptAdjacent extends Test {
  FlatRefOptAdjacent(this, Api_mysql_async)
}
