package molecule.sql.postgres.compliance.relation

import molecule.coreTests.setup.{Test, MUnitSuiteWithArrays}
import molecule.coreTests.spi.relation.nested._
import molecule.sql.postgres.setup.Api_postgres_async

class NestedBasic extends Test {
  NestedBasic(this, Api_postgres_async)
}
class NestedExpr extends Test {
  NestedExpr(this, Api_postgres_async)
}
class NestedLevels extends Test {
  NestedLevels(this, Api_postgres_async)
}
class NestedOptional extends Test {
  NestedOptional(this, Api_postgres_async)
}
class NestedRef extends Test {
  NestedRef(this, Api_postgres_async)
}
class NestedSemantics extends Test {
  NestedSemantics(this, Api_postgres_async)
}
class NestedTypes extends MUnitSuiteWithArrays {
  NestedTypes(this, Api_postgres_async)
}

