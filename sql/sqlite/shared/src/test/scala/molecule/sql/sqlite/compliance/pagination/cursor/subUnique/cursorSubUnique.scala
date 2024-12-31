package molecule.sql.sqlite.compliance.pagination.cursor.subUnique

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.pagination.cursor.subUnique._
import molecule.sql.sqlite.setup.Api_sqlite_async

class AttrOrder extends MUnitSuite {
  AttrOrder(this, Api_sqlite_async)
}
class DirectionsStandardUnique extends MUnitSuite {
  DirectionsStandardUnique(this, Api_sqlite_async)
}
class DirectionsUniqueStandard extends MUnitSuite {
  DirectionsUniqueStandard(this, Api_sqlite_async)
}
class MutationAdd extends MUnitSuite {
  MutationAdd(this, Api_sqlite_async)
}
class MutationDelete extends MUnitSuite {
  MutationDelete(this, Api_sqlite_async)
}
class Nested extends MUnitSuite {
  Nested(this, Api_sqlite_async)
}
class OptNested extends MUnitSuite {
  OptNested(this, Api_sqlite_async)
}
class TypesUniqueValue extends MUnitSuite {
  TypesUniqueValue(this, Api_sqlite_async)
}
