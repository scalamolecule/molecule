package molecule.db.mysql.compliance.relationship

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db
import molecule.db.compliance.test.relationship.*
import molecule.db.compliance.test.relationship.flat.*
import molecule.db.compliance.test.relationship.nested.*
import molecule.db.mysql.setup.Api_mysql_async


class BidirectionalTest extends MUnit {
  Bidirectional(this, Api_mysql_async)
}
class ManyToManyTest extends MUnit {
  ManyToMany(this, Api_mysql_async)
}

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

class NestedBasicTest extends MUnit {
  NestedBasic(this, Api_mysql_async)
}
class NestedLevelsTest extends MUnit {
  NestedLevels(this, Api_mysql_async)
}
class NestedOptionalTest extends MUnit {
  NestedOptional(this, Api_mysql_async)
}
class NestedRefTest extends MUnit {
  NestedRef(this, Api_mysql_async)
}
class NestedSemanticsTest extends MUnit {
  NestedSemantics(this, Api_mysql_async)
}
class NestedTypesTest extends MUnit_arrays {
  NestedTypes(this, Api_mysql_async)
}
