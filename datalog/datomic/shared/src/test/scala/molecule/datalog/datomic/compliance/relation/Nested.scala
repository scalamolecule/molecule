package molecule.datalog.datomic.compliance.relation

import molecule.coreTests.setup.{MUnitSuite, MUnitSuiteWithArrays}
import molecule.coreTests.spi.relation.nested._
import molecule.datalog.datomic.setup.Api_datomic_async

class NestedBasic extends MUnitSuite {
  NestedBasic(this, Api_datomic_async)
}
class NestedExpr extends MUnitSuite {
  NestedExpr(this, Api_datomic_async)
}
class NestedLevels extends MUnitSuite {
  NestedLevels(this, Api_datomic_async)
}
class NestedOptional extends MUnitSuite {
  NestedOptional(this, Api_datomic_async)
}
class NestedRef extends MUnitSuite {
  NestedRef(this, Api_datomic_async)
}
class NestedSemantics extends MUnitSuite {
  NestedSemantics(this, Api_datomic_async)
}
class NestedTypes extends MUnitSuiteWithArrays {
  NestedTypes(this, Api_datomic_async)
}