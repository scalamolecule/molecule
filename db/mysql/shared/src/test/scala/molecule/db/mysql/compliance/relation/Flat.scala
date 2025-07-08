package molecule.db.mysql.compliance.relation

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.relation.flat.*
import molecule.db.mysql.setup.Api_mysql_async

class FlatEntityTest extends MUnit {
  FlatEntity(this, Api_mysql_async)
}
class FlatRefTest extends MUnit {
  FlatRef(this, Api_mysql_async)
}
class FlatOptEntityTest extends MUnit {
  FlatOptEntity(this, Api_mysql_async)
}
class FlatOptRefTest extends MUnit {
  FlatOptRef(this, Api_mysql_async)
}
class FlatOptRefNestedTest extends MUnit {
  FlatOptRefNested(this, Api_mysql_async)
}
class FlatOptRefAdjacentTest extends MUnit {
  FlatOptRefAdjacent(this, Api_mysql_async)
}
