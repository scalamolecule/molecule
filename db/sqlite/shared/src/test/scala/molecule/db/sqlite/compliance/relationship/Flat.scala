package molecule.db.sqlite.compliance.relationship

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.relationship.flat.*
import molecule.db.sqlite.setup.Api_sqlite_async

class FlatEntityTest extends MUnit {
  FlatEntity(this, Api_sqlite_async)
}
class FlatRefTest extends MUnit {
  FlatRef(this, Api_sqlite_async)
}
class FlatOptEntityTest extends MUnit {
  FlatOptEntity(this, Api_sqlite_async)
}
class FlatOptRefTest extends MUnit {
  FlatOptRef(this, Api_sqlite_async)
}
class FlatOptRefNestedTest extends MUnit {
  FlatOptRefNested(this, Api_sqlite_async)
}
class FlatOptRefAdjacentTest extends MUnit {
  FlatOptRefAdjacent(this, Api_sqlite_async)
}