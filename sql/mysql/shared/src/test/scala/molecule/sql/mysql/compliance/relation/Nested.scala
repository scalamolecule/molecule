package molecule.sql.mysql.compliance.relation

import molecule.coreTests.setup.{MUnitSuite, MUnitSuiteWithArrays}
import molecule.coreTests.spi.relation.nested._
import molecule.sql.mysql.setup.Api_mysql_async

class NestedBasic extends MUnitSuite {
  NestedBasic(this, Api_mysql_async)
}
class NestedExpr extends MUnitSuite {
  NestedExpr(this, Api_mysql_async)
}
class NestedLevels extends MUnitSuite {
  NestedLevels(this, Api_mysql_async)
}
class NestedOptional extends MUnitSuite {
  NestedOptional(this, Api_mysql_async)
}
class NestedRef extends MUnitSuite {
  NestedRef(this, Api_mysql_async)
}
class NestedSemantics extends MUnitSuite {
  NestedSemantics(this, Api_mysql_async)
}
class NestedTypes extends MUnitSuiteWithArrays {
  NestedTypes(this, Api_mysql_async)
}

