package molecule.db.sql.mysql.compliance.pagination.cursor.subUnique

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.pagination.subUnique.*
import molecule.db.sql.mysql.setup.Api_mysql_async

class AttrOrderTest extends Test {
  AttrOrder(this, Api_mysql_async)
}
class DirectionsStandardUniqueTest extends Test {
  DirectionsStandardUnique(this, Api_mysql_async)
}
class DirectionsUniqueStandardTest extends Test {
  DirectionsUniqueStandard(this, Api_mysql_async)
}
class MutationAddTest extends Test {
  MutationAdd(this, Api_mysql_async)
}
class MutationDeleteTest extends Test {
  MutationDelete(this, Api_mysql_async)
}
class NestedTest extends Test {
  Nested(this, Api_mysql_async)
}
class OptNestedTest extends Test {
  OptNested(this, Api_mysql_async)
}
class TypesUniqueValueTest extends Test {
  TypesUniqueValue(this, Api_mysql_async)
}
