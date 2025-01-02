package molecule.sql.sqlite.compliance.relation

import molecule.coreTests.setup.{MUnitSuiteWithArrays, Test}
import molecule.coreTests.spi.relation.nested._
import molecule.sql.sqlite.setup.Api_sqlite_async

class NestedBasic extends Test {
  NestedBasic(this, Api_sqlite_async)
}
class NestedExpr extends Test {
  NestedExpr(this, Api_sqlite_async)
}
class NestedLevels extends Test {
  NestedLevels(this, Api_sqlite_async)
}
class NestedOptional extends Test {
  NestedOptional(this, Api_sqlite_async)
}
class NestedRef extends Test {
  NestedRef(this, Api_sqlite_async)
}
class NestedSemantics extends Test {
  NestedSemantics(this, Api_sqlite_async)
}
class NestedTypes extends MUnitSuiteWithArrays {
  NestedTypes(this, Api_sqlite_async)
}

