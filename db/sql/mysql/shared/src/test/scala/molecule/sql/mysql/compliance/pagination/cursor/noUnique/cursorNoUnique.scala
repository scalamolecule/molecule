package molecule.sql.mysql.compliance.pagination.cursor.noUnique

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.cursor.noUnique.*
import molecule.sql.mysql.setup.Api_mysql_async

class AttrOrderMandatoryTest extends Test {
  AttrOrderMandatory(this, Api_mysql_async)
}
class AttrOrderOptionalTest extends Test {
  AttrOrderOptional(this, Api_mysql_async)
}
class DirectionsMandatoryTest extends Test {
  DirectionsMandatory(this, Api_mysql_async)
}
class DirectionsOptionalTest extends Test {
  DirectionsOptional(this, Api_mysql_async)
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
class TypesOptionalTest extends Test {
  TypesOptional(this, Api_mysql_async)
}
