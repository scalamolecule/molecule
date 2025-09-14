package molecule.db.mariadb.compliance.relationship

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.relationship.flat.*
import molecule.db.mariadb.setup.Api_mariadb_async

class FlatEntityTest extends MUnit {
  FlatEntity(this, Api_mariadb_async)
}
class FlatRefTest extends MUnit {
  FlatRef(this, Api_mariadb_async)
}
class FlatOptEntityTest extends MUnit {
  FlatOptEntity(this, Api_mariadb_async)
}
class FlatOptRefTest extends MUnit {
  FlatOptRef(this, Api_mariadb_async)
}
class FlatOptRefNestedTest extends MUnit {
  FlatOptRefNested(this, Api_mariadb_async)
}
class FlatOptRefAdjacentTest extends MUnit {
  FlatOptRefAdjacent(this, Api_mariadb_async)
}
