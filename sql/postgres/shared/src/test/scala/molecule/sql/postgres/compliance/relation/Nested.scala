package molecule.sql.postgres.compliance.relation

import molecule.coreTests.setup.{MUnitSuite, MUnitSuiteWithArrays}
import molecule.coreTests.spi.relation.nested._
import molecule.sql.postgres.setup.Api_postgres_async

class NestedBasic extends MUnitSuite {
  NestedBasic(this, Api_postgres_async)
}
class NestedExpr extends MUnitSuite {
  NestedExpr(this, Api_postgres_async)
}
class NestedLevels extends MUnitSuite {
  NestedLevels(this, Api_postgres_async)
}
class NestedOptional extends MUnitSuite {
  NestedOptional(this, Api_postgres_async)
}
class NestedRef extends MUnitSuite {
  NestedRef(this, Api_postgres_async)
}
class NestedSemantics extends MUnitSuite {
  NestedSemantics(this, Api_postgres_async)
}
class NestedTypes extends MUnitSuiteWithArrays {
  NestedTypes(this, Api_postgres_async)
}

