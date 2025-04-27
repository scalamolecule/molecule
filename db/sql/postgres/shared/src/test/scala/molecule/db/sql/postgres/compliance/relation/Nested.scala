package molecule.db.sql.postgres.compliance.relation

import molecule.coreTests.setup.{MUnitSuiteWithArrays, Test}
import molecule.coreTests.spi.relation.nested.*
import molecule.db.sql
import molecule.db.sql.postgres.setup.Api_postgres_async

class NestedBasicTest extends Test {
  NestedBasic(this, Api_postgres_async)
}
class NestedExprTest extends Test {
  NestedExpr(this, Api_postgres_async)
}
class NestedLevelsTest extends Test {
  NestedLevels(this, Api_postgres_async)
}
class NestedOptionalTest extends Test {
  NestedOptional(this, Api_postgres_async)
}
class NestedRefTest extends Test {
  NestedRef(this, Api_postgres_async)
}
class NestedSemanticsTest extends Test {
  NestedSemantics(this, Api_postgres_async)
}
class NestedTypesTest extends MUnitSuiteWithArrays {
  NestedTypes(this, Api_postgres_async)
}

