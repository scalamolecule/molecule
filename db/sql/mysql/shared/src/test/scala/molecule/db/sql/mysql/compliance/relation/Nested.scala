package molecule.db.sql.mysql.compliance.relation

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db.compliance.test.relation.nested.*
import molecule.db.sql.mysql.setup.Api_mysql_async

class NestedBasicTest extends MUnit {
  NestedBasic(this, Api_mysql_async)
}
class NestedLevelsTest extends MUnit {
  NestedLevels(this, Api_mysql_async)
}
class NestedOptionalTest extends MUnit {
  NestedOptional(this, Api_mysql_async)
}
class NestedRefTest extends MUnit {
  NestedRef(this, Api_mysql_async)
}
class NestedSemanticsTest extends MUnit {
  NestedSemantics(this, Api_mysql_async)
}
class NestedTypesTest extends MUnit_arrays {
  NestedTypes(this, Api_mysql_async)
}

