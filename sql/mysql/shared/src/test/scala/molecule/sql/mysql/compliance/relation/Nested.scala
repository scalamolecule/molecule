package molecule.sql.mysql.compliance.relation

import molecule.coreTests.setup.{MUnitSuiteWithArrays, Test}
import molecule.coreTests.spi.relation.nested.*
import molecule.sql.mysql.setup.Api_mysql_async

class NestedBasicTest extends Test {
  NestedBasic(this, Api_mysql_async)
}
class NestedExprTest extends Test {
  NestedExpr(this, Api_mysql_async)
}
class NestedLevelsTest extends Test {
  NestedLevels(this, Api_mysql_async)
}
class NestedOptionalTest extends Test {
  NestedOptional(this, Api_mysql_async)
}
class NestedRefTest extends Test {
  NestedRef(this, Api_mysql_async)
}
class NestedSemanticsTest extends Test {
  NestedSemantics(this, Api_mysql_async)
}
class NestedTypesTest extends MUnitSuiteWithArrays {
  NestedTypes(this, Api_mysql_async)
}

