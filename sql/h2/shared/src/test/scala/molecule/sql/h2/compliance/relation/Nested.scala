package molecule.sql.h2.compliance.relation

import molecule.coreTests.setup.{MUnitSuiteWithArrays, Test}
import molecule.coreTests.spi.relation.nested._
import molecule.sql.h2.setup.Api_h2_async

class NestedBasic extends Test {
  NestedBasic(this, Api_h2_async)
}
class NestedExpr extends Test {
  NestedExpr(this, Api_h2_async)
}
class NestedLevels extends Test {
  NestedLevels(this, Api_h2_async)
}
class NestedOptional extends Test {
  NestedOptional(this, Api_h2_async)
}
class NestedRef extends Test {
  NestedRef(this, Api_h2_async)
}
class NestedSemantics extends Test {
  NestedSemantics(this, Api_h2_async)
}
class NestedTypes extends MUnitSuiteWithArrays {
  NestedTypes(this, Api_h2_async)
}

