package molecule.db.sql.mariadb.compliance.relation

import molecule.db.compliance.setup.{MUnitSuiteWithArrays, Test}
import molecule.db.compliance.test.relation.nested.{NestedBasic, NestedExpr, NestedLevels, NestedOptional, NestedRef, NestedSemantics, NestedTypes}
import molecule.db.sql
import molecule.db.sql.mariadb.setup.Api_mariadb_async

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

