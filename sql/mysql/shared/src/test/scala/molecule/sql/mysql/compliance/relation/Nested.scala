package molecule.sql.mysql.compliance.relation

import molecule.coreTests.setup.{MUnitSuiteWithArrays, Test}
import molecule.coreTests.spi.relation.nested._
import molecule.sql.mysql.setup.Api_mysql_async

class NestedBasic extends Test {
  NestedBasic(this, Api_mysql_async)
}
class NestedExpr extends Test {
  NestedExpr(this, Api_mysql_async)
}
class NestedLevels extends Test {
  NestedLevels(this, Api_mysql_async)
}
class NestedOptional extends Test {
  NestedOptional(this, Api_mysql_async)
}
class NestedRef extends Test {
  NestedRef(this, Api_mysql_async)
}
class NestedSemantics extends Test {
  NestedSemantics(this, Api_mysql_async)
}
class NestedTypes extends MUnitSuiteWithArrays {
  NestedTypes(this, Api_mysql_async)
}

