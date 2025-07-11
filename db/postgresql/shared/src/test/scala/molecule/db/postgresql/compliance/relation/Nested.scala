package molecule.db.postgresql.compliance.relation

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db
import molecule.db.compliance.test.relation.nested.*
import molecule.db.postgresql.setup.Api_postgresql_async

class NestedBasicTest extends MUnit {
  NestedBasic(this, Api_postgresql_async)
}
class NestedLevelsTest extends MUnit {
  NestedLevels(this, Api_postgresql_async)
}
class NestedOptionalTest extends MUnit {
  NestedOptional(this, Api_postgresql_async)
}
class NestedRefTest extends MUnit {
  NestedRef(this, Api_postgresql_async)
}
class NestedSemanticsTest extends MUnit {
  NestedSemantics(this, Api_postgresql_async)
}
class NestedTypesTest extends MUnit_arrays {
  NestedTypes(this, Api_postgresql_async)
}

