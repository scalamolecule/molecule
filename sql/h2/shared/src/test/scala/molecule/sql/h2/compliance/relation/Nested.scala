package molecule.sql.h2.compliance.relation

import molecule.coreTests.setup.{MUnitSuite, MUnitSuiteWithArrays}
import molecule.coreTests.spi.relation.nested._
import molecule.sql.h2.setup.Api_h2_async

class NestedBasic extends MUnitSuite {
  NestedBasic(this, Api_h2_async)
}
class NestedExpr extends MUnitSuite {
  NestedExpr(this, Api_h2_async)
}
class NestedLevels extends MUnitSuite {
  NestedLevels(this, Api_h2_async)
}
class NestedOptional extends MUnitSuite {
  NestedOptional(this, Api_h2_async)
}
class NestedRef extends MUnitSuite {
  NestedRef(this, Api_h2_async)
}
class NestedSemantics extends MUnitSuite {
  NestedSemantics(this, Api_h2_async)
}
class NestedTypes extends MUnitSuiteWithArrays {
  NestedTypes(this, Api_h2_async)
}

