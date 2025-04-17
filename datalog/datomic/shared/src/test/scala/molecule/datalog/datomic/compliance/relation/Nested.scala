package molecule.datalog.datomic.compliance.relation

import molecule.coreTests.setup.{MUnitSuiteWithArrays, Test}
import molecule.coreTests.spi.relation.nested._
import molecule.datalog.datomic.setup.Api_datomic_async

class NestedBasicTest extends Test {
  NestedBasic(this, Api_datomic_async)
}
class NestedExprTest extends Test {
  NestedExpr(this, Api_datomic_async)
}
class NestedLevelsTest extends Test {
  NestedLevels(this, Api_datomic_async)
}
class NestedOptionalTest extends Test {
  NestedOptional(this, Api_datomic_async)
}
class NestedRefTest extends Test {
  NestedRef(this, Api_datomic_async)
}
class NestedSemanticsTest extends Test {
  NestedSemantics(this, Api_datomic_async)
}
class NestedTypesTest extends MUnitSuiteWithArrays {
  NestedTypes(this, Api_datomic_async)
}