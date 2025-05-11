package molecule.db.sql.mysql.compliance.relation

import molecule.db.compliance.setup.{MUnitSuiteWithArrays, Test}
import molecule.db.compliance.test.relation.nested.{NestedBasic, NestedExpr, NestedLevels, NestedOptional, NestedRef, NestedSemantics, NestedTypes}
import molecule.db.sql
import molecule.db.sql.mysql.setup.Api_mysql_async

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

