package molecule.db.sql.h2.compliance.relation

import molecule.coreTests.setup.{MUnitSuiteWithArrays, Test}
import molecule.coreTests.spi.relation.nested.*
import molecule.db.sql
import molecule.db.sql.h2.setup.Api_h2_async

class NestedBasicTest extends Test {
  NestedBasic(this, Api_h2_async)
}
class NestedExprTest extends Test {
  NestedExpr(this, Api_h2_async)
}
class NestedLevelsTest extends Test {
  NestedLevels(this, Api_h2_async)
}
class NestedOptionalTest extends Test {
  NestedOptional(this, Api_h2_async)
}
class NestedRefTest extends Test {
  NestedRef(this, Api_h2_async)
}
class NestedSemanticsTest extends Test {
  NestedSemantics(this, Api_h2_async)
}
class NestedTypesTest extends MUnitSuiteWithArrays {
  NestedTypes(this, Api_h2_async)
}

