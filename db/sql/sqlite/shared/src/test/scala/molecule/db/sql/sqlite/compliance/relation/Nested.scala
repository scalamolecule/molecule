package molecule.db.sql.sqlite.compliance.relation

import molecule.db.compliance.setup.{MUnitSuiteWithArrays, Test}
import molecule.db.compliance.test.relation.nested.*
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class NestedBasicTest extends Test {
  NestedBasic(this, Api_sqlite_async)
}
class NestedExprTest extends Test {
  NestedExpr(this, Api_sqlite_async)
}
class NestedLevelsTest extends Test {
  NestedLevels(this, Api_sqlite_async)
}
class NestedOptionalTest extends Test {
  NestedOptional(this, Api_sqlite_async)
}
class NestedRefTest extends Test {
  NestedRef(this, Api_sqlite_async)
}
class NestedSemanticsTest extends Test {
  NestedSemantics(this, Api_sqlite_async)
}
class NestedTypesTest extends MUnitSuiteWithArrays {
  NestedTypes(this, Api_sqlite_async)
}

