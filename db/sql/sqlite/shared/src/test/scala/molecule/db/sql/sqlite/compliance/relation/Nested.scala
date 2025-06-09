package molecule.db.sql.sqlite.compliance.relation

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db.compliance.test.relation.nested.*
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class NestedBasicTest extends MUnit {
  NestedBasic(this, Api_sqlite_async)
}
class NestedExprTest extends MUnit {
  NestedExpr(this, Api_sqlite_async)
}
class NestedLevelsTest extends MUnit {
  NestedLevels(this, Api_sqlite_async)
}
class NestedOptionalTest extends MUnit {
  NestedOptional(this, Api_sqlite_async)
}
class NestedRefTest extends MUnit {
  NestedRef(this, Api_sqlite_async)
}
class NestedSemanticsTest extends MUnit {
  NestedSemantics(this, Api_sqlite_async)
}
class NestedTypesTest extends MUnit_arrays {
  NestedTypes(this, Api_sqlite_async)
}

