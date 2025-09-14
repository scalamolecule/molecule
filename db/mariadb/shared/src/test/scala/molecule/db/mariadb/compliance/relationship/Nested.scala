package molecule.db.mariadb.compliance.relationship

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db
import molecule.db.compliance.test.relationship.nested.*
import molecule.db.mariadb.setup.Api_mariadb_async

class NestedBasicTest extends MUnit {
  NestedBasic(this, Api_mariadb_async)
}
class NestedLevelsTest extends MUnit {
  NestedLevels(this, Api_mariadb_async)
}
class NestedOptionalTest extends MUnit {
  NestedOptional(this, Api_mariadb_async)
}
class NestedRefTest extends MUnit {
  NestedRef(this, Api_mariadb_async)
}
class NestedSemanticsTest extends MUnit {
  NestedSemantics(this, Api_mariadb_async)
}
class NestedTypesTest extends MUnit_arrays {
  NestedTypes(this, Api_mariadb_async)
}

