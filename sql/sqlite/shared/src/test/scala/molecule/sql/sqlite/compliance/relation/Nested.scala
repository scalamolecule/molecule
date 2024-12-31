package molecule.sql.sqlite.compliance.relation

import molecule.coreTests.setup.{MUnitSuite, MUnitSuiteWithArrays}
import molecule.coreTests.spi.relation.nested._
import molecule.sql.sqlite.setup.Api_sqlite_async

class NestedBasic extends MUnitSuite {
  NestedBasic(this, Api_sqlite_async)
}
class NestedExpr extends MUnitSuite {
  NestedExpr(this, Api_sqlite_async)
}
class NestedLevels extends MUnitSuite {
  NestedLevels(this, Api_sqlite_async)
}
class NestedOptional extends MUnitSuite {
  NestedOptional(this, Api_sqlite_async)
}
class NestedRef extends MUnitSuite {
  NestedRef(this, Api_sqlite_async)
}
class NestedSemantics extends MUnitSuite {
  NestedSemantics(this, Api_sqlite_async)
}
class NestedTypes extends MUnitSuiteWithArrays {
  NestedTypes(this, Api_sqlite_async)
}

