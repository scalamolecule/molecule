package molecule.db.sql.h2.compliance.relation

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db.compliance.test.relation.nested.*
import molecule.db.sql.h2.setup.Api_h2_async

class NestedBasicTest extends MUnit {
  NestedBasic(this, Api_h2_async)
}
class NestedLevelsTest extends MUnit {
  NestedLevels(this, Api_h2_async)
}
class NestedOptionalTest extends MUnit {
  NestedOptional(this, Api_h2_async)
}
class NestedRefTest extends MUnit {
  NestedRef(this, Api_h2_async)
}
class NestedSemanticsTest extends MUnit {
  NestedSemantics(this, Api_h2_async)
}
class NestedTypesTest extends MUnit_arrays {
  NestedTypes(this, Api_h2_async)
}

