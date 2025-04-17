package molecule.sql.mysql.compliance.relation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.relation.flat._
import molecule.sql.mysql.setup.Api_mysql_async

class FlatRefTest extends Test {
  FlatRef(this, Api_mysql_async)
}
class FlatOptEntityTest extends Test {
  FlatOptEntity(this, Api_mysql_async)
}
class FlatOptRefTest extends Test {
  FlatOptRef(this, Api_mysql_async)
}
class FlatOptRefNestedTest extends Test {
  FlatOptRefNested(this, Api_mysql_async)
}
class FlatOptRefAdjacentTest extends Test {
  FlatOptRefAdjacent(this, Api_mysql_async)
}
