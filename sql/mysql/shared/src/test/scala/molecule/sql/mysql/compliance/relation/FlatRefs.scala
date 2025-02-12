package molecule.sql.mysql.compliance.relation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.relation.flat._
import molecule.sql.mysql.setup.Api_mysql_async

class FlatRef extends Test {
  FlatRef(this, Api_mysql_async)
}
class FlatOptEntity extends Test {
  FlatOptEntity(this, Api_mysql_async)
}
class FlatOptRef extends Test {
  FlatOptRef(this, Api_mysql_async)
}
class FlatOptRefNested extends Test {
  FlatOptRefNested(this, Api_mysql_async)
}
class FlatOptRefAdjacent extends Test {
  FlatOptRefAdjacent(this, Api_mysql_async)
}
