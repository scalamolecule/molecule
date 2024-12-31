package molecule.sql.mysql.compliance.pagination.cursor.noUnique

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.pagination.cursor.noUnique._
import molecule.sql.mysql.setup.Api_mysql_async

class AttrOrderMandatory extends MUnitSuite {
  AttrOrderMandatory(this, Api_mysql_async)
}
class AttrOrderOptional extends MUnitSuite {
  AttrOrderOptional(this, Api_mysql_async)
}
class DirectionsMandatory extends MUnitSuite {
  DirectionsMandatory(this, Api_mysql_async)
}
class DirectionsOptional extends MUnitSuite {
  DirectionsOptional(this, Api_mysql_async)
}
class MutationAdd extends MUnitSuite {
  MutationAdd(this, Api_mysql_async)
}
class MutationDelete extends MUnitSuite {
  MutationDelete(this, Api_mysql_async)
}
class Nested extends MUnitSuite {
  Nested(this, Api_mysql_async)
}
class OptNested extends MUnitSuite {
  OptNested(this, Api_mysql_async)
}
class TypesOptional extends MUnitSuite {
  TypesOptional(this, Api_mysql_async)
}
