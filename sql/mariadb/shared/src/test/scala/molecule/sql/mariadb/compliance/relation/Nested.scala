package molecule.sql.mariadb.compliance.relation

import molecule.coreTests.setup.{MUnitSuiteWithArrays, Test}
import molecule.coreTests.spi.relation.nested._
import molecule.sql.mariadb.setup.Api_mariadb_async

class NestedBasicTest extends Test {
  NestedBasic(this, Api_mariadb_async)
}
class NestedExprTest extends Test {
  NestedExpr(this, Api_mariadb_async)
}
class NestedLevelsTest extends Test {
  NestedLevels(this, Api_mariadb_async)
}
class NestedOptionalTest extends Test {
  NestedOptional(this, Api_mariadb_async)
}
class NestedRefTest extends Test {
  NestedRef(this, Api_mariadb_async)
}
class NestedSemanticsTest extends Test {
  NestedSemantics(this, Api_mariadb_async)
}
class NestedTypesTest extends MUnitSuiteWithArrays {
  NestedTypes(this, Api_mariadb_async)
}

