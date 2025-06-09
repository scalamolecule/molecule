package molecule.db.sql.postgres.compliance.relation

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db.compliance.test.relation.nested.*
import molecule.db.sql.postgres.setup.Api_postgres_async

class NestedBasicTest extends MUnit {
  NestedBasic(this, Api_postgres_async)
}
class NestedExprTest extends MUnit {
  NestedExpr(this, Api_postgres_async)
}
class NestedLevelsTest extends MUnit {
  NestedLevels(this, Api_postgres_async)
}
class NestedOptionalTest extends MUnit {
  NestedOptional(this, Api_postgres_async)
}
class NestedRefTest extends MUnit {
  NestedRef(this, Api_postgres_async)
}
class NestedSemanticsTest extends MUnit {
  NestedSemantics(this, Api_postgres_async)
}
class NestedTypesTest extends MUnit_arrays {
  NestedTypes(this, Api_postgres_async)
}

