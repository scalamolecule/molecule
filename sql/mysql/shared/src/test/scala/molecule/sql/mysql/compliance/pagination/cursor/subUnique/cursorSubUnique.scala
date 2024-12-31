package molecule.sql.mysql.compliance.pagination.cursor.subUnique

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.cursor.subUnique._
import molecule.sql.mysql.setup.Api_mysql_async

class AttrOrder extends Test {
  AttrOrder(this, Api_mysql_async)
}
class DirectionsStandardUnique extends Test {
  DirectionsStandardUnique(this, Api_mysql_async)
}
class DirectionsUniqueStandard extends Test {
  DirectionsUniqueStandard(this, Api_mysql_async)
}
class MutationAdd extends Test {
  MutationAdd(this, Api_mysql_async)
}
class MutationDelete extends Test {
  MutationDelete(this, Api_mysql_async)
}
class Nested extends Test {
  Nested(this, Api_mysql_async)
}
class OptNested extends Test {
  OptNested(this, Api_mysql_async)
}
class TypesUniqueValue extends Test {
  TypesUniqueValue(this, Api_mysql_async)
}
