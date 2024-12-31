package molecule.datalog.datomic.compliance.relation

import molecule.coreTests.setup.{Test, MUnitSuiteWithArrays}
import molecule.coreTests.spi.relation.nested._
import molecule.datalog.datomic.setup.Api_datomic_async

class NestedBasic extends Test {
  NestedBasic(this, Api_datomic_async)
}
class NestedExpr extends Test {
  NestedExpr(this, Api_datomic_async)
}
class NestedLevels extends Test {
  NestedLevels(this, Api_datomic_async)
}
class NestedOptional extends Test {
  NestedOptional(this, Api_datomic_async)
}
class NestedRef extends Test {
  NestedRef(this, Api_datomic_async)
}
class NestedSemantics extends Test {
  NestedSemantics(this, Api_datomic_async)
}
class NestedTypes extends MUnitSuiteWithArrays {
  NestedTypes(this, Api_datomic_async)
}