package molecule.db.h2.compliance.relation

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.relation.flat.*
import molecule.db.h2.setup.Api_h2_async

class FlatEntityTest extends MUnit {
  FlatEntity(this, Api_h2_async)
}
class FlatOptEntityTest extends MUnit {
  FlatOptEntity(this, Api_h2_async)
}
class FlatOptRefTest extends MUnit {
  FlatOptRef(this, Api_h2_async)
}
class FlatOptRefAdjacentTest extends MUnit {
  FlatOptRefAdjacent(this, Api_h2_async)
}
class FlatOptRefNestedTest extends MUnit {
  FlatOptRefNested(this, Api_h2_async)
}
class FlatRefTest extends MUnit {
  FlatRef(this, Api_h2_async)
}
