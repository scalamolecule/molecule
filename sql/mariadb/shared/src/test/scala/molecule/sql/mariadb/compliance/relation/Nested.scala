package molecule.sql.mariadb.compliance.relation

import molecule.coreTests.setup.{MUnitSuite, MUnitSuiteWithArrays}
import molecule.coreTests.spi.relation.nested._
import molecule.sql.mariadb.setup.Api_mariadb_async

class NestedBasic extends MUnitSuite {
  NestedBasic(this, Api_mariadb_async)
}
class NestedExpr extends MUnitSuite {
  NestedExpr(this, Api_mariadb_async)
}
class NestedLevels extends MUnitSuite {
  NestedLevels(this, Api_mariadb_async)
}
class NestedOptional extends MUnitSuite {
  NestedOptional(this, Api_mariadb_async)
}
class NestedRef extends MUnitSuite {
  NestedRef(this, Api_mariadb_async)
}
class NestedSemantics extends MUnitSuite {
  NestedSemantics(this, Api_mariadb_async)
}
class NestedTypes extends MUnitSuiteWithArrays {
  NestedTypes(this, Api_mariadb_async)
}

